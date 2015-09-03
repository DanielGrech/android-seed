package com.{{company_name}}.android.{{app_package_name_prefix}}

import android.app.Application
import android.content.ComponentCallbacks2
import android.os.StrictMode
import com.crashlytics.android.Crashlytics
import com.{{company_name}}.android.{{app_package_name_prefix}}.BuildConfig
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.{{app_class_prefix}}Module
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.Api
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.CrashlyticsLogger
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.LoggingLifecycleCallbacks
import com.facebook.stetho.Stetho
import com.facebook.stetho.timber.StethoTree
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.squareup.picasso.Cache
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import timber.log.Timber

abstract class {{app_class_prefix}}App : Application() {

    var refWatcher: RefWatcher = RefWatcher.DISABLED

    private var picassoImageCache: Cache? = null

    private var appServicesComponent: AppServicesComponent? = null

    protected abstract fun createAppServicesComponent() : AppServicesComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            enableDebugTools()
        }

        enableAppOnlyFunctionality()

        appServicesComponent = createAppServicesComponent()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        if (level >= ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Timber.d("Android is suggesting to trim memory .. clearing picasso cache. Level = %s", level)
            // Clear our picasso cache
            picassoImageCache!!.clear()
        }
    }

    /**
     * @return an [AppServicesComponent] which holds all the necessary dependencies
     * * other application components may want to use for injection purposes
     */
    public fun getAppServicesComponent(): AppServicesComponent {
        return appServicesComponent!!
    }

    protected fun getModule() : {{app_class_prefix}}Module {
        return {{app_class_prefix}}Module(this);
    }

    /**
     * Enables functionality only wanted in the actual app.
     *
     *
     * This allows overriding in tests/other modules
     */
    protected open fun enableAppOnlyFunctionality() {
        if (BuildConfig.CRASHLYTICS_ENABLED) {
            Crashlytics.start(this)
            Timber.plant(CrashlyticsLogger())
        }
        registerActivityLifecycleCallbacks(LoggingLifecycleCallbacks())

        createPicassoCache()
    }

    /**
     * Installs a custom picasso instance with a memory cache that can be controlled
     */
    private fun createPicassoCache() {
        picassoImageCache = LruCache(this)
        Picasso.setSingletonInstance(Picasso.Builder(this)
                .memoryCache(picassoImageCache)
                .build())
    }

    protected open fun enableDebugTools() {
        Timber.plant(Timber.DebugTree())
        Timber.plant(StethoTree())

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build())

        StrictMode.enableDefaults()

        if (BuildConfig.LEAK_CANARY_ENABLED && Api.isUpTo(Api.LOLLIPOP)) {
            // LeakCanary causes a crash on M Developer Preview
            refWatcher = LeakCanary.install(this)
        }
    }
}
