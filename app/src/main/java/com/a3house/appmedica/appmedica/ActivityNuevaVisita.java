package com.a3house.appmedica.appmedica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityNuevaVisita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_visita);
        ///Accion de DatePicker HOY
        EditText edtFecha = findViewById(R.id.edtFecha);

        edtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
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

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"MI ETIQUETA");
    }
}
