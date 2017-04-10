package com.prominente.android.vittal.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.prominente.android.vittal.BR;
import com.prominente.android.vittal.R;
import com.prominente.android.vittal.model.PaymentForm;
import com.prominente.android.vittal.views.RadioButtonsManager;

import java.util.ArrayList;

public class PaymentFormFragment extends Fragment {

    private PaymentForm paymentForm;
    private String[] paymentsMode;
    private ArrayAdapter<String> adapter;
    private Spinner paymentsModeSpinners;
    private ArrayList<RadioButton> paymentExpirationDateRadioButtons;
    private RadioButtonsManager paymentExpirationDateRadioButtonsManager;

    public PaymentFormFragment() {

        paymentExpirationDateRadioButtonsManager = new RadioButtonsManager(new RadioButtonsManager.OnRadioButtonSelectedListener() {
            @Override
            public void onRadioButtonSelected(int index) {
                paymentForm.setExpirationPayment(index);
            }
        });
    }

    public static PaymentFormFragment newInstance(PaymentForm paymentForm) {
        PaymentFormFragment fragment = new PaymentFormFragment();
        fragment.paymentForm = paymentForm;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, paymentsMode);
        paymentsModeSpinners.setAdapter(adapter);

        paymentExpirationDateRadioButtons = new ArrayList<>();
        paymentExpirationDateRadioButtons.add((RadioButton)view.findViewById(R.id.fr_payment_form_rb_overdue_month));
        paymentExpirationDateRadioButtons.add((RadioButton)view.findViewById(R.id.fr_payment_form_rb_early_month));
        paymentExpirationDateRadioButtonsManager.setButtons(paymentExpirationDateRadioButtons);

        return view;
    }
}
