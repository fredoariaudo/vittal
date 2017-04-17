package com.prominente.android.vittal.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.data.FormData;
import com.prominente.android.vittal.model.Sale;
import com.prominente.android.vittal.util.ResourceUtil;

public class SalesRvAdapter extends FilterableRvAdapter<Sale>
{
    private RvAdapterListener rvAdapterListener;

    public class SaleViewHolder extends SelectableViewHolder implements View.OnClickListener, View.OnLongClickListener
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
        protected int getDefaultBackground()
        {
            return ResourceUtil.getResourceIdFromAttr(itemView.getContext().getTheme(), android.R.attr.selectableItemBackground);
        }

        @Override
        protected int getActivatedBackground()
        {
            return R.drawable.sale_item_activated;
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
        saleViewHolder.setSelected(isSelected(position));
        saleViewHolder.tv_sale_title.setText(sale.getApplicantForm().getRazonSocial());
        saleViewHolder.tv_sale_area_address.setText(String.format(saleViewHolder.itemView.getContext().getResources().getString(R.string.sales_rv_item_area_address_format), FormData.getCoverages()[sale.getApplicantForm().getPlan()], sale.getApplicantForm().getAddress()));
    }

    @Override
    public boolean containsFilter(Sale sale, CharSequence constraint)
    {
        return sale.getApplicantForm().getRazonSocial() !=null && sale.getApplicantForm().getRazonSocial().toLowerCase().contains(constraint.toString().toLowerCase())
                || sale.getApplicantForm().getAddress() !=null && sale.getApplicantForm().getAddress().toLowerCase().contains(constraint.toString().toLowerCase());

    }
}
