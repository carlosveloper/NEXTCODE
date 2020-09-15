package com.scorpion.nextcode.Repository;


import com.scorpion.nextcode.Common.Constantes;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    /*public static Retrofit getInstance(){

        if(newInstance==null)  {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .retryOnConnectionFailure(true)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            newInstance= new Retrofit.Builder()
                    .client(client)
                    .baseUrl(Constantes.UrlBase)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                  //  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return newInstance;

    }

*/
    private static RetrofitClient instance = null;
    private ApiService miniTwitterService;
    private Retrofit retrofit;

    public RetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)

                .baseUrl(Constantes.UrlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        miniTwitterService = retrofit.create(ApiService.class);
    }

    // Patrón Singleton
    public static RetrofitClient getInstance() {
        if(instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ApiService getMiniTwitterService() {
        return miniTwitterService;
    }

}
