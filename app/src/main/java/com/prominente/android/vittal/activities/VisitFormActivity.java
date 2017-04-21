package com.prominente.android.vittal.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.prominente.android.vittal.BR;
import com.prominente.android.vittal.R;
import com.prominente.android.vittal.components.NavUpActivity;
import com.prominente.android.vittal.constants.ExtraKeys;
import com.prominente.android.vittal.constants.IntentActions;
import com.prominente.android.vittal.model.Visit;

public class VisitFormActivity extends NavUpActivity
{
    private Visit visit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        visit = (Visit) getIntent().getSerializableExtra(ExtraKeys.VISIT);
        if(visit == null)
            visit = new Visit();

        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_visit_form);
        viewDataBinding.setVariable(BR.visitForm, visit);

        //Hide actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab_visit_save = (FloatingActionButton) findViewById(R.id.fab_visit_save);
        fab_visit_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                save();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.visit_form, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        if(visit.getId() == null)
        {
            menu.findItem(R.id.action_visits_delete).setVisible(false);
            menu.findItem(R.id.action_visits_turn_into_sale).setVisible(false);
        }
        else
        {
            menu.findItem(R.id.action_visits_delete).setVisible(true);
            menu.findItem(R.id.action_visits_turn_into_sale).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(ExtraKeys.VISIT, visit);

        switch (item.getItemId())
        {
            case R.id.action_visits_delete:
                resultIntent.setAction(IntentActions.ACTION_DELETE);
                setResult(RESULT_OK, resultIntent);
                finish();
                return true;

            case R.id.action_visits_turn_into_sale:
                //Start SaleFormActivity Intent
                Intent intent = new Intent(this, SaleFormActivity.class);
                intent.putExtra(ExtraKeys.VISIT, visit);
                startActivity(intent);

                //Set result and action to be processed by VisitFragment onActivityResult when visit is saved as sale
                resultIntent.setAction(IntentActions.ACTION_TURN_INTO_SALE);
                setResult(RESULT_OK, resultIntent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void save()
    {
        visit.save();
        Intent data = new Intent();
        data.putExtra(ExtraKeys.VISIT, visit);
        data.setAction(IntentActions.ACTION_SAVE);
        setResult(RESULT_OK, data);
        finish();
    }
}
