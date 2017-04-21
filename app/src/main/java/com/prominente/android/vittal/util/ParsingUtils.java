package com.prominente.android.vittal.util;

import com.prominente.android.vittal.model.Sale;
import com.prominente.android.vittal.model.Visit;

public class ParsingUtils
{
    public static Sale turnIntoSale(Visit visit)
    {
        Sale sale = new Sale();
        //Save sale to generate id for forms
        sale.save();

        sale.getApplicantForm().setRazonSocial(visit.getBusinessName());
        sale.getApplicantForm().setAddress(visit.getAddress());
        return sale;
    }
}
