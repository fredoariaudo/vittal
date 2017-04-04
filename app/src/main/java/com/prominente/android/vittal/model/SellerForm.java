package com.prominente.android.vittal.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Pablo Poza on 4/4/2017.
 */

public class SellerForm extends BaseObservable{

    String amountIibb;
    String capita;
    String monthlyFeeWhitoutIva;

    @Bindable
    public String getAmountIibb() {
        return amountIibb;
    }

    public void setAmountIibb(String amountIibb) {
        this.amountIibb = amountIibb;
    }

    @Bindable
    public String getCapita() {
        return capita;
    }

    public void setCapita(String capita) {
        this.capita = capita;
    }

    @Bindable
    public String getMonthlyFeeWhitoutIva() {
        return monthlyFeeWhitoutIva;
    }

    public void setMonthlyFeeWhitoutIva(String monthlyFeeWhitoutIva) {
        this.monthlyFeeWhitoutIva = monthlyFeeWhitoutIva;
    }
}
