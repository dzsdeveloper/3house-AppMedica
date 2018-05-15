package com.a3house.appmedica.appmedica;

import java.util.*;

/**
 * 
 */
public class Usuario {

    private String nombre;

    /**
     *
     */
    private String apellidos;

    /**
     *
     */
    private double altura;

    /**
     * Default constructor
     */
    public Usuario(String nombre, String apellidos, double altura){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.altura = altura;
    }


    /**
     * @return
     */
    public String getNombre() {
        // TODO implement here
        return nombre;
    }

    /**
     * @param value
     */
    public void setNombre(String value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getApellidos() {
        // TODO implement here
        return apellidos;
    }

    /**
     * @param value
     */
    public void setApellidos(String value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public double getAltura() {
        // TODO implement here
        return altura;
    }

    /**
     * @param value
     */
    public void setAltura(double value) {
        // TODO implement here
    }

}