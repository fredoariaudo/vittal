package com.prominente.android.vittal.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.prominente.android.vittal.constants.RequestCodes;
import com.prominente.android.vittal.model.Visit;

import java.util.ArrayList;
import java.util.List;

public class VisitsFragment extends Fragment implements RvAdapterListener
{
    private VisitsRvAdapter adapter;
    private ProgressBar pb_visits;

    private VisitsLoadTask visitsLoadTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_visits, container, false);

        RecyclerView rv_visits = (RecyclerView) rootView.findViewById(R.id.rv_vists);
        rv_visits.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_visits.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new VisitsRvAdapter(this);
        rv_visits.setAdapter(adapter);

        pb_visits = (ProgressBar) rootView.findViewById(R.id.pb_visits);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_visits_add);
        fab.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case RequestCodes.REQUEST_NEW_VISIT:
                    break;

                case RequestCodes.REQUEST_MODIFY_VISIT:
                    break;

                default:
                    super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onItemClick(View v, int itemPosition, int layoutPosition)
    {
    }

    @Override
    public boolean onItemLongClick(View v, int itemPosition, int layoutPosition)
    {
        return false;
    }

    private void startVisitForm()
    {
        Intent intent = new Intent(getContext(), VisitFormActivity.class);
        startActivityForResult(intent, RequestCodes.REQUEST_NEW_VISIT);
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
            return new ArrayList<Visit>();
        }

        @Override
        protected void onPostExecute(List<Visit> visits)
        {
            adapter.addAll(visits);
            pb_visits.setVisibility(View.GONE);
        }
    }
}
