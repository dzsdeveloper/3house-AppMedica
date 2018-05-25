package com.a3house.appmedica.appmedica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ActivityEditPerfil extends AppCompatActivity implements ValueEventListener{
    EditText nombreEP,apellidosEP,alturaEP;
    RadioButton hombreEP,mujerEP;
    private GestionFirebase gf = new GestionFirebase();
    private DatabaseReference refSexo = gf.crearReferencia().child("perfil-peso/sexo");
    private DatabaseReference refNombre = gf.crearReferencia().child("perfil-peso/nombre");
    private DatabaseReference refApellidos = gf.crearReferencia().child("perfil-peso/apellidos");
    private DatabaseReference refAltura = gf.crearReferencia().child("perfil-peso/altura");
    private RadioGroup rdSexoMP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);
        nombreEP = (EditText) findViewById(R.id.edtEditNombre);
        apellidosEP = (EditText) findViewById(R.id.edtEditApellidos);
        alturaEP = (EditText) findViewById(R.id.edtEditAltura);
        hombreEP = (RadioButton) findViewById(R.id.radioEditButtonH);
        mujerEP = (RadioButton) findViewById(R.id.radioEditButtonM);
        rdSexoMP = (RadioGroup) findViewById(R.id.selectorSexoMP);
    }
    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.radioEditButtonH:
                refSexo.setValue("H");
                break;
            case R.id.radioEditButtonM:
                refSexo.setValue("M");
                break;
        }
    }
    public void modificarPerfilEnvio2(View v){
        final String nombre3 = nombreEP.getText().toString();
        final String apellidos3 = apellidosEP.getText().toString();
        int altura = Integer.parseInt(alturaEP.getText().toString());
        refAltura.setValue(altura);
        refNombre.setValue(nombre3);
        refApellidos.setValue(apellidos3);
        char sexo=' ';
        int radioButtonId = rdSexoMP.getCheckedRadioButtonId();
        RadioButton radiobutton = findViewById(radioButtonId);
        if (radiobutton.getText().toString().equals("Hombre"))
            sexo='H';
        if (radiobutton.getText().toString().equals("Mujer"))
            sexo='M';
        Usuario usr3 = new Usuario(nombre3,apellidos3,altura,sexo);
        gf.enviarDatosUsuario(usr3);
        this.finish();
    }
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
            if (snapshot.getValue(String.class) != null) {
                String key = dataSnapshot.getKey();
                if (key.equals("perfil-peso/altura")) {
                    String h = dataSnapshot.getValue(String.class);
                    alturaEP.setText(h);
                } else if (key.equals("perfil-peso/sexo")) {
                    String s = dataSnapshot.getValue(String.class);
                    if (s.equals("H")) {
                        hombreEP.setChecked(true);
                    } else if (s.equals("M")) {
                        mujerEP.setChecked(true);
                    }
                }
            }
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        refSexo.addValueEventListener(this);
        refAltura.addValueEventListener(this);
        refNombre.addValueEventListener(this);
        refApellidos.addValueEventListener(this);
    }
}
