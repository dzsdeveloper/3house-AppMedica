package com.a3house.appmedica.appmedica;

import java.util.*;

/**
 * 
 */
public class Peso {




    /**
     * 
     */
    public Calendar fecha;

    /**
     * 
     */
    public double variacion;

    /**
     * 
     */
    public double imc;

    /**
     * 
     */
    public String notas;

    /**
     * 
     */
    public double valor;

    /**
     *
     */
    public char sexo;

    /**
     * Default constructor
     */
    public Peso( Calendar fecha, double variacion, double imc, String notas, double valor ) {

        this.fecha = fecha;
        this.variacion = variacion;
        this.imc = imc;
        this.notas = notas;
        this.valor = valor;


    }

    /**
     * @return
     */
    public Calendar getFecha() {
        // TODO implement here
        return fecha;
    }

    /**
     * @param value
     */
    public void setFecha(Calendar value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public double getVariacion() {
        // TODO implement here
        return variacion;
    }

    /**
     * @param value
     */
    public void setVariacion(double value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public double getImc() {
        // TODO implement here pendiente
        return imc;
    }

    /**
     * @param value
     */
    public void setImc(double value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getNotas() {
        // TODO implement here
        return notas;
    }

    /**
     * @param value
     */
    public void setNotas(String value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public double getValor() {
        // TODO implement here
        return valor;
    }

    /**
     * @param value
     */
    public void setValor(double value) {
        // TODO implement here
    }



}