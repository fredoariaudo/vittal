package com.prominente.android.vittal.fragments;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.prominente.android.vittal.BR;
import com.prominente.android.vittal.R;
import com.prominente.android.vittal.constants.SaveStateKeys;
import com.prominente.android.vittal.model.DebtCollectorForm;

import static com.prominente.android.vittal.BR.applicantForm;

/**
 * A simple {@link Fragment} subclass.
 */
public class DebtCollectorFormFragment extends Fragment {

    private final DebtCollectorForm debtCollectorForm;
    private String[] locations;
    private Spinner locationsSpinners;
    private ArrayAdapter<String> locationsAdapter;

    public DebtCollectorFormFragment() {
        debtCollectorForm = new DebtCollectorForm();
    }

    public static DebtCollectorFormFragment newInstance() {
        DebtCollectorFormFragment fragment = new DebtCollectorFormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_debt_collector_form, container, false);
        binding.setVariable(BR.debtCollectorForm,debtCollectorForm);
        binding.executePendingBindings();

        View view = binding.getRoot();

        locations = new String[] {
                "Localidad","Avellaneda", "Lanus", "Capital Federal"
        };

        locationsSpinners= (Spinner) view.findViewById(R.id.fr_debt_collector_form_spn_location);
        locationsAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, locations);
        locationsSpinners.setAdapter(locationsAdapter);

        return view;
    }


}
