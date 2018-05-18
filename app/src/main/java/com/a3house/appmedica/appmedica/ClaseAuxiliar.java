package com.a3house.appmedica.appmedica;

import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

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




}
