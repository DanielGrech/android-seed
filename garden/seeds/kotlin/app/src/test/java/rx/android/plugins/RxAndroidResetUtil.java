package rx.android.plugins;

/**
 * Utility to access the package-private {@link RxAndroidPlugins#reset()} method
 */
public class RxAndroidResetUtil {

    private RxAndroidResetUtil() {
        // No instances..
    }

    public static void reset() {
        RxAndroidPlugins.getInstance().reset();
    }
}