package com.prominente.android.vittal.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.BaseBundle;

/**
 * Created by Pablo Poza on 4/4/2017.
 */

public class DebtCollectorForm extends BaseObservable{

    String andStreet;
    String betweenStreet;
    String billTo;
    String cp;
    String dpto;
    String floor;
    String number;
    String observations;
    String phones;
    String street;

    @Bindable
    public String getAndStreet() {
        return andStreet;
    }

    public void setAndStreet(String andStreet) {
        this.andStreet = andStreet;
    }

    @Bindable
    public String getBetweenStreet() {
        return betweenStreet;
    }

    public void setBetweenStreet(String betweenStreet) {
        this.betweenStreet = betweenStreet;
    }

    @Bindable
    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    @Bindable
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Bindable
    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    @Bindable
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Bindable
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Bindable
    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Bindable
    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    @Bindable
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}