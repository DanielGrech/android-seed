package com.{{company_name}}.android.{{app_package_name_prefix}};

import android.support.annotation.NonNull;

import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent;
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.DaggerAppServicesComponent;

public class {{app_class_prefix}}AppImpl extends {{app_class_prefix}}App {

    @NonNull
    @Override
    protected AppServicesComponent createAppServicesComponent() {
        return DaggerAppServicesComponent.builder()
                .{{app_class_prefix_lowercase}}Module(getModule())
                .build();
    }
}
