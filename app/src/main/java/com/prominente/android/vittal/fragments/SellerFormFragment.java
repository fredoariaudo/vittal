package com.prominente.android.vittal.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.prominente.android.vittal.R;

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
        configureSpinners(view);
    }

    private void configureSpinners(View view) {
        //
        sellerTypes = new String[] {
                "Vendedor Logueado", "Otro vendedor"
        };

        sellerTypesSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_seller);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, sellerTypes);
        sellerTypesSpinner.setAdapter(adapter);


        //
        radius = new String[] {
                "Radio 1", "Radio 2", "Radio 3"
        };

        radiusSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_radius);
        adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, radius);
        radiusSpinner.setAdapter(adapter);

        //
        circuits = new String[] {
                "Circuito 1", "Circuito 2"
        };

        circuitsSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_circuit);
        adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, circuits);
        circuitsSpinner.setAdapter(adapter);


        //
        promos = new String[] {
                "Promo 1", "Promo 2","Promo 3"
        };

        promosSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_promo);
        adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, promos);
        promosSpinner.setAdapter(adapter);
    }


}
