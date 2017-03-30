package com.prominente.android.vittal.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.activities.NewSaleFormActivity;
import com.prominente.android.vittal.model.Sale;

import java.util.List;

public class SalesRvAdapter extends SelectableRvAdapter<Sale>
{
    private Activity parentActivity;
    private ActionMode actionMode;
    private ActionModeCallback actionModeCallback;

    public class SaleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        TextView tv_sale_title;
        TextView tv_sale_area_address;

        public SaleViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            tv_sale_title = (TextView) itemView.findViewById(R.id.tv_sale_title);
            tv_sale_area_address = (TextView) itemView.findViewById(R.id.tv_sale_area_address);
        }

        @Override
        public void onClick(View v)
        {
            if(getSelectedItemCount() > 0)
            {
                toggleSelection(getLayoutPosition(), getLayoutPosition(), true);
            }
            else
            {
                Intent intent = new Intent(v.getContext(), NewSaleFormActivity.class);
                v.getContext().startActivity(intent);
            }
        }

        @Override
        public boolean onLongClick(View v)
        {
            if(getSelectedItemCount() <= 0 && parentActivity instanceof AppCompatActivity)
            {
                if(actionModeCallback == null)
                    actionModeCallback = new ActionModeCallback();

                actionMode = ((AppCompatActivity) parentActivity).startSupportActionMode(actionModeCallback);
            }

            toggleSelection(getLayoutPosition(), getLayoutPosition(), true);
            return true;
        }
    }

    public SalesRvAdapter(Activity parentActivity)
    {
        this.parentActivity = parentActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.sale_rv_item, parent, false);
        return new SaleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        Sale sale = getItems().get(position);
        SaleViewHolder saleViewHolder = (SaleViewHolder) holder;
        saleViewHolder.itemView.setActivated(isSelected(position));
        saleViewHolder.tv_sale_title.setText(sale.getClient());
        saleViewHolder.tv_sale_area_address.setText(String.format(saleViewHolder.itemView.getContext().getResources().getString(R.string.sales_rv_item_area_address_format), sale.getArea(), sale.getAddress()));
    }

    @Override
    public void toggleSelection(int itemPosition, int layoutPosition, boolean notifyChange)
    {
        super.toggleSelection(itemPosition, layoutPosition, notifyChange);

        if(notifyChange)
        {
            if (getSelectedItemCount() == 0)
                actionMode.finish();
            else
                actionMode.invalidate();
        }
    }

    private void removeSelected()
    {
        List<Integer> selectedItems = getSelectedItems(true, true);

        for(Integer selectedItem: selectedItems)
        {
            Sale sale = getItems().remove(selectedItem.intValue());
            notifyItemRemoved(selectedItem.intValue());
            //TODO: Remove item from actual data
        }
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
            if(getSelectedItemCount() > 1)
                menu.findItem(R.id.action_sales_edit).setVisible(false);
            else
                menu.findItem(R.id.action_sales_edit).setVisible(true);

            //Change select/unselect all action text and icon
            if(getSelectedItemCount() == getItemCount())
            {
                menu.findItem(R.id.action_select_unselect_all).setTitle(parentActivity.getString(R.string.unselect_all));
                menu.findItem(R.id.action_select_unselect_all).setIcon(R.drawable.ic_check_box_outline_blank_white_24dp);
            }
            else
            {
                menu.findItem(R.id.action_select_unselect_all).setTitle(parentActivity.getString(R.string.select_all));
                menu.findItem(R.id.action_select_unselect_all).setIcon(R.drawable.ic_check_box_white_24dp);
            }

            //Show selected items count
            mode.setTitle(String.valueOf(getSelectedItemCount()));

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
                    clearSelection(true);
                    mode.finish();
                    Intent intent = new Intent(parentActivity, NewSaleFormActivity.class);
                    parentActivity.startActivity(intent);
                    return true;

                case R.id.action_sales_send:
                    clearSelection(true);
                    mode.finish();
                    return true;

                case R.id.action_select_unselect_all:
                    //Verify if has to select all or deselect all
                    if(item.getTitle().equals(parentActivity.getString(R.string.select_all)))
                    {
                        clearSelection(false);
                        for(int i=0; i<getItemCount(); i++)
                        {
                            toggleSelection(i,i,false);
                        }
                        actionMode.invalidate();
                    }
                    else
                    {
                        clearSelection(false);
                        actionMode.finish();
                    }
                    notifyDataSetChanged();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode)
        {
            clearSelection(true);
            actionMode.finish();
        }
    }
}
