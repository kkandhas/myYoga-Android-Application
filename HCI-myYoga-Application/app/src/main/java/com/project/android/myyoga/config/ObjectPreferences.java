package com.project.android.myyoga.config;

import android.app.Application;
import android.util.Log;

/**
 * Created by thaik on 4/26/2017.
 */

public class ObjectPreferences extends Application {
    private static final String TAG = "ObjectPreferences";
    private SessionManager sessionManager = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sessionManager = SessionManager.getSessionManager(getBaseContext());
        Log.i(TAG, "Preference Created");
    }

    public SessionManager getSessionManager(){
        if(sessionManager != null){
            return sessionManager;
        }
        return null;
    }
}
