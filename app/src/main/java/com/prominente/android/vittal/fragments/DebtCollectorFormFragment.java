package com.prominente.android.vittal.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prominente.android.vittal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DebtCollectorFormFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_debt_collector_form, container, false);
    }

}
