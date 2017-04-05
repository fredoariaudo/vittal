package com.prominente.android.vittal.model;

import android.databinding.Bindable;
import android.databinding.Observable;

import com.prominente.android.vittal.BR;

/**
 * Created by Pablo Poza on 4/4/2017.
 */

public class PaymentForm extends FormModel {

    int paymentMode;

    public PaymentForm() {

    }

    @Bindable
    public int getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(int paymentMode) {
        this.paymentMode = paymentMode;
        notifyPropertyChanged(BR.paymentMode);
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
