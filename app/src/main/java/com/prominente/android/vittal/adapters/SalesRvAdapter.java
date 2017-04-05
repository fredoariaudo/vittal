package com.prominente.android.vittal.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.model.Sale;

public class SalesRvAdapter extends FilterableRvAdapter<Sale>
{
    private RvAdapterListener rvAdapterListener;

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
            rvAdapterListener.onItemClick(v, getLayoutPosition(), getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v)
        {
            return rvAdapterListener.onItemLongClick(v, getLayoutPosition(), getLayoutPosition());
        }
    }

    public SalesRvAdapter(RvAdapterListener rvAdapterListener)
    {
        this.rvAdapterListener = rvAdapterListener;
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
        saleViewHolder.itemView.setSelected(isSelected(position));
        saleViewHolder.tv_sale_title.setText(sale.getClient());
        saleViewHolder.tv_sale_area_address.setText(String.format(saleViewHolder.itemView.getContext().getResources().getString(R.string.sales_rv_item_area_address_format), sale.getArea(), sale.getAddress()));
    }

    @Override
    public boolean containsFilter(Sale sale, CharSequence constraint)
    {
        if(sale.getClient().toLowerCase().contains(constraint.toString().toLowerCase()))
        {
            return true;
        }

        return false;
    }
}
