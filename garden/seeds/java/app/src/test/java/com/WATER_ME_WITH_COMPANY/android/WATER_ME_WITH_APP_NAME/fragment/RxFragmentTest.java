package com.{{company_name}}.android.{{app_package_name_prefix}}.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}TestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import com.trello.rxlifecycle.FragmentEvent;
import rx.observers.TestSubscriber;

@RunWith({{app_class_prefix}}TestRunner.class)
public class RxFragmentTest {

    @Test
    public void testLifecycleObservable() {
        LameRxFragment fragment = new LameRxFragment();

        final TestSubscriber<FragmentEvent> subscriber = new TestSubscriber<>();

        fragment.lifecycle().subscribe(subscriber);

        final AppCompatActivity activity = Robolectric.setupActivity(AppCompatActivity.class);
        final LinearLayout contentView = new LinearLayout(RuntimeEnvironment.application);

        contentView.setId(android.R.id.content);
        activity.setContentView(contentView);

        activity.getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content,fragment, null).commit();

        activity.getSupportFragmentManager().beginTransaction()
                .remove(fragment).commit();

        subscriber.assertValues(
                // Create ...
                FragmentEvent.ATTACH,
                FragmentEvent.CREATE,
                FragmentEvent.CREATE_VIEW,
                FragmentEvent.START,
                FragmentEvent.RESUME,

                // ... and destroy
                FragmentEvent.PAUSE,
                FragmentEvent.STOP,
                FragmentEvent.DESTROY_VIEW,
                FragmentEvent.DESTROY,
                FragmentEvent.DETACH
        );
    }

    /**
     * RxFragment is an abstract class, so we need a concrete implementation here..
     */
    public static class LameRxFragment extends RxFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return new View(container.getContext());
        }
    }
}