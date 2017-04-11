package com.prominente.android.vittal.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.prominente.android.vittal.R;
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

public class NewSaleFormActivity extends NavUpActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private Sale sale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Long id = getIntent().getLongExtra(ExtraKeys.SALE,0);

        Sale tempSale = Sale.findById(Sale.class,id);

        if (tempSale == null) tempSale = new Sale(id);

        sale = tempSale;

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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

    private void save()
    {

        sale.save();

        /*Intent data = new Intent();
        data.putExtra(ExtraKeys.SALE, sale);
        data.setAction(IntentActions.ACTION_SAVE);
        setResult(RESULT_OK, data);
        finish();*/
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0 : return ApplicantFormFragment.newInstance(sale.getApplicantForm());
                case 1 : return CoverageFormFragment.newInstance(sale.getCoverageForm());
                case 2 : return ModalityFormFragment.newInstance(sale.getModalityForm());
                case 3 : return PaymentFormFragment.newInstance(sale.getPaymentForm());
                case 4 : return DebtCollectorFormFragment.newInstance(sale.getDebtCollectorForm());
                case 5 : return SellerFormFragment.newInstance(sale.getSellerForm());
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            final String[] titles = {
                                        "Solicitante",
                                        "Cobertura",
                                        "Modalidad",
                                        "Forma de Pago",
                                        "Cobrador",
                                        "Vendedor"
                                    };
            return titles[position];
        }
    }
}
