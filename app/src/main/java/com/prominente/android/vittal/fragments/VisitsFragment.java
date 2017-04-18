package com.prominente.android.vittal.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.prominente.android.vittal.R;
import com.prominente.android.vittal.activities.VisitFormActivity;
import com.prominente.android.vittal.constants.RequestCodes;
import com.prominente.android.vittal.model.Visit;

import java.util.List;

public class VisitsFragment extends Fragment
{
    private ProgressBar pb_visits;

    private VisitsLoadTask visitsLoadTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_visits, container, false);

        RecyclerView rv_visits = (RecyclerView) rootView.findViewById(R.id.rv_vists);
        rv_visits.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_visits.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        pb_visits = (ProgressBar) rootView.findViewById(R.id.pb_visits);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_visits_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startVisitForm();
            }
        });

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
            return null;
        }

        @Override
        protected void onPostExecute(List<Visit> visits)
        {
            pb_visits.setVisibility(View.GONE);
        }
    }
}
