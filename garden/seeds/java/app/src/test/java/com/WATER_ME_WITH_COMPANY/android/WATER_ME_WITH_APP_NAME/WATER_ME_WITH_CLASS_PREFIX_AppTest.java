package com.{{company_name}}.android.{{app_package_name_prefix}};

import android.app.Application;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RunWith({{app_class_prefix}}TestRunner.class)
public class {{app_class_prefix}}AppTest {

    @After
    public void teardown() {
        Mockito.validateMockitoUsage();
    }

    @Test
    public void testGetApplicationComponent() {
        // Mostly for code coverage..
        final {{app_class_prefix}}App app = ({{app_class_prefix}}App) RuntimeEnvironment.application;
        assertThat(app.getAppServicesComponent()).isNotNull();
    }

    @Test
    public void testOnTrimMemoryClearsImageCacheWhenAboveUiLevel() {
        final {{app_class_prefix}}App app = ({{app_class_prefix}}App) RuntimeEnvironment.application;
        setupPicassoCache(app);
        app.onTrimMemory(Application.TRIM_MEMORY_COMPLETE);
        assertThat(app.picassoImageCache.size()).isZero();
    }

    @Test
    public void testOnTrimMemoryClearsImageCacheWhenUiHidden() {
        final {{app_class_prefix}}App app = ({{app_class_prefix}}App) RuntimeEnvironment.application;
        setupPicassoCache(app);
        app.onTrimMemory(Application.TRIM_MEMORY_UI_HIDDEN);
        assertThat(app.picassoImageCache.size()).isZero();
    }

    @Test
    public void testOnTrimMemoryDoesntClearCacheIfBelowUiHiddenLevel() {
        final {{app_class_prefix}}App app = ({{app_class_prefix}}App) RuntimeEnvironment.application;
        setupPicassoCache(app);
        app.onTrimMemory(Application.TRIM_MEMORY_RUNNING_LOW);
        Mockito.verifyZeroInteractions(app.picassoImageCache);
    }

    private void setupPicassoCache({{app_class_prefix}}App app) {
        app.onCreate();
        try {
            app.createPicassoCache();
        } catch (IllegalStateException ex) {
            // Happens when picasso singleton set more than once .. ignore..
        }

        app.picassoImageCache = spy(app.picassoImageCache);
    }
}
