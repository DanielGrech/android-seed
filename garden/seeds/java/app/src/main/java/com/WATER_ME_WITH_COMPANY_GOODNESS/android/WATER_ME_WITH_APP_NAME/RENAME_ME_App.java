package com.{company_name}.android.{app_package_name_prefix};

import android.app.Application;
import android.os.StrictMode;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.timber.StethoTree;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.{company_name}.android.{app_package_name_prefix}.module.AppServicesComponent;
import com.{company_name}.android.{app_package_name_prefix}.module.DaggerAppServicesComponent;
import com.{company_name}.android.{app_package_name_prefix}.module.{app_class_prefix}Module;
import com.{company_name}.android.{app_package_name_prefix}.util.Api;

import timber.log.Timber;

/**
 *
 */
public class {app_class_prefix}App extends Application {

    private AppServicesComponent appServicesComponent;

    private RefWatcher refWatcher = RefWatcher.DISABLED;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            enableDebugTools();
        }

        enableAppOnlyFunctionality();

        appServicesComponent = DaggerAppServicesComponent.builder()
                .{app_class_prefix_lowercase}Module(getModule())
                .build();
    }

    {app_class_prefix}Module getModule() {
        return new {app_class_prefix}Module(this);
    }

    void enableAppOnlyFunctionality() {
        // Crashlytics etc
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
