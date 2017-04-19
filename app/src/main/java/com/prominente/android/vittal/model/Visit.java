package com.prominente.android.vittal.model;

public class Visit extends VittalModel
{
    private static final long serialVersionUID = 2182711807224411197L;

    private String businessName;
    private String cuit;
    private String address;
    private String phone;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Visit && ((Visit) obj).getId().equals(getId());
    }
}
