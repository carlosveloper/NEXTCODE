package com.scorpion.nextcode.ViewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorpion.nextcode.Common.Global;
import com.scorpion.nextcode.Common.MyApp;
import com.scorpion.nextcode.Model.Response.DatosPlanUsuario;
import com.scorpion.nextcode.Model.Response.ResponsePlanesUsuario;
import com.scorpion.nextcode.Repository.ApiService;
import com.scorpion.nextcode.Repository.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelDetallePlanes extends ViewModel {


    RetrofitClient NextClient;
    ApiService NextService;

    private MutableLiveData<Boolean> isViewLoading;
    private MutableLiveData<List<DatosPlanUsuario>> planesConsulta;
    private MutableLiveData<Boolean> intentHome;


    public ViewModelDetallePlanes() {
        this.isViewLoading = new MutableLiveData<>();
        this.planesConsulta=new MutableLiveData<>();
        this.intentHome = new MutableLiveData<>();

        RetrofitInit();

    }

    private void RetrofitInit(){
        NextClient = RetrofitClient.getInstance();
        NextService = NextClient.getMiniTwitterService();
    }


    public LiveData<Boolean> getIsViewLoading(){
        return  isViewLoading;
    }
    public LiveData<List<DatosPlanUsuario>> getplanesConsulta(){
        return  planesConsulta;
    }
    public LiveData<Boolean> getintentHome(){
        return  intentHome;
    }


    public void peticionMisPlanes(DatosPlanUsuario SolicitarPlan){
        isViewLoading.setValue(true);



        Gson gson = new Gson();
        String JSON= gson.toJson(SolicitarPlan);
        JsonObject convertedObject = new Gson().fromJson(JSON, JsonObject.class);

        Log.e("Petición","petición planes");

        Call<DatosPlanUsuario> call = NextService.agregarPlan(convertedObject);
        call.enqueue(new Callback<DatosPlanUsuario>() {
            @Override
            public void onResponse(Call<DatosPlanUsuario> call, Response<DatosPlanUsuario> response) {
                if(response.isSuccessful()) {
                    isViewLoading.setValue(false);
                    intentHome.setValue(true);
                } else {
                    isViewLoading.setValue(false);
                    Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DatosPlanUsuario> call, Throwable t) {
                isViewLoading.setValue(false);
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }






}
