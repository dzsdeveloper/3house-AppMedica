package com.a3house.appmedica.appmedica;

import android.support.v7.widget.RecyclerView;

import java.util.*;
import com.a3house.appmedica.appmedica.GestionFirebase.class;

/**
 * 
 */
public class GestionVisita {

    /**
     * Default constructor
     */
    public GestionVisita() {

    }

    /**
     * Crea objeto visita y lo envía a través del método enviarDatosVisita de la clase GestionFirebase
     */
    public void crearVisita(Calendar fecha, String lugar, String doctor, String notas) {
        // TODO Falta añadir en GestionFirebase.enviarDatosVisita la variable que recibirá (visita)
        Visita visita = new Visita(fecha, lugar, doctor, notas);
        GestionFirebase gf = new GestionFirebase();
        gf.enviarDatosVisita(visita);
    }

    /**
     * 
     */
    public void mostrarVisitas() {
        // TODO Pendiente. Falta saber como recibirá los datos de GestionFirebase
        GestionFirebase gf = new GestionFirebase();
        gf.recibirDatos();

    }

}