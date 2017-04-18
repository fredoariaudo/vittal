package com.prominente.android.vittal.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.components.NavUpActivity;

public class VisitFormActivity extends NavUpActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Hide actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected int getContentView()
    {
        return R.layout.activity_visit_form;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.visit_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }
}
