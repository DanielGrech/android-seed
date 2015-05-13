package com.{company_name}.android.{app_package_name_prefix}.util;

import android.view.View;

import com.{company_name}.android.{app_package_name_prefix}.{app_package_name_prefix}TestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith({app_class_prefix}TestRunner.class)
public class ViewUtilsTest {

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
}
