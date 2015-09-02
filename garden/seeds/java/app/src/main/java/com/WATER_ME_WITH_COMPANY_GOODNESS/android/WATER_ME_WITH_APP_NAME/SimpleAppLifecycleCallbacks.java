package com.{company_name}.android.{app_package_name_prefix};

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Empty implementation of {@link android.app.Application.ActivityLifecycleCallbacks}
 * to allow base classes to only implement the methods they're interested in.
 */
abstract class SimpleAppLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
