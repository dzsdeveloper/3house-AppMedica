package com.a3house.appmedica.appmedica;

import java.util.*;

/**
 * 
 */
public class Visita {

    /**
     * 
     */
    private Calendar fecha;

    /**
     * 
     */
    private String lugar;

    /**
     * 
     */
    private String doctor;

    /**
     * 
     */
    private String notas;

    /**
     * Default constructor
     */
    public Visita(Calendar fecha, String lugar, String doctor, String notas) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.doctor = doctor;
        this.notas = notas;
    }

    /**
     * @return
     */
    public Calendar getFecha() {
        // TODO implement here
        return fecha;
    }

    /**
     * @param fecha
     */
    public void setFecha(Calendar fecha) {
        // TODO implement here
        this.fecha = fecha;
    }

    /**
     * @return
     */
    public String getLugar() {
        // TODO implement here
        return lugar;
    }

    /**
     * @param lugar
     */
    public void setLugar(String lugar) {
        // TODO implement here
        this.lugar = lugar;
    }

    /**
     * @return
     */
    public String getDoctor() {
        // TODO implement here
        return doctor;
    }

    /**
     * @param doctor
     */
    public void setDoctor(String doctor) {
        // TODO implement here
        this.doctor = doctor;
    }

    /**
     * @return
     */
    public String getNotas() {
        // TODO implement here
        return notas;
    }

    /**
     * @param notas
     */
    public void setNotas(String notas) {
        // TODO implement here
        this.notas = notas;
    }

}