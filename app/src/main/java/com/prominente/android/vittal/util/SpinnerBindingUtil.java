package com.prominente.android.vittal.util;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Pablo Poza on 4/4/2017.
 */

public class SpinnerBindingUtil {

    @BindingAdapter(value = {"bind:selectedValue", "bind:selectedValueAttrChanged"}, requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner pAppCompatSpinner, int newSelectedValue, final InverseBindingListener newTextAttrChanged) {
        pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (newSelectedValue > 0) {
            pAppCompatSpinner.setSelection(newSelectedValue, true);
        }
    }

    @InverseBindingAdapter(attribute = "bind:selectedValue", event = "bind:selectedValueAttrChanged")
    public static int captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
        return pAppCompatSpinner.getSelectedItemPosition();
    }

}