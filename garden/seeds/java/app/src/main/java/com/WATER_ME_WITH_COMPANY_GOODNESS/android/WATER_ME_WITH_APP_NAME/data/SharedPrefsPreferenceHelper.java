package com.{company_name}.android.{app_package_name_prefix}.data;

import android.content.SharedPreferences;

/**
 * Implementation of the {@link PreferenceHelper} interface backed by Android
 * {@link SharedPreferences}
 */
public class SharedPrefsPreferenceHelper implements PreferenceHelper {

    private SharedPreferences prefs;

    public SharedPrefsPreferenceHelper(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public void clear() {
        prefs.edit().clear().apply();
    }

    @Override
    public void remove(String key) {
        prefs.edit().remove(key).apply();
    }

    @Override
    public boolean contains(String key) {
        return prefs.contains(key);
    }

    @Override
    public String get(String key, String defVal) {
        return prefs.getString(key, defVal);
    }

    @Override
    public boolean get(String key, boolean defVal) {
        return prefs.getBoolean(key, defVal);
    }

    @Override
    public int get(String key, int defVal) {
        return prefs.getInt(key, defVal);
    }

    @Override
    public long get(String key, long defVal) {
        return prefs.getLong(key, defVal);
    }

    @Override
    public float get(String key, float defVal) {
        return prefs.getFloat(key, defVal);
    }

    @Override
    public void set(String key, boolean val) {
        prefs.edit().putBoolean(key, val).apply();
    }

    @Override
    public void set(String key, int val) {
        prefs.edit().putInt(key, val).apply();
    }

    @Override
    public void set(String key, float val) {
        prefs.edit().putFloat(key, val).apply();
    }

    @Override
    public void set(String key, long val) {
        prefs.edit().putLong(key, val).apply();
    }

    @Override
    public void set(String key, String val) {
        prefs.edit().putString(key, val).apply();
    }
}
