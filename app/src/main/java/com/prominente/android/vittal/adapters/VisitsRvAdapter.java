package com.prominente.android.vittal.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.model.Visit;
import com.prominente.android.vittal.util.ResourceUtil;

public class VisitsRvAdapter extends FilterableRvAdapter<Visit>
{
    private RvAdapterListener rvAdapterListener;

    public class VisitViewHolder extends SelectableViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        TextView tv_visit_business_name;
        TextView tv_visit_address;

        public VisitViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            tv_visit_business_name = (TextView) itemView.findViewById(R.id.tv_visit_business_name);
            tv_visit_address = (TextView) itemView.findViewById(R.id.tv_visit_address);
        }

        @Override
        protected int getDefaultBackground()
        {
            return ResourceUtil.getResourceIdFromAttr(itemView.getContext().getTheme(), android.R.attr.selectableItemBackground);
        }

        @Override
        protected int getActivatedBackground()
        {
            return R.drawable.rv_item_activated;
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

    public VisitsRvAdapter(RvAdapterListener rvAdapterListener)
    {
        this.rvAdapterListener = rvAdapterListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.visit_rv_item, parent, false);
        return new VisitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        Visit visit = getItems().get(position);
        VisitViewHolder visitViewHolder = (VisitViewHolder) holder;
        visitViewHolder.setSelected(isSelected(position));
        visitViewHolder.tv_visit_business_name.setText(visit.getBusinessName());
        visitViewHolder.tv_visit_address.setText(visit.getAddress());
    }

    @Override
    public boolean containsFilter(Visit visit, CharSequence constraint)
    {
        return visit.getBusinessName() != null && visit.getBusinessName().toLowerCase().contains(constraint.toString().toLowerCase())
                || visit.getAddress() != null && visit.getAddress().toLowerCase().contains(constraint.toString().toLowerCase());
    }
}
