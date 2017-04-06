package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.BR;

/**
 * Created by Pablo Poza on 4/4/2017.
 */

public class PaymentForm extends FormModel {

    private NowPaymentForm nowPaymentForm;
    private CashPaymentForm cashPaymentForm;
    private CheckPaymentForm checkPaymentForm;
    private CreditCardOrCbuPaymentForm creditCardOrCbuPaymentForm;
    int paymentMode;
    int expirationPayment;


    public PaymentForm() {
        cashPaymentForm = new CashPaymentForm();
        nowPaymentForm = new NowPaymentForm();
        checkPaymentForm = new CheckPaymentForm();
        creditCardOrCbuPaymentForm = new CreditCardOrCbuPaymentForm();
    }

    public int getExpirationPayment() {
        return expirationPayment;
    }

    public void setExpirationPayment(int expirationPayment) {
        this.expirationPayment = expirationPayment;
    }

    @Bindable
    public CreditCardOrCbuPaymentForm getCreditCardOrCbuPaymentForm() {
        return creditCardOrCbuPaymentForm;
    }

    public void setCreditCardOrCbuPaymentForm(CreditCardOrCbuPaymentForm creditCardOrCbuPaymentForm) {
        this.creditCardOrCbuPaymentForm = creditCardOrCbuPaymentForm;
    }

    @Bindable
    public CheckPaymentForm getCheckPaymentForm() {
        return checkPaymentForm;
    }

    public void setCheckPaymentForm(CheckPaymentForm checkPaymentForm) {
        this.checkPaymentForm = checkPaymentForm;
    }

    @Bindable
    public CashPaymentForm getCashPaymentForm() {
        return cashPaymentForm;
    }

    public void setCashPaymentForm(CashPaymentForm cashPaymentForm) {
        this.cashPaymentForm = cashPaymentForm;
    }

    @Bindable
    public NowPaymentForm getNowPaymentForm() {
        return nowPaymentForm;
    }

    public void setNowPaymentForm(NowPaymentForm nowPaymentForm) {
        this.nowPaymentForm = nowPaymentForm;
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
