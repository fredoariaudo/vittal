package com.prominente.android.vittal.model;

import com.orm.SugarRecord;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo Poza on 10/4/2017.
 */

public class VittalModel extends SugarRecord implements Serializable{


    public static <T> List<T> find(Class<T> type, String whereClause, String... whereArgs) {
        List<T> ts = find(type, whereClause, whereArgs, null, null, null);
        if(ts  == null || ts.size() == 0) {
            ArrayList<T> ts1 = new ArrayList<>();
            ts1.add(null);
            return ts1;
        }

        return ts;
    }


    private void writeObject(ObjectOutputStream o)
            throws IOException {

        o.defaultWriteObject();
        o.writeLong(getId());
    }

    private void readObject(ObjectInputStream o)
            throws IOException, ClassNotFoundException {

        o.defaultReadObject();
        setId(o.readLong());
    }

}
