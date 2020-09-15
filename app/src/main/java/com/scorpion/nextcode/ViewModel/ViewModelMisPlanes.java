package com.scorpion.nextcode.ViewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.constraintlayout.solver.GoalRow;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorpion.nextcode.Common.Global;
import com.scorpion.nextcode.Common.MyApp;
import com.scorpion.nextcode.Model.Response.DatosFactura;
import com.scorpion.nextcode.Model.Response.DatosPlanUsuario;
import com.scorpion.nextcode.Model.Response.DatosPlanes;
import com.scorpion.nextcode.Model.Response.ResponsePlanes;
import com.scorpion.nextcode.Model.Response.ResponsePlanesUsuario;
import com.scorpion.nextcode.Repository.ApiService;
import com.scorpion.nextcode.Repository.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelMisPlanes extends ViewModel {


    RetrofitClient NextClient;
    ApiService NextService;

    private MutableLiveData<Boolean> isViewLoading;
    private MutableLiveData<Boolean> isViewLoadingFactura;
    private MutableLiveData<Boolean> intentFactura;

    private MutableLiveData<List<DatosPlanUsuario>> planesConsulta;




    public ViewModelMisPlanes() {
        this.isViewLoading = new MutableLiveData<>();
        this.isViewLoadingFactura = new MutableLiveData<>();

        this.intentFactura = new MutableLiveData<>();


        this.planesConsulta=new MutableLiveData<>();
        RetrofitInit();

    }

    private void RetrofitInit(){
        NextClient = RetrofitClient.getInstance();
        NextService = NextClient.getMiniTwitterService();
    }


    public LiveData<Boolean> getIsViewLoading(){
        return  isViewLoading;
    }

    public LiveData<Boolean> getIntentFactura(){
        return  intentFactura;
    }


    public LiveData<Boolean> getIsViewLoadingFactura(){
        return  isViewLoadingFactura;
    }
    public LiveData<List<DatosPlanUsuario>> getplanesConsulta(){
        return  planesConsulta;
    }


    public void AgregarFactura(){
        isViewLoadingFactura.setValue(true);
        DatosFactura factura=new DatosFactura();
        Double subtotal=0.0;
        Double iva=0.12;
        Double valorIva;

        for(DatosPlanes plan:Global.Misplanes){
            subtotal=subtotal+ Double.parseDouble(plan.getSubtotal());

        }
        valorIva=(subtotal*iva);
        factura.setSubtotal(""+subtotal);
        factura.setIva(""+valorIva);
        factura.setTotal(""+(subtotal+valorIva));
        factura.setUsuarioId(Global.miPerfil.getId());
        factura.setSerie("000-005");
        Date myDate = new Date();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        factura.setFechaEmision(sf.format(myDate));
        Log.e("fecha",sf.format(myDate));
        peticionFactura(factura);


    }


    void peticionFactura(DatosFactura factura){

        Gson gson = new Gson();
        String JSON= gson.toJson(factura);
        JsonObject convertedObject = new Gson().fromJson(JSON, JsonObject.class);

        Log.e("Petición","petición planes");

        Call<DatosFactura> call = NextService.crearFactura(convertedObject);
        call.enqueue(new Callback<DatosFactura>() {
            @Override
            public void onResponse(Call<DatosFactura> call, Response<DatosFactura> response) {
                if(response.isSuccessful()) {
                  isViewLoadingFactura.setValue(false);
                   intentFactura.setValue(true);
                } else {
                 isViewLoadingFactura.setValue(false);
                    Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DatosFactura> call, Throwable t) {
                isViewLoadingFactura.setValue(false);
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void peticionMisPlanes(){
        Log.e("Petición","petición planes");
        Call<ResponsePlanesUsuario> call = NextService.misPlanes(""+Global.miPerfil.getId());
        call.enqueue(new Callback<ResponsePlanesUsuario>() {
            @Override
            public void onResponse(Call<ResponsePlanesUsuario> call, Response<ResponsePlanesUsuario> response) {
                if(response.isSuccessful()) {


                    if(response.body().getPlanes().size()<=0){
                        isViewLoading.setValue(false);
                        Toast.makeText(MyApp.getContext(), "No se encontro ningun plan actualmente", Toast.LENGTH_SHORT).show();
                    }else{
                        planesConsulta.setValue(response.body().getPlanes());

                    }
                } else {
                    isViewLoading.setValue(false);
                    Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponsePlanesUsuario> call, Throwable t) {
                isViewLoading.setValue(false);
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }






}
