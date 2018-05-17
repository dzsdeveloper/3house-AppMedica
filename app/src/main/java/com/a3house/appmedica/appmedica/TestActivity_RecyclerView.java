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
    Adapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__recycler_view);
        btncoche = (Button) findViewById(R.id.btnEnviar);
        rv = (RecyclerView) findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        lstUser = new ArrayList<>();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        adp = new Adapter(lstUser);
        rv.setAdapter(adp);
        DatabaseReference myRef = db.getReference(GestionFirebase.DEMO_REFERENCE);

        myRef.child("perfil").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstUser.removeAll(lstUser);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    //Double d = Double.parseDouble(String.valueOf(hm.get("altura")));
                    int d = Integer.parseInt(String.valueOf(hm.get("altura")));
                    Usuario2 user = new Usuario2((String) hm.get("nombre"),
                            (String) hm.get("apellidos"),
                            d,(String) hm.get("sexo"));
                    //Usuario2 user = snapshot.getValue(Usuario2.class);
                    lstUser.add(user);
                }
                adp.notifyDataSetChanged();
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
