package com.{{company_name}}.android.{{app_package_name_prefix}}.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Common utilities and helper methods to perform on {@link View} objects
 */
public class ViewUtils {

    ViewUtils() {
        // No instances
    }

    /**
     * Set the given string as the text of the given TextView, or hide the view completely if it
     * is empty
     *
     * @param tv   The view to set the text on
     * @param text The text to set
     */
    public static void setTextOrHide(TextView tv, String text) {
        if (TextUtils.isEmpty(text)) {
            hide(tv);
        } else {
            tv.setText(text);
            show(tv);
        }
    }

    /**
     * Returns the x/y coordinates of the given view on the screen
     *
     * @param view The view to find the position of
     * @return The coordinates of the view on screen
     */
    public static Point getLocationOnScreen(View view) {
        if (view == null) {
            return null;
        }

        int[] position = new int[2];
        view.getLocationOnScreen(position);
        return new Point(position[0], position[1]);
    }

    /**
     * Gets the current status bar height. Useful for when having a translucentStatusBar
     * on lollipop+
     *
     * @param activity The activity which is displaying the status bar
     * @return The size of the windows decoration views, such as the status bar
     */
    public static int getStatusBarCompensation(Activity activity) {
        final DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        return dm.heightPixels -
                activity.findViewById(android.R.id.content).getMeasuredHeight();
    }

    /**
     * Starts an activity with an animation scaling up from the given view
     *
     * @param intent The intent to describing the new activity
     * @param view   The view from which to scale up
     */
    @TargetApi(Api.JELLYBEAN)
    public static void startActivity(@NonNull Intent intent, @NonNull View view) {
        final Context context = view.getContext();
        if (Api.isMin(Api.JELLYBEAN)) {
            context.startActivity(intent, createJellyBeanTransitionBundle(view));
        } else {
            context.startActivity(intent);
        }
    }

    /**
     * Starts an activity with an animation scaling up from the given view
     *
     * @param context     The context of the transition, must be an activity
     * @param intent      The intent to describing the new activity
     * @param requestCode the code of the request for future tracking
     * @param view        The view from which to scale up
     */
    @TargetApi(Api.JELLYBEAN)
    public static void startActivityForResult(@NonNull Activity context, @NonNull Intent intent, int requestCode, @NonNull View view) {
        if (Api.isMin(Api.JELLYBEAN)) {
            context.startActivityForResult(intent, requestCode, createJellyBeanTransitionBundle(view));
        } else {
            context.startActivityForResult(intent, requestCode);
        }
    }

    @TargetApi(Api.JELLYBEAN)
    public static Bundle createJellyBeanTransitionBundle(View view) {
        final int w = view.getWidth();
        final int h = view.getHeight();
        return ActivityOptions.makeScaleUpAnimation(view, w / 2, h / 2, w, h).toBundle();
    }

    /**
     * Runs the given action in an {@link OnPreDrawListener}
     *
     * @param view   The view from which to extract the {@link OnPreDrawListener}
     * @param action The action to perform
     */
    public static void onPreDraw(final View view, final Runnable action) {
        if (view != null && action != null) {
            final ViewTreeObserver vto = view.getViewTreeObserver();
            if (vto.isAlive()) {
                vto.addOnPreDrawListener(new OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        ViewTreeObserver vto = view.getViewTreeObserver();
                        if (vto.isAlive()) {
                            vto.removeOnPreDrawListener(this);
                        }

                        action.run();

                        return true;
                    }
                });
            }
        }
    }

    /**
     * Sets the given views LayoutParams to ensure that the width and height match the given size
     *
     * @param view The view to fix in size
     * @param size The size to fix the view
     */
    public static void fixToSize(View view, int size) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(size, size);
        } else {
            params.width = size;
            params.height = size;
        }
        view.setLayoutParams(params);
    }

    /**
     * Converts a value from dp to px
     *
     * @param context Context object used to calculate the density factor
     * @param dpiVal  Value to dps to convert
     * @return The given dp value in pixels based off the current screen density
     */
    public static float dpToPx(Context context, int dpiVal) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpiVal, r.getDisplayMetrics());
    }

    /**
     * Set the view visibility to either {@link View#VISIBLE} or {@link View#GONE} based off the
     * given boolean
     *
     * @param setVisible Whether or not the given views should be visible or not
     * @param views      The views to operate on
     */
    public static void visibleWhen(boolean setVisible, View... views) {
        setVisibility(setVisible ? View.VISIBLE : View.GONE, views);
    }

    /**
     * Set the visibility of all the given views to {@link View#GONE}
     *
     * @param views The views to hide
     */
    public static void hide(View... views) {
        setVisibility(View.GONE, views);
    }

    /**
     * Set the visibility of all the given views to {@link View#INVISIBLE}
     *
     * @param views The views to
     */
    public static void hideInvisible(View... views) {
        setVisibility(View.INVISIBLE, views);
    }

    /**
     * Set the visibility of all the given views to {@link View#VISIBLE}
     *
     * @param views The views to show
     */
    public static void show(View... views) {
        setVisibility(View.VISIBLE, views);
    }

    /**
     * Hides the soft-input keyboard
     */
    public static void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm
                    = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Set the visibility of all given views
     *
     * @param visibility The visibility of the view. Eg. {@link View#VISIBLE}
     * @param views      The views to set the visibility of
     */
    private static void setVisibility(int visibility, View... views) {
        if (views != null) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(visibility);
                }
            }
        }
    }
}