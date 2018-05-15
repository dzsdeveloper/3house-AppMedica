package com.a3house.appmedica.appmedica;

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
     * 
     */
    public void crearVisita(Calendar fecha, String lugar, String doctor, String notas) {
        // TODO Sólo crea un objeto Visita... quizás innecesaria esta función
        Visita visita = new Visita(fecha, lugar, doctor, notas);
    }

    /**
     * 
     */
    public void mostrarVisitas() {
        // TODO Pedirá los datos en GestionFirebase
        // Get de la información desde GestionFirebase

    }

}