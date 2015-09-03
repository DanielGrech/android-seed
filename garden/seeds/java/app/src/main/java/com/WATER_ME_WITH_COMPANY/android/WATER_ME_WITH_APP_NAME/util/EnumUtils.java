package com.{{company_name}}.android.{{app_package_name_prefix}}.util;

/**
 * Helper methods for dealing with java enums
 */
public class EnumUtils {

    EnumUtils() {
        // No instances..
    }

    public static <T extends Enum> T from(Class<T> cls, String value) {
        if (cls.isEnum()) {
            T[] values = cls.getEnumConstants();
            for (T val : values) {
                if (val.toString().equalsIgnoreCase(value)) {
                    return val;
                }
            }
        }

        return null;
    }

    public static <T extends Enum> T from(Class<T> cls, int ordinal) {
        if (cls.isEnum()) {
            T[] values = cls.getEnumConstants();
            for (T val : values) {
                if (val.ordinal() == ordinal) {
                    return val;
                }
            }
        }

        return null;
    }

}