package com.prominente.android.vittal.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.adapters.SaleFormPagerAdapter;
import com.prominente.android.vittal.components.NavUpActivity;
import com.prominente.android.vittal.constants.ExtraKeys;
import com.prominente.android.vittal.constants.IntentActions;
import com.prominente.android.vittal.fragments.ApplicantFormFragment;
import com.prominente.android.vittal.fragments.CoverageFormFragment;
import com.prominente.android.vittal.fragments.DebtCollectorFormFragment;
import com.prominente.android.vittal.fragments.ModalityFormFragment;
import com.prominente.android.vittal.fragments.PaymentFormFragment;
import com.prominente.android.vittal.fragments.SellerFormFragment;
import com.prominente.android.vittal.model.Sale;
import com.prominente.android.vittal.model.Visit;
import com.prominente.android.vittal.util.ParsingUtils;

import java.util.ArrayList;

public class SaleFormActivity extends NavUpActivity {

    private Sale sale;
    private boolean isNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_form);

        //Hide actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sale = createSale();

        SaleFormPagerAdapter mSectionsPagerAdapter = new SaleFormPagerAdapter(getSupportFragmentManager(), getFormTabs());
        ViewPager vp_sale_form = (ViewPager) findViewById(R.id.vp_sale_form);
        vp_sale_form.setAdapter(mSectionsPagerAdapter);

        TabLayout tl_sale_form = (TabLayout) findViewById(R.id.tl_sale_form);
        tl_sale_form.setupWithViewPager(vp_sale_form);

        FloatingActionButton fab_sale_save = (FloatingActionButton) findViewById(R.id.fab_sale_save);
        fab_sale_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                save();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sale_form, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(isNew)
        {
            menu.findItem(R.id.action_sales_delete).setVisible(false);
        }
        else
        {
            menu.findItem(R.id.action_sales_delete).setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent resultIntent = new Intent();
        resultIntent.putExtra(ExtraKeys.SALE, sale);

        switch (item.getItemId())
        {
            case R.id.action_sales_delete:
                resultIntent.setAction(IntentActions.ACTION_DELETE);
                setResult(RESULT_OK, resultIntent);
                finish();
                return true;

            case R.id.action_sales_send:
                if (sale.isValid()) {
                    resultIntent.setAction(IntentActions.ACTION_SEND);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(this,R.string.incomplete_form,Toast.LENGTH_LONG).show();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.vittal)
                .setMessage(R.string.are_you_sure_close_whitout_save)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (isNew) {
                            sale.delete();
                        }

                        SaleFormActivity.super.onBackPressed();
                    }

                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    private Sale createSale()
    {
        Sale sale;
        Visit visit;

        visit = (Visit) getIntent().getSerializableExtra(ExtraKeys.VISIT);
        if(visit == null)
        {
            sale = (Sale) getIntent().getSerializableExtra(ExtraKeys.SALE);
            // Si no hay Sale, entonces es una venta nueva
            if(sale == null) {
                isNew = true;
                sale = new Sale();
                sale.save();
            }
        }
        else
        {
            sale = ParsingUtils.turnIntoSale(visit);
            visit.delete();
            sale.save();
        }

        return sale;
    }

    private ArrayList<Fragment> getFormTabs()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();

        Bundle afBundle = new Bundle();
        afBundle.putString(ExtraKeys.FORM_TAB_TITLE, getString(R.string.tab_title_applicant));
        fragments.add(ApplicantFormFragment.newInstance(sale, afBundle));

        Bundle cfBundle = new Bundle();
        cfBundle.putString(ExtraKeys.FORM_TAB_TITLE, getString(R.string.tab_title_coverage));
        fragments.add(CoverageFormFragment.newInstance(sale, cfBundle));

        Bundle mfBundle = new Bundle();
        mfBundle.putString(ExtraKeys.FORM_TAB_TITLE, getString(R.string.tab_title_modality));
        fragments.add(ModalityFormFragment.newInstance(sale, mfBundle));

        Bundle pfBundle = new Bundle();
        pfBundle.putString(ExtraKeys.FORM_TAB_TITLE, getString(R.string.tab_title_payment));
        fragments.add(PaymentFormFragment.newInstance(sale, pfBundle));

        Bundle dcfBundle = new Bundle();
        dcfBundle.putString(ExtraKeys.FORM_TAB_TITLE, getString(R.string.tab_title_debt_collector));
        fragments.add(DebtCollectorFormFragment.newInstance(sale, dcfBundle));

        Bundle sfBundle = new Bundle();
        sfBundle.putString(ExtraKeys.FORM_TAB_TITLE, getString(R.string.tab_title_seller));
        fragments.add(SellerFormFragment.newInstance(sale, sfBundle));

        return fragments;
    }

    private void save()
    {
        sale.save();
        Intent data = new Intent();
        data.putExtra(ExtraKeys.SALE, sale);
        data.setAction(IntentActions.ACTION_SAVE);
        setResult(RESULT_OK, data);
        finish();
    }
}
