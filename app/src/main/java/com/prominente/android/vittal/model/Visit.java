package com.prominente.android.vittal.model;

public class Visit extends VittalModel
{
    private static final long serialVersionUID = 2182711807224411197L;

    private String businessName;
    private String address;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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
        return obj instanceof Visit && ((Visit) obj).getId().equals(getId());
    }
}
