package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.interfaces.Formeable;

/**
 * Created by Pablo Poza on 5/4/2017.
 */

public class NowPaymentForm extends FormModel implements Formeable {

    String days;
    String date;


    @Bindable
    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @Bindable
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
