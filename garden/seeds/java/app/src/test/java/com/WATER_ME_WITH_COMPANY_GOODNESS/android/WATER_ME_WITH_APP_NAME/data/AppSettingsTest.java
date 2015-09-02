package com.{company_name}.android.{app_package_name_prefix}.data;

import com.{company_name}.android.{app_package_name_prefix}.BuildConfig;
import com.{company_name}.android.{app_package_name_prefix}.{app_class_prefix}TestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith({app_class_prefix}TestRunner.class)
public class AppSettingsTest {

    @Test
    public void testHasRegisteredIfNoUserId() {
        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_USER_ID, -1)).thenReturn(-1);
        when(helper.get(AppSettings.KEY_OAUTH_TOKEN, null)).thenReturn("auth token");

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.hasRegistered()).isFalse();
    }

    @Test
    public void testHasRegisteredIfNoAuthToken() {
        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_USER_ID, null)).thenReturn("name");
        when(helper.get(AppSettings.KEY_OAUTH_TOKEN, null)).thenReturn(null);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.hasRegistered()).isFalse();
    }

    @Test
    public void testHasRegisteredIfNoAuthTokenAndNoUserId() {
        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_USER_ID, null)).thenReturn(null);
        when(helper.get(AppSettings.KEY_OAUTH_TOKEN, null)).thenReturn(null);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.hasRegistered()).isFalse();
    }

    @Test
    public void testHasRegistered() {
        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_USER_ID, null)).thenReturn("name");
        when(helper.get(AppSettings.KEY_OAUTH_TOKEN, null)).thenReturn("oauth_token");

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.hasRegistered()).isTrue();
    }

    @Test
    public void testSetUserId() {
        final int EXPECTED_USER_ID = 12345;
        final PreferenceHelper helper = mock(PreferenceHelper.class);

        final AppSettings settings = new AppSettings(helper);
        settings.setUserId(EXPECTED_USER_ID);

        verify(helper).set(AppSettings.KEY_USER_ID, EXPECTED_USER_ID);
    }

    @Test
    public void testSetReferrer() {
        final String EXPECTED_REFERRER = "_referrer";
        final PreferenceHelper helper = mock(PreferenceHelper.class);

        final AppSettings settings = new AppSettings(helper);
        settings.setReferrer(EXPECTED_REFERRER);

        verify(helper).set(AppSettings.KEY_REFERRER, EXPECTED_REFERRER);
    }

    @Test
    public void testSetAuthToken() {
        final String EXPECTED_AUTH_TOKEN = "_auth_token";
        final PreferenceHelper helper = mock(PreferenceHelper.class);

        final AppSettings settings = new AppSettings(helper);
        settings.setAuthToken(EXPECTED_AUTH_TOKEN);

        verify(helper).set(AppSettings.KEY_OAUTH_TOKEN, EXPECTED_AUTH_TOKEN);
    }

    @Test
    public void testGetReferrer() {
        final String EXPECTED_REFERRER = "_referrer";

        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_REFERRER, null)).thenReturn(EXPECTED_REFERRER);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.getReferrer()).isEqualTo(EXPECTED_REFERRER);
    }

    @Test
    public void testSetPushToken() {
        final String EXPECTED_PUSH_TOKEN = "_push_token";
        final PreferenceHelper helper = mock(PreferenceHelper.class);

        final AppSettings settings = new AppSettings(helper);
        settings.setPushToken(EXPECTED_PUSH_TOKEN);

        verify(helper).set(AppSettings.KEY_PUSH_TOKEN, EXPECTED_PUSH_TOKEN);
    }

    @Test
    public void testGetPushToken() {
        final String EXPECTED_PUSH_TOKEN = "_push_token";

        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_PUSH_TOKEN, null)).thenReturn(EXPECTED_PUSH_TOKEN);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.getPushToken()).isEqualTo(EXPECTED_PUSH_TOKEN);
    }

    @Test
    public void testSetLastShownCoachmarkTime() {
        final long EXPECTED_TIME = System.currentTimeMillis();
        final PreferenceHelper helper = mock(PreferenceHelper.class);

        final AppSettings settings = new AppSettings(helper);
        settings.setLastShownCoachmarkTime(EXPECTED_TIME);

        verify(helper).set(AppSettings.KEY_LAST_SHOWN_COACHMARK_TIME, EXPECTED_TIME);
    }

    @Test
    public void testGetLastShownCoachmarkTime() {
        final long EXPECTED_TIME = System.currentTimeMillis();

        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_LAST_SHOWN_COACHMARK_TIME, -1L)).thenReturn(EXPECTED_TIME);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.getLastShownCoachmarkTime()).isEqualTo(EXPECTED_TIME);
    }

    @Test
    public void testSetLastShownCoachmarkVersion() {
        final int EXPECTED_VERSION = BuildConfig.VERSION_CODE;
        final PreferenceHelper helper = mock(PreferenceHelper.class);

        final AppSettings settings = new AppSettings(helper);
        settings.setLastShownCoachmarkVersion(EXPECTED_VERSION);

        verify(helper).set(AppSettings.KEY_LAST_SHOWN_COACHMARK_VERSION, EXPECTED_VERSION);
    }

    @Test
    public void testGetLastShownCoachmarkVersion() {
        final int EXPECTED_VERSION = BuildConfig.VERSION_CODE;

        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_LAST_SHOWN_COACHMARK_VERSION, 0)).thenReturn(EXPECTED_VERSION);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.getLastShownCoachmarkVersion()).isEqualTo(EXPECTED_VERSION);
    }

    @Test
    public void testSetUsername() {
        final String EXPECTED_USERNAME = "Homer Simpson";
        final PreferenceHelper helper = mock(PreferenceHelper.class);

        final AppSettings settings = new AppSettings(helper);
        settings.setUserName(EXPECTED_USERNAME);

        verify(helper).set(AppSettings.KEY_USER_NAME, EXPECTED_USERNAME);
    }

    @Test
    public void testGetUsername() {
        final String EXPECTED_USERNAME = "Homer Simpson";

        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_USER_NAME, null)).thenReturn(EXPECTED_USERNAME);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.getUserName()).isEqualTo(EXPECTED_USERNAME);
    }

    @Test
    public void testSetHasSeenRadiusWarning() {
        final PreferenceHelper helper = mock(PreferenceHelper.class);

        final AppSettings settings = new AppSettings(helper);
        settings.setHasSeenRadiusWarning(true);

        verify(helper).set(AppSettings.KEY_HAS_SEEN_RADIUS_WARNING, true);
    }

    @Test
    public void testHasSeenRadiusWarning() {
        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_HAS_SEEN_RADIUS_WARNING, false)).thenReturn(true);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.hasSeenRadiusWarning()).isTrue();
    }

    @Test
    public void testSetHasSeenAppirater() {
        final PreferenceHelper helper = mock(PreferenceHelper.class);

        final AppSettings settings = new AppSettings(helper);
        settings.setHasSeenAppirater(true);

        verify(helper).set(AppSettings.KEY_HAS_SEEN_APPIRATER, true);
    }

    @Test
    public void testHasSeenAppirater() {
        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(AppSettings.KEY_HAS_SEEN_APPIRATER, false)).thenReturn(true);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.hasSeenAppirater()).isTrue();
    }

    @Test
    public void testSetWatchThisSpaceIconType() {
        final int EXPECTED_TYPE = AppSettings.WATCH_THIS_SPACE_TYPE_SHOPPING;
        final PreferenceHelper helper = mock(PreferenceHelper.class);

        final AppSettings settings = new AppSettings(helper);
        settings.setWatchThisSpaceIconType(EXPECTED_TYPE);

        verify(helper).set(AppSettings.KEY_WATCH_THIS_SPACE_ICON_TYPE, EXPECTED_TYPE);
    }

    @Test
    public void testGetWatchThisSpaceIconType() {
        final int EXPECTED_TYPE = AppSettings.WATCH_THIS_SPACE_TYPE_SHOPPING;

        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.get(eq(AppSettings.KEY_WATCH_THIS_SPACE_ICON_TYPE), anyInt())).thenReturn(EXPECTED_TYPE);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.getWatchThisSpaceIconType()).isEqualTo(EXPECTED_TYPE);
    }

    @Test
    public void testHasSelectedWatchThisSpaceIconType() {
        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.contains(AppSettings.KEY_WATCH_THIS_SPACE_ICON_TYPE)).thenReturn(true);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.hasSelectedWatchThisSpaceIconType()).isTrue();
    }

    @Test
    public void testHasSelectedWatchThisSpaceIconTypeWhenMissing() {
        final PreferenceHelper helper = mock(PreferenceHelper.class);
        when(helper.contains(AppSettings.KEY_WATCH_THIS_SPACE_ICON_TYPE)).thenReturn(false);

        final AppSettings settings = new AppSettings(helper);

        assertThat(settings.hasSelectedWatchThisSpaceIconType()).isFalse();
    }
}
