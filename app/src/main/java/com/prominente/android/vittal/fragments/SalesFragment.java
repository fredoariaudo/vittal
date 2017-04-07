package com.prominente.android.vittal.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.prominente.android.vittal.constants.IntentActions;
import com.prominente.android.vittal.constants.RequestCodes;
import com.prominente.android.vittal.dataprovider.DummyDataProvider;
import com.prominente.android.vittal.model.Sale;

import java.util.ArrayList;
import java.util.List;

public class SalesFragment extends Fragment implements RvAdapterListener
{
    private static int newItemId = 1000;

    private View rootView;
    private RecyclerView rv_sales;
    private LinearLayoutManager rvLayoutManager;
    private SalesRvAdapter adapter;
    private FloatingActionButton fab_sales_add;
    private ActionMode actionMode;
    private ActionModeCallback actionModeCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_sales, container, false);

        rv_sales = (RecyclerView) rootView.findViewById(R.id.rv_sales);
        rvLayoutManager = new LinearLayoutManager(getContext());
        rv_sales.setLayoutManager(rvLayoutManager);
        rv_sales.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new SalesRvAdapter(this);
        rv_sales.setAdapter(adapter);

        adapter.addAll(DummyDataProvider.getInstance().getSales());

        fab_sales_add = (FloatingActionButton) rootView.findViewById(R.id.fab_sales_add);
        fab_sales_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startSaleForm();
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
            Sale sale = (Sale) data.getSerializableExtra(ExtraKeys.SALE);

            switch (requestCode)
            {
                case RequestCodes.REQUEST_NEW_SALE:
                    add(sale);
                    break;

                case RequestCodes.REQUEST_MODIFY_SALE:
                    String action = data.getAction();
                    if(action.equals(IntentActions.ACTION_SAVE))
                    {
                        save(sale);
                    }
                    else if(action.equals(IntentActions.ACTION_DELETE))
                    {
                        remove(sale);
                    }
                    else if(action.equals(IntentActions.ACTION_SEND))
                    {
                        send(sale);
                    }
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
            Sale sale = adapter.getItems().get(itemPosition);
            intent.putExtra(ExtraKeys.SALE, sale);
            startActivityForResult(intent, RequestCodes.REQUEST_MODIFY_SALE);
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

    private void remove(final List<Integer> selectedItems)
    {
        //Save deleted items to restore on undo
        final ArrayList<Sale> deletedItems = new ArrayList<Sale>();
        //Save deleted items original indexes to restore on undo
        final ArrayList<Integer> deletedItemsOriginalIndexes = new ArrayList<Integer>();
        //RecyclerView first item position
        final int actualScroll = rvLayoutManager.findFirstVisibleItemPosition();
        //RecyclerView actual scroll offset
        final int actualScrollOffset = rv_sales.getChildAt(0) == null ? 0 : (rv_sales.getChildAt(0).getTop() - rv_sales.getPaddingTop());

        for(int selectedItem: selectedItems)
        {
            Sale sale = adapter.getItems().get(selectedItem);
            deletedItems.add(sale);
            deletedItemsOriginalIndexes.add(adapter.originalIndexOf(sale));
            adapter.remove(selectedItem);
        }

        //Create and show Undo SnackBar
        Snackbar snackbar = Snackbar.make(rootView, getResources().getQuantityString(R.plurals.sales_deleted, selectedItems.size(), selectedItems.size()), Snackbar.LENGTH_SHORT);
        snackbar.setAction(R.string.undo, new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Reverse add to prevent IndexOutOfBoundsException
                for(int i=selectedItems.size()-1; i>=0; i--)
                {
                    adapter.add(deletedItemsOriginalIndexes.get(i), selectedItems.get(i), deletedItems.get(i));
                }
                //Restore scroll position to original before remove
                rvLayoutManager.scrollToPositionWithOffset(actualScroll, actualScrollOffset);
            }
        });
        snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event)
            {
                if(event != DISMISS_EVENT_ACTION) //Undo not pressed
                {
                    //TODO: Remover items de la base de datos
                }
            }

            @Override
            public void onShown(Snackbar transientBottomBar)
            {
                super.onShown(transientBottomBar);
            }
        });
        snackbar.show();
    }

    private void edit(final List<Integer> selectedItems)
    {
        Sale sale = adapter.getItems().get(selectedItems.get(0));
        Intent intent = new Intent(getContext(), NewSaleFormActivity.class);
        intent.putExtra(ExtraKeys.SALE, sale);
        startActivityForResult(intent, RequestCodes.REQUEST_MODIFY_SALE);
    }

    private void send(final List<Integer> selectedItems)
    {
        for(int selectedItem: selectedItems)
        {
            //TODO: Ejecutar el servicio de envio de ventas
        }

        Snackbar.make(rootView, getResources().getQuantityString(R.plurals.sales_sent, selectedItems.size(), selectedItems.size()), Snackbar.LENGTH_SHORT).show();
    }

    private void add(Sale sale)
    {
        //TODO: Revisar esto despues, se coloca un id al nuevo elemento
        sale.setId(newItemId++);
        adapter.add(sale);
    }

    private void save(Sale sale)
    {
        //TODO: Revisar esto, aqui se modifica la venta
        adapter.set(adapter.getItems().indexOf(sale), sale);
    }

    private void remove(Sale sale)
    {
        ArrayList<Integer> selectedItems = new ArrayList<Integer>();
        selectedItems.add(adapter.getItems().indexOf(sale));
        remove(selectedItems);
    }

    private void send(Sale sale)
    {
        ArrayList<Integer> selectedItems = new ArrayList<Integer>();
        selectedItems.add(adapter.getItems().indexOf(sale));
        send(selectedItems);
    }

    private void startSaleForm()
    {
        Intent intent = new Intent(getContext(), NewSaleFormActivity.class);
        intent.putExtra(ExtraKeys.SALE, new Sale());
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
                menu.findItem(R.id.action_select_unselect_all).setTitle(R.string.unselect_all);
                menu.findItem(R.id.action_select_unselect_all).setIcon(R.drawable.ic_check_box_outline_blank_white_24dp);
            }
            else
            {
                menu.findItem(R.id.action_select_unselect_all).setTitle(R.string.select_all);
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
                    //Get selected items ordered in reverse order to prevent IndexOutOfBoundsException on delete
                    remove(adapter.getSelectedItems(true, true));
                    mode.finish();
                    return true;

                case R.id.action_sales_edit:
                    edit(adapter.getSelectedItems(true, false));
                    adapter.clearSelection(true);
                    mode.finish();
                    return true;

                case R.id.action_sales_send:
                    send(adapter.getSelectedItems(true, false));
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
