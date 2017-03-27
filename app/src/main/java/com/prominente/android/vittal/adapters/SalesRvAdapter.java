package com.prominente.android.vittal.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.model.Sale;

public class SalesRvAdapter extends ArrayRvAdapter<Sale>
{
    public class SaleViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_sale_title;

        public SaleViewHolder(View itemView)
        {
            super(itemView);
            tv_sale_title = (TextView) itemView.findViewById(R.id.tv_sale_title);
        }
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
        saleViewHolder.tv_sale_title.setText(sale.getClient());
    }
}
