package com.{{company_name}}.android.{{app_package_name_prefix}};

import android.app.Application;
import android.os.StrictMode;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.timber.StethoTree;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.picasso.Cache;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent;
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.DaggerAppServicesComponent;
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.{{app_class_prefix}}Module;
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.Api;
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.CrashlyticsLogger;

import timber.log.Timber;

/**
 *
 */
public class {{app_class_prefix}}App extends Application {

    private AppServicesComponent appServicesComponent;

    private RefWatcher refWatcher = RefWatcher.DISABLED;

    Cache picassoImageCache;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            enableDebugTools();
        }

        enableAppOnlyFunctionality();

        appServicesComponent = DaggerAppServicesComponent.builder()
                .{{app_class_prefix_lowercase}}Module(getModule())
                .build();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level >= TRIM_MEMORY_UI_HIDDEN) {
            Timber.d("Android is suggesting to trim memory .. clearing picasso cache. Level = %s", level);
            // Clear our picasso cache
            picassoImageCache.clear();
        }
    }

    {{app_class_prefix}}Module getModule() {
        return new {{app_class_prefix}}Module(this);
    }

    void enableAppOnlyFunctionality() {
        if (BuildConfig.CRASHLYTICS_ENABLED) {
            Crashlytics.start(this);
            Timber.plant(new CrashlyticsLogger());
        }

        createPicassoCache();
    }

    /**
     * Enables all debug-only functionality.
     * <p/>
     * Extract into a method to allow overriding in other modules/tests
     */
    void enableDebugTools() {
        Timber.plant(new Timber.DebugTree());
        Timber.plant(new StethoTree());

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());

        StrictMode.enableDefaults();

        if (BuildConfig.LEAK_CANARY_ENABLED && Api.isUpTo(Api.LOLLIPOP)) {
            // LeakCanary causes a crash on M Developer Preview
            refWatcher = LeakCanary.install(this);
        }
    }

    /**
     * Installs a custom picasso instance with a memory cache that can be controlled
     */
    void createPicassoCache() {
        Picasso.setSingletonInstance(new Picasso.Builder(this)
                .memoryCache(picassoImageCache = new LruCache(this))
                .build());
    }

     /**
     * @return an {@link AppServicesComponent} which holds all the necessary dependencies
     * other application components may want to use for injection purposes
     */
    public AppServicesComponent getAppServicesComponent() {
        return appServicesComponent;
    }

    public RefWatcher getRefWatcher() {
        return refWatcher;
    }
}
