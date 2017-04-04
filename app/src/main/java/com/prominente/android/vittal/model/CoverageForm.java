package com.prominente.android.vittal.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Pablo Poza on 4/4/2017.
 */

public class CoverageForm extends BaseObservable{

    String entreCalle;
    String andStreet;
    String dpto;
    String floor;
    String number;
    String street;


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
}
