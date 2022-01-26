package vn.icar.taxicontacts.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vn.icar.taxicontacts.RegisterResponse;
import vn.icar.taxicontacts.LoginResponse;
import vn.icar.taxicontacts.models.PushContacts;


public interface UserService {
    @FormUrlEncoded
    @POST("login?")
    Call<LoginResponse> userLogin(@Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @POST("register?")
    Call<RegisterResponse> register(@Field("_id") String _id, @Field("password") String password, @Field("name") String name);
    @GET("updateContac?")
    Call<PushContacts> UpdateContacts(@Query("statusCode") String status, @Header("Authorization") String token);

}
