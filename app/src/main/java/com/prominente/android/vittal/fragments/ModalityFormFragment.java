package com.prominente.android.vittal.fragments;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.prominente.android.vittal.BR;
import com.prominente.android.vittal.R;
import com.prominente.android.vittal.model.ModalityForm;
import com.prominente.android.vittal.views.RadioButtonsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModalityFormFragment extends Fragment {

    private ModalityForm modalityForm;
    private List<RadioButton> modalitiesRadioButtons;
    private RadioButtonsManager modalitiesRadioButtonsManager;
    private RadioButtonsManager typesRadioButtonsManager;
    private List<RadioButton> typesRadioButtons;

    public ModalityFormFragment() {
        modalitiesRadioButtonsManager = new RadioButtonsManager(new RadioButtonsManager.OnRadioButtonSelectedListener() {
            @Override
            public void onRadioButtonSelected(int index) {
                modalityForm.setModality(index + "");
            }
        });

        typesRadioButtonsManager = new RadioButtonsManager(new RadioButtonsManager.OnRadioButtonSelectedListener() {
            @Override
            public void onRadioButtonSelected(int index) {
                modalityForm.setType(index + "");
            }
        });
    }

    public static ModalityFormFragment newInstance(ModalityForm modalityForm) {
        ModalityFormFragment fragment = new ModalityFormFragment();
        fragment.modalityForm = modalityForm;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewDataBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modality_form, container, false);
        binding.setVariable(BR.modalityForm,modalityForm);
        binding.executePendingBindings();

        View view = binding.getRoot();

        modalitiesRadioButtons = new ArrayList<>();
        modalitiesRadioButtons.add((RadioButton)view.findViewById(R.id.partial_modality_type_protected_area));
        modalitiesRadioButtons.add((RadioButton)view.findViewById(R.id.partial_modality_type_convenio_capitado));
        modalitiesRadioButtons.add((RadioButton)view.findViewById(R.id.partial_modality_type_bump_service));
        modalitiesRadioButtons.add((RadioButton)view.findViewById(R.id.partial_modality_type_others));
        modalitiesRadioButtonsManager.setButtons(modalitiesRadioButtons);

        typesRadioButtons = new ArrayList<>();
        typesRadioButtons.add((RadioButton)view.findViewById(R.id.fr_modality_form_rb_emergencias));
        typesRadioButtons.add((RadioButton)view.findViewById(R.id.fr_modality_form_rd_home_health_consultation));
        typesRadioButtonsManager.setButtons(typesRadioButtons);

        return view;
    }

}
