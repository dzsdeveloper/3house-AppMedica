package com.a3house.appmedica.appmedica;

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

public class ActivityListaPesos extends AppCompatActivity {

    GestionFirebase gf = new GestionFirebase();
    List<Peso2> lstPeso = new ArrayList<>();
    AdapterPeso adpP = new AdapterPeso(lstPeso);
    RecyclerView rvListaPesos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pesos);

        rvListaPesos = findViewById(R.id.rvListaPesos);
        rvListaPesos.setLayoutManager(new LinearLayoutManager(this));
        //Implementar Peso
        rvListaPesos.setAdapter(adpP);
        gf.crearReferencia().child("pesos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Peso2 p = new Peso2();
                final double var1, var2, var3;
                lstPeso.removeAll(lstPeso);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    p = new Peso2((String) hm.get("fecha"), Integer.parseInt(String.valueOf(hm.get("variacion"))), Double.parseDouble(String.valueOf(hm.get("imc"))), (String) hm.get("notas"), Double.parseDouble(String.valueOf(hm.get("valor"))));
                    lstPeso.add(p);
                }
                var1 = p.getValor();
                var2 = lstPeso.get(lstPeso.size() - 2).getValor();
                var3 = var1 - var2;
                //Recuperar la altura de un child especifico
                gf.crearReferencia().child("altura").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int h = Integer.parseInt(dataSnapshot.getValue().toString());
                        //Calculo del IMC
                        System.out.println("El IMC es: " + (var3 + h));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                adpP.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public void volver (View v){
        this.finish();
    }
}
