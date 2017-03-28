package com.prominente.android.vittal.adapters;

import android.app.Activity;
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
import com.prominente.android.vittal.model.Sale;

public class SalesRvAdapter extends SelectableRvAdapter<Sale>
{
    private Activity parentActivity;
    private ActionMode actionMode;
    private ActionModeCallback actionModeCallback;

    public class SaleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        TextView tv_sale_title;

        public SaleViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            tv_sale_title = (TextView) itemView.findViewById(R.id.tv_sale_title);
        }

        @Override
        public void onClick(View v)
        {
            if(getSelectedItemCount() > 0)
                toggleSelection(getLayoutPosition(), getLayoutPosition());
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

            toggleSelection(getLayoutPosition(), getLayoutPosition());
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
    }

    @Override
    public void toggleSelection(int itemPosition, int layoutPosition)
    {
        super.toggleSelection(itemPosition, layoutPosition);

        if(getSelectedItemCount() == 0)
            actionMode.finish();
        else
            actionMode.invalidate();
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
            if(getSelectedItemCount() > 1)
                menu.findItem(R.id.action_sales_edit).setVisible(false);
            else
                menu.findItem(R.id.action_sales_edit).setVisible(true);

            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item)
        {
            switch(item.getItemId())
            {
                case R.id.action_sales_delete:
                    clearSelection();
                    mode.finish();
                    return true;

                case R.id.action_sales_edit:
                    clearSelection();
                    mode.finish();
                    return true;

                case R.id.action_sales_send:
                    clearSelection();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode)
        {
            clearSelection();
            actionMode.finish();
        }
    }
}
