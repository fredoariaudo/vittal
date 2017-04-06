package com.prominente.android.vittal.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.components.NavUpActivity;
import com.prominente.android.vittal.constants.ExtraKeys;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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
                saveSale();
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

        switch (item.getItemId())
        {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveSale()
    {
        //TODO: Reemplazar esto por datos tomados de los formularios
        Sale sale = new Sale();
        sale.setClient("Juan Pedro Lopez");
        sale.setArea("B8");
        sale.setAddress("Avellaneda 900");

        Intent data = new Intent();
        data.putExtra(ExtraKeys.SALE, sale);
        setResult(RESULT_OK, data);
        finish();
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_new_sale_form, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0 : return ApplicantFormFragment.newInstance();
                case 1 : return CoverageFormFragment.newInstance();
                case 2 : return ModalityFormFragment.newInstance();
                case 3 : return PaymentFormFragment.newInstance();
                case 4 : return DebtCollectorFormFragment.newInstance();
                case 5 : return SellerFormFragment.newInstance();
            }

            return PlaceholderFragment.newInstance(position + 1);
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
