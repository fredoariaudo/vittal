package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.BR;

import java.util.Iterator;


public class PaymentForm extends SaleSubFormModel {

    private CashPaymentForm cashPaymentForm;
    private NowPaymentForm nowPaymentForm;
    private CheckPaymentForm checkPaymentForm;
    private CreditCardOrCbuPaymentForm creditCardOrCbuPaymentForm;

    int paymentMode;
    int expirationPayment;

    public PaymentForm() {
    }



    public PaymentForm(Long saleId) {
        this.sale = saleId;
    }

    @Bindable
    public int getExpirationPayment() {
        return expirationPayment;
    }

    public void setExpirationPayment(int expirationPayment) {
        this.expirationPayment = expirationPayment;
    }

    @Bindable
    public CashPaymentForm getCashPaymentForm() {
        if (cashPaymentForm ==  null) {
            cashPaymentForm = CashPaymentForm.find(CashPaymentForm.class,"sale = ?",getId() + "").get(0);
            if (cashPaymentForm ==  null) {
                cashPaymentForm = new CashPaymentForm(getId());
            }
        }
        return cashPaymentForm;
    }

    public void setCashPaymentForm(CashPaymentForm cashPaymentForm) {
        this.cashPaymentForm = cashPaymentForm;
    }

    @Bindable
    public CreditCardOrCbuPaymentForm getCreditCardOrCbuPaymentForm() {
        if (creditCardOrCbuPaymentForm ==  null) {
            creditCardOrCbuPaymentForm = CreditCardOrCbuPaymentForm.find(CreditCardOrCbuPaymentForm.class,"sale = ?",getId() + "").get(0);
            if (creditCardOrCbuPaymentForm ==  null) {
                creditCardOrCbuPaymentForm = new CreditCardOrCbuPaymentForm(getId());
            }
        }
        return creditCardOrCbuPaymentForm;
    }

    public void setCreditCardOrCbuPaymentForm(CreditCardOrCbuPaymentForm creditCardOrCbuPaymentForm) {
        this.creditCardOrCbuPaymentForm = creditCardOrCbuPaymentForm;
    }

    @Bindable
    public CheckPaymentForm getCheckPaymentForm() {
        if (checkPaymentForm ==  null) {
            checkPaymentForm = CheckPaymentForm.find(CheckPaymentForm.class,"sale = ?",getId() + "").get(0);
            if (checkPaymentForm ==  null) {
                checkPaymentForm = new CheckPaymentForm(getId());
            }
        }
        return checkPaymentForm;
    }

    public void setCheckPaymentForm(CheckPaymentForm checkPaymentForm) {
        this.checkPaymentForm = checkPaymentForm;
    }


    @Bindable
    public NowPaymentForm getNowPaymentForm() {
        return nowPaymentForm;
    }

    public void setNowPaymentForm(NowPaymentForm nowPaymentForm) {
        if (nowPaymentForm ==  null) {
            nowPaymentForm = NowPaymentForm.find(NowPaymentForm.class,"sale = ?",getId() + "").get(0);
            if (nowPaymentForm ==  null) {
                nowPaymentForm = new NowPaymentForm(getId());
            }
        }
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

    @Override
    public long save() {

        getCashPaymentForm().save();
        getCheckPaymentForm().save();
        getCreditCardOrCbuPaymentForm().save();
        getNowPaymentForm().save();

        return super.save();
    }
}
