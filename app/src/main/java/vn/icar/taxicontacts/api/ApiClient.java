package vn.icar.taxicontacts.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient= new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://contacts-api.icar.vn/")
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    public static vn.icar.taxicontacts.api.UserService getUserService(){
        vn.icar.taxicontacts.api.UserService userService= getRetrofit().create(vn.icar.taxicontacts.api.UserService.class);
        return userService;
    }

}
