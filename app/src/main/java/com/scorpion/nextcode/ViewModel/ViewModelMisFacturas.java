package com.scorpion.nextcode.ViewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.scorpion.nextcode.Common.Global;
import com.scorpion.nextcode.Common.MyApp;
import com.scorpion.nextcode.Model.Response.DatosFactura;
import com.scorpion.nextcode.Model.Response.DatosPlanUsuario;
import com.scorpion.nextcode.Model.Response.ResponseFactura;
import com.scorpion.nextcode.Model.Response.ResponsePlanesUsuario;
import com.scorpion.nextcode.Repository.ApiService;
import com.scorpion.nextcode.Repository.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelMisFacturas extends ViewModel {


    RetrofitClient NextClient;
    ApiService NextService;

    private MutableLiveData<Boolean> isViewLoading;
    private MutableLiveData<List<DatosFactura>> planesConsulta;


    public ViewModelMisFacturas() {
        this.isViewLoading = new MutableLiveData<>();
        this.planesConsulta = new MutableLiveData<>();
        RetrofitInit();

    }

    private void RetrofitInit() {
        NextClient = RetrofitClient.getInstance();
        NextService = NextClient.getMiniTwitterService();
    }


    public LiveData<Boolean> getIsViewLoading() {
        return isViewLoading;
    }

    public LiveData<List<DatosFactura>> getplanesConsulta() {
        return planesConsulta;
    }

    public void peticionMisFactura() {
        Log.e("Petición", "petición planes");
        Call<ResponseFactura> call = NextService.misFacturas("" + Global.miPerfil.getId());
        call.enqueue(new Callback<ResponseFactura>() {
            @Override
            public void onResponse(Call<ResponseFactura> call, Response<ResponseFactura> response) {
                if (response.isSuccessful()) {

                    if (response.body().getFacturas().size() <= 0) {
                        isViewLoading.setValue(false);
                        Toast.makeText(MyApp.getContext(), "No se encontro ninguna factura actualmente", Toast.LENGTH_SHORT).show();
                    } else {
                        planesConsulta.setValue(response.body().getFacturas());

                    }
                } else {
                    isViewLoading.setValue(false);
                    Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseFactura> call, Throwable t) {
                isViewLoading.setValue(false);
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
