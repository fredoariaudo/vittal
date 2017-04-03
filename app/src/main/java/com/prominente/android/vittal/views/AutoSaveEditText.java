package com.prominente.android.vittal.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;

/**
 * Created by Pablo Poza on 3/4/2017.
 */

public class AutoSaveEditText extends android.support.v7.widget.AppCompatEditText {


    public AutoSaveEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putString("text",getText().toString());
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            setText(bundle.getString("text"));
        }
        super.onRestoreInstanceState(state);
    }

}
