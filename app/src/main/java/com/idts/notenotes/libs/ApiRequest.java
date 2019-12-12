package com.idts.notenotes.libs;

import com.idts.notenotes.models.LoginModel;
import com.idts.notenotes.models.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {
    @POST("/api/users/auth")
    Call<UserLogin> auth(@Body LoginModel login);
}
