package com.prominente.android.vittal.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.constants.SaveStateKeys;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModalityFormFragment extends Fragment {

    EditText additionalPerExcedentHelpTextView;
    EditText additionalPerServiceRequest;
    EditText capitaAmountEditText;
    EditText coseguroEditText;
    EditText detailOtherEditText;
    EditText hiredServiceAmountEditText;
    EditText observationsEditText;

    public ModalityFormFragment() {
        // Required empty public constructor
    }

    public static ModalityFormFragment newInstance() {
        ModalityFormFragment fragment = new ModalityFormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modality_form, container, false);

        additionalPerExcedentHelpTextView = (EditText) view.findViewById(R.id.fr_modality_form_et_additional_per_excedent_help);
        additionalPerServiceRequest = (EditText) view.findViewById(R.id.fr_modality_form_et_aditional_per_service_request);
        capitaAmountEditText = (EditText) view.findViewById(R.id.fr_modality_form_et_capita_amount);
        coseguroEditText = (EditText) view.findViewById(R.id.fr_modality_form_et_coseguro);
        detailOtherEditText = (EditText) view.findViewById(R.id.fr_modality_form_et_detail_others);
        hiredServiceAmountEditText = (EditText) view.findViewById(R.id.fr_modality_form_et_hired_service_ammount);
        observationsEditText = (EditText) view.findViewById(R.id.fr_modality_form_et_observations);


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SaveStateKeys.ADDITIONAL_PER_EXCEDENT_HELP , additionalPerExcedentHelpTextView.getText().toString());
        outState.putString(SaveStateKeys.ADDITIONAL_PER_SERVICE_REQUEST , additionalPerServiceRequest.getText().toString());
        outState.putString(SaveStateKeys.CAPITA_AMOUNT ,capitaAmountEditText.getText().toString());
        outState.putString(SaveStateKeys.COSEGURO ,coseguroEditText.getText().toString());
        outState.putString(SaveStateKeys.DETAIL_OTHERS ,detailOtherEditText.getText().toString());
        outState.putString(SaveStateKeys.HIRED_SERVICE_AMOUNT ,hiredServiceAmountEditText.getText().toString());
        outState.putString(SaveStateKeys.OBSERVATIONS ,observationsEditText.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            additionalPerExcedentHelpTextView.setText(savedInstanceState.getString(SaveStateKeys.ADDITIONAL_PER_EXCEDENT_HELP));
            additionalPerServiceRequest.setText(savedInstanceState.getString(SaveStateKeys.ADDITIONAL_PER_SERVICE_REQUEST));
            capitaAmountEditText.setText(savedInstanceState.getString(SaveStateKeys.CAPITA_AMOUNT));
            coseguroEditText.setText(savedInstanceState.getString(SaveStateKeys.COSEGURO));
            detailOtherEditText.setText(savedInstanceState.getString(SaveStateKeys.DETAIL_OTHERS));
            hiredServiceAmountEditText.setText(savedInstanceState.getString(SaveStateKeys.HIRED_SERVICE_AMOUNT));
            observationsEditText.setText(savedInstanceState.getString(SaveStateKeys.OBSERVATIONS));
        }
    }

}
