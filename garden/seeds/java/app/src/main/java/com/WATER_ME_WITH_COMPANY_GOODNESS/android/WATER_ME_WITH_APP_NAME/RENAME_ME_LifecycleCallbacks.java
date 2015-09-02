package com.{company_name}.android.{app_package_name_prefix};

import android.app.Activity;

/**
 * Listen for the starting/stopping of activities in the app.
 * <p/>
 * This class is primarily used to track whether or not the application has any visible screens
 * showing
 */
public class {app_class_prefix}LifecycleCallbacks extends SimpleAppLifecycleCallbacks {

    int visibleActivities;

    @Override
    public void onActivityResumed(Activity activity) {
        visibleActivities++;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        visibleActivities = Math.max(0, visibleActivities - 1);
    }

    /**
     * Check if the application appears in the foreground (Ie, it has at least 1 visible activity)
     *
     * @return <code>true</code> if a screen of the application is visible, <code>false</code> otherwise
     */
    public boolean isAppInForeground() {
        return visibleActivities > 0;
    }
}
