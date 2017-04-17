package com.prominente.android.vittal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.prominente.android.vittal.data.UserSerializer;
import com.prominente.android.vittal.model.User;

public class SplashActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Check if necessary to show login screen
        checkLogin();
    }

    private void checkLogin()
    {
        User user = UserSerializer.getInstance().load(this);
        Intent intent;

        if(user == null)
        {
            intent = new Intent(this, LoginActivity.class);
        }
        else
        {
            intent = new Intent(this, HomeActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
