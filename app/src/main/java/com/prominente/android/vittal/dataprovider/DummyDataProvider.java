package com.prominente.android.vittal.dataprovider;

import com.prominente.android.vittal.model.Sale;

import java.util.ArrayList;

public class DummyDataProvider
{
    private static DummyDataProvider dummyDataProvider;

    private DummyDataProvider()
    {
    }

    public static synchronized DummyDataProvider getInstance()
    {
        if(dummyDataProvider == null)
            dummyDataProvider = new DummyDataProvider();

        return dummyDataProvider;
    }

    public ArrayList<Sale> getSales()
    {
        ArrayList<Sale> sales = new ArrayList<Sale>();

        for(int i=0; i<6; i++)
        {
            Sale sale = new Sale();
            sale.setId(i);
            sale.setClient("Cliente "+(i+1));
            sale.getApplicantForm().setRazonSocial("Cliente "+(i+1));
            sale.setArea("AP#100");
            sale.setAddress("Dirección "+(i+1));
            sale.getApplicantForm().setAddress("Dirección "+(i+1));
            sales.add(sale);
        }

        return sales;
    }
}
