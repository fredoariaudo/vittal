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
public class CoverageFormFragment extends Fragment {


    private String[] covergaTypes;
    private Spinner coverageTypesSpinner;
    private Spinner locationsSpinners;
    private String[] locations;

    public static CoverageFormFragment newInstance() {
        Bundle args = new Bundle();
        CoverageFormFragment fragment = new CoverageFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CoverageFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coverage_form, container, false);

        //
        covergaTypes = new String[] {
                "Tipo de aréa protegida","Comercio Menores", "Micro Escolar", "Pub", "Hotel", "Otro"
        };

        coverageTypesSpinner  = (Spinner) view.findViewById(R.id.fr_coverage_form_spn_protected_area_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, covergaTypes);
        coverageTypesSpinner.setAdapter(adapter);


        //
        locations = new String[] {
                "Avellaneda", "Lanus", "Capital Federal"
        };

        locationsSpinners= (Spinner) view.findViewById(R.id.fr_coverage_form_spn_location);
        adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, locations);
        locationsSpinners.setAdapter(adapter);
        
        return view;
    }

}
