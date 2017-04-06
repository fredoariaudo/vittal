package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.interfaces.Formeable;

import org.greenrobot.greendao.annotation.Entity;

/**
 * Created by Pablo Poza on 5/4/2017.
 */
@Entity
public class CheckPaymentForm extends FormModel implements Formeable {

    String branch;
    String number;
    String days;
    String date;


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
        return false;
    }

}
