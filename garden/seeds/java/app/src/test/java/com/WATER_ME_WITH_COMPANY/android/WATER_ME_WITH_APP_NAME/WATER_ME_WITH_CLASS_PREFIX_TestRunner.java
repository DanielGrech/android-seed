package com.{{company_name}}.android.{{app_package_name_prefix}};

import android.app.Application;
import android.os.Build;
import android.support.annotation.NonNull;

import com.{{company_name}}.android.{{app_package_name_prefix}}.BuildConfig;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import rx.TestSchedulerRule;

public class {{app_class_prefix}}TestRunner extends RobolectricGradleTestRunner {

    static {
        // Argh! There is a strongly typed `RxJavaPlugins.registerSchedulersHook(), but it
        // needs to be loaded before the `Schedulers` class is loaded. This is the only
        // dodgy way to guarantee our hook is registered first
        System.setProperty("rxjava.plugin.RxJavaSchedulersHook.implementation",
                TestSchedulerRule.RxSchedulersHook.class.getName());
    }

    public {{app_class_prefix}}TestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public Config getConfig(Method method) {
        final Config config = super.getConfig(method);
        if (config == null) {
            return null;
        } else {
            final Config methodConfig = method.getAnnotation(Config.class);

            return new DelegatingConfig(config) {
                @Override
                public Class<?> constants() {
                    return BuildConfig.class;
                }

                @Override
                public String packageName() {
                    // We assume that the test runner class is in the root package..
                    return {{app_class_prefix}}TestRunner.class.getPackage().getName();
                }

                @Override
                public int[] sdk() {
                    if (methodConfig != null && methodConfig.sdk() != null && methodConfig.sdk().length > 0) {
                        // If the method specifies a different SDK, let's use it
                        return methodConfig.sdk();
                    } else {
                        return new int[]{Build.VERSION_CODES.KITKAT};
                    }
                }
            };
        }
    }

    private class DelegatingConfig implements Config {

        final Config delegate;

        public DelegatingConfig(@NonNull Config delegate) {
            this.delegate = delegate;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return delegate.annotationType();
        }

        @Override
        public int[] sdk() {
            return delegate.sdk();
        }

        @Override
        public String manifest() {
            return delegate.manifest();
        }

        @Override
        public Class<?> constants() {
            return delegate.constants();
        }

        @Override
        public Class<? extends Application> application() {
            return delegate.application();
        }

        @Override
        public String packageName() {
            return delegate.packageName();
        }

        @Override
        public String qualifiers() {
            return delegate.qualifiers();
        }

        @Override
        public String resourceDir() {
            return delegate.resourceDir();
        }

        @Override
        public String assetDir() {
            return delegate.assetDir();
        }

        @Override
        public Class<?>[] shadows() {
            return delegate.shadows();
        }

        @Override
        public String[] libraries() {
            return delegate.libraries();
        }
    }
}
