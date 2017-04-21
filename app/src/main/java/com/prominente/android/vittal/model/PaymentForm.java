package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.BR;

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


    public int getExpirationPayment() {
        return expirationPayment;
    }

    public void setExpirationPayment(int expirationPayment) {
        this.expirationPayment = expirationPayment;
    }

    @Bindable
    public CashPaymentForm getCashPaymentForm() {
        if (cashPaymentForm ==  null) {
            cashPaymentForm = CashPaymentForm.find(CashPaymentForm.class,"sale = ?",sale + "").get(0);
            if (cashPaymentForm ==  null) {
                cashPaymentForm = new CashPaymentForm(sale);
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
            creditCardOrCbuPaymentForm = CreditCardOrCbuPaymentForm.find(CreditCardOrCbuPaymentForm.class,"sale = ?",sale + "").get(0);
            if (creditCardOrCbuPaymentForm ==  null) {
                creditCardOrCbuPaymentForm = new CreditCardOrCbuPaymentForm(sale);
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
            checkPaymentForm = CheckPaymentForm.find(CheckPaymentForm.class,"sale = ?",sale + "").get(0);
            if (checkPaymentForm ==  null) {
                checkPaymentForm = new CheckPaymentForm(sale);
            }
        }
        return checkPaymentForm;
    }

    public void setCheckPaymentForm(CheckPaymentForm checkPaymentForm) {
        this.checkPaymentForm = checkPaymentForm;
    }


    @Bindable
    public NowPaymentForm getNowPaymentForm() {
        if (nowPaymentForm ==  null) {
            nowPaymentForm = NowPaymentForm.find(NowPaymentForm.class,"sale = ?",sale + "").get(0);
            if (nowPaymentForm ==  null) {
                nowPaymentForm = new NowPaymentForm(sale);
            }
        }
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

        switch (paymentMode) {

            case 0 : return  false;
            case 1 : return  cashPaymentForm.isValid();
            case 2 : return  nowPaymentForm.isValid();
            case 3 : return  checkPaymentForm.isValid();
            case 4 : return  creditCardOrCbuPaymentForm.isValid();
        }

        return false;

    }

    @Override
    public long save() {
        long id = super.save();
        getCashPaymentForm().save();
        getCheckPaymentForm().save();
        getCreditCardOrCbuPaymentForm().save();
        getNowPaymentForm().save();
        return id;
    }
}
