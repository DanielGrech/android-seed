package com.{{company_name}}.android.{{app_package_name_prefix}};

/**
 * App class used to run Robolectric tests.
 */
@SuppressWarnings("unused")
public class Test{{app_class_prefix}}App extends {{app_class_prefix}}App {

    @Override
    void enableDebugTools() {
        // Not whilst running tests
    }

    @Override
    void enableAppOnlyFunctionality() {
        // Not whilst running tests
    }
}