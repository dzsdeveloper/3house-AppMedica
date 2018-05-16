package com.a3house.appmedica.appmedica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Envia al nuevo usuario al registro
        Intent lanzadorRegistro = new Intent(this, ActivityRegistro.class);
        this.startActivity(lanzadorRegistro); */

        /*Envia al usuario ya registrado al Dashboard/Panel Principal de la app */
        Intent lanzadorDashboard = new Intent(this, ActivityDashboard.class);
        this.startActivity(lanzadorDashboard);
    }
}
