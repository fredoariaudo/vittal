package com.prominente.android.vittal.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.constants.SaveStateKeys;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellerFormFragment extends Fragment {


    private String[] sellerTypes;
    private Spinner sellerTypesSpinner;
    private String[] radius;
    private Spinner radiusSpinner;
    private String[] circuits;
    private Spinner circuitsSpinner;
    private String[] promos;
    private Spinner promosSpinner;

    EditText amountIibbTextView;
    EditText capitaTextView;
    EditText monthlyFeeWhitoutIvaEditView;
    private ArrayAdapter<String> sellerTypesAdapter;
    private ArrayAdapter<String> radiusAdapter;
    private ArrayAdapter<String> promosAdapter;
    private ArrayAdapter<String> circuitsAdapter;

    public SellerFormFragment() {
        // Required empty public constructor
    }

    public static SellerFormFragment newInstance() {
        SellerFormFragment fragment = new SellerFormFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller_form, container, false);

        configureView(view);

        return view;
    }

    private void configureView(View view) {

        amountIibbTextView = (EditText) view.findViewById(R.id.fr_seller_form_et_amount_iibb);
        capitaTextView = (EditText) view.findViewById(R.id.fr_seller_form_et_capita);
        monthlyFeeWhitoutIvaEditView = (EditText) view.findViewById(R.id.fr_seller_form_et_monthly_fee_whitout_iva);

        configureSpinners(view);
    }

    private void configureSpinners(View view) {
        //
        sellerTypes = new String[] {
                "Vendedor Logueado", "Otro vendedor"
        };

        sellerTypesSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_seller);
        sellerTypesAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, sellerTypes);
        sellerTypesSpinner.setAdapter(sellerTypesAdapter);


        //
        radius = new String[] {
                "Radio 1", "Radio 2", "Radio 3"
        };

        radiusSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_radius);
        radiusAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, radius);
        radiusSpinner.setAdapter(radiusAdapter);

        //
        circuits = new String[] {
                "Circuito 1", "Circuito 2"
        };

        circuitsSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_circuit);
        circuitsAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, circuits);
        circuitsSpinner.setAdapter(circuitsAdapter);


        //
        promos = new String[] {
                "Promo 1", "Promo 2","Promo 3"
        };

        promosSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_promo);
        promosAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, promos);
        promosSpinner.setAdapter(promosAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SaveStateKeys.AMOUNT_IIBB, amountIibbTextView.getText().toString());
        outState.putString(SaveStateKeys.CAPITA, capitaTextView.getText().toString());
        outState.putString(SaveStateKeys.MONTHLY_FEE_WHITOUT_IVA, monthlyFeeWhitoutIvaEditView.getText().toString());
        outState.putInt(SaveStateKeys.SELLER, sellerTypesAdapter.getPosition(sellerTypesSpinner.getSelectedItem().toString()));
        outState.putInt(SaveStateKeys.RADIUS, radiusAdapter.getPosition(radiusSpinner.getSelectedItem().toString()));
        outState.putInt(SaveStateKeys.CIRCUIT, circuitsAdapter.getPosition(circuitsSpinner.getSelectedItem().toString()));
        outState.putInt(SaveStateKeys.PROMOS, promosAdapter.getPosition(promosSpinner.getSelectedItem().toString()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            amountIibbTextView.setText(savedInstanceState.getString(SaveStateKeys.AMOUNT_IIBB));
            capitaTextView.setText(savedInstanceState.getString(SaveStateKeys.CAPITA));
            monthlyFeeWhitoutIvaEditView.setText(savedInstanceState.getString(SaveStateKeys.MONTHLY_FEE_WHITOUT_IVA));
            sellerTypesSpinner.setSelection(savedInstanceState.getInt(SaveStateKeys.SELLER));
            radiusSpinner.setSelection(savedInstanceState.getInt(SaveStateKeys.RADIUS));
            circuitsSpinner.setSelection(savedInstanceState.getInt(SaveStateKeys.CIRCUIT));
            promosSpinner.setSelection(savedInstanceState.getInt(SaveStateKeys.PROMOS));
        }
    }

}
