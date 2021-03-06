package com.a3house.appmedica.appmedica;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;


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

        final NumberPicker numberpickerKilos,numberPickerGramos;

        final Dialog dialog = new Dialog(activity);
        //dialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cuadro_dialogo_plantilla2);
        ///dialog.setTitle();

        numberpickerKilos = (NumberPicker)dialog.findViewById(R.id.numberPickerKilos);
        numberPickerGramos = (NumberPicker)dialog.findViewById(R.id.numberPickerGramos);

        numberpickerKilos.setMinValue(0);

        numberpickerKilos.setMaxValue(250);
        numberpickerKilos.setValue(50);


        numberPickerGramos.setMinValue(0);

        numberPickerGramos.setMaxValue(9);



        //Accion de DatePicker ELEGIR FECHA
        final EditText etFechaElegir = (EditText) dialog.findViewById(R.id.etPlannedDateELEGIR);
        ClaseAuxiliar claseaux = new ClaseAuxiliar();
        etFechaElegir.setText(claseaux.sacarFechaHoy());

        etFechaElegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(activity,etFechaElegir);

            }
        });


        //Boton que realiza la accion de guardar
        Button dialogButton = (Button) dialog.findViewById(R.id.bt_guardar);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //TODO GUARDAR LA INFO EN LA BASE DE DATOS;
                final GestionFirebase gf = new GestionFirebase();

                //Creamos el calendario del textview(de String a Calendar)
                ClaseAuxiliar miaux = new ClaseAuxiliar();
                Calendar miCalendario =new GregorianCalendar();
                miCalendario=(miaux.pasarAcalendario(etFechaElegir.getText().toString()));

                //Creamos la variacion de Peso con respecto a la ultima medicion
                //TODO recuperar el ultimo PESO de Firebase y compararlo con el nuevo
                  double miVariacion = 0;



                  //Creamos las notas
                  String miNotas = "";

                  //Cogemos el valor del Peso
                  double miValor = numberpickerKilos.getValue()+(numberPickerGramos.getValue()*0.1);

                //Creamos  el IMC
                double miImc = calcularIMC(ActivityDashboard.ultimoUsuario,miValor);




                  //Enviamos el nuevo Peso al FireBase
                  Peso nuevoPeso = new Peso(miCalendario,miVariacion,miImc,miNotas,miValor);
                  gf.enviarDatosPeso(nuevoPeso);
                  DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                  simbolos.setDecimalSeparator('.');
                  DecimalFormat df = new DecimalFormat("#.#", simbolos);
                  ActivityDashboard.btnPesoActual.setText(String.valueOf(miValor));
                  ActivityDashboard.btnIMCActual.setText(df.format(miImc));


                dialog.dismiss();




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
                // Añadimos uno al mes inicial porque empieza en cero
                final String selectedDate = ((day<10)?"0"+day:String.valueOf(day)) + "/" + (((month++)<10)?"0"+month:String.valueOf(month)) + "/" + year;

                etFecha.setText(selectedDate);
            }
        });
        newFragment.show(activity.getFragmentManager(),"datePicker");

    }


    public double calcularIMC(Usuario2 usuario, double peso) {
        // TODO  recibir los datos del Firebase sobre el usuario, comprobar que la division de double entre int no da problemas
        //Clase Usuario altura en centimetros
        //Clase Peso valor en Kilos
        double imc;

        //La formula es  imc= val/(alt*alt)
        imc = peso/((usuario.getAltura()*0.01) * (usuario.getAltura()*0.01));



            return  imc;
    }

    /**
     * La funcion recibe el objeto Peso de la base de datos y nos devuelve un String
     * Indicando cual es la clasificación de nuestro peso
     * imc = el índice de masa corporal
     * cad = mensaje que nos indica nuestra clasificacion de peso
     */

    public String clasificacionIMC(Double imc) {

        String cad="";
        if(imc<16.00){
            cad="Infrapeso: Delgadez Severa";
        }else if(imc<=16.00 || imc<=16.99){
            cad="Infrapeso: Delgadez moderada";
        }else if(imc<=17.00 ||imc<=18.49){
            cad="Infrapeso: Delgadez aceptable";
        }else if(imc<=18.50 || imc<=24.99){
            cad="Peso Normal";
        }else if(imc<=25.00 || imc<=29.99){
            cad="Sobrepeso: grado I";
        }else if(imc<=30.00 || imc<=34.99){
            cad="Sobrepeso: grado II";
        }else if(imc<=35.00 || imc <=40.00){
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
    public double calcularPesoIdeal(Usuario2 usuario) {
        // TODO implement here
        //La fórmula de Lorentz
        //Peso ideal masculino = estatura (en cm) – 100 – [(estatura en cm – 150) / 4]
        //Peso ideal femenino = estatura (en cm) – 100 – [(estatura en cm – 150) / 2,5]

        double idealMasculino = usuario.getAltura()-100 - ((usuario.getAltura() -150)/4);
        double idealFemenino = usuario.getAltura() - 100 -((usuario.getAltura() -150)/4);

        //Si el usuario es un hombre en el atributo sexo estara marcado como 'H'
        if(usuario.getSexo()=="H"){return idealMasculino;}
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