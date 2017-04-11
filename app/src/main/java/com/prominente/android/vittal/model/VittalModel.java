package com.prominente.android.vittal.model;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo Poza on 10/4/2017.
 */

public class VittalModel extends SugarRecord {


    public static <T> List<T> find(Class<T> type, String whereClause, String... whereArgs) {
        List<T> ts = find(type, whereClause, whereArgs, null, null, null);
        if(ts  == null || ts.size() == 0) {
            ArrayList<T> ts1 = new ArrayList<>();
            ts1.add(null);
            return ts1;
        }

        return ts;
    }
}
