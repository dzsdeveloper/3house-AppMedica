package com.a3house.appmedica.appmedica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityPerfil extends AppCompatActivity {
    //variables
    private RecyclerView rv;
    private List<Usuario2> lstUser = new ArrayList<>();
    private AdapterUsuario adpU = new AdapterUsuario(lstUser);
    private GestionFirebase gf = new GestionFirebase();
    Button btnModificar;

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        btnModificar = (Button) findViewById(R.id.btnSiguiente);
        //Implementacion RecyclerView para PerfilActivity
        rv = (RecyclerView) findViewById(R.id.recycler_perfil);
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
    public void homePerfil(View v){
        this.finish();
    }
    public void modificar(View v){
        Intent lanzadorDashboard = new Intent(this, ActivityEditPerfil.class);
        this.startActivity(lanzadorDashboard);
    }
}
