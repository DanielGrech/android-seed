package com.{{company_name}}.android.{{app_package_name_prefix}}.util

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Empty implementation of [android.app.Application.ActivityLifecycleCallbacks]
 * to allow base classes to only implement the methods they're interested in.
 */
abstract class SimpleAppLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}
