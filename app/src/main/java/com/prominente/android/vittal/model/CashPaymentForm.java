package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.interfaces.Formeable;

/**
 * Created by Pablo Poza on 5/4/2017.
 */

public class CashPaymentForm extends FormModel implements Formeable {


    String number;
    String letter;

    public CashPaymentForm() {
    }

    public CashPaymentForm(Long sale) {
        this.sale = sale;
    }

    @Bindable
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Bindable
    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public boolean isValid() {
        return false;
    }




}
