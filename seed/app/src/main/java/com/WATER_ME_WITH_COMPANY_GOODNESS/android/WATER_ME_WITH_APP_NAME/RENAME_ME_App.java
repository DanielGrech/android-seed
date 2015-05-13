package com.{company_name}.android.{app_package_name_prefix};

import android.app.Application;
import android.os.StrictMode;

import com.facebook.stetho.Stetho;

import timber.log.Timber;

/**
 *
 */
public class {app_class_prefix}App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());

            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build());

            StrictMode.enableDefaults();
        }
    }
}
