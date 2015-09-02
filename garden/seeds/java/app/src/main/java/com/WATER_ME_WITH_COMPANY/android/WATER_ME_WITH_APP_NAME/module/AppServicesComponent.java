package com.{{company_name}}.android.{{app_package_name_prefix}}.module;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger component to provide dependency injection
 */
@Singleton
@Component(modules = {{app_class_prefix}}Module.class)
public interface AppServicesComponent {

}
