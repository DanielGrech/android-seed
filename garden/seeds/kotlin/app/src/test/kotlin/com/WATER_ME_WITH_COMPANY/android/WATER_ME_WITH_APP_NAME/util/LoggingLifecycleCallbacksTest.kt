package com.{{company_name}}.android.{{app_package_name_prefix}}.util

import android.app.Activity
import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}TestRunner
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.LoggingLifecycleCallbacks
import org.assertj.core.api.Assertions

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import java.util.LinkedList

import timber.log.Timber

import org.assertj.core.api.Assertions.assertThat

RunWith({{app_class_prefix}}TestRunner::class)
public class LoggingLifecycleCallbacksTest {

    var testTree: TestTree = TestTree()

    Before
    public fun setup() {
        testTree = TestTree()
        Timber.plant(testTree)
    }

    After
    public fun teardown() {
        Timber.uproot(testTree)
    }

    Test
    public fun testLogsCorrectEntries() {
        val activity = Activity()

        val callbacks = LoggingLifecycleCallbacks()
        callbacks.onActivityCreated(activity, null)
        callbacks.onActivityStarted(activity)
        callbacks.onActivityResumed(activity)
        callbacks.onActivityPaused(activity)
        callbacks.onActivityStopped(activity)
        callbacks.onActivitySaveInstanceState(activity, null)
        callbacks.onActivityDestroyed(activity)

        Assertions.assertThat(testTree.messages).containsExactly(
                "onCreate() - Activity",
                "onStart() - Activity",
                "onResume() - Activity",
                "onPause() - Activity",
                "onStop() - Activity",
                "onSaveInstanceState() - Activity",
                "onDestroy() - Activity"
        )
    }


    private inner class TestTree : Timber.DebugTree() {

        val messages: MutableList<String> = LinkedList()

        override fun log(priority: Int, tag: String, message: String, t: Throwable?) {
            messages.add(message)
        }
    }
}