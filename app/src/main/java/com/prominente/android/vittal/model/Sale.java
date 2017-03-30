package com.prominente.android.vittal.model;

import java.io.Serializable;

public class Sale implements Serializable
{
    private String client;
    private String area;
    private String address;

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
}
