package com.prominente.android.vittal.adapters;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class SelectableViewHolder extends RecyclerView.ViewHolder
{
    public SelectableViewHolder(View itemView)
    {
        super(itemView);
    }

    protected void setSelected(boolean isSelected)
    {
        itemView.setActivated(isSelected);
        changeBackgroundDrawable(isSelected);
    }

    protected void changeBackgroundDrawable(boolean isSelected)
    {
        int drawableId = isSelected ? getActivatedBackground() : getDefaultBackground();
        setBackgroundResource(itemView, drawableId);
        itemView.getBackground().jumpToCurrentState();
    }

    public static void setBackgroundResource(View view, int resId)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
        {
            int paddingTop = view.getPaddingTop();
            int paddingLeft = view.getPaddingLeft();
            int paddingRight = view.getPaddingRight();
            int paddingBottom = view.getPaddingBottom();
            view.setBackgroundResource(resId);
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
        else
        {
            view.setBackgroundResource(resId);
        }
    }

    protected abstract int getDefaultBackground();
    protected abstract int getActivatedBackground();
}
