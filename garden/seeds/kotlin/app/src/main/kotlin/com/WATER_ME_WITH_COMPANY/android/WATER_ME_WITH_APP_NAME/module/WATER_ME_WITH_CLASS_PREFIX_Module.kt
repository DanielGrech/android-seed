package com.{{company_name}}.android.{{app_package_name_prefix}}.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.{{company_name}}.android.{{app_package_name_prefix}}.BuildConfig
import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}App
import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}AppImpl
import com.{{company_name}}.android.{{app_package_name_prefix}}.data.AppSettings
import com.{{company_name}}.{{app_package_name_prefix}}.DataSource
import com.{{company_name}}.{{app_package_name_prefix}}.networkDataSource
import com.lacronicus.easydatastorelib.DatastoreBuilder
import dagger.Module
import dagger.Provides
import retrofit.RestAdapter
import javax.inject.Singleton

/**
 * Dagger module to provide dependency injection
 */
SuppressWarnings("UNUSED_PARAMETER")
Module
public class {{app_class_prefix}}Module(private val application: {{app_class_prefix}}App) {

    Provides
    Singleton
    fun providesApp(): {{app_class_prefix}}App {
        return application
    }

    Provides
    fun providesContext(): Context {
        return application
    }

    Provides
    Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    Provides
    Singleton
    fun providesAppSettings(sharedPreferences: SharedPreferences): AppSettings {
        return DatastoreBuilder(sharedPreferences).create(javaClass<AppSettings>())
    }

    Provides
    Singleton
    fun providesDataSource() : DataSource {
        return networkDataSource {
            logging = BuildConfig.DEBUG
            endpoint = "http://myawesomeserver.com"
            networkInterceptors = listOf()
        }
    }
}
