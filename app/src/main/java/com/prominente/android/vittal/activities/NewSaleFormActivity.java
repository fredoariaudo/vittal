package com.prominente.android.vittal.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

import java.util.ArrayList;

public class NewSaleFormActivity extends NavUpActivity {

    private Sale sale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sale = (Sale) getIntent().getSerializableExtra(ExtraKeys.SALE);

        SaleFormPagerAdapter mSectionsPagerAdapter = new SaleFormPagerAdapter(getSupportFragmentManager(), getFormTabs());
        ViewPager vp_sale_form = (ViewPager) findViewById(R.id.vp_sale_form);
        vp_sale_form.setAdapter(mSectionsPagerAdapter);

        TabLayout tl_sale_form = (TabLayout) findViewById(R.id.tl_sale_form);
        tl_sale_form.setupWithViewPager(vp_sale_form);

        FloatingActionButton fab_new_sale_save = (FloatingActionButton) findViewById(R.id.fab_new_sale_save);
        fab_new_sale_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                save();
            }
        });
    }

    @Override
    protected int getContentView()
    {
        return R.layout.activity_new_sale_form;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_sale_form, menu);
        return true;
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

            case R.id.action_sales_edit:
                return true;

            case R.id.action_sales_send:
                resultIntent.setAction(IntentActions.ACTION_SEND);
                setResult(RESULT_OK, resultIntent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
        //TODO: Reemplazar esto por datos tomados de los formularios
        sale.setClient(sale.getApplicantForm().getRazonSocial());
        sale.setArea("AP#100");
        sale.setAddress(sale.getApplicantForm().getAddress());

        Intent data = new Intent();
        data.putExtra(ExtraKeys.SALE, sale);
        data.setAction(IntentActions.ACTION_SAVE);
        setResult(RESULT_OK, data);
        finish();
    }
}
