package com.a3house.appmedica.appmedica;

public class PreferenceHelper {

        final public static String KEY_DEMO_NAME = "usuarios";
        public static void setName(String value) {
            MainActivity.preferences.edit().putString(KEY_DEMO_NAME, value ).commit();
        }
        public static String getName() {
            return MainActivity.preferences.getString(KEY_DEMO_NAME,"");

        }
}
