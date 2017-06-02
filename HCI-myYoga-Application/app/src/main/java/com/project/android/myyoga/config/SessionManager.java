package com.project.android.myyoga.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by thaik on 4/26/2017.
 */

public class SessionManager {

    public static final String KEY_NAME = "name";
    public static final String Key_EMAIL = "email";
    private static final String PREF_NAME = "myYogaPref";
    private static final String IS_LOGIN = "isLoggedIn";
    public static int PRIVATE_MODE = 0;
    private static SessionManager sessionManager;
    private static Gson GSON = new Gson();
    SharedPreferences sharedPreferences;
    Type typeOfObject = new TypeToken<Object>() {
    }.getType();
    Editor editor;
    Context _context;

    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public static SessionManager getSessionManager(Context context) {
        sessionManager = new SessionManager(context);
        return sessionManager;
    }

    public void putObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object is Null");
        }
        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("key is empty or null");
        }
        editor.putString(key, GSON.toJson(object));
    }

    public void commit() {
        editor.commit();
    }

    public <T> T getObject(String key, Class<T> a) {
        String gson = sharedPreferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Object stored with key" + key + "Is instance of another class");

            }
        }
    }

}
