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
import com.prominente.android.vittal.data.FormData;
import com.prominente.android.vittal.model.Sale;
import com.prominente.android.vittal.model.SellerForm;

public class SellerFormFragment extends Fragment {

    private SellerForm sellerForm;
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

    public static SellerFormFragment newInstance(Sale sale, Bundle args) {
        SellerFormFragment fragment = new SellerFormFragment();
        fragment.sellerForm = sale.getSellerForm();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        sellerTypes = FormData.getSellerTypes();

        sellerTypesSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_seller);
        sellerTypesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, sellerTypes);
        sellerTypesSpinner.setAdapter(sellerTypesAdapter);
        sellerTypesSpinner.setSelection(sellerForm.getSeller());

        //
        radius = FormData.getRadius();

        radiusSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_radius);
        radiusAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, radius);
        radiusSpinner.setAdapter(radiusAdapter);
        radiusSpinner.setSelection(sellerForm.getRadius());

        //
        circuits = FormData.getCircuits();

        circuitsSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_circuit);
        circuitsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, circuits);
        circuitsSpinner.setAdapter(circuitsAdapter);
        circuitsSpinner.setSelection(sellerForm.getCircuit());

        //
        promos = FormData.getPromos();

        promosSpinner  = (Spinner) view.findViewById(R.id.fr_seller_form_spn_promo);
        promosAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, promos);
        promosSpinner.setAdapter(promosAdapter);
        promosSpinner.setSelection(sellerForm.getPromo());
    }
}
