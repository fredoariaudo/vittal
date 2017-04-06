package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.interfaces.Formeable;

import org.greenrobot.greendao.annotation.Entity;

/**
 * Created by Pablo Poza on 5/4/2017.
 */
@Entity
public class CashPaymentForm extends FormModel implements Formeable {

    String number;
    String letter;

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
