package com.prominente.android.vittal.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.prominente.android.vittal.R;


public class ApplicantFormFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

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

        Button pickDateButton = (Button) v.findViewById(R.id.fragment_application_form_button_pick_date);
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),ApplicantFormFragment.this, 1,2,2017).show();
            }
        });



        return v;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


    }
}
