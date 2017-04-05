package com.prominente.android.vittal.adapters;

import android.view.View;

public interface RvAdapterListener
{
    void onItemClick(View v, int itemPosition, int layoutPosition);
    boolean onItemLongClick(View v, int itemPosition, int layoutPosition);
}
