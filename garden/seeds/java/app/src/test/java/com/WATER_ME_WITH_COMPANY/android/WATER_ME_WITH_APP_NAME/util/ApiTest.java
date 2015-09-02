package com.{{company_name}}.android.{{app_package_name_prefix}}.util;

import android.os.Build;

import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}TestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith({{app_class_prefix}}TestRunner.class)
public class ApiTest {

    @Test
    public void testConstructor() {
        // For code coverage..
        new Api();
    }

    @Config(sdk = Build.VERSION_CODES.KITKAT)
    @Test
    public void testIsFunctionalityWhenNotMatching() {
        assertThat(Api.is(Api.LOLLIPOP)).isFalse();
    }

    @Config(sdk = Build.VERSION_CODES.JELLY_BEAN)
    @Test
    public void testIsFunctionalityForJellybean() {
        assertThat(Api.is(Api.JELLYBEAN)).isTrue();
    }

    @Config(sdk = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Test
    public void testIsFunctionalityForJellybeanMr1() {
        assertThat(Api.is(Api.JELLYBEAN_MR1)).isTrue();
    }

    @Config(sdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Test
    public void testIsFunctionalityForJellybeanMr2() {
        assertThat(Api.is(Api.JELLYBEAN_MR2)).isTrue();
    }

    @Config(sdk = Build.VERSION_CODES.KITKAT)
    @Test
    public void testIsFunctionalityForKitkat() {
        assertThat(Api.is(Api.KITKAT)).isTrue();
    }

    @Config(sdk = Build.VERSION_CODES.LOLLIPOP)
    @Test
    public void testIsFunctionalityForLollipop() {
        assertThat(Api.is(Api.LOLLIPOP)).isTrue();
    }

    @Config(sdk = Build.VERSION_CODES.KITKAT)
    @Test
    public void testIsMinWhenNotMeetingApi() {
        assertThat(Api.isMin(Api.LOLLIPOP)).isFalse();
    }

    @Config(sdk = Build.VERSION_CODES.KITKAT)
    @Test
    public void testIsMinWhenAboveApi() {
        assertThat(Api.isMin(Api.JELLYBEAN)).isTrue();
    }

    @Config(sdk = Build.VERSION_CODES.KITKAT)
    @Test
    public void testIsMinWhenOnSameApi() {
        assertThat(Api.isMin(Api.KITKAT)).isTrue();
    }

    @Config(sdk = Build.VERSION_CODES.KITKAT)
    @Test
    public void testIsUpToOnSameApi() {
        assertThat(Api.isUpTo(Api.KITKAT)).isTrue();
    }


    @Config(sdk = Build.VERSION_CODES.LOLLIPOP)
    @Test
    public void testIsUpToOnLowerApi() {
        assertThat(Api.isUpTo(Api.KITKAT)).isFalse();
    }

    @Config(sdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Test
    public void testIsUpToOnHigherApi() {
        assertThat(Api.isUpTo(Api.KITKAT)).isTrue();
    }
}
