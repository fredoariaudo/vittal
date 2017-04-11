package com.prominente.android.vittal.model;

import android.databinding.Bindable;
/**
 * Created by Pablo Poza on 4/4/2017.
 */

public class CoverageForm extends SaleSubFormModel{

    private String entreCalle;
    private String andStreet;
    private String dpto;
    private String floor;
    private String number;
    private String street;
    private int protectedArea;
    private int location;

    public CoverageForm() {
    }

    public CoverageForm(Long saleId) {
        this.sale = saleId;
    }

    @Bindable
    public String getEntreCalle() {
        return entreCalle;
    }

    public void setEntreCalle(String entreCalle) {
        this.entreCalle = entreCalle;
    }

    @Bindable
    public String getAndStreet() {
        return andStreet;
    }

    public void setAndStreet(String andStreet) {
        this.andStreet = andStreet;
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
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Bindable
    public int getProtectedArea() {
        return protectedArea;
    }

    public void setProtectedArea(int protectedArea) {
        this.protectedArea = protectedArea;
    }

    @Bindable
    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
