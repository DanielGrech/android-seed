package com.{company_name}.android.{app_package_name_prefix}.data;

/**
 * Interface to save and retrieve key-value pairs
 */
public interface PreferenceHelper {

    void clear();

    void remove(String key);

    boolean contains(String key);

    String get(String key, String defVal);

    boolean get(String key, boolean defVal);

    int get(String key, int defVal);

    long get(String key, long defVal);

    float get(String key, float defVal);

    void set(String key, boolean val);

    void set(String key, int val);

    void set(String key, float val);

    void set(String key, long val);

    void set(String key, String val);
}
