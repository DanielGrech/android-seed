package com.{company_name}.android.{app_package_name_prefix};

import android.app.Activity;
import android.os.Bundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith({app_class_prefix}TestRunner.class)
public class {app_class_prefix}LifecycleCallbacksTests {

    @Test
    public void testIsAppInForegroundWithSingleResumedActivity() {
        final {app_class_prefix}LifecycleCallbacks callbacks = createCallbacks();
        final Activity mockActivity = createMockActivity();

        callbacks.onActivityCreated(mockActivity, null);
        callbacks.onActivityStarted(mockActivity);
        callbacks.onActivityResumed(mockActivity);

        assertThat(callbacks.isAppInForeground()).isTrue();
    }

    @Test
    public void testIsAppInForegroundWithMultipleResumedActivity() {
        final {app_class_prefix}LifecycleCallbacks callbacks = createCallbacks();
        final Activity mockActivity1 = createMockActivity();
        final Activity mockActivity2 = createMockActivity();
        final Activity mockActivity3 = createMockActivity();

        final Activity[] activities = { mockActivity1, mockActivity2, mockActivity3 };

        for (int i = 0, len = activities.length; i < len; i++) {
            if (i > 0) {
                callbacks.onActivityPaused(activities[i - 1]);
                callbacks.onActivityStopped(activities[i - 1]);
            }

            callbacks.onActivityCreated(activities[i], null);
            callbacks.onActivityStarted(activities[i]);
            callbacks.onActivityResumed(activities[i]);
        }

        assertThat(callbacks.isAppInForeground()).isTrue();
    }

    @Test
    public void testIsAppInForegroundWithNoResumedActivity() {
        final {app_class_prefix}LifecycleCallbacks callbacks = createCallbacks();

        final Activity mockActivity = createMockActivity();

        callbacks.onActivityCreated(mockActivity, null);
        callbacks.onActivityStarted(mockActivity);
        callbacks.onActivityResumed(mockActivity);
        callbacks.onActivityPaused(mockActivity);
        callbacks.onActivitySaveInstanceState(mockActivity, mock(Bundle.class));
        callbacks.onActivityStopped(mockActivity);
        callbacks.onActivityDestroyed(mockActivity);

        assertThat(callbacks.isAppInForeground()).isFalse();
    }

    @Test
    public void testIsAppInForegroundWithNoResumedAfterPauseActivity() {
        final {app_class_prefix}LifecycleCallbacks callbacks = createCallbacks();
        final Activity mockActivity1 = createMockActivity();
        final Activity mockActivity2 = createMockActivity();
        final Activity mockActivity3 = createMockActivity();

        final Activity[] activities = { mockActivity1, mockActivity2, mockActivity3 };

        for (Activity activity : activities) {
            callbacks.onActivityCreated(activity, null);
            callbacks.onActivityStarted(activity);
            callbacks.onActivityResumed(activity);
            callbacks.onActivityPaused(activity);
            callbacks.onActivitySaveInstanceState(activity, mock(Bundle.class));
            callbacks.onActivityStopped(activity);
            callbacks.onActivityDestroyed(activity);
        }

        assertThat(callbacks.isAppInForeground()).isFalse();
    }

    @Test
    public void testIsAppInForegroundWhenPausedAndResumedActivity() {
        final {app_class_prefix}LifecycleCallbacks callbacks = createCallbacks();
        final Activity mockActivity1 = createMockActivity();
        final Activity mockActivity2 = createMockActivity();
        final Activity mockActivity3 = createMockActivity();

        final Activity[] activities = { mockActivity1, mockActivity2, mockActivity3 };

        for (Activity activity : activities) {
            callbacks.onActivityCreated(activity, null);
            callbacks.onActivityStarted(activity);
            callbacks.onActivityResumed(activity);
            callbacks.onActivityPaused(activity);
            callbacks.onActivitySaveInstanceState(activity, mock(Bundle.class));
            callbacks.onActivityStopped(activity);
            callbacks.onActivityDestroyed(activity);
        }

        assertThat(callbacks.isAppInForeground()).isFalse();

        final Activity mockActivity4 = createMockActivity();
        callbacks.onActivityCreated(mockActivity4, null);
        callbacks.onActivityStarted(mockActivity4);
        callbacks.onActivityResumed(mockActivity4);

        assertThat(callbacks.isAppInForeground()).isTrue();
    }

    private {app_class_prefix}LifecycleCallbacks createCallbacks() {
        return new {app_class_prefix}LifecycleCallbacks(){};
    }
    
    private Activity createMockActivity() {
        final Activity activity = mock(Activity.class);
        when(activity.getApplication()).thenReturn(RuntimeEnvironment.application);
        return activity;
    }
}
