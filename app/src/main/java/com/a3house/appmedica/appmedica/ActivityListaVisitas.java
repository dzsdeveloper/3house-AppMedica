package com.a3house.appmedica.appmedica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityListaVisitas extends AppCompatActivity {

    RecyclerView rvListaVisitas;
    List<Visita2> lstVisita = new ArrayList<>();
    AdapterVisita adpV = new AdapterVisita(lstVisita);
    GestionFirebase gf = new GestionFirebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_visitas);

        rvListaVisitas = findViewById(R.id.rvListaVisitas);
        rvListaVisitas.setLayoutManager(new LinearLayoutManager(this));
        //Implementar Visita
        rvListaVisitas.setAdapter(adpV);
        gf.crearReferencia().child("visitas").addValueEventListener(new ValueEventListener() {
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

    public void volver (View v){ this.finish();
    }

    public void solicitarNuevaVisita(View v){
        Intent lanzadorNuevaVisita = new Intent(this, ActivityNuevaVisita.class);
        this.startActivity(lanzadorNuevaVisita);
    }
}
