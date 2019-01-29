package com.ms.util;

import java.util.Properties;

/**
 * Created by lenovo on 2018/2/28.
 */
public class PropertiesUtils {


    public static Integer getInt(Properties properties, String key, Integer defaultVal) {
        try {
            String valStr = String.valueOf(properties.get(key));
            return Integer.valueOf(Integer.parseInt(valStr.trim()));
        } catch (Exception localException) {
        }
        return defaultVal;
    }

    public static boolean getBoolean(Properties properties, String key, boolean defaultVal) {
        try {
            String valStr = getString(properties, key);
            return Boolean.parseBoolean(valStr.trim());
        } catch (Exception localException) {
        }
        return defaultVal;
    }
    public static Long getBoolean(Properties properties, String key, Long defaultVal) {
        try {
            String valStr = getString(properties, key);
            return Long.parseLong(valStr.trim());
        } catch (Exception localException) {
        }
        return defaultVal;
    }


    public static String getString(Properties properties, String key) {
        return String.valueOf(properties.get(key));
    }

    public static String getString(Properties properties, String key, String defaultVal) {
        try {
            return getString(properties, key);
        } catch (Exception localException) {
        }
        return defaultVal;
    }
}
