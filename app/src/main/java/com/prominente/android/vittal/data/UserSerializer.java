package com.prominente.android.vittal.data;

import android.content.Context;

import com.prominente.android.vittal.model.User;

public class UserSerializer extends ObjectSerializer
{
    private final static String FILE_NAME = "User.dat";
    private static UserSerializer userSerializer;

    private UserSerializer()
    {
    }

    public static synchronized UserSerializer getInstance()
    {
        if(userSerializer == null)
            userSerializer = new UserSerializer();

        return userSerializer;
    }

    public void save(Context context, User user)
    {
        save(context, FILE_NAME, user);
    }

    public User load(Context context)
    {
        return (User) load(context, FILE_NAME);
    }
}
