package com.prominente.android.vittal.model;

import android.databinding.Bindable;

/**
 * Created by Pablo Poza on 4/4/2017.
 */

public class PaymentForm extends FormModel {

    int paymentMode;

    @Bindable
    public int getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(int paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
