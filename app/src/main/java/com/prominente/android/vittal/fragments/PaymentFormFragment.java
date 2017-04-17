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
import com.prominente.android.vittal.data.FormData;
import com.prominente.android.vittal.model.PaymentForm;
import com.prominente.android.vittal.model.Sale;
import com.prominente.android.vittal.views.RadioButtonsManager;

import java.util.ArrayList;

public class PaymentFormFragment extends Fragment {

    private PaymentForm paymentForm;
    private String[] paymentsMode;
    private ArrayAdapter<String> adapter;
    private Spinner paymentsModeSpinners;
    private ArrayList<RadioButton> paymentExpirationDateRadioButtons;
    private RadioButtonsManager paymentExpirationDateRadioButtonsManager;
    private String[] banks;
    private ArrayAdapter<String> banksAdapters;
    private Spinner bankSpinner;

    public PaymentFormFragment() {

        paymentExpirationDateRadioButtonsManager = new RadioButtonsManager(new RadioButtonsManager.OnRadioButtonSelectedListener() {
            @Override
            public void onRadioButtonSelected(int index) {
                paymentForm.setExpirationPayment(index);
            }
        });
    }

    public static PaymentFormFragment newInstance(Sale sale, Bundle args) {
        PaymentFormFragment fragment = new PaymentFormFragment();
        fragment.paymentForm = sale.getPaymentForm();
        fragment.setArguments(args);
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
        paymentsMode = FormData.getPaymentModes();

        paymentsModeSpinners= (Spinner) view.findViewById(R.id.fr_payment_form_spn_payment_mode);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, paymentsMode);
        paymentsModeSpinners.setAdapter(adapter);
        paymentsModeSpinners.setSelection(paymentForm.getPaymentMode());

        paymentExpirationDateRadioButtons = new ArrayList<>();
        paymentExpirationDateRadioButtons.add((RadioButton)view.findViewById(R.id.fr_payment_form_rb_overdue_month));
        paymentExpirationDateRadioButtons.add((RadioButton)view.findViewById(R.id.fr_payment_form_rb_early_month));
        paymentExpirationDateRadioButtonsManager.setButtons(paymentExpirationDateRadioButtons);

        banks = FormData.getBanks();
        bankSpinner= (Spinner) view.findViewById(R.id.partial_payment_mode_credit_card_or_cbu_spn_bank);
        banksAdapters = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, banks);
        bankSpinner.setAdapter(banksAdapters);
        bankSpinner.setSelection(paymentForm.getCreditCardOrCbuPaymentForm().getBank());


        return view;
    }
}
