package com.pankaj.zomatosearchapi_assignment;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

/**
 * Signifies main application which initialises context and json parsing
 *
 * @author Pankaj Nirban
 * @since 16-06-2019
 */

public class MyApplication extends Application {

    public static Gson gson;
    private static MyApplication mInstance;
    private static Context mContext;

    public static Gson getGsonInstance() {
        if (gson != null) {
            return gson;
        } else {
            gson = new Gson();
            return gson;
        }
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public static synchronized MyApplication getContext() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
    }
}

