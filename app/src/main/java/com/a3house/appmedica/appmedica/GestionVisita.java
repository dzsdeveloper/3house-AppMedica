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
    public static void crearVisita(int dia, int mes, int anyo, int hora, int minuto, String lugar,
                                   String doctor, String notas) {
        Calendar calendario = new GregorianCalendar();
        calendario.set(anyo, mes, dia, hora, minuto);
        Visita visita = new Visita(calendario, lugar, doctor, notas);
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