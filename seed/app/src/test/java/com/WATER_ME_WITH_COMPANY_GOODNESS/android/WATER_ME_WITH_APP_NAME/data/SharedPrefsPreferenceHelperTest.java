package com.{company_name}.android.{app_package_name_prefix}.data;

import android.content.SharedPreferences;

import com.{company_name}.android.{app_package_name_prefix}.{app_class_prefix}TestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith({app_class_prefix}TestRunner.class)
public class SharedPrefsPreferenceHelperTest {

    SharedPrefsPreferenceHelper preferenceHelper;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor sharedPrefsEditor;

    @Before
    public void setup() {
        sharedPrefsEditor = mock(SharedPreferences.Editor.class);
        sharedPrefs = mock(SharedPreferences.class);
        when(sharedPrefs.edit()).thenReturn(sharedPrefsEditor);
        when(sharedPrefsEditor.clear()).thenReturn(sharedPrefsEditor);
        preferenceHelper = new SharedPrefsPreferenceHelper(sharedPrefs);
    }

    @Test
    public void testRemove() {
        final String TEST_KEY = "_test_key";
        when(sharedPrefsEditor.remove(TEST_KEY)).thenReturn(sharedPrefsEditor);

        preferenceHelper.remove(TEST_KEY);

        verify(sharedPrefsEditor).remove(eq(TEST_KEY));
    }

    @Test
    public void testSetString() {
        final String TEST_KEY = "_test_key";
        final String TEST_VALUE = "_value";

        when(sharedPrefsEditor.putString(TEST_KEY, TEST_VALUE)).thenReturn(sharedPrefsEditor);
        preferenceHelper.set(TEST_KEY, TEST_VALUE);

        verify(sharedPrefsEditor).putString(eq(TEST_KEY), eq(TEST_VALUE));
    }

    @Test
    public void testSetLong() {
        final String TEST_KEY = "_test_key";
        final long TEST_VALUE = 1L;

        when(sharedPrefsEditor.putLong(TEST_KEY, TEST_VALUE)).thenReturn(sharedPrefsEditor);
        preferenceHelper.set(TEST_KEY, TEST_VALUE);

        verify(sharedPrefsEditor).putLong(eq(TEST_KEY), eq(TEST_VALUE));
    }

    @Test
    public void testSetFloat() {
        final String TEST_KEY = "_test_key";
        final float TEST_VALUE = 1F;

        when(sharedPrefsEditor.putFloat(TEST_KEY, TEST_VALUE)).thenReturn(sharedPrefsEditor);
        preferenceHelper.set(TEST_KEY, TEST_VALUE);

        verify(sharedPrefsEditor).putFloat(eq(TEST_KEY), eq(TEST_VALUE));
    }

    @Test
    public void testSetInt() {
        final String TEST_KEY = "_test_key";
        final int TEST_VALUE = 1;

        when(sharedPrefsEditor.putInt(TEST_KEY, TEST_VALUE)).thenReturn(sharedPrefsEditor);
        preferenceHelper.set(TEST_KEY, TEST_VALUE);

        verify(sharedPrefsEditor).putInt(eq(TEST_KEY), eq(TEST_VALUE));
    }

    @Test
    public void testSetBoolean() {
        final String TEST_KEY = "_test_key";
        final boolean TEST_VALUE = true;

        when(sharedPrefsEditor.putBoolean(TEST_KEY, TEST_VALUE)).thenReturn(sharedPrefsEditor);
        preferenceHelper.set(TEST_KEY, TEST_VALUE);

        verify(sharedPrefsEditor).putBoolean(eq(TEST_KEY), eq(TEST_VALUE));
    }

    @Test
    public void testGetString() {
        final String TEST_KEY = "_test_key";
        final String EXPECTED_VALUE = "_value";

        when(sharedPrefs.getString(TEST_KEY, null)).thenReturn(EXPECTED_VALUE);

        String val = preferenceHelper.get(TEST_KEY, null);
        assertThat(val).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    public void testGetLong() {
        final String TEST_KEY = "_test_key";
        final long EXPECTED_VALUE = 1L;

        when(sharedPrefs.getLong(TEST_KEY, -1L)).thenReturn(EXPECTED_VALUE);

        long val = preferenceHelper.get(TEST_KEY, -1L);
        assertThat(val).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    public void testGetFloat() {
        final String TEST_KEY = "_test_key";
        final float EXPECTED_VALUE = 1f;

        when(sharedPrefs.getFloat(TEST_KEY, -1f)).thenReturn(EXPECTED_VALUE);

        float val = preferenceHelper.get(TEST_KEY, -1f);
        assertThat(val).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    public void testGetInt() {
        final String TEST_KEY = "_test_key";
        final int EXPECTED_VALUE = 1;

        when(sharedPrefs.getInt(TEST_KEY, -1)).thenReturn(EXPECTED_VALUE);

        int val = preferenceHelper.get(TEST_KEY, -1);
        assertThat(val).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    public void testGetBoolean() {
        final String TEST_KEY = "_test_key";
        final boolean EXPECTED_VALUE = true;

        when(sharedPrefs.getBoolean(TEST_KEY, true)).thenReturn(EXPECTED_VALUE);

        boolean val = preferenceHelper.get(TEST_KEY, true);
        assertThat(val).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    public void testContains() {
        final String TEST_KEY = "_test_key";
        final boolean EXPECTED_VALUE = true;

        when(sharedPrefs.contains(TEST_KEY)).thenReturn(EXPECTED_VALUE);

        boolean val = preferenceHelper.contains(TEST_KEY);
        assertThat(val).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    public void testContainsWhenMissing() {
        final String TEST_KEY = "_test_key";
        final boolean EXPECTED_VALUE = false;

        when(sharedPrefs.contains(TEST_KEY)).thenReturn(EXPECTED_VALUE);

        boolean val = preferenceHelper.contains(TEST_KEY);
        assertThat(val).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    public void testClear() {
        preferenceHelper.clear();
        verify(sharedPrefsEditor).clear();
    }
}
