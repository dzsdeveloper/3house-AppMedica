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
    private int altura;

    /**
     *
     */
    private char sexo;
    /**
     * Default constructor
     */
    public Usuario(String nombre, String apellidos, int altura, char sexo){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.altura = altura;
        this.sexo = sexo;
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
    public int getAltura() {
        // TODO implement here
        return altura;
    }

    /**
     * @param value
     */
    public void setAltura(int value) {
        // TODO implement here
    }

    public char getSexo() {
        // TODO implement here
        return sexo;
    }

    /**
     * @param value
     */
    public void setSexo(char value) {
        // TODO implement here
    }

}