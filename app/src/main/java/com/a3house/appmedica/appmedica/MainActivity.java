package com.a3house.appmedica.appmedica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import butterknife.ButterKnife;

import static com.a3house.appmedica.appmedica.PreferenceHelper.getName;

public class MainActivity extends AppCompatActivity {

    //Creamos una variable global estatica para acceder a Shared Preferences
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Add a static method to use SharedPreference without Context Object
        preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);

        //Recuperamos el valor guardado en SharedPreferences
        //Comprobamos si ya hay un usuario guardado
        //String nombre = getName();
        String nombre = "Paco";

        if (nombre.equals("") ) {
            //Envia al nuevo usuario al registro
            Intent lanzadorRegistro = new Intent(this, ActivityRegistro.class);
            this.startActivity(lanzadorRegistro);
        }else {
            //Envia al usuario registrado al Dashboard
             Intent lanzadorDashboard = new Intent(this, ActivityDashboard.class);
             this.startActivity(lanzadorDashboard);
        }

    }

}