package com.a3house.appmedica.appmedica;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ActivityRegistro extends AppCompatActivity {
    EditText nombre, apellidos,altura;
    private RadioGroup rdSexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = (EditText) findViewById(R.id.edtNombre);
        apellidos = (EditText) findViewById(R.id.edtApellidos);
        altura = (EditText) findViewById(R.id.edtAltura);
        rdSexo=findViewById(R.id.selectorSexo);
    }
    public void crear(View v){
        char sexo=' ';
        GestionFirebase gf = new GestionFirebase();
        // Is the button checked?
        int radioButtonId = rdSexo.getCheckedRadioButtonId();
        RadioButton radiobutton = findViewById(radioButtonId);
        if (radiobutton.getText().toString().equals("Hombre"))
            sexo='H';
        else
            sexo='M';
        Usuario usr = new Usuario(nombre.getText().toString(),apellidos.getText().toString(),Integer.parseInt(altura.getText().toString()),sexo);
        gf.enviarDatosUsuario(usr);
        this.finish();
    }
}