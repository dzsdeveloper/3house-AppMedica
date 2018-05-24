package com.a3house.appmedica.appmedica;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.DatePicker;
import android.widget.EditText;


import java.text.ParseException;
import java.util.Date;

import java.util.Calendar;

public class ClaseAuxiliar {

    /**
     *
     */
    public String capitalizarNombre(String nombre) {
        String[] arrayNombre = nombre.split(" ");
        String nombreCapitalizado = "";
        for (int i = 0; i < arrayNombre.length; i++) {
            nombreCapitalizado = nombreCapitalizado + " " + arrayNombre[i].substring(0, 1).toUpperCase() + arrayNombre[i].substring(1);
        }
        return nombreCapitalizado.substring(1, nombreCapitalizado.length());
    }

    public void showDatePickerDialog(final EditText view,Context context) {
        /**
         * @author Mohammed Al-Safwan
         * **/
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            /**
             *
             * @param year selected year.
             * @param month selected month.
             * @param day selected day.
             * **/
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

            }


        }, mYear, mMonth, mDay);

        // finally, show your dialog
        datePickerDialog.show();
    }



    /*
    * Devuelve la fecha de hoy en formato dd/mm/aaaa
    * Como un String separado por "/" teniendo en cuenta que si es menor de 9 añade un cero a la izquierda
    * */
    public StringBuilder sacarFechaHoy (){




        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);


        // Contruimos el String que vamos a devolver
        StringBuilder hoy = new StringBuilder()
                // Mes empieza por cero asi que añadimos 1
                .append((dd <10)?"0"+dd:String.valueOf(dd)).append("/")
                .append((mm++ <10)?"0"+mm:String.valueOf(mm)).append("/")
                .append(yy).append("");



        return hoy;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Calendar pasarAcalendario(String mifecha){

        //Use .parse() method to convert the String to Date object:

        Date dateString = null;
        try {
            dateString = new SimpleDateFormat("dd/MM/yyyy").parse(mifecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //getInstance() will create an instance of one of the Subclass of the abstract Calendar class:

        Calendar cal = Calendar.getInstance();

        //setTime() will take a Date object:

        cal.setTime(dateString);

        return cal;
    }

}
