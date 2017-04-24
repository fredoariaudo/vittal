package com.prominente.android.vittal.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.data.UserSerializer;
import com.prominente.android.vittal.data.VittalRestApi;
import com.prominente.android.vittal.model.User;
import com.prominente.android.vittal.util.ResourceUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity
{
    private LinearLayout ll_login_data;
    private ProgressBar pb_login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ll_login_data = (LinearLayout) findViewById(R.id.ll_login_data);

        pb_login = (ProgressBar) findViewById(R.id.pb_login);

        final EditText et_login_user = (EditText) findViewById(R.id.et_login_user);
        setEditTextDrawable(R.drawable.ic_person_outline_white_24dp, ResourceUtil.getThemedColor(this, R.attr.colorControlNormal), et_login_user);

        final EditText et_login_password = (EditText) findViewById(R.id.et_login_password);
        et_login_password.setTypeface(Typeface.DEFAULT);
        setEditTextDrawable(R.drawable.ic_lock_outline_white_24dp, ResourceUtil.getThemedColor(this, R.attr.colorControlNormal), et_login_password);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                login(et_login_user.getText().toString(), et_login_password.getText().toString());
            }
        });
    }

    private void setEditTextDrawable(int drawable, int color, EditText editText)
    {
        Drawable compoundDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, drawable));
        DrawableCompat.setTint(compoundDrawable, color);
        editText.setCompoundDrawablesWithIntrinsicBounds(compoundDrawable, null, null, null);
    }

    private void login(String userName, String password)
    {
        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password))
        {
            Toast.makeText(this, R.string.must_enter_user_password, Toast.LENGTH_SHORT).show();
            return;
        }

        startLoginAnimation(userName, password);
    }

    private void startLoginAnimation(final String userName, final String password)
    {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.login_alpha_in);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                pb_login.setVisibility(View.VISIBLE);
                sendRequest(userName, password);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });
        ll_login_data.startAnimation(anim);
    }

    private void startFailAnimation()
    {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.login_alpha_out);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation)
            {
                pb_login.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });
        ll_login_data.startAnimation(anim);
    }

    private void sendRequest(String userName, String password)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("UserName", userName);
        params.put("Password", password);

        VittalRestApi.getInstance().login(params, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response)
            {
                User user = response.body();
                UserSerializer.getInstance().save(LoginActivity.this, user);
                pb_login.setVisibility(View.INVISIBLE);
                startHomeActivity();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {
                startFailAnimation();
                Toast.makeText(LoginActivity.this, R.string.incorrect_user_password, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startHomeActivity()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
