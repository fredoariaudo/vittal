package com.prominente.android.vittal.model;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;

@Entity
public class Sale implements Serializable
{
    private long id;
    private String client;
    private String area;
    private String address;
    private ApplicantForm applicantForm ;
    private CoverageForm coverageForm;
    private DebtCollectorForm debtCollectorForm;
    private ModalityForm modalityForm;
    private PaymentForm paymentForm;
    private SellerForm sellerForm;

    public Sale() {
        applicantForm  = new ApplicantForm();
        coverageForm = new CoverageForm();
        debtCollectorForm =  new DebtCollectorForm();
        modalityForm = new ModalityForm();
        paymentForm = new PaymentForm();
        sellerForm  = new SellerForm();
    }

    public ApplicantForm getApplicantForm() {
        return applicantForm;
    }

    public CoverageForm getCoverageForm() {
        return coverageForm;
    }

    public DebtCollectorForm getDebtCollectorForm() {
        return debtCollectorForm;
    }

    public ModalityForm getModalityForm() {
        return modalityForm;
    }

    public PaymentForm getPaymentForm() {
        return paymentForm;
    }

    public SellerForm getSellerForm() {
        return sellerForm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Sale && ((Sale) obj).getId() == this.getId();
    }
}
