package com.prominente.android.vittal.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.prominente.android.vittal.model.DaoMaster;
import com.prominente.android.vittal.model.DaoSession;

/**
 * Created by Pablo Poza on 6/4/2017.
 */

public class VittalApplication extends Application {

    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        helper = new DaoMaster.DevOpenHelper(this, "vittal-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }
}
