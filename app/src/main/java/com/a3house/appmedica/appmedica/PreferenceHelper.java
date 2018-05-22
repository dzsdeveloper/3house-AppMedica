package com.a3house.appmedica.appmedica;

public class PreferenceHelper {

        final public static String KEY_DEMO_NAME = "usuarios";

        //Función para guardar el nombre del usuario en SharedPreferences
        public static void setName(String value) {
            MainActivity.preferences.edit().putString(KEY_DEMO_NAME, value ).commit();
        }

        //Función para recuperar el nombre de usuario de SharedPreferences
        public static String getName() {
            return MainActivity.preferences.getString(KEY_DEMO_NAME,"");

        }

        //Función para borrar el usuario de SharedPreferences
        public static void logOutUser() {
            MainActivity.preferences.edit().clear().apply();
        }
}
