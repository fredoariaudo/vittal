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
import com.prominente.android.vittal.model.DebtCollectorForm;
import com.prominente.android.vittal.model.Sale;
import com.prominente.android.vittal.views.RadioButtonsManager;

import java.util.ArrayList;
import java.util.List;

public class DebtCollectorFormFragment extends Fragment {

    private DebtCollectorForm debtCollectorForm;
    private final RadioButtonsManager conditionVsIvaRadioButtonsManager;
    private String[] locations;
    private Spinner locationsSpinners;
    private ArrayAdapter<String> locationsAdapter;
    private List<RadioButton> conditionVsIvaRadioButton;
    private List<RadioButton> deliveredDocumentsRadioButton;
    private RadioButtonsManager deliveredDocumentsRadioButtonManager;

    public DebtCollectorFormFragment() {

        conditionVsIvaRadioButtonsManager = new RadioButtonsManager(new RadioButtonsManager.OnRadioButtonSelectedListener() {
            @Override
            public void onRadioButtonSelected(int index) {
                debtCollectorForm.setConditionVsIva(index);
            }
        });

        deliveredDocumentsRadioButtonManager = new RadioButtonsManager(new RadioButtonsManager.OnRadioButtonSelectedListener() {
            @Override
            public void onRadioButtonSelected(int index) {
                debtCollectorForm.setDeliverDocuments(index);
            }
        });
    }

    public static DebtCollectorFormFragment newInstance(Sale sale, Bundle args) {
        DebtCollectorFormFragment fragment = new DebtCollectorFormFragment();
        fragment.debtCollectorForm = sale.getDebtCollectorForm();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_debt_collector_form, container, false);
        binding.setVariable(BR.debtCollectorForm,debtCollectorForm);
        binding.executePendingBindings();

        View view = binding.getRoot();

        locations = new String[] {
                "Localidad","Avellaneda", "Lanus", "Capital Federal"
        };

        locationsSpinners= (Spinner) view.findViewById(R.id.fr_debt_collector_form_spn_location);
        locationsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, locations);
        locationsSpinners.setAdapter(locationsAdapter);

        conditionVsIvaRadioButton = new ArrayList<>();
        conditionVsIvaRadioButton.add((RadioButton)view.findViewById(R.id.partial_condition_vs_iva_rb_exento));
        conditionVsIvaRadioButton.add((RadioButton)view.findViewById(R.id.partial_condition_vs_iva_rb_cf));
        conditionVsIvaRadioButton.add((RadioButton)view.findViewById(R.id.partial_condition_vs_iva_rb_ri));
        conditionVsIvaRadioButton.add((RadioButton)view.findViewById(R.id.partial_condition_vs_iva_rb_mt));
        conditionVsIvaRadioButton.add((RadioButton)view.findViewById(R.id.partial_condition_vs_iva_rb_ten_fifty));
        conditionVsIvaRadioButtonsManager.setButtons(conditionVsIvaRadioButton);

        deliveredDocumentsRadioButton = new ArrayList<>();
        deliveredDocumentsRadioButton.add((RadioButton)view.findViewById(R.id.partial_delivered_documents_cuit));
        deliveredDocumentsRadioButton.add((RadioButton)view.findViewById(R.id.partial_delivered_documents_iibb));
        deliveredDocumentsRadioButton.add((RadioButton)view.findViewById(R.id.partial_delivered_documents_dni));
        deliveredDocumentsRadioButton.add((RadioButton)view.findViewById(R.id.partial_delivered_documents_others));
        deliveredDocumentsRadioButtonManager.setButtons(deliveredDocumentsRadioButton);

        return view;
    }
}
