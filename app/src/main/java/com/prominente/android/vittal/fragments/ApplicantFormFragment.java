package com.prominente.android.vittal.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.constants.SaveStateKeys;


public class ApplicantFormFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private String[] arraySpinner;
    private EditText razonSocialEditText;
    private EditText addressEditText;
    private EditText cellponesEditText;
    private EditText cpEditText;
    private EditText cuitEditText;
    private EditText emailEditText;
    private EditText fantasyNameEditext;
    private EditText faxEditText;
    private EditText phonesEditText;

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
        View view = inflater.inflate(R.layout.fragment_applicant_form, container, false);




        razonSocialEditText = (EditText)view.findViewById(R.id.fr_applicant_form_et_razon_social);
        addressEditText = (EditText)view.findViewById(R.id.fr_applicant_form_et_address);
        cellponesEditText = (EditText)view.findViewById(R.id.fr_applicant_form_et_cellphones);
        cpEditText = (EditText)view.findViewById(R.id.fr_applicant_form_et_cp);
        cuitEditText = (EditText)view.findViewById(R.id.fr_applicant_form_et_cuit);
        emailEditText = (EditText)view.findViewById(R.id.fr_applicant_form_et_email);
        fantasyNameEditext = (EditText)view.findViewById(R.id.fr_applicant_form_et_fantasy_name);
        faxEditText = (EditText)view.findViewById(R.id.fr_applicant_form_et_fax);
        phonesEditText = (EditText)view.findViewById(R.id.fr_applicant_form_et_phones);

        // Pick date button.
        Button pickDateButton = (Button) view.findViewById(R.id.fr_applicant_form_btn_pick_date);
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
        Spinner s = (Spinner) view.findViewById(R.id.fr_applicant_form_spn_protected_area_code);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);


        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SaveStateKeys.RAZON_SOCIAL, razonSocialEditText.getText().toString());
        outState.putString(SaveStateKeys.ADDRESS, addressEditText.getText().toString());
        outState.putString(SaveStateKeys.CELLPHONES, cellponesEditText.getText().toString());
        outState.putString(SaveStateKeys.CP, cpEditText.getText().toString());
        outState.putString(SaveStateKeys.CUIT, cuitEditText.getText().toString());
        outState.putString(SaveStateKeys.EMAIL, emailEditText.getText().toString());
        outState.putString(SaveStateKeys.FANTASY_NAME, fantasyNameEditext.getText().toString());
        outState.putString(SaveStateKeys.FAX, faxEditText.getText().toString());
        outState.putString(SaveStateKeys.PHONES, phonesEditText.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            razonSocialEditText.setText(savedInstanceState.getString(SaveStateKeys.RAZON_SOCIAL));
            addressEditText.setText(savedInstanceState.getString(SaveStateKeys.ADDRESS));
            cellponesEditText.setText(savedInstanceState.getString(SaveStateKeys.CELLPHONES));
            cpEditText.setText(savedInstanceState.getString(SaveStateKeys.CP));
            cuitEditText.setText(savedInstanceState.getString(SaveStateKeys.CUIT));
            emailEditText.setText(savedInstanceState.getString(SaveStateKeys.EMAIL));
            fantasyNameEditext.setText(savedInstanceState.getString(SaveStateKeys.FANTASY_NAME));
            faxEditText.setText(savedInstanceState.getString(SaveStateKeys.FAX));
            phonesEditText.setText(savedInstanceState.getString(SaveStateKeys.PHONES));
        }
    }

}
