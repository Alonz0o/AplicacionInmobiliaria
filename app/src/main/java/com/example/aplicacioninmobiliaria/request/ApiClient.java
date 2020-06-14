package com.example.aplicacioninmobiliaria.request;

import android.util.Log;

import com.example.aplicacioninmobiliaria.model.Inmueble;
import com.example.aplicacioninmobiliaria.model.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiClient {
    private static final String PATH="http://192.168.2.49:45455/Api/";
    private static  MyApiInterface myApiInteface;
    private static String accessToken=null;

    public static MyApiInterface getMyApiClient(){
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Log.d("salida",retrofit.baseUrl().toString());
        myApiInteface = retrofit.create(MyApiInterface.class);
        return myApiInteface;
    }

    public interface MyApiInterface {
        //---------------------------------------Propietario----------------------------------------//
        //Login
        @POST("Propietarios/Login")
        Call<String> login(@Query("Usuario") String usuario, @Query("Clave") String clave);

        //Buscar Propietario
        @GET("Propietarios/")
        Call<Propietario> leer(@Header("Authorization") String token);

        //Editar Propieterio
        @FormUrlEncoded
        @PUT("Propietarios/{id}")
        Call<Propietario> actualizar(@Header("Authorization") String token,
                                     @Path("id") int propietarioId,
                                     @Field("Dni") int dni,
                                     @Field("Apellido") String apellido,
                                     @Field("Nombre") String nombre,
                                     @Field("Telefono") int telefono,
                                     @Field("Email") String email,
                                     @Field("Clave") String clave);
        //No se usa todavia
        @POST("UsuarioRegistro")
        Call<Propietario> createUsuario(@Body Propietario oUsuario);

        //-----------------------------------------Inmueble-----------------------------------------//
        //Editar Inmueble
        @FormUrlEncoded
        @PUT("Inmuebles/{id}")
        Call<Inmueble> actualizarInmueble(@Header("Authorization") String token,
                                          @Path("id") int inmuebleId,
                                          @Field("Direccion") String direccion,
                                          @Field("Ambientes") int ambientes,
                                          @Field("Superficie") int superficie,
                                          @Field("Latitud") String latitud,
                                          @Field("Longitud") String longitud,
                                          @Field("PropietarioId") int propietarioId,
                                          @Field("EstaPublicado") boolean estaPublicado,
                                          @Field("EstaHabilitado") boolean estaHabilitado);


        @FormUrlEncoded
        @POST("Inmuebles/")
        Call<Inmueble> crearInmueble(@Header("Authorization") String token,
                                     @Field("id") int inmuebleId,
                                     @Field("Direccion") String direccion,
                                     @Field("Ambientes") int ambientes,
                                     @Field("Superficie") int superficie,
                                     @Field("Latitud") String latitud,
                                     @Field("Longitud") String longitud,
                                     @Field("PropietarioId") int propietarioId,
                                     @Field("EstaPublicado") boolean estaPublicado,
                                     @Field("EstaHabilitado") boolean estaHabilitado);
        //Eliminar Inmueble
        @DELETE("Inmuebles/{id}")
        Call<Inmueble> eliminarInmueble(@Header("Authorization") String token, @Path("id") int inmubleId);

        //Listar Inmueble
        @GET("Inmuebles/")
        Call<List<Inmueble>> listaInmuebles(@Header("Authorization") String token);
    }
}