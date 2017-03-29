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
public class ModalityFormFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_modality_form, container, false);
    }

}
