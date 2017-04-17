package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.interfaces.Formeable;


public class CheckPaymentForm extends FormModel implements Formeable {

    String branch;
    String number;
    String days;
    String date;

    public CheckPaymentForm() {
    }

    public CheckPaymentForm(Long sale) {
        this.sale = sale;
    }

    @Bindable
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Bindable
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        return true;
    }

}
