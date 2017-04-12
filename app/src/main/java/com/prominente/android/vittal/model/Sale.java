package com.prominente.android.vittal.model;

public class Sale extends FormModel
{
    private String client;
    private String area;
    private String address;

    private ApplicantForm applicantForm ;
    private CoverageForm coverageForm;
    private DebtCollectorForm debtCollectorForm;
    private ModalityForm modalityForm;
    private PaymentForm paymentForm;
    private SellerForm sellerForm;

    public Sale(Long id) {
        this.setId(id);
    }

    public Sale() {

    }

    public ApplicantForm getApplicantForm() {
        if (applicantForm ==  null) {
            applicantForm = ApplicantForm.find(ApplicantForm.class,"sale = ?",getId() + "").get(0);
            if (applicantForm ==  null) {
                applicantForm = new ApplicantForm(getId());
            }
        }

        return applicantForm;
    }

    public CoverageForm getCoverageForm() {
        if (coverageForm ==  null) {
            coverageForm = CoverageForm.find(CoverageForm.class,"sale = ?",getId() + "").get(0);
            if (coverageForm ==  null) {
                coverageForm = new CoverageForm(getId());
            }
        }
        return coverageForm;
    }

    public DebtCollectorForm getDebtCollectorForm() {
        if (debtCollectorForm ==  null) {
            debtCollectorForm = DebtCollectorForm.find(DebtCollectorForm.class,"sale = ?",getId() + "").get(0);
            if (debtCollectorForm ==  null) {
                debtCollectorForm = new DebtCollectorForm(getId());
            }
        }
        return debtCollectorForm;
    }

    public ModalityForm getModalityForm() {
        if (modalityForm ==  null) {
            modalityForm = ModalityForm.find(ModalityForm.class,"sale = ?",getId() + "").get(0);
            if (modalityForm ==  null) {
                modalityForm = new ModalityForm(getId());
            }
        }
        return modalityForm;
    }

    public PaymentForm getPaymentForm() {
        if (paymentForm ==  null) {
            paymentForm = PaymentForm.find(PaymentForm.class,"sale = ?",getId() + "").get(0);
            if (paymentForm ==  null) {
                paymentForm = new PaymentForm(getId());
            }
            return paymentForm;
        }
        return paymentForm;
    }

    public SellerForm getSellerForm() {
        if (sellerForm ==  null) {
            sellerForm = SellerForm.find(SellerForm.class,"sale = ?",getId() + "").get(0);
            if (sellerForm ==  null) {
                sellerForm = new SellerForm(getId());
            }
        }
        return sellerForm;
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
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Sale && ((Sale) obj).getId().equals(getId());
    }

    @Override
    public long save() {
        long id = super.save();
        getApplicantForm().save();
        getCoverageForm().save();
        getDebtCollectorForm().save();
        getModalityForm().save();
        getPaymentForm().save();
        getSellerForm().save();
        return id;
    }
}
