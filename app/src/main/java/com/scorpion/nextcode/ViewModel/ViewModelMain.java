package com.scorpion.nextcode.ViewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.scorpion.nextcode.Common.Global;
import com.scorpion.nextcode.Common.MyApp;
import com.scorpion.nextcode.Model.Response.DatosPlanUsuario;
import com.scorpion.nextcode.Model.Response.DatosPlanes;
import com.scorpion.nextcode.Model.Response.ResponsePlanes;
import com.scorpion.nextcode.Model.Response.ResponsePlanesUsuario;
import com.scorpion.nextcode.Repository.ApiService;
import com.scorpion.nextcode.Repository.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelMain extends ViewModel {


    RetrofitClient NextClient;
    ApiService NextService;

    private MutableLiveData<List<DatosPlanes>> planesConsulta;




    public ViewModelMain() {
        this.planesConsulta=new MutableLiveData<>();
        RetrofitInit();

    }

    private void RetrofitInit(){
        NextClient = RetrofitClient.getInstance();
        NextService = NextClient.getMiniTwitterService();
    }


    public void peticionPlanes(){
        Log.e("Petición","petición planes");
        Call<ResponsePlanes> call = NextService.getPlanes();
        call.enqueue(new Callback<ResponsePlanes>() {
            @Override
            public void onResponse(Call<ResponsePlanes> call, Response<ResponsePlanes> response) {
                if(response.isSuccessful()) {


                    if(response.body().getData().size()<=0){
                       // Toast.makeText(MyApp.getContext(), "No se encontro ningun plan actualmente", Toast.LENGTH_SHORT).show();
                    }else{
                        //planesConsulta.setValue(response.body().getData());

                        Global.planes=response.body().getData();

                    }
                } else {
                   // Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponsePlanes> call, Throwable t) {
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
                    }else{
                        Global.Misplanes.clear();
                        for (DatosPlanUsuario datosPlan:response.body().getPlanes()){
                            for (DatosPlanes planes: Global.planes){

                                if(datosPlan.getPlanId()==planes.getId()){
                                    Global.Misplanes.add(planes);
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponsePlanesUsuario> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
