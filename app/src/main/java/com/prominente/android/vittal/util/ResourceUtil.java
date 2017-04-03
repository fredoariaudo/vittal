package com.prominente.android.vittal.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.TypedValue;

public class ResourceUtil
{
    public static int getThemedColor(Context context, int attr)
    {
        TypedValue typedValue = new TypedValue();
        TypedArray styledAttrs = context.obtainStyledAttributes(typedValue.data, new int[] { attr });
        int color = styledAttrs.getColor(0, 0);
        styledAttrs.recycle();
        return color;
    }

    public static int getResourceIdFromAttr(Resources.Theme theme, int attr)
    {
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(attr, typedValue, true);
        int id = typedValue.resourceId;
        return id;
    }
}
