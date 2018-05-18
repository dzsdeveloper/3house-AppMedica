package com.a3house.appmedica.appmedica;

import android.widget.EditText;

import java.time.LocalDateTime;
import java.util.*;

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
     * Crea un objeto Visita y lo envía al método enviarDatosUsuario del objeto GestionFirebase
     */
    public void crearVisita() {
        // TODO Falta añadir en GestionFirebase.enviarDatosVisita la variable que recibirá (visita)
        /*LocalDateTime fecha = new LocalDateTime();
        fecha.
        Visita visita = new Visita(fecha, lugar, doctor, notas);
        GestionFirebase gf = new GestionFirebase();
        gf.enviarDatosVisita(visita);*/
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