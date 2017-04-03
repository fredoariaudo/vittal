package com.prominente.android.vittal.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.constants.SaveStateKeys;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoverageFormFragment extends Fragment {


    private String[] covergaTypes;
    private Spinner coverageTypesSpinner;
    private Spinner locationsSpinners;
    private String[] locations;

    EditText entreCalleEditText;
    EditText andStreetEditText;
    EditText dptoTextView;
    EditText floorEditText;
    EditText numberEditText;
    EditText streetEditText;
    private ArrayAdapter<String> covergaTypesAdapter;
    private ArrayAdapter<String> locationsAdapter;

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
        entreCalleEditText = (EditText) view.findViewById(R.id.fr_coverage_form_et_and_street);
        andStreetEditText = (EditText) view.findViewById(R.id.fr_coverage_form_et_between_street);
        dptoTextView = (EditText) view.findViewById(R.id.fr_coverage_form_et_dpto);
        floorEditText = (EditText) view.findViewById(R.id.fr_coverage_form_et_floor);
        numberEditText = (EditText) view.findViewById(R.id.fr_coverage_form_et_number);
        streetEditText = (EditText) view.findViewById(R.id.fr_coverage_form_et_street);


        //
        covergaTypes = new String[] {
                "Tipo de aréa protegida","Comercio Menores", "Micro Escolar", "Pub", "Hotel", "Otro"
        };

        coverageTypesSpinner  = (Spinner) view.findViewById(R.id.fr_coverage_form_spn_protected_area_type);
        covergaTypesAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, covergaTypes);
        coverageTypesSpinner.setAdapter(covergaTypesAdapter);


        //
        locations = new String[] {
                "Localidad","Avellaneda", "Lanus", "Capital Federal"
        };

        locationsSpinners= (Spinner) view.findViewById(R.id.fr_coverage_form_spn_location);
        locationsAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, locations);
        locationsSpinners.setAdapter(locationsAdapter);
        
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SaveStateKeys.BETWEEN_STREET, entreCalleEditText.getText().toString());
        outState.putString(SaveStateKeys.AND_STREET, andStreetEditText.getText().toString());
        outState.putString(SaveStateKeys.DPTO, dptoTextView.getText().toString());
        outState.putString(SaveStateKeys.FLOOR, floorEditText.getText().toString());
        outState.putString(SaveStateKeys.NUMBER, numberEditText.getText().toString());
        outState.putString(SaveStateKeys.STREET, streetEditText.getText().toString());
        outState.putInt(SaveStateKeys.COVERAGE_TYPE, covergaTypesAdapter.getPosition(coverageTypesSpinner.getSelectedItem().toString()));
        outState.putInt(SaveStateKeys.LOCATION, locationsAdapter.getPosition(locationsSpinners.getSelectedItem().toString()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            entreCalleEditText.setText(savedInstanceState.getString(SaveStateKeys.BETWEEN_STREET));
            andStreetEditText.setText(savedInstanceState.getString(SaveStateKeys.AND_STREET));
            dptoTextView.setText(savedInstanceState.getString(SaveStateKeys.DPTO));
            floorEditText.setText(savedInstanceState.getString(SaveStateKeys.FLOOR));
            numberEditText.setText(savedInstanceState.getString(SaveStateKeys.NUMBER));
            streetEditText.setText(savedInstanceState.getString(SaveStateKeys.STREET));
            coverageTypesSpinner.setSelection(savedInstanceState.getInt(SaveStateKeys.COVERAGE_TYPE));
            locationsSpinners.setSelection(savedInstanceState.getInt(SaveStateKeys.LOCATION));
        }
    }
}
