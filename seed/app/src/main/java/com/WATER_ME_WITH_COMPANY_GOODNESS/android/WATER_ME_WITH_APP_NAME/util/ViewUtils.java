package com.{company_name}.android.{app_package_name_prefix}.util;

import android.view.View;

/**
 *
 */
public class ViewUtils {

    public static void hide(View... views) {
        if (views != null) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    public static void show(View... views) {
        if (views != null) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
