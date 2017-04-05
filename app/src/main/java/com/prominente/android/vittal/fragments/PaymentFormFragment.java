package com.prominente.android.vittal.fragments;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.prominente.android.vittal.BR;
import com.prominente.android.vittal.R;
import com.prominente.android.vittal.model.PaymentForm;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFormFragment extends Fragment {

    private final PaymentForm paymentForm;
    private String[] paymentsMode;
    private ArrayAdapter<String> adapter;
    private Spinner paymentsModeSpinners;

    public PaymentFormFragment() {
        paymentForm = new PaymentForm();
    }

    public static PaymentFormFragment newInstance() {
        PaymentFormFragment fragment = new PaymentFormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_payment_form, container, false);
        binding.setVariable(BR.paymentForm,paymentForm);
        binding.executePendingBindings();

        View view = binding.getRoot();

        //
        paymentsMode = new String[] {
                "Tipo de pago",  "Efectivo", "Contado", "Cheque" ,"Tarjeta de credito/CBU"
        };

        paymentsModeSpinners= (Spinner) view.findViewById(R.id.fr_payment_form_spn_payment_mode);
        adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, paymentsMode);
        paymentsModeSpinners.setAdapter(adapter);

        return view;
    }

}
