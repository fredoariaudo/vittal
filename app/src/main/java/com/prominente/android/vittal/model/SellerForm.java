package com.prominente.android.vittal.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Pablo Poza on 4/4/2017.
 */

public class SellerForm extends BaseObservable{

    private String amountIibb;
    private String capita;
    private String monthlyFeeWhitoutIva;
    private int radius;
    private int circuit;
    private int promo;
    private int seller;

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

    @Bindable
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Bindable
    public int getCircuit() {
        return circuit;
    }

    public void setCircuit(int circuit) {
        this.circuit = circuit;
    }

    @Bindable
    public int getPromo() {
        return promo;
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }

    @Bindable
    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }
}
