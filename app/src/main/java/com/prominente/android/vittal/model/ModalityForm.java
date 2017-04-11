package com.prominente.android.vittal.model;

import android.databinding.Bindable;

import com.prominente.android.vittal.BR;


/**
 * Created by Pablo Poza on 3/4/2017.
 */

public class ModalityForm extends SaleSubFormModel {

    private String monthlyFee;
    private String hiredServicesAmount;
    private String additionalPerServiceRequest;
    private String additionalPerExcedentHelp;
    private String capitaAmount;
    private String coseguro;
    private String detailOthers;
    private String observations;
    private int modality = -1;
    private int type     = -1;

    public ModalityForm() {
    }

    public ModalityForm(Long saleId) {
        this.sale = saleId;
    }

    public int getModality() {
        return modality;
    }

    public void setModality(int modality) {
        this.modality = modality;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Bindable
    public String getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(String monthlyFee) {
        this.monthlyFee = monthlyFee;
        notifyPropertyChanged(BR.monthlyFee);
    }

    @Bindable
    public String getHiredServicesAmount() {
        return hiredServicesAmount;
    }

    public void setHiredServicesAmount(String hiredServicesAmount) {
        this.hiredServicesAmount = hiredServicesAmount;
    }

    @Bindable
    public String getAdditionalPerServiceRequest() {
        return additionalPerServiceRequest;
    }

    public void setAdditionalPerServiceRequest(String additionalPerServiceRequest) {
        this.additionalPerServiceRequest = additionalPerServiceRequest;
    }

    @Bindable
    public String getCapitaAmount() {
        return capitaAmount;
    }

    public void setCapitaAmount(String capitaAmount) {
        this.capitaAmount = capitaAmount;
    }

    @Bindable
    public String getCoseguro() {
        return coseguro;
    }

    public void setCoseguro(String coseguro) {
        this.coseguro = coseguro;
    }

    @Bindable
    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Bindable
    public String getAdditionalPerExcedentHelp() {
        return additionalPerExcedentHelp;
    }

    public void setAdditionalPerExcedentHelp(String additionalPerExcedentHelp) {
        this.additionalPerExcedentHelp = additionalPerExcedentHelp;
    }

    @Bindable
    public String getDetailOthers() {
        return detailOthers;
    }

    public void setDetailOthers(String detailOthers) {
        this.detailOthers = detailOthers;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}



