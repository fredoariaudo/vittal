package com.prominente.android.vittal.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private static int newItemId = 1000;

    private SalesRvAdapter adapter;
    private FloatingActionButton fab_sales_add;

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

        fab_sales_add = (FloatingActionButton) rootView.findViewById(R.id.fab_sales_add);
        fab_sales_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startNewSaleForm();
            }
        });

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.sales, menu);
        MenuItem searchItem = menu.findItem(R.id.action_sales_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                if(adapter !=null)
                {
                    adapter.getFilter().filter(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                if(adapter !=null)
                {
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        //Hide FAB when searchView is expanded
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item)
            {
                fab_sales_add.hide();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item)
            {
                fab_sales_add.show();
                return true;
            }
        });
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
                    //TODO: Revisar esto despues, se coloca un id al nuevo elemento
                    sale.setId(newItemId++);
                    adapter.add(sale);
                    break;

                default:
                    super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
