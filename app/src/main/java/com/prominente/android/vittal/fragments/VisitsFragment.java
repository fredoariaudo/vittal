package com.prominente.android.vittal.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.activities.VisitFormActivity;
import com.prominente.android.vittal.adapters.RvAdapterListener;
import com.prominente.android.vittal.adapters.VisitsRvAdapter;
import com.prominente.android.vittal.constants.ExtraKeys;
import com.prominente.android.vittal.constants.IntentActions;
import com.prominente.android.vittal.constants.RequestCodes;
import com.prominente.android.vittal.model.Visit;

import java.util.ArrayList;
import java.util.List;

public class VisitsFragment extends Fragment implements RvAdapterListener
{
    private final static int ADAPTER_UPDATE_POST_DELAY = 200;

    private View rootView;
    private RecyclerView rv_visits;
    private LinearLayoutManager rvLayoutManager;
    private VisitsRvAdapter adapter;
    private ProgressBar pb_visits;
    private FloatingActionButton fab_visits_add;
    private ActionMode actionMode;
    private ActionModeCallback actionModeCallback;
    private SearchView searchView;

    private VisitsLoadTask visitsLoadTask;

    private VisitFragmentListener visitFragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_visits, container, false);

        rv_visits = (RecyclerView) rootView.findViewById(R.id.rv_vists);
        rvLayoutManager = new LinearLayoutManager(getContext());
        rv_visits.setLayoutManager(rvLayoutManager);
        rv_visits.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new VisitsRvAdapter(this);
        rv_visits.setAdapter(adapter);

        pb_visits = (ProgressBar) rootView.findViewById(R.id.pb_visits);

        fab_visits_add = (FloatingActionButton) rootView.findViewById(R.id.fab_visits_add);
        fab_visits_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startVisitForm();
            }
        });

        setHasOptionsMenu(true);

        visitsLoadTask = new VisitsLoadTask();
        visitsLoadTask.execute();

        return rootView;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if(getActivity() instanceof VisitFragmentListener)
        {
            visitFragmentListener = (VisitFragmentListener) getActivity();
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();

        if(visitsLoadTask != null)
            visitsLoadTask.cancel(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.visits, menu);
        MenuItem searchItem = menu.findItem(R.id.action_visits_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                if(adapter !=null)
                {
                    adapter.getFilter().filter(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                if(adapter !=null)
                {
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        //Hide FAB when searchView is expanded
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item)
            {
                fab_visits_add.hide();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item)
            {
                fab_visits_add.show();
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == Activity.RESULT_OK)
        {
            Visit visit = (Visit) data.getSerializableExtra(ExtraKeys.VISIT);

            switch (requestCode)
            {
                case RequestCodes.REQUEST_NEW_ITEM:
                    add(visit);
                    break;

                case RequestCodes.REQUEST_MODIFY_ITEM:
                    String action = data.getAction();
                    if(action.equals(IntentActions.ACTION_SAVE))
                    {
                        save(visit);
                    }
                    else if(action.equals(IntentActions.ACTION_DELETE))
                    {
                        remove(visit);
                    }
                    else if(action.equals(IntentActions.ACTION_TURN_INTO_SALE))
                    {
                        visitFragmentListener.onTurnIntoSale();
                    }
                    break;

                default:
                    super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onItemClick(View v, int itemPosition, int layoutPosition)
    {
        if(adapter.getSelectedItemCount() > 0)
        {
            toggleSelection(itemPosition, layoutPosition, true);
        }
        else
        {
            Intent intent = new Intent(getContext(), VisitFormActivity.class);
            Visit visit = adapter.getItems().get(itemPosition);
            intent.putExtra(ExtraKeys.VISIT, visit);
            startActivityForResult(intent, RequestCodes.REQUEST_MODIFY_ITEM);
        }
    }

    @Override
    public boolean onItemLongClick(View v, int itemPosition, int layoutPosition)
    {
        if(adapter.getSelectedItemCount() <= 0 && getActivity() instanceof AppCompatActivity)
        {
            if(actionModeCallback == null)
                actionModeCallback = new ActionModeCallback();

            actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
        }

        toggleSelection(itemPosition, layoutPosition, true);
        return true;
    }

    public void toggleSelection(int itemPosition, int layoutPosition, boolean notifyChange)
    {
        adapter.toggleSelection(itemPosition, layoutPosition, notifyChange);

        if(notifyChange)
        {
            if (adapter.getSelectedItemCount() == 0)
                actionMode.finish();
            else
                actionMode.invalidate();
        }
    }

    private void remove(final List<Integer> selectedItems)
    {
        //Save deleted items to restore on undo
        final ArrayList<Visit> deletedItems = new ArrayList<Visit>();
        //Save deleted items original indexes to restore on undo
        final ArrayList<Integer> deletedItemsOriginalIndexes = new ArrayList<Integer>();
        //RecyclerView first item position
        final int actualScroll = rvLayoutManager.findFirstVisibleItemPosition();
        //RecyclerView actual scroll offset
        final int actualScrollOffset = rv_visits.getChildAt(0) == null ? 0 : (rv_visits.getChildAt(0).getTop() - rv_visits.getPaddingTop());

        for(int selectedItem: selectedItems)
        {
            Visit visit = adapter.getItems().get(selectedItem);
            deletedItems.add(visit);
            deletedItemsOriginalIndexes.add(adapter.originalIndexOf(visit));
            adapter.remove(selectedItem);
        }

        //Create and show Undo SnackBar
        Snackbar snackbar = Snackbar.make(rootView, getResources().getQuantityString(R.plurals.visits_deleted, selectedItems.size(), selectedItems.size()), Snackbar.LENGTH_SHORT);
        snackbar.setAction(R.string.undo, new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Reverse add to prevent IndexOutOfBoundsException
                for(int i=selectedItems.size()-1; i>=0; i--)
                {
                    adapter.add(deletedItemsOriginalIndexes.get(i), selectedItems.get(i), deletedItems.get(i));
                }
                //Restore scroll position to original before remove
                rvLayoutManager.scrollToPositionWithOffset(actualScroll, actualScrollOffset);
            }
        });
        snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event)
            {
                if(event != DISMISS_EVENT_ACTION) //Undo not pressed
                {
                    for(Visit deletedVisit: deletedItems)
                    {
                        //Remove item from DB if not undo is pressed
                        deletedVisit.delete();
                    }
                }
            }

            @Override
            public void onShown(Snackbar transientBottomBar)
            {
                super.onShown(transientBottomBar);
            }
        });
        snackbar.show();
    }

    private void edit(final List<Integer> selectedItems)
    {
        Visit visit = adapter.getItems().get(selectedItems.get(0));
        Intent intent = new Intent(getContext(), VisitFormActivity.class);
        intent.putExtra(ExtraKeys.VISIT, visit);
        startActivityForResult(intent, RequestCodes.REQUEST_MODIFY_ITEM);
    }

    private void add(Visit visit)
    {
        //Update adapter
        adapter.add(visit);
        Snackbar.make(rootView, R.string.visit_saved, Snackbar.LENGTH_SHORT).show();
    }

    private void save(Visit visit)
    {
        //Update adapter
        adapter.set(adapter.getItems().indexOf(visit), visit);
        Snackbar.make(rootView, R.string.visit_modified, Snackbar.LENGTH_SHORT).show();

        //Enqueue and delay filter reset to prevent itemChange animation corruption when update adapter
        rv_visits.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                //Filter again to consider modified item
                adapter.getFilter().filter(searchView.getQuery());
            }
        }, ADAPTER_UPDATE_POST_DELAY);
    }

    private void remove(Visit visit)
    {
        ArrayList<Integer> selectedItems = new ArrayList<Integer>();
        selectedItems.add(adapter.getItems().indexOf(visit));
        remove(selectedItems);
    }

    private void startVisitForm()
    {
        Intent intent = new Intent(getContext(), VisitFormActivity.class);
        startActivityForResult(intent, RequestCodes.REQUEST_NEW_ITEM);
    }

    private class ActionModeCallback implements ActionMode.Callback
    {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu)
        {
            mode.getMenuInflater().inflate(R.menu.visits_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu)
        {
            //Remove edit and turn into sale actions if more than one item is selected
            if(adapter.getSelectedItemCount() > 1)
                menu.findItem(R.id.action_visits_edit).setVisible(false);
            else
                menu.findItem(R.id.action_visits_edit).setVisible(true);

            //Change select/unselect all action text and icon
            if(adapter.getSelectedItemCount() == adapter.getItemCount())
            {
                menu.findItem(R.id.action_visits_select_unselect_all).setTitle(R.string.unselect_all);
                menu.findItem(R.id.action_visits_select_unselect_all).setIcon(R.drawable.ic_check_box_outline_blank_white_24dp);
            }
            else
            {
                menu.findItem(R.id.action_visits_select_unselect_all).setTitle(R.string.select_all);
                menu.findItem(R.id.action_visits_select_unselect_all).setIcon(R.drawable.ic_check_box_white_24dp);
            }

            //Show selected items count
            mode.setTitle(String.valueOf(adapter.getSelectedItemCount()));
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.action_visits_delete:
                    //Get selected items ordered in reverse order to prevent IndexOutOfBoundsException on delete
                    remove(adapter.getSelectedItems(true, true));
                    mode.finish();
                    return true;

                case R.id.action_visits_edit:
                    edit(adapter.getSelectedItems(true, false));
                    adapter.clearSelection(true);
                    mode.finish();
                    return true;

                case R.id.action_visits_select_unselect_all:
                    //Verify if has to select all or deselect all
                    if(item.getTitle().equals(getString(R.string.select_all)))
                    {
                        adapter.clearSelection(false);
                        for(int i=0; i<adapter.getItemCount(); i++)
                        {
                            toggleSelection(i,i,false);
                        }
                        actionMode.invalidate();
                    }
                    else
                    {
                        adapter.clearSelection(false);
                        actionMode.finish();
                    }
                    adapter.notifyDataSetChanged();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode)
        {
            adapter.clearSelection(true);
            actionMode.finish();
        }
    }

    private class VisitsLoadTask extends AsyncTask<Void, Integer, List<Visit>>
    {
        @Override
        protected void onPreExecute()
        {
            pb_visits.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Visit> doInBackground(Void... params)
        {
            return Visit.listAll(Visit.class);
        }

        @Override
        protected void onPostExecute(List<Visit> visits)
        {
            adapter.addAll(visits);
            pb_visits.setVisibility(View.GONE);
        }
    }

    public interface VisitFragmentListener
    {
        void onTurnIntoSale();
    }
}
