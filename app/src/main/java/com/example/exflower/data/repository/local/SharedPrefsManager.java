package com.example.exflower.data.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {
    private static final String PREF_NAME = "FlowerAppPrefs";
    private static final String KEY_TOKEN = "user_token";

    private final SharedPreferences prefs;

    public SharedPrefsManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    public void clearToken() {
        prefs.edit().remove(KEY_TOKEN).apply();
    }
}