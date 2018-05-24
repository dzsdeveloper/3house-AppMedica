package com.a3house.appmedica.appmedica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.a3house.appmedica.appmedica.PreferenceHelper.getAlturaUsuario;
import static com.a3house.appmedica.appmedica.PreferenceHelper.getName;
import static com.a3house.appmedica.appmedica.PreferenceHelper.getSexoUsuario;

public class MainActivity extends AppCompatActivity {

    //Creamos una variable global estatica para acceder a Shared Preferences
    public static SharedPreferences preferences;

    //Creamos variables privadas para el ProgressBar
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Botones Login y Registro

        final Button botonAcceder = findViewById(R.id.btnAcceder);
        botonAcceder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lanzadorDashboard();
            }
        });

        final Button botonRegistro = findViewById(R.id.btnRegistro);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lanzadorRegistro();
            }
        });

        //Add a static method to use SharedPreference without Context Object
        preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);

        //Recuperamos el valor guardado en SharedPreferences
        //y comprobamos si ya hay un usuario guardado
        String nombre = getName();

        if (nombre.equals("") ) {
            //Envia al nuevo usuario al registro
            lanzadorRegistro();
        }else {
            //Envia al usuario registrado al Dashboard
            lanzadorDashboard();
        }
    }

    public void lanzadorRegistro(){
        Intent lanzadorRegistro = new Intent(this, ActivityRegistro.class);
        this.startActivity(lanzadorRegistro);
    }

    public void lanzadorDashboard(){
        Intent lanzadorDashboard = new Intent(this, ActivityDashboard.class);
        this.startActivity(lanzadorDashboard);
    }



}