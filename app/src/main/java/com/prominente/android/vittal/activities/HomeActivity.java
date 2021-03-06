package com.prominente.android.vittal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.constants.GcmPreferences;
import com.prominente.android.vittal.data.UserSerializer;
import com.prominente.android.vittal.fragments.QueriesFragment;
import com.prominente.android.vittal.fragments.QuotationsFragment;
import com.prominente.android.vittal.fragments.SalesFragment;
import com.prominente.android.vittal.fragments.VisitsFragment;
import com.prominente.android.vittal.model.User;
import com.prominente.android.vittal.services.RegistrationIntentService;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, VisitsFragment.VisitFragmentListener
{
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        TextView tv_nav_user_name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_nav_user_name);
        TextView tv_nav_email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_nav_email);

        // Set user name and email to NavigationView header
        User user = UserSerializer.getInstance().load(this);
        if(user != null)
        {
            tv_nav_user_name.setText(user.getUserName());
            tv_nav_email.setText(user.getEmail());
        }

        navigationView.setNavigationItemSelectedListener(this);

        //Select Navigation Drawer default option
        navigationView.getMenu().findItem(R.id.nav_sales).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_sales));

        //Start GCM Registration
        registerGcm();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;

        switch (item.getItemId())
        {
            case R.id.nav_visits:
                fragment = Fragment.instantiate(this, VisitsFragment.class.getName());
                break;

            case R.id.nav_sales:
                fragment = Fragment.instantiate(this, SalesFragment.class.getName());
                break;

            case R.id.nav_quotations:
                fragment = Fragment.instantiate(this, QuotationsFragment.class.getName());
                break;

            case R.id.nav_queries:
                fragment = Fragment.instantiate(this, QueriesFragment.class.getName());
                break;
        }

        if(fragment !=null)
            fragmentManager.beginTransaction().replace(R.id.content_home, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        setTitle(item.getTitle());
        return true;
    }

    @Override
    public void onTurnIntoSale()
    {
        //Select Navigation Drawer default option
        navigationView.getMenu().findItem(R.id.nav_sales).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_sales));
    }

    private void registerGcm()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean tokenSent = sharedPreferences.getBoolean(GcmPreferences.SENT_TOKEN_TO_SERVER, false);

        if(!tokenSent)
        {
            Intent registrationIntentService = new Intent(this, RegistrationIntentService.class);
            startService(registrationIntentService);
        }
    }
}