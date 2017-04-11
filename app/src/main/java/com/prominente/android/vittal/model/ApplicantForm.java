package com.prominente.android.vittal.model;

import android.databinding.Bindable;
import com.prominente.android.vittal.BR;
/**
 * Created by Pablo Poza on 4/4/2017.
 */


public class ApplicantForm extends SaleSubFormModel{

    private String razonSocial;
    private String address;
    private String cellpones;
    private String cp;
    private String cuit;
    private String email;
    private String fantasyName;
    private String fax;
    private String phones;
    private String date = "dd/mm/aaaa";
    private int plan;


    public ApplicantForm(Long saleId) {
        this.sale = saleId;
    }



    @Bindable
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    @Bindable
    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    @Bindable
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Bindable
    public String getCellpones() {
        return cellpones;
    }

    public void setCellpones(String cellpones) {
        this.cellpones = cellpones;
    }

    @Bindable
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Bindable
    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Bindable
    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    @Bindable
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Bindable
    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    @Override
    public boolean isValid() {
        return false;
    }

}
