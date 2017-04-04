package com.prominente.android.vittal.fragments;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.prominente.android.vittal.BR;
import com.prominente.android.vittal.R;
import com.prominente.android.vittal.model.SellerForm;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellerFormFragment extends Fragment {


    private final SellerForm sellerForm;
    private String[] sellerTypes;
    private Spinner sellerTypesSpinner;
    private String[] radius;
    private Spinner radiusSpinner;
    private String[] circuits;
    private Spinner circuitsSpinner;
    private String[] promos;
    private Spinner promosSpinner;


    private ArrayAdapter<String> sellerTypesAdapter;
    private ArrayAdapter<String> radiusAdapter;
    private ArrayAdapter<String> promosAdapter;
    private ArrayAdapter<String> circuitsAdapter;

    public SellerFormFragment() {
        sellerForm = new SellerForm();
    }

    public static SellerFormFragment newInstance() {
        SellerFormFragment fragment = new SellerFormFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_seller_form, container, false);
        binding.setVariable(BR.sellerForm,sellerForm);
        binding.executePendingBindings();

        View view = binding.getRoot();

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

}
