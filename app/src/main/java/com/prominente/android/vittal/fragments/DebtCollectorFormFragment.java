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
public class DebtCollectorFormFragment extends Fragment {

    EditText andStreetTextView;
    EditText betweenStreetTextView;
    EditText billToTextView;
    EditText cpTextView;
    EditText dptoTextView;
    EditText floorEditText;
    EditText numberEditText;
    EditText observationsEditText;
    EditText phonesEditText;
    EditText streetTextView;

    public DebtCollectorFormFragment() {
        // Required empty public constructor
    }

    public static DebtCollectorFormFragment newInstance() {
        DebtCollectorFormFragment fragment = new DebtCollectorFormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_debt_collector_form, container, false);

        //
        andStreetTextView = (EditText) view.findViewById(R.id.fr_debt_collector_et_and_street);
        betweenStreetTextView = (EditText) view.findViewById(R.id.fr_debt_collector_et_between_street);
        billToTextView = (EditText) view.findViewById(R.id.fr_debt_collector_et_bill_to);
        cpTextView = (EditText) view.findViewById(R.id.fr_debt_collector_et_cp);
        dptoTextView = (EditText) view.findViewById(R.id.fr_debt_collector_et_dpto);
        floorEditText = (EditText) view.findViewById(R.id.fr_debt_collector_et_floor);
        numberEditText = (EditText) view.findViewById(R.id.fr_debt_collector_et_number);
        observationsEditText = (EditText) view.findViewById(R.id.fr_debt_collector_et_observations);
        phonesEditText = (EditText) view.findViewById(R.id.fr_debt_collector_et_phones);
        streetTextView = (EditText) view.findViewById(R.id.fr_debt_collector_et_street);
        streetTextView = (EditText) view.findViewById(R.id.fr_debt_collector_et_street);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SaveStateKeys.AND_STREET, andStreetTextView.getText().toString());
        outState.putString(SaveStateKeys.BETWEEN_STREET, betweenStreetTextView.getText().toString());
        outState.putString(SaveStateKeys.BILL_TO, billToTextView.getText().toString());
        outState.putString(SaveStateKeys.CP, cpTextView.getText().toString());
        outState.putString(SaveStateKeys.DPTO, dptoTextView.getText().toString());
        outState.putString(SaveStateKeys.FLOOR, floorEditText.getText().toString());
        outState.putString(SaveStateKeys.NUMBER, numberEditText.getText().toString());
        outState.putString(SaveStateKeys.OBSERVATIONS, observationsEditText.getText().toString());
        outState.putString(SaveStateKeys.PHONES, phonesEditText.getText().toString());
        outState.putString(SaveStateKeys.DETAIL_OTHERS, phonesEditText.getText().toString());
        outState.putString(SaveStateKeys.STREET, streetTextView.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            andStreetTextView.setText(savedInstanceState.getString(SaveStateKeys.AND_STREET));
            betweenStreetTextView.setText(savedInstanceState.getString(SaveStateKeys.BETWEEN_STREET));
            billToTextView.setText(savedInstanceState.getString(SaveStateKeys.BILL_TO));
            cpTextView.setText(savedInstanceState.getString(SaveStateKeys.CP));
            dptoTextView.setText(savedInstanceState.getString(SaveStateKeys.DPTO));
            floorEditText.setText(savedInstanceState.getString(SaveStateKeys.FLOOR));
            numberEditText.setText(savedInstanceState.getString(SaveStateKeys.NUMBER));
            observationsEditText.setText(savedInstanceState.getString(SaveStateKeys.OBSERVATIONS));
            phonesEditText.setText(savedInstanceState.getString(SaveStateKeys.PHONES));
            streetTextView.setText(savedInstanceState.getString(SaveStateKeys.STREET));
            streetTextView.setText(savedInstanceState.getString(SaveStateKeys.DETAIL_OTHERS));

        }
    }

}
