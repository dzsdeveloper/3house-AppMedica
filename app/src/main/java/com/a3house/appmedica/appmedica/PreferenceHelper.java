package com.a3house.appmedica.appmedica;

public class PreferenceHelper {

        final public static String KEY_NOMBRE_USUARIO = "Nombre";
        final public static String KEY_ALTURA_USUARIO = "Altura";
        final public static String KEY_SEXO_USUARIO = "Sexo";

        //Función para guardar el nombre del usuario en SharedPreferences
        public static void setName(String value) {
            MainActivity.preferences.edit().putString(KEY_NOMBRE_USUARIO, value ).commit();
        }

        //Función para recuperar el nombre de usuario de SharedPreferences
        public static String getName() {
            return MainActivity.preferences.getString(KEY_NOMBRE_USUARIO,"");

        }

        //Función para guardar la altura del usuario en SharedPreferences
        public static void setAlturaUsuario(int value) {
            MainActivity.preferences.edit().putInt(KEY_ALTURA_USUARIO, value ).commit();
        }

        //Función para recuperar la altura del usuario de SharedPreferences
        public static String getAlturaUsuario() {
            return MainActivity.preferences.getString(KEY_ALTURA_USUARIO,"");

        }

        //Función para guardar el sexo del usuario en SharedPreferences
        public static void setSexoUsuario(String value) {
            MainActivity.preferences.edit().putString(KEY_SEXO_USUARIO, value ).commit();
        }

        //Función para recuperar el sexo de usuario de SharedPreferences
        public static String getSexoUsuario() {
            return MainActivity.preferences.getString(KEY_SEXO_USUARIO,"");

        }

        //Función para borrar el usuario de SharedPreferences
        public static void logOutUser() {
            MainActivity.preferences.edit().clear().apply();
        }
}
