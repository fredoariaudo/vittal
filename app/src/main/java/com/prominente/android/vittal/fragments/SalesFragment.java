package com.prominente.android.vittal.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
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
import com.prominente.android.vittal.adapters.RvAdapterListener;
import com.prominente.android.vittal.constants.ExtraKeys;
import com.prominente.android.vittal.constants.RequestCodes;
import com.prominente.android.vittal.dataprovider.DummyDataProvider;
import com.prominente.android.vittal.model.Sale;

import java.util.List;

public class SalesFragment extends Fragment implements RvAdapterListener
{
    private static int newItemId = 1000;

    private View rootView;
    private SalesRvAdapter adapter;
    private FloatingActionButton fab_sales_add;
    private ActionMode actionMode;
    private ActionModeCallback actionModeCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_sales, container, false);

        RecyclerView rv_sales = (RecyclerView) rootView.findViewById(R.id.rv_sales);
        rv_sales.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_sales.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new SalesRvAdapter(this);
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

    @Override
    public void onItemClick(View v, int itemPosition, int layoutPosition)
    {
        if(adapter.getSelectedItemCount() > 0)
        {
            toggleSelection(itemPosition, layoutPosition, true);
        }
        else
        {
            Intent intent = new Intent(getContext(), NewSaleFormActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onItemLongClick(View v, int itemPosition, int layoutPosition)
    {
        if(adapter.getSelectedItemCount() <= 0 && getActivity() instanceof AppCompatActivity)
        {
            if(actionModeCallback == null)
                actionModeCallback = new ActionModeCallback();

            actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
        }

        toggleSelection(itemPosition, layoutPosition, true);
        return true;
    }

    public void toggleSelection(int itemPosition, int layoutPosition, boolean notifyChange)
    {
        adapter.toggleSelection(itemPosition, layoutPosition, notifyChange);

        if(notifyChange)
        {
            if (adapter.getSelectedItemCount() == 0)
                actionMode.finish();
            else
                actionMode.invalidate();
        }
    }

    private void removeSelected()
    {
        final List<Integer> selectedItems = adapter.getSelectedItems(true, true);

        for(int selectedItem: selectedItems)
        {
            //TODO: Remove item from actual data
            Sale sale = adapter.remove(selectedItem);
        }
    }

    private void startNewSaleForm()
    {
        Intent intent = new Intent(getContext(), NewSaleFormActivity.class);
        startActivityForResult(intent, RequestCodes.REQUEST_NEW_SALE);
    }

    private class ActionModeCallback implements ActionMode.Callback
    {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu)
        {
            mode.getMenuInflater().inflate(R.menu.sales_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu)
        {
            //Remove edit action if more than one item is selected
            if(adapter.getSelectedItemCount() > 1)
                menu.findItem(R.id.action_sales_edit).setVisible(false);
            else
                menu.findItem(R.id.action_sales_edit).setVisible(true);

            //Change select/unselect all action text and icon
            if(adapter.getSelectedItemCount() == adapter.getItemCount())
            {
                menu.findItem(R.id.action_select_unselect_all).setTitle(getString(R.string.unselect_all));
                menu.findItem(R.id.action_select_unselect_all).setIcon(R.drawable.ic_check_box_outline_blank_white_24dp);
            }
            else
            {
                menu.findItem(R.id.action_select_unselect_all).setTitle(getString(R.string.select_all));
                menu.findItem(R.id.action_select_unselect_all).setIcon(R.drawable.ic_check_box_white_24dp);
            }

            //Show selected items count
            mode.setTitle(String.valueOf(adapter.getSelectedItemCount()));

            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item)
        {
            switch(item.getItemId())
            {
                case R.id.action_sales_delete:
                    removeSelected();
                    mode.finish();
                    return true;

                case R.id.action_sales_edit:
                    adapter.clearSelection(true);
                    mode.finish();
                    Intent intent = new Intent(getContext(), NewSaleFormActivity.class);
                    startActivity(intent);
                    return true;

                case R.id.action_sales_send:
                    adapter.clearSelection(true);
                    mode.finish();
                    return true;

                case R.id.action_select_unselect_all:
                    //Verify if has to select all or deselect all
                    if(item.getTitle().equals(getString(R.string.select_all)))
                    {
                        adapter.clearSelection(false);
                        for(int i=0; i<adapter.getItemCount(); i++)
                        {
                            toggleSelection(i,i,false);
                        }
                        actionMode.invalidate();
                    }
                    else
                    {
                        adapter.clearSelection(false);
                        actionMode.finish();
                    }
                    adapter.notifyDataSetChanged();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode)
        {
            adapter.clearSelection(true);
            actionMode.finish();
        }
    }
}
