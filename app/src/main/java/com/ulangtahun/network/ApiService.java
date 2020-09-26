package com.ulangtahun.network;

import com.ulangtahun.model.LoginRecord;
import com.ulangtahun.model.LoginResponse;
import com.ulangtahun.model.ReadPersonResponse;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginRequest(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("read-person.php")
    Call<ReadPersonResponse> listPerson(
            @Query("tgl_lahir") String tgl_lahir,
            @Query("status") String status
    );

    @FormUrlEncoded
    @POST("update_status.php")
    Call<ReadPersonResponse> updateStatus(
            @Field("id") String id,
            @Field("statusr") String status,
            @Field("token") String token
    );
}
