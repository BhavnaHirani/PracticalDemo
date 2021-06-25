package com.bizita.tatvademo.prect2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {
    @GET("users?page=1")
    Call<List<UserModel.User>> getUserData();
}
