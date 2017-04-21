package com.prominente.android.vittal.data;

import com.prominente.android.vittal.model.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService
{
    @POST("Account/LogIn")
    Call<User> login(@Body HashMap<String, String> user);
}
