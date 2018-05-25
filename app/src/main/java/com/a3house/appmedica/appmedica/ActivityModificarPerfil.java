package com.a3house.appmedica.appmedica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityModificarPerfil extends AppCompatActivity {
    EditText nombre, apellidos,altura;
    private RadioGroup rdSexo;
    //variables
    private RecyclerView rv;
    private List<Usuario2> lstUser = new ArrayList<>();
    private AdapterModificarPerfil adpU = new AdapterModificarPerfil(lstUser,this);
    private GestionFirebase gf = new GestionFirebase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_perfil);
        //Implementacion RecyclerView para PerfilActivity
        rv = (RecyclerView) findViewById(R.id.recycler_modificar);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adpU);
        //Obtener el ultimo elemento de perfil del Firebase

        gf.crearReferencia().child("perfil").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstUser.removeAll(lstUser);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //Obtencion de valores
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    String s = "";
                    if (((String) hm.get("sexo")).equals("H"))
                        s="Hombre";
                    if (((String) hm.get("sexo")).equals("M"))
                        s="Mujer";
                    //Creacion del objeto Usuario
                    Usuario2 user = new Usuario2((String) hm.get("nombre"),
                            (String) hm.get("apellidos"),
                            Integer.parseInt(String.valueOf(hm.get("altura"))), s);
                    lstUser.add(user);
                }
                adpU.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    /*
    public void crear(View v){
        char sexo=' ';
        GestionFirebase gf = new GestionFirebase();
        // Is the button checked?
        int radioButtonId = rdSexo.getCheckedRadioButtonId();
        RadioButton radiobutton = findViewById(radioButtonId);
        if (radiobutton.getText().toString().equals("Hombre"))
            sexo='H';
        if (radiobutton.getText().toString().equals("Mujer"))
            sexo='M';
        Usuario usr = new Usuario(nombre.getText().toString(),apellidos.getText().toString(),Integer.parseInt(altura.getText().toString()),sexo);
        gf.enviarDatosUsuario(usr);
        this.finish();
    }*/

}
