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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class TestActivity_RecyclerView extends AppCompatActivity {
    //variables
    RecyclerView rv;
    List<Usuario2> lstUser = new ArrayList<>();
    List<Peso2> lstPeso = new ArrayList<>();
    List<Visita2> lstVisita = new ArrayList<>();
    AdapterUsuario adpU = new AdapterUsuario(lstUser);
    AdapterPeso adpP = new AdapterPeso(lstPeso);
    AdapterVisita adpV = new AdapterVisita(lstVisita);
    GestionFirebase gf = new GestionFirebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__recycler_view);
        //RecyclerView
        Button btncoche = (Button) findViewById(R.id.btnEnviar);
        rv = (RecyclerView) findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //Implementar Usuario
        rv.setAdapter(adpU);
        gf.crearReferencia().child("perfil").addValueEventListener(new ValueEventListener() {
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
        //Implementar Peso
        rv.setAdapter(adpP);
        gf.crearReferencia().child("pesos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstPeso.removeAll(lstPeso);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Peso2 p = new Peso2((String) hm.get("fecha"),Integer.parseInt(String.valueOf(hm.get("variacion"))),Double.parseDouble(String.valueOf(hm.get("imc"))),(String) hm.get("notas"),Double.parseDouble(String.valueOf(hm.get("valor"))) );
                    lstPeso.add(p);
                }
                Peso2 p2 = gf.recibirPeso(lstPeso);
                lstPeso = gf.recibirPesoHistorico(lstPeso);
                adpP.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Implementar Visita
        //rv.setAdapter(adpV);
        gf.crearReferencia().child("visitas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstVisita.removeAll(lstVisita);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Visita2 v = new Visita2((String) hm.get("fecha"),(String) hm.get("lugar"),(String) hm.get("doctor"),(String) hm.get("notas"));
                    lstVisita.add(v);
                }
                Visita2 v2 = gf.recibirVisita(lstVisita);
                lstVisita = gf.recibirVisitaHistorico(lstVisita);
                adpV.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void ver(View v){
        Usuario usr = new Usuario("Gabriel","Tello",170,'M');
        Calendar calendar = Calendar.getInstance();
        Visita vst = new Visita(calendar,"Barcelona","Doctor1","Nota1");
        Peso pso = new Peso(calendar,2,2.5,"notas",3.5);
        gf.enviarDatosUsuario(usr);
        gf.enviarDatosVisita(vst);
        gf.enviarDatosPeso(pso);
    }

}
