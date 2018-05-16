package com.a3house.appmedica.appmedica;

import java.util.*;

/**
 * 
 */
public class GestionUsuario {

    /**
     * Default constructor
     */
    public GestionUsuario() {
    }

    /**
     * Crea un objeto Usuario y lo envía al método enviarDatosUsuario del objeto GestionFirebase
     */
    public void crearUsuario(String nombre, String apellidos, int altura, char sexo) {
        // TODO Falta añadir en GestionFirebase.enviarDatosUsuario la variable que recibirá (usuario)
        Usuario usuario = new Usuario(nombre, apellidos, altura, sexo);
        GestionFirebase gf = new GestionFirebase();
        gf.enviarDatosUsuario(usuario);
    }

}