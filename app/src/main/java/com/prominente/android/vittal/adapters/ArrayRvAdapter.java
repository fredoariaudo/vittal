package com.prominente.android.vittal.adapters;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ArrayRvAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private ArrayList<T> items = new ArrayList<T>();

    public void add(T item)
    {
        this.items.add(item);
        notifyItemInserted(this.items.size() - 1);
    }

    public void add(int position, T item)
    {
        this.items.add(position, item);
        notifyItemInserted(position);
    }

    public void addAll(Collection<T> items)
    {
        this.items.addAll(items);
        notifyItemRangeInserted(this.items.size(), items.size());
    }

    public void set(int position, T item)
    {
        this.items.set(position, item);
        notifyItemChanged(position);
    }

    public T remove(int position)
    {
        T item = this.items.remove(position);
        notifyItemRemoved(position);
        return item;
    }

    public void clear(boolean notifyClear)
    {
        this.items.clear();

        if(notifyClear)
            notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public ArrayList<T> getItems()
    {
        return items;
    }
}
