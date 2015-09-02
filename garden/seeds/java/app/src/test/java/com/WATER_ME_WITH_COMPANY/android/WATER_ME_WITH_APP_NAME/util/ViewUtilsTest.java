package com.{{company_name}}.android.{{app_package_name_prefix}}.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}TestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith({{app_class_prefix}}TestRunner.class)
public class ViewUtilsTest {

    @Test
    public void testConstructor() {
        // For code coverage..
        new ViewUtils();
    }

    @Test
    public void testHideHandlesEmptyInput() {
        ViewUtils.hide();
    }

    @Test
    public void testHideHandlesNullInput() {
        ViewUtils.hide((View[]) null);
    }

    @Test
    public void testHideHandlesNullViewInput() {
        ViewUtils.hide((View) null);
    }

    @Test
    public void testHideHandlesOneViewInput() {
        final View view = new View(RuntimeEnvironment.application);
        view.setVisibility(View.VISIBLE);

        ViewUtils.hide(view);

        assertThat(view.getVisibility()).isEqualTo(View.GONE);
    }

    @Test
    public void testHideWithMultipleViews() {
        final View[] views = {
                new View(RuntimeEnvironment.application),
                new View(RuntimeEnvironment.application),
                new View(RuntimeEnvironment.application)
        };
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }

        ViewUtils.hide(views);

        for (View view : views) {
            assertThat(view.getVisibility()).isEqualTo(View.GONE);
        }
    }

    @Test
    public void testHideInvisibleHandlesEmptyInput() {
        ViewUtils.hideInvisible();
    }

    @Test
    public void testHideInvisibleHandlesNullInput() {
        ViewUtils.hideInvisible((View[]) null);
    }

    @Test
    public void testHideInvisibleHandlesNullViewInput() {
        ViewUtils.hideInvisible((View) null);
    }

    @Test
    public void testHideInvisibleHandlesOneViewInput() {
        final View view = new View(RuntimeEnvironment.application);
        view.setVisibility(View.VISIBLE);

        ViewUtils.hideInvisible(view);

        assertThat(view.getVisibility()).isEqualTo(View.INVISIBLE);
    }

    @Test
    public void testHideInvisibleWithMultipleViews() {
        final View[] views = {
                new View(RuntimeEnvironment.application),
                new View(RuntimeEnvironment.application),
                new View(RuntimeEnvironment.application)
        };
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }

        ViewUtils.hideInvisible(views);

        for (View view : views) {
            assertThat(view.getVisibility()).isEqualTo(View.INVISIBLE);
        }
    }

    @Test
    public void testShowHandlesEmptyInput() {
        ViewUtils.show();
    }

    @Test
    public void testShowHandlesNullInput() {
        ViewUtils.show((View[]) null);
    }

    @Test
    public void testShowHandlesNullViewInput() {
        ViewUtils.show((View) null);
    }

    @Test
    public void testShowHandlesOneViewInput() {
        final View view = new View(RuntimeEnvironment.application);
        view.setVisibility(View.GONE);

        ViewUtils.show(view);

        assertThat(view.getVisibility()).isEqualTo(View.VISIBLE);
    }

    @Test
    public void testShowWithMultipleViews() {
        final View[] views = {
                new View(RuntimeEnvironment.application),
                new View(RuntimeEnvironment.application),
                new View(RuntimeEnvironment.application)
        };
        for (View view : views) {
            view.setVisibility(View.GONE);
        }

        ViewUtils.show(views);

        for (View view : views) {
            assertThat(view.getVisibility()).isEqualTo(View.VISIBLE);
        }
    }

    @Test
    public void testVisibileWhenTrue() {
        final View view = new View(RuntimeEnvironment.application);
        view.setVisibility(View.GONE);

        ViewUtils.visibleWhen(true, view);

        assertThat(view.getVisibility()).isEqualTo(View.VISIBLE);
    }

    @Test
    public void testVisibileWhenFalse() {
        final View view = new View(RuntimeEnvironment.application);
        view.setVisibility(View.VISIBLE);

        ViewUtils.visibleWhen(false, view);

        assertThat(view.getVisibility()).isEqualTo(View.GONE);
    }

    @Test
    public void testFixToSizeWithNoLayoutParams() {
        final int EXPECTED_SIZE = 100;
        final View view = new View(RuntimeEnvironment.application);

        assertThat(view.getLayoutParams()).isNull();

        ViewUtils.fixToSize(view, EXPECTED_SIZE);

        final ViewGroup.LayoutParams lps = view.getLayoutParams();

        assertThat(lps).isNotNull();
        assertThat(lps.width).isEqualTo(EXPECTED_SIZE);
        assertThat(lps.height).isEqualTo(EXPECTED_SIZE);
    }

    @Test
    public void testFixToSizeWithExistingLayoutParams() {
        final int EXPECTED_SIZE = 100;
        final View view = new View(RuntimeEnvironment.application);
        view.setLayoutParams(new ViewGroup.LayoutParams(EXPECTED_SIZE * 2, EXPECTED_SIZE * 2));

        ViewUtils.fixToSize(view, EXPECTED_SIZE);

        final ViewGroup.LayoutParams lps = view.getLayoutParams();

        assertThat(lps).isNotNull();
        assertThat(lps.width).isEqualTo(EXPECTED_SIZE);
        assertThat(lps.height).isEqualTo(EXPECTED_SIZE);
    }

    @Test
    public void testDpToPx() {
        final float DENSITY = 3f;

        final int DPS = 100;
        final float EXPECTED_PX = DPS * DENSITY;

        final DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics.densityDpi = DisplayMetrics.DENSITY_XXXHIGH;
        displayMetrics.density = DENSITY;

        final Resources mockResources = mock(Resources.class);
        when(mockResources.getDisplayMetrics()).thenReturn(displayMetrics);

        final Context context = mock(Context.class);
        when(context.getResources()).thenReturn(mockResources);

        final float px = ViewUtils.dpToPx(context, DPS);

        assertThat(px).isEqualTo(EXPECTED_PX);
    }

    @Test
    public void testHideKeyboard() {
        final InputMethodManager imm = mock(InputMethodManager.class);
        final IBinder mockBinder = mock(IBinder.class);

        final Context context = mock(Context.class);
        when(context.getSystemService(Context.INPUT_METHOD_SERVICE)).thenReturn(imm);

        final View view = mock(View.class);
        when(view.getWindowToken()).thenReturn(mockBinder);
        when(view.getContext()).thenReturn(context);

        ViewUtils.hideKeyboard(view);

        verify(imm).hideSoftInputFromWindow(mockBinder, 0);
    }

    @Test
    public void testHideKeyboardWithNullInput() {
        ViewUtils.hideKeyboard(null);
    }
}
