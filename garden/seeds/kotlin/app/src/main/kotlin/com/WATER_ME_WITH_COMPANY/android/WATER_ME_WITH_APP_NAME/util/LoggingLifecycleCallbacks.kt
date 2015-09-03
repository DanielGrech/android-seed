package com.{{company_name}}.android.{{app_package_name_prefix}}.util

import android.app.Activity
import android.os.Bundle
import timber.log.Timber

/**
 * Lifecycle callbacks which simply logs the current lifecycle method and activity name
 */
public class LoggingLifecycleCallbacks : SimpleAppLifecycleCallbacks() {

    override public fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        log("onCreate", activity)
    }

    override public fun onActivityStarted(activity: Activity) {
        log("onStart", activity)
    }

    override public fun onActivityResumed(activity: Activity) {
        log("onResume", activity)
    }

    override public fun onActivityPaused(activity: Activity) {
        log("onPause", activity)
    }

    override public fun onActivityStopped(activity: Activity) {
        log("onStop", activity)
    }

    override public fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        log("onSaveInstanceState", activity)
    }

    override public fun onActivityDestroyed(activity: Activity) {
        log("onDestroy", activity)
    }

    private fun log(method: String, activity: Activity) {
        Timber.d("%s() - %s", method, activity.javaClass.getSimpleName())
    }
}
