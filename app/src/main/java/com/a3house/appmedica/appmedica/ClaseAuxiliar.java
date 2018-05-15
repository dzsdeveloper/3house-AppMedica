package com.a3house.appmedica.appmedica;

public class ClaseAuxiliar {

    /**
     *
     */
    public String capitalizarNombre(String nombre) {
        String[] arrayNombre = nombre.split(" ");
        String nombreCapitalizado = "";
        for (int i = 0; i < arrayNombre.length; i++) {
            nombreCapitalizado = nombreCapitalizado + " " + arrayNombre[i].substring(0, 1).toUpperCase() + arrayNombre[i].substring(1);
        }
        return nombreCapitalizado.substring(1, nombreCapitalizado.length());
    }



}
