package com.{company_name}.android.{app_package_name_prefix}.data;

/**
 * Stores application-level settings and preferences. Values stored here are persisted
 * between openings of the app
 */
public class AppSettings {

    private final PreferenceHelper preferences;

    public AppSettings(PreferenceHelper preferenceHelper) {
        this.preferences = preferenceHelper;
    }
}
