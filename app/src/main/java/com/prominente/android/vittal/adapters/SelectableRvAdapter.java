package com.prominente.android.vittal.adapters;

import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SelectableRvAdapter<T> extends ArrayRvAdapter<T>
{
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private SparseBooleanArray selectedViews = new SparseBooleanArray();

    public boolean isSelected(int position)
    {
        return getSelectedItems(false, false).contains(position);
    }

    public void toggleSelection(int itemPosition, int layoutPosition, boolean notifyChange)
    {
        if (selectedItems.get(itemPosition, false))
        {
            selectedItems.delete(itemPosition);
            selectedViews.delete(layoutPosition);
        }
        else
        {
            selectedItems.put(itemPosition, true);
            selectedViews.put(layoutPosition, true);
        }

        if(notifyChange)
            notifyItemChanged(layoutPosition);
    }

    public void clearSelection(boolean notifyChange)
    {
        List<Integer> selectionViews = getSelectedViews();

        selectedItems.clear();
        selectedViews.clear();

        if(notifyChange)
        {
            for (Integer position : selectionViews)
            {
                notifyItemChanged(position);
            }
        }
    }

    public int getSelectedItemCount()
    {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems(boolean sort, boolean reverse)
    {
        List<Integer> items = new ArrayList<Integer>(selectedItems.size());

        for (int i = 0; i <selectedItems.size(); ++i)
        {
            items.add(selectedItems.keyAt(i));
        }

        if(sort)
        {
            if(reverse)
                Collections.sort(items, Collections.reverseOrder());
            else
                Collections.sort(items);
        }

        return items;
    }

    public List<Integer> getSelectedViews()
    {
        List<Integer> views = new ArrayList<Integer>(selectedViews.size());

        for (int i = 0; i <selectedViews.size(); ++i)
        {
            views.add(selectedViews.keyAt(i));
        }

        return views;
    }
}
