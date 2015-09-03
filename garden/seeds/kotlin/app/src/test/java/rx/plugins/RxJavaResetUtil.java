package rx.plugins;

/**
 * Utility to access the package-private {@link RxJavaPlugins#reset()} method
 */
public class RxJavaResetUtil {

    private RxJavaResetUtil() {
        // No instances..
    }

    public static void reset() {
        RxJavaPlugins.getInstance().reset();
    }
}