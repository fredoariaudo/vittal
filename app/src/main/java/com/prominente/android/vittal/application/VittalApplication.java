package com.prominente.android.vittal.application;

import android.app.Application;
/**
 * Created by Pablo Poza on 6/4/2017.
 */

public class VittalApplication extends Application {

    private static VittalApplication application;

    @Override
    public void onCreate() {
        super.onCreate();

        VittalApplication.application = this;
    }

    public static VittalApplication getApplication() {
        return VittalApplication.application;
    }

}
