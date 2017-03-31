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
import com.prominente.android.vittal.constants.SaveKeys;


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
        outState.putString(SaveKeys.RAZON_SOCIAL, razonSocialEditText.getText().toString());
        outState.putString(SaveKeys.ADDRESS, addressEditText.getText().toString());
        outState.putString(SaveKeys.CELLPHONES, cellponesEditText.getText().toString());
        outState.putString(SaveKeys.CP, cpEditText.getText().toString());
        outState.putString(SaveKeys.CUIT, cuitEditText.getText().toString());
        outState.putString(SaveKeys.EMAIL, emailEditText.getText().toString());
        outState.putString(SaveKeys.FANTASY_NAME, fantasyNameEditext.getText().toString());
        outState.putString(SaveKeys.FAX, faxEditText.getText().toString());
        outState.putString(SaveKeys.PHONES, phonesEditText.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            razonSocialEditText.setText(savedInstanceState.getString(SaveKeys.RAZON_SOCIAL));
            addressEditText.setText(savedInstanceState.getString(SaveKeys.ADDRESS));
            cellponesEditText.setText(savedInstanceState.getString(SaveKeys.CELLPHONES));
            cpEditText.setText(savedInstanceState.getString(SaveKeys.CP));
            cuitEditText.setText(savedInstanceState.getString(SaveKeys.CUIT));
            emailEditText.setText(savedInstanceState.getString(SaveKeys.EMAIL));
            fantasyNameEditext.setText(savedInstanceState.getString(SaveKeys.FANTASY_NAME));
            faxEditText.setText(savedInstanceState.getString(SaveKeys.FAX));
            phonesEditText.setText(savedInstanceState.getString(SaveKeys.PHONES));
        }
    }

}
