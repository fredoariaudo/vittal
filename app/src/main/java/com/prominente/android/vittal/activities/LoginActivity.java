package com.prominente.android.vittal.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.util.ResourceUtil;

public class LoginActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText et_login_user = (EditText) findViewById(R.id.et_login_user);
        setEditTextDrawable(R.drawable.ic_person_outline_white_24dp, ResourceUtil.getThemedColor(this, R.attr.colorControlNormal), et_login_user);

        EditText et_login_password = (EditText) findViewById(R.id.et_login_password);
        et_login_password.setTypeface(Typeface.DEFAULT);
        setEditTextDrawable(R.drawable.ic_lock_outline_white_24dp, ResourceUtil.getThemedColor(this, R.attr.colorControlNormal), et_login_password);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                login();
            }
        });
    }

    private void setEditTextDrawable(int drawable, int color, EditText editText)
    {
        Drawable compoundDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, drawable));
        DrawableCompat.setTint(compoundDrawable, color);
        editText.setCompoundDrawablesWithIntrinsicBounds(compoundDrawable, null, null, null);
    }

    private void login()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
