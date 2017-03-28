package com.prominente.android.vittal.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.prominente.android.vittal.R;


public class ApplicantFormFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private String[] arraySpinner;

    public ApplicantFormFragment() {
        // Required empty public constructor
    }

    public static ApplicantFormFragment newInstance() {
        ApplicantFormFragment fragment = new ApplicantFormFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_applicant_form, container, false);


        // Pick date button.
        Button pickDateButton = (Button) v.findViewById(R.id.fr_applicant_form_btn_pick_date);
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),ApplicantFormFragment.this, 1,2,2017).show();
            }
        });

        // Codigo Cobertura
        arraySpinner = new String[] {
                "AP#100", "AP#200", "AP#300", "AP#400", "AP#500"
        };
        Spinner s = (Spinner) v.findViewById(R.id.fr_applicant_form_spn_protected_area_code);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);


        return v;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


    }
}
