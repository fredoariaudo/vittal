package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.interfaces.Formeable;


public class NowPaymentForm extends FormModel implements Formeable {

    String days = "";
    String date = "";

    public NowPaymentForm() {
    }

    public NowPaymentForm(Long sale) {
        this.sale = sale;
    }

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
        return  date.length() > 0 &&
                days.length() > 0;
    }
}
