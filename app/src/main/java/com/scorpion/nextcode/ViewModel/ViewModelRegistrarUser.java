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
import com.scorpion.nextcode.Common.Validation;
import com.scorpion.nextcode.Model.Response.DatosLogin;
import com.scorpion.nextcode.Model.Response.DatosPlanUsuario;
import com.scorpion.nextcode.Model.Response.ResponsePlanesUsuario;
import com.scorpion.nextcode.Repository.ApiService;
import com.scorpion.nextcode.Repository.RetrofitClient;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelRegistrarUser extends ViewModel {

    private MutableLiveData<String> email;
    private MutableLiveData<String> password;
    private MutableLiveData<String> nombres;
    private MutableLiveData<String> apellidos;
    private MutableLiveData<String> cedula;

    private MutableLiveData<Boolean> intentHome;



    RetrofitClient NextClient;
    ApiService NextService;

    private MutableLiveData<Boolean> isViewLoading;




    public ViewModelRegistrarUser() {
        this.isViewLoading = new MutableLiveData<>();
        this.email = new MutableLiveData<>();
        this.password = new MutableLiveData<>();

        this.nombres = new MutableLiveData<>();
        this.apellidos = new MutableLiveData<>();
        this.cedula = new MutableLiveData<>();
        this.intentHome = new MutableLiveData<>();

        RetrofitInit();

    }

    public LiveData<String> getApellidos(){
        return  apellidos;
    }
    public LiveData<String> getNombres(){
        return  nombres;
    }
    public LiveData<String> getEmail(){
        return  email;
    }
    public LiveData<String> getCedula(){
        return  cedula;
    }

    public LiveData<String> getPassword(){
        return  password;
    }
    public LiveData<Boolean> getintentHome(){
        return  intentHome;
    }

    public  void  onClickRegistro(String email,String pass,String nombre,String apellido,String cedul){
        isViewLoading.setValue(true);

        boolean pasar=validaEmail(email) &&   validaPassword(pass)&& validaNombres(nombre) && validaApellidos(apellido) && validaCedula(cedul);
        Log.e("pasar",""+pasar);
        if(pasar){
            DatosLogin nuevoUsuario= new  DatosLogin();
            nuevoUsuario.setApellidos(apellido);
            nuevoUsuario.setCorreo(email);
            nuevoUsuario.setContrasenia(pass);
            nuevoUsuario.setNombres(nombre);
            nuevoUsuario.setCedula(cedul);
            nuevoUsuario.setStatus("A");
            peticionAgregarUsuario(nuevoUsuario);
        }else {
            isViewLoading.setValue(false);

            // peticionMisPlanes(nuevoUsuario);
        }
        //  resultado.setValue(Validation.validarEmail(data));
    }






    private void RetrofitInit(){
        NextClient = RetrofitClient.getInstance();
        NextService = NextClient.getMiniTwitterService();
    }


    public LiveData<Boolean> getIsViewLoading(){
        return  isViewLoading;
    }








    public void peticionAgregarUsuario(DatosLogin nuevoUsuario){
        Gson gson = new Gson();
        String JSON= gson.toJson(nuevoUsuario);
        JsonObject convertedObject = new Gson().fromJson(JSON, JsonObject.class);
        Log.e("Petición","petición planes");
        Call<DatosLogin> call = NextService.crearUsuario(convertedObject);
        call.enqueue(new Callback<DatosLogin>() {
            @Override
            public void onResponse(Call<DatosLogin> call, Response<DatosLogin> response) {
                if(response.isSuccessful()) {
                    Global.miPerfil=response.body();
                    isViewLoading.setValue(false);
                    intentHome.setValue(true);
                } else {
                    isViewLoading.setValue(false);
                    Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DatosLogin> call, Throwable t) {
                isViewLoading.setValue(false);
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private boolean validaEmail(String user){
        HashMap<String, Object> meMap= Validation.validarEmail(user);
        boolean verificar= (boolean) meMap.get("bool");
        if (verificar){
            email.setValue((String) meMap.get("mensaje"));
            return false;

        }
        return true;
    }

    private boolean validaPassword(String pass){
        HashMap<String, Object> meMap= Validation.validarPassword(pass);
        boolean verificar= (boolean) meMap.get("bool");
        if (verificar ){
            password.setValue((String) meMap.get("mensaje"));
            return false;

        }
        return true;
    }



    private boolean validaNombres(String nombre){
        boolean verificar= Validation.verificar_vacio(nombre);
        HashMap<String, Object> meMap;
        if (verificar ){
            nombres.setValue("El nombre es requerido");
            return false;

        }
        return true;
    }


    private boolean validaApellidos(String apellido){
        boolean verificar= Validation.verificar_vacio(apellido);
        HashMap<String, Object> meMap;
        if (verificar ){
            apellidos.setValue("El apellido es requerido");
            return false;

        }
        return true;
    }


    private boolean validaCedula(String cedu){
        boolean verificar= Validation.verificar_vacio(cedu);
        HashMap<String, Object> meMap;
        if (verificar){
            cedula.setValue("La cédula es requerido");
            return false;
        }
        return true;
    }




}
