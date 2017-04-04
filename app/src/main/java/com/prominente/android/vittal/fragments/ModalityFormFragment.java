package com.prominente.android.vittal.fragments;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.prominente.android.vittal.BR;
import com.prominente.android.vittal.R;
import com.prominente.android.vittal.constants.SaveStateKeys;
import com.prominente.android.vittal.model.ModalityForm;
import com.prominente.android.vittal.views.RadioButtonsManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModalityFormFragment extends Fragment {

    private ModalityForm modalityForm;

    public ModalityFormFragment() {
        modalityForm = new ModalityForm("asdsadas");
    }

    public static ModalityFormFragment newInstance() {
        ModalityFormFragment fragment = new ModalityFormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewDataBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modality_form, container, false);
        binding.setVariable(BR.modalityForm,modalityForm);
        binding.executePendingBindings();



        return binding.getRoot();
    }

}
