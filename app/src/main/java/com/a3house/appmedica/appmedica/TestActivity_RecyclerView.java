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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestActivity_RecyclerView extends AppCompatActivity {
    Button btncoche;
    RecyclerView rv;
    List<Usuario2> lstUser;
    List<Peso2> lstPeso;
    List<Visita2> lstVisita;
    AdapterUsuario adpU;
    AdapterPeso adpP;
    AdapterVisita adpV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__recycler_view);
        btncoche = (Button) findViewById(R.id.btnEnviar);
        rv = (RecyclerView) findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        lstUser = new ArrayList<>();
        adpU = new AdapterUsuario(lstUser);
        lstPeso = new ArrayList<>();
        adpP = new AdapterPeso(lstPeso);
        lstVisita = new ArrayList<>();
        adpV = new AdapterVisita(lstVisita);
        rv.setAdapter(adpU);
        DatabaseReference myRef = db.getReference(GestionFirebase.DEMO_REFERENCE);

        myRef.child("perfil").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstUser.removeAll(lstUser);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Usuario2 user = new Usuario2((String) hm.get("nombre"),
                            (String) hm.get("apellidos"),
                            Integer.parseInt(String.valueOf(hm.get("altura"))),(String) hm.get("sexo"));

                    lstUser.add(user);
                }
                adpU.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        rv.setAdapter(adpP);
        myRef.child("pesos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstPeso.removeAll(lstPeso);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Peso2 p = new Peso2((String) hm.get("fecha"),Integer.parseInt(String.valueOf(hm.get("variacion"))),Double.parseDouble(String.valueOf(hm.get("imc"))),(String) hm.get("notas"),Double.parseDouble(String.valueOf(hm.get("valor"))) );
                    lstPeso.add(p);
                }
                adpP.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //rv.setAdapter(adpV);
        myRef.child("visitas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstVisita.removeAll(lstVisita);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Visita2 v = new Visita2((String) hm.get("fecha"),(String) hm.get("lugar"),(String) hm.get("doctor"),(String) hm.get("notas"));
                    lstVisita.add(v);
                }
                adpV.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void ver(View v){
        GestionFirebase gf = new GestionFirebase();
        //gf.recibirDatos();
        gf.enviarDatosUsuario();
        gf.enviarDatosVisita();
        gf.enviarDatosPeso();
    }

}
