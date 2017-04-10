package com.prominente.android.vittal.application;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by Pablo Poza on 6/4/2017.
 */

public class VittalApplication extends Application {

    private static VittalApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        VittalApplication.application = this;
    }

    public static VittalApplication getApplication() {
        return VittalApplication.application;
    }

}
