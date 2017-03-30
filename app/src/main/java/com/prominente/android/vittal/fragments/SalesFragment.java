package com.prominente.android.vittal.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.activities.NewSaleFormActivity;
import com.prominente.android.vittal.adapters.SalesRvAdapter;
import com.prominente.android.vittal.constants.ExtraKeys;
import com.prominente.android.vittal.constants.RequestCodes;
import com.prominente.android.vittal.dataprovider.DummyDataProvider;
import com.prominente.android.vittal.model.Sale;

public class SalesFragment extends Fragment
{
    private SalesRvAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_sales, container, false);

        RecyclerView rv_sales = (RecyclerView) rootView.findViewById(R.id.rv_sales);
        rv_sales.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_sales.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new SalesRvAdapter(getActivity());
        rv_sales.setAdapter(adapter);

        adapter.addAll(DummyDataProvider.getInstance().getSales());

        FloatingActionButton fab_sales_add = (FloatingActionButton) rootView.findViewById(R.id.fab_sales_add);
        fab_sales_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startNewSaleForm();
            }
        });

        return rootView;
    }

    private void startNewSaleForm()
    {
        Intent intent = new Intent(getContext(), NewSaleFormActivity.class);
        startActivityForResult(intent, RequestCodes.REQUEST_NEW_SALE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case RequestCodes.REQUEST_NEW_SALE:
                    Sale sale = (Sale) data.getSerializableExtra(ExtraKeys.SALE);
                    adapter.add(sale);
                    break;

                default:
                    super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
