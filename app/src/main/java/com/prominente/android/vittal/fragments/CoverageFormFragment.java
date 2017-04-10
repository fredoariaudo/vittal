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
import com.prominente.android.vittal.model.CoverageForm;

public class CoverageFormFragment extends Fragment {

    private CoverageForm coverageForm;
    private String[] covergaTypes;
    private Spinner coverageTypesSpinner;
    private Spinner locationsSpinners;
    private String[] locations;

    private ArrayAdapter<String> covergaTypesAdapter;
    private ArrayAdapter<String> locationsAdapter;

    public static CoverageFormFragment newInstance(CoverageForm coverageForm) {
        CoverageFormFragment fragment = new CoverageFormFragment();
        fragment.coverageForm = coverageForm;
        return fragment;
    }

    public CoverageFormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_coverage_form, container, false);
        binding.setVariable(BR.coverageForm,coverageForm);
        binding.executePendingBindings();

        View view = binding.getRoot();
        //
        covergaTypes = new String[] {
                "Tipo de aréa protegida", "Comercio Menores", "Micro Escolar", "Pub", "Hotel", "Otro"
        };

        coverageTypesSpinner  = (Spinner) view.findViewById(R.id.fr_coverage_form_spn_protected_area_type);
        covergaTypesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, covergaTypes);
        coverageTypesSpinner.setAdapter(covergaTypesAdapter);

        //
        locations = new String[] {
                "Localidad", "Avellaneda", "Lanus", "Capital Federal"
        };

        locationsSpinners= (Spinner) view.findViewById(R.id.fr_coverage_form_spn_location);
        locationsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, locations);
        locationsSpinners.setAdapter(locationsAdapter);
        
        return view;
    }
}
