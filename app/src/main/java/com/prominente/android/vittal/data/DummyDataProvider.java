package com.prominente.android.vittal.data;

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
        //TODO: Mejorar esto
        ArrayList<Sale> sales = new ArrayList<Sale>();
        sales.addAll(Sale.listAll(Sale.class));
        return sales;
    }
}
