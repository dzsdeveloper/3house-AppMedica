package com.a3house.appmedica.appmedica;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class ActivityNuevaVisita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_visita);

        final EditText edtFecha = findViewById(R.id.edtFecha);
        edtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(ActivityNuevaVisita.this, edtFecha);
            }
        });

        final EditText edtHora = findViewById(R.id.edtHora);
        edtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(ActivityNuevaVisita.this, edtHora);
            }
        });

        //Boton que realiza la accion de guardar
       /* Button dialogButton = (Button) findViewById(R.id.btnGuardarVisita);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO GUARDAR LA INFO EN LA BASE DE DATOS;
            }
        });*/
    }

   /* @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtFecha:
                showDatePickerDialog();
                break;
        }
    }*/

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
       //(activity.getSupportFragmentManager(), "datePicker");
   }
}
