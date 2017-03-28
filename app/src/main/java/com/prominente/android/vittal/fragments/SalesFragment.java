package com.prominente.android.vittal.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.adapters.SalesRvAdapter;
import com.prominente.android.vittal.dataprovider.DummyDataProvider;

public class SalesFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_sales, container, false);

        RecyclerView rv_sales = (RecyclerView) rootView.findViewById(R.id.rv_sales);
        rv_sales.setLayoutManager(new LinearLayoutManager(getContext()));

        SalesRvAdapter adapter = new SalesRvAdapter(getActivity());
        rv_sales.setAdapter(adapter);

        adapter.addAll(DummyDataProvider.getInstance().getSales());

        FloatingActionButton fab_sales_add = (FloatingActionButton) rootView.findViewById(R.id.fab_sales_add);
        fab_sales_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
            }
        });

        return rootView;
    }
}
