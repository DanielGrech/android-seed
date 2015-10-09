package com.{{company_name}}.android.{{app_package_name_prefix}}.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.{{company_name}}.android.{{app_package_name_prefix}}.BuildConfig;
import com.{{company_name}}.android.{{app_package_name_prefix}}.R;

import java.util.List;

/**
 * Common Intent-related functionality
 */
public class IntentUtils {

    private static final String GOOGLE_MAPS_DIRECTIONS_URI_PREFIX
            = "http://maps.google.com/maps?f=d&hl=en&z=13&daddr=";

    IntentUtils() {
        // No instances..
    }

    /**
     * Check if the given Intent has a component to handle it
     *
     * @param context Context used to query the {@link PackageManager}
     * @param intent  The intent to check
     * @return <code>true</code> if a component is available, <code>false</code> otherwise
     */
    public static boolean isAvailable(Context context, Intent intent) {
        final List<ResolveInfo> info = getResolveInfo(context, intent);
        return info != null && !info.isEmpty();
    }

    /**
     * @param destinationAddress The address to get directions too
     * @return An intent which will launch the native maps app
     */
    public static Intent getDirectionsIntent(String destinationAddress) {
        final String uri = GOOGLE_MAPS_DIRECTIONS_URI_PREFIX + destinationAddress;
        return new Intent(Intent.ACTION_VIEW).setData(Uri.parse(uri));
    }

    /**
     * Create an intent that will open the phones default dialer application with the
     * given number prepopulated
     *
     * @param phoneNumber The number to prepopulate the dialer with
     * @return An intent which can be passed to {@link Activity#startActivity(Intent)}
     */
    public static Intent getDialIntent(String phoneNumber) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;
    }

    /**
     * Create an intent that will open up a web link in an external browser
     *
     * @param url The url to view
     * @return An intent to open an external browser to the given page
     */
    public static Intent getUrlIntent(String url) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

    public static Intent getEmailIntent(String email, String subject) {
        return new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null))
                .putExtra(Intent.EXTRA_SUBJECT, subject);
    }

    public static Intent getPlayStoreIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID));
    }

    /**
     * Query the package manager with the given intent
     *
     * @param context Context used to query the {@link PackageManager}
     * @param intent  The intent to check
     * @return A list of components on the device which can handle the intent
     */
    static List<ResolveInfo> getResolveInfo(Context context, Intent intent) {
        final PackageManager manager = context.getPackageManager();
        return manager.queryIntentActivities(intent, 0);
    }
}
