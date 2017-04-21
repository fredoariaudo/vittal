package com.prominente.android.vittal.data;

import com.prominente.android.vittal.model.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VittalRestApi
{
    private final static String BASE_URL = "http://172.16.129.80/vittalapi/api/";

    private static VittalRestApi vittalRestApi;

    private VittalRestApi()
    {
        //Prevent form the reflection api.
        if (vittalRestApi != null)
            throw new RuntimeException("Use getInstance() method to get the single instance of this class");
    }

    public static synchronized VittalRestApi getInstance()
    {
        if(vittalRestApi == null)
            vittalRestApi = new VittalRestApi();

        return vittalRestApi;
    }

    public void login(HashMap<String, String> params, Callback<User> callback)
    {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        LoginService loginService = retrofit.create(LoginService.class);
        Call<User> loginCall = loginService.login(params);
        loginCall.enqueue(callback);
    }
}
