package com.a3house.appmedica.appmedica;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;


/**
 * 
 */
public class GestionPeso implements View.OnClickListener {

    /**
     * Default constructor
     */
    public GestionPeso() {
    }

    /**
     * 
     */


    public void introducirPesoshowDialog(final Activity activity, String msg){

        NumberPicker numberpickerKilos,numberPickerGramos;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cuadro_dialogo_plantilla2);
        dialog.setTitle("Introduce tu peso");



        numberpickerKilos = (NumberPicker)dialog.findViewById(R.id.numberPickerKilos);
        numberPickerGramos = (NumberPicker)dialog.findViewById(R.id.numberPickerGramos);

        numberpickerKilos.setMinValue(0);

        numberpickerKilos.setMaxValue(250);
        numberpickerKilos.setValue(50);


        numberPickerGramos.setMinValue(0);

        numberPickerGramos.setMaxValue(9);








        //Accion de DatePicker ELEGIR FECHA
        final EditText etFechaElegir = (EditText) dialog.findViewById(R.id.etPlannedDateELEGIR);

        etFechaElegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(activity,etFechaElegir);

            }
        });


        //Boton que realiza la accion de guardar
        Button dialogButton = (Button) dialog.findViewById(R.id.bt_guardar);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO GUARDAR LA INFO EN LA BASE DE DATOS;
            }
        });

        //Accion boton cancelar
        Button btcancelar= (Button) dialog.findViewById(R.id.bt_cancelar);

        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


        dialog.show();


    }



    private void showDatePickerDialog(Activity activity, final EditText etFecha ) {

        
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etFecha.setText(selectedDate);
            }
        });
        newFragment.show(activity.getFragmentManager(),"datePicker");

    }


    public double calcularIMC(Usuario usuario, Peso peso) {
        // TODO  recibir los datos del Firebase sobre el usuario, comprobar que la division de double entre int no da problemas
        //Clase Usuario altura en centimetros
        //Clase Peso valor en Kilos
        double imc;

        //La formula es  imc= val/(alt*alt)
        imc = peso.getValor()/(usuario.getAltura()*usuario.getAltura());



            return  imc;
    }

    /**
     * La funcion recibe el objeto Peso de la base de datos y nos devuelve un String
     * Indicando cual es la clasificación de nuestro peso
     * imc = el índice de masa corporal
     * cad = mensaje que nos indica nuestra clasificacion de peso
     */
    public String clasificacionIMC(Peso peso) {

        String cad="";
        if(peso.getImc()<16.00){
            cad="Infrapeso: Delgadez Severa";
        }else if(peso.getImc()<=16.00 || peso.getImc()<=16.99){
            cad="Infrapeso: Delgadez moderada";
        }else if(peso.getImc()<=17.00 ||peso.getImc()<=18.49){
            cad="Infrapeso: Delgadez aceptable";
        }else if(peso.getImc()<=18.50 || peso.getImc()<=24.99){
            cad="Peso Normal";
        }else if(peso.getImc()<=25.00 || peso.getImc()<=29.99){
            cad="Sobrepeso: grado I";
        }else if(peso.getImc()<=30.00 || peso.getImc()<=34.99){
            cad="Sobrepeso: grado II";
        }else if(peso.getImc()<=35.00 || peso.getImc() <=40.00){
            cad="Sobrepeso: grado III";
        }else{
            cad="no existe clasificacion";
        }

        return cad;

    }

    /**
     * La funcion recibe el ultimo peso registrado pesoAnterior y el nuevo peso que hemos introducido pesoNuevo
     * Hace la resta del pesoNuevo menos el pesoAnterior, de tal forma que el resultado
     * si es positivo +cantidad sera el peso que hemos engordado
     * si es negativo -cantidad sera el peso que hemos adelgazado
     */
    public double calcularVariacion(Peso pesoAnterior, Peso pesoNuevo) {
        // TODO revisar si hay que inicializarlo

        //double pesoFinal = 0;

        double pesoFinal = pesoNuevo.getValor()-pesoAnterior.getValor();

        return pesoFinal;
    }

    /**
     * 
     */
    public void mostrarPesos() {
        // TODO hay que vincular los textView mediante data Binding
    }

    /**
     * La funcion recibe la altura registrada en Usuario y el valor registrado en Peso
     * Realiza el calculo mediante la formula de Lorentz
     */
    public double calcularPesoIdeal(Usuario usuario) {
        // TODO implement here
        //La fórmula de Lorentz
        //Peso ideal masculino = estatura (en cm) – 100 – [(estatura en cm – 150) / 4]
        //Peso ideal femenino = estatura (en cm) – 100 – [(estatura en cm – 150) / 2,5]

        double idealMasculino = usuario.getAltura()-100 - ((usuario.getAltura() -150)/4);
        double idealFemenino = usuario.getAltura() - 100 -((usuario.getAltura() -150)/4);

        //Si el usuario es un hombre en el atributo sexo estara marcado como 'H'
        if(usuario.getSexo()=='H'){return idealMasculino;}
        else{return idealFemenino;}

    }

    @Override
    public void onClick(View v) {

    }

  /*  @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case
                R.id.etPlannedDateHOY:
                showDatePickerDialog();
                break;
            case
                R.id.etPlannedDateELEGIR:
                showDatePickerDialog();
                break;
            case
                R.id.bt_guardar:
                //TODO GUARDAR LOS DATOS EN FIREBASE
                break;
        }
    }*/



}