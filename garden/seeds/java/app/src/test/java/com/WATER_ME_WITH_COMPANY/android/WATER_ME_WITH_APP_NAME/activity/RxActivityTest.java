package com.{{company_name}}.android.{{app_package_name_prefix}}.activity;

import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}TestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import com.trello.rxlifecycle.ActivityEvent;
import rx.observers.TestSubscriber;

@RunWith({{app_class_prefix}}TestRunner.class)
public class RxActivityTest {

    @Test
    public void testLifecycleObservable() {
        ActivityController<LameRxActivity> controller = Robolectric.buildActivity(LameRxActivity.class);

        final TestSubscriber<ActivityEvent> subscriber = new TestSubscriber<>();

        controller.get().lifecycle().subscribe(subscriber);

        controller.create()
                .start()
                .resume()
                .pause()
                .stop()
                .destroy();

        subscriber.assertValues(
                ActivityEvent.CREATE,
                ActivityEvent.START,
                ActivityEvent.RESUME,
                ActivityEvent.PAUSE,
                ActivityEvent.STOP,
                ActivityEvent.DESTROY
        );
    }

    /**
     * RxActivity is an abstract class, so we need a concrete implementation here..
     */
    public static class LameRxActivity extends RxActivity {

    }
}