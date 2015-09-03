package com.{{company_name}}.android.{{app_package_name_prefix}}.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.{{company_name}}.android.{{app_package_name_prefix}}.TestUtils;
import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}TestRunner;
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent;
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter.HomePresenter;
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter.Presenter;
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.HomeMvpView;
import com.{{company_name}}.{{app_package_name_prefix}}.network.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.robolectric.Robolectric.buildActivity;

@RunWith({{app_class_prefix}}TestRunner.class)
public class PresentableActivityTest {

    @Test(expected = IllegalStateException.class)
    public void testThrowsExceptionIfNoPresenterProvided() {
        Robolectric.setupActivity(PresentableActivityWithNoPresenter.class);
    }

    @Test
    public void testDelegatesToPresenter() {
        final ActivityController<PresentableActivityWithMockPresenter> controller
                = buildActivity(PresentableActivityWithMockPresenter.class);

        final Presenter presenter = controller.setup().get().presenter;
        controller.saveInstanceState(mock(Bundle.class))
                .pause()
                .stop()
                .destroy()
                .get();

        verify(presenter).onCreate(any(Bundle.class));
        verify(presenter).onStart();
        verify(presenter).onResume();
        verify(presenter).onSaveInstanceState(any(Bundle.class));
        verify(presenter).onPause();
        verify(presenter).onStop();
        verify(presenter).onDestroy();
    }

    static class PresentableActivityWithMockPresenter extends PresentableActivity {
        @Override
        protected Presenter createPresenter(AppServicesComponent component) {
            return spy(new HomePresenter(TestUtils.createView(HomeMvpView.class), component));
        }

        @Override
        protected int getLayoutResource() {
            return 0;
        }
    }

    static class PresentableActivityWithNoPresenter extends PresentableActivityWithMockPresenter {
        @NonNull
        @Override
        protected Presenter createPresenter(AppServicesComponent component) {
            return null;
        }
    }
}