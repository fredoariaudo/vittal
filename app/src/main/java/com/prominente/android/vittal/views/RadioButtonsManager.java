package com.prominente.android.vittal.views;

import android.view.View;
import android.widget.RadioButton;

import java.util.List;

/**
 * Created by Pablo Poza on 3/4/2017.
 */

public class RadioButtonsManager {

    RadioButton selected = null;
    List<RadioButton> buttons;
    OnRadioButtonSelectedListener listener;

    int selectedIndex = -1;

    public RadioButtonsManager(List<RadioButton> buttons, OnRadioButtonSelectedListener listener) {
        this.listener = listener;

        setButtons(buttons);
    }

    public RadioButtonsManager(OnRadioButtonSelectedListener listener) {
        this.listener = listener;
    }

    public void setButtons(List<RadioButton> buttons) {
        this.buttons = buttons;

        for (RadioButton rb : buttons) {
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButtonSelected((RadioButton)v);
                }
            });
        }

        setSelected(selectedIndex);
    }

    public void radioButtonSelected(RadioButton radioButton) {
        selected = radioButton;
        for (int i = 0; i < buttons.size(); i++) {
            RadioButton rb = buttons.get(i);
            boolean b = rb == selected;
            rb.setChecked(b);
            if(b){
                selectedIndex = i;
                if(listener != null) {
                    listener.onRadioButtonSelected(i);
                }
            }
        }
    }

    public void setSelected(int index) {
        selectedIndex = index;
        for (int i = 0 ; i < buttons.size() ; i++) {
            boolean b = i == index;
            buttons.get(i).setChecked(b);
            if (b) selected = buttons.get(i);
        }
    }

    public int getSelectedIndex () {
        return selectedIndex;
    }

    public interface OnRadioButtonSelectedListener {
        void onRadioButtonSelected(int index);
    }
}
