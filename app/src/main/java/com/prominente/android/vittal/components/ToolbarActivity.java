package com.prominente.android.vittal.components;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.prominente.android.vittal.R;

public abstract class ToolbarActivity extends AppCompatActivity
{
    private Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID)
    {
        super.setContentView(layoutResID);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public Toolbar getToolbar()
    {
        return toolbar;
    }
}
