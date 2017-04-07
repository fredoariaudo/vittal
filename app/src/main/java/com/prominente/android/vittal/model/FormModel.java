package com.prominente.android.vittal.model;


import android.databinding.BaseObservable;

import com.prominente.android.vittal.application.VittalApplication;
import com.prominente.android.vittal.interfaces.Formeable;

import java.io.Serializable;

/**
 * Created by Pablo Poza on 4/4/2017.
 */

public abstract class FormModel extends BaseObservable implements Formeable,Serializable {

    private static final long serialVersionUID = 7526472295622776147L;
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}