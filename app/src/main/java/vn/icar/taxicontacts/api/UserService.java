package vn.icar.taxicontacts.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.icar.taxicontacts.LoginResponse;


public interface UserService {
    @FormUrlEncoded
    @POST("login?")
    Call<LoginResponse> userLogin(@Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @POST("register?")
    Call<LoginResponse> register(@Field("_id") String _id, @Field("password") String password, @Field("name") String name);

}
