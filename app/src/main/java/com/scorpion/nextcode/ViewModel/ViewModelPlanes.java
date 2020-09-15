package com.scorpion.nextcode.ViewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.scorpion.nextcode.Common.MyApp;
import com.scorpion.nextcode.Common.Validation;
import com.scorpion.nextcode.Model.Request.RequestLogin;
import com.scorpion.nextcode.Model.Response.DatosPlanes;
import com.scorpion.nextcode.Model.Response.ResponseLogin;
import com.scorpion.nextcode.Model.Response.ResponsePlanes;
import com.scorpion.nextcode.Repository.ApiService;
import com.scorpion.nextcode.Repository.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelPlanes extends ViewModel {


    RetrofitClient NextClient;
    ApiService NextService;

    private MutableLiveData<Boolean> isViewLoading;
    private MutableLiveData<List<DatosPlanes>> planesConsulta;




    public ViewModelPlanes() {
        this.isViewLoading = new MutableLiveData<>();
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
    public LiveData<List<DatosPlanes>> getplanesConsulta(){
        return  planesConsulta;
    }




    public void peticionPlanes(){
        Log.e("Petición","petición planes");
        Call<ResponsePlanes> call = NextService.getPlanes();
        call.enqueue(new Callback<ResponsePlanes>() {
            @Override
            public void onResponse(Call<ResponsePlanes> call, Response<ResponsePlanes> response) {
                if(response.isSuccessful()) {

                    if(response.body().getData().size()<=0){
                        isViewLoading.setValue(false);
                        Toast.makeText(MyApp.getContext(), "No se encontro ningun plan actualmente", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.e("los planes",Validation.convertObjToString(response.body().getData()));
                        planesConsulta.setValue(response.body().getData());
                    }
                } else {
                    isViewLoading.setValue(false);
                    Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponsePlanes> call, Throwable t) {
                isViewLoading.setValue(false);
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }






}
