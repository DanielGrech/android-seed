package com.{{company_name}}.android.{{app_package_name_prefix}};

/**
 * App class used to run Robolectric tests.
 */
@SuppressWarnings("unused")
public class Test{{app_class_prefix}}AppImpl extends {{app_class_prefix}}AppImpl {

    @Override
    protected void enableDebugTools() {
        // Not whilst running tests
    }

    @Override
    protected void enableAppOnlyFunctionality() {
        // Not whilst running tests
    }
}
