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

    public RadioButtonsManager(List<RadioButton> buttons) {
        this.buttons = buttons;

        for (RadioButton rb : buttons) {
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButtonSelected((RadioButton)v);
                }
            });
        }

    }

    public void radioButtonSelected(RadioButton radioButton) {
        selected = radioButton;

        for (RadioButton rb :buttons) {
            rb.setChecked(rb == selected);
        }

    }

    public void setSelected(int index) {
        for (int i = 0 ; i < buttons.size() ; i++) {
            boolean b = i == index;
            buttons.get(i).setChecked(b);
            if (b) selected = buttons.get(i);
        }
    }

    public int getSelectedIndex () {
        for (int i = 0 ; i < buttons.size() ; i++) {
            if (buttons.get(i) == selected) return i;
        }
        return -1;
    }

}
