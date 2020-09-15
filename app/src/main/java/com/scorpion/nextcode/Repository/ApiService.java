package com.scorpion.nextcode.Repository;


import com.google.gson.JsonObject;
import com.scorpion.nextcode.Model.Request.RequestLogin;
import com.scorpion.nextcode.Model.Response.DatosFactura;
import com.scorpion.nextcode.Model.Response.DatosLogin;
import com.scorpion.nextcode.Model.Response.DatosPlanUsuario;
import com.scorpion.nextcode.Model.Response.ResponseFactura;
import com.scorpion.nextcode.Model.Response.ResponseLogin;
import com.scorpion.nextcode.Model.Response.ResponsePlanes;
import com.scorpion.nextcode.Model.Response.ResponsePlanesUsuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    /* @Headers("Content-Type: application/json")
     @POST("usuarios")
     Observable<Response<ResponseRegistroUser>>RegistroUser(@Body JsonObject object);


     @Headers("Content-Type: application/json")
     @GET("usuarios")
     Observable<Response<List<ResponseUserPorID>>>ObtenerTransportistas(@Query("tipo") String ValueChild , @Header("Authorization") String authorization);





 */
    @GET("usuarios")
    Call<ResponseLogin> doLogin(@Query("correo") String correo,
                                @Query("contrasenia") String idTienda);


    @POST("usuarios")
    Call<DatosLogin> crearUsuario(@Body JsonObject object);

    @POST("facturas")
    Call<DatosFactura> crearFactura(@Body JsonObject object);




    //Todo planes de usuario
    @GET("usuarios/{id}?embed=planes?embed=plan")
    Call<ResponsePlanesUsuario> misPlanes(@Path(value = "id", encoded = true) String idUsuario);

    @GET("planes")
    Call<ResponsePlanes> getPlanes();

    @POST("planesusuario")
    Call<DatosPlanUsuario> agregarPlan(@Body JsonObject object);



    ///Todo facturas de usuario

    //Todo planes de usuario
    @GET("usuarios/{id}?embed=facturas")
    Call<ResponseFactura> misFacturas(@Path(value = "id", encoded = true) String idUsuario);


}
