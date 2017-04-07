package com.prominente.android.vittal.adapters;

import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

public abstract class FilterableRvAdapter<T> extends SelectableRvAdapter<T> implements Filterable
{
    private ArrayList<T> originalItems = new ArrayList<T>();
    private ArrayList<T> filteredItems = new ArrayList<T>();
    private Filter filter;

    @Override
    public void add(T item)
    {
        super.add(item);
        originalItems.add(item);
    }

    @Override
    public void add(int position, T item)
    {
        throw new UnsupportedOperationException("Must call add(int itemPosition, int layoutPosition, T item) for FilterableRvAdapter to ensure correct add position when items are filtered");
    }

    public void add(int itemPosition, int layoutPosition, T item)
    {
        super.add(layoutPosition, item);

        if(filteredItems.size() > 0)
        {
            originalItems.add(itemPosition, item);
        }
        else
        {
            originalItems.add(layoutPosition, item);
        }
    }

    @Override
    public void addAll(ArrayList<T> items)
    {
        super.addAll(items);
        originalItems.addAll(items);
    }

    @Override
    public void set(int position, T item)
    {
        super.set(position, item);

        if(filteredItems.size() > 0)
        {
            originalItems.set(originalItems.indexOf(item), item);
        }
        else
        {
            originalItems.set(position, item);
        }
    }

    @Override
    public T remove(int position)
    {
        T item = super.remove(position);

        if(filteredItems.size() > 0)
        {
            if(position < filteredItems.size())
                originalItems.remove(filteredItems.get(position));
        }
        else
        {
            if(originalItems.size() > 0)
                originalItems.remove(position);
        }
        return item;
    }

    public int originalIndexOf(T item)
    {
        return originalItems.indexOf(item);
    }

    @Override
    public Filter getFilter()
    {
        if(filter == null)
        {
            filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint)
                {
                    filteredItems.clear();
                    FilterResults result = new FilterResults();

                    if(constraint == null || constraint.equals(""))
                    {
                        result.count = originalItems.size();
                        result.values = originalItems;
                        return result;
                    }

                    for(int i=0; i<originalItems.size(); i++)
                    {
                        T item = originalItems.get(i);
                        if(containsFilter(item, constraint))
                            filteredItems.add(item);
                    }

                    result.count = filteredItems.size();
                    result.values = filteredItems;
                    return result;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results)
                {
                    getItems().clear();
                    getItems().addAll((ArrayList<T>) results.values);
                    notifyDataSetChanged();
                }
            };
        }
        return filter;
    }

    //Function to determine if elements match filter
    public abstract boolean containsFilter(T item, CharSequence constraint);
}
