package com.a3house.appmedica.appmedica;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ActivityNuevaVisita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_visita);
        //Soporta la toolbar para mostrar el menu
        //Toolbar toolbarNuevaVisita = (Toolbar) findViewById(R.id.toolbarNuevaVisita);
        //setSupportActionBar(toolbarNuevaVisita);
    }
}
