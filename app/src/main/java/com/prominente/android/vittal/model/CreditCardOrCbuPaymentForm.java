package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.interfaces.Formeable;


/**
 * Created by Pablo Poza on 5/4/2017.
 */

public class CreditCardOrCbuPaymentForm extends FormModel implements Formeable {

    String name = "";
    String branch = "";
    String cardNumber = "";
    String cbu = "";
    String expirationDate = "";
    String dni = "";
    String cuit = "";
    String nroCupon = "";
    String paymentAmount = "";
    private int bank = 0;

    public CreditCardOrCbuPaymentForm() {
    }

    public CreditCardOrCbuPaymentForm(Long sale) {
        this.sale = sale;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Bindable
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Bindable
    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    @Bindable
    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Bindable
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Bindable
    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    @Bindable
    public String getNroCupon() {
        return nroCupon;
    }

    public void setNroCupon(String nroCupon) {
        this.nroCupon = nroCupon;
    }

    @Bindable
    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Bindable
    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    @Override
    public boolean isValid() {
        return  name.length() > 0 &&
                branch.length() > 0 &&
                cardNumber.length() > 0 &&
                cbu.length() > 0 &&
                expirationDate.length() > 0 &&
                dni.length() > 0 &&
                cuit.length() > 0 &&
                nroCupon.length() > 0 &&
                paymentAmount.length() > 0 &&
                bank > 0;
    }


}
