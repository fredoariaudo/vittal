package com.prominente.android.vittal.fragments;


import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;

import com.prominente.android.vittal.BR;
import com.prominente.android.vittal.R;
import com.prominente.android.vittal.model.ApplicantForm;
import com.prominente.android.vittal.model.ApplicantFormDao;


public class ApplicantFormFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    private ApplicantForm applicantForm;
    private ApplicantFormDao applicantFormDao;
    private String[] arraySpinner;

    public ApplicantFormFragment() {
    }

    public static ApplicantFormFragment newInstance(ApplicantForm applicantForm) {
        ApplicantFormFragment fragment = new ApplicantFormFragment();
        fragment.applicantForm = applicantForm;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewDataBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_applicant_form, container, false);
        binding.setVariable(BR.applicantForm,applicantForm);
        binding.executePendingBindings();

        View view = binding.getRoot();

        // Pick date button.
        Button pickDateButton = (Button) view.findViewById(R.id.fr_applicant_form_btn_pick_date);
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),ApplicantFormFragment.this, 2011,1,1).show();
            }
        });

        // Codigo Cobertura
        arraySpinner = new String[] {
                "AP#100", "AP#200", "AP#300", "AP#400", "AP#500"
        };
        AppCompatSpinner s = (AppCompatSpinner) view.findViewById(R.id.fr_applicant_form_spn_protected_area_code);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);


        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        applicantForm.setDate(dayOfMonth + "/" + (month  + 1)+ "/" + year);
    }



}
