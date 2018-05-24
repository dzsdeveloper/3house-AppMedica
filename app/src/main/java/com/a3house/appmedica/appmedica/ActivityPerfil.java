package com.a3house.appmedica.appmedica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    private Usuario2 usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        rv = (RecyclerView) findViewById(R.id.recycler_perfil);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adpU);
        gf.crearReferencia().child("perfil").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstUser.removeAll(lstUser);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Usuario2 user = new Usuario2((String) hm.get("nombre"),
                            (String) hm.get("apellidos"),
                            Integer.parseInt(String.valueOf(hm.get("altura"))), (String) hm.get("sexo"));
                    lstUser.add(user);
                    usr = user;
                }
                //int h = usr.getAltura();
                adpU.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //int h = usr.getAltura();                                                                      }
    }
}
