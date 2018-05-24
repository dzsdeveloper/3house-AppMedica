package com.a3house.appmedica.appmedica;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.*;

import static android.content.Context.MODE_PRIVATE;
import static com.a3house.appmedica.appmedica.PreferenceHelper.setAlturaUsuario;
import static com.a3house.appmedica.appmedica.PreferenceHelper.setName;
import static com.a3house.appmedica.appmedica.PreferenceHelper.setSexoUsuario;

/**
 *
 */
public class GestionFirebase {
    final public static String DEMO_REFERENCE = "usuario_prueba"; //raiz del Firebase
    final public static String USUARIO_REFERENCE = "perfil";
    final public static String VISITA2_REFERENCE = "visitas";
    final public static String PESO2_REFERENCE = "pesos";
    List<Usuario2> lstUser = new ArrayList<>();
    List<Peso2> lstPesoH = new ArrayList<>();
    List<Visita2> lstVisita = new ArrayList<>();
    /**
     * Default constructor
     */
    public GestionFirebase() {
    }
    /**
     *
     */
    public DatabaseReference crearReferencia(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DEMO_REFERENCE);
        return myRef;
    }
    public void enviarDatosUsuario(Usuario u) {
        // TODO implement here
        //Guardamos el objeto Usuario
        crearReferencia().child(USUARIO_REFERENCE).push().setValue(new Usuario2(u));
        //Guardamos el valor de altura en un child especifico
        crearReferencia().child("altura").setValue(new Usuario2(u).getAltura());
        //Guardamos el valor de sexo en un child especifico
        crearReferencia().child("sexo").setValue(new Usuario2(u).getSexo());

        //Guardamos los datos del usuario en SharedPreferences
        String userName = u.getNombre();
        setName(userName);
        String userAltura = String.valueOf(u.getAltura());
        setAlturaUsuario(userAltura);
        String userSexo = String.valueOf(u.getSexo());
        setSexoUsuario(userSexo);
    }

    public void enviarDatosVisita(Visita v) {
        // TODO implement here
        crearReferencia().child(VISITA2_REFERENCE).push().setValue(new Visita2(v));
    }
    public void enviarDatosPeso(Peso p) {
        // TODO implement here
        crearReferencia().child(PESO2_REFERENCE).push().setValue(new Peso2(p));
    }
    /**
     *
     */
    //Borrar
    public void recibirDatos() {
        // TODO implement here
        final List<Usuario> lstUser = new ArrayList<>();
        final List<String> lstIds = new ArrayList<>();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = db.getReference(DEMO_REFERENCE);
    }

    public Peso2 recibirPeso(final List<Peso2> lstPeso){
        Peso2 p;
        crearReferencia().child("pesos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstPeso.removeAll(lstPeso);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Peso2 p = new Peso2((String) hm.get("fecha"),Integer.parseInt(String.valueOf(hm.get("variacion"))),Double.parseDouble(String.valueOf(hm.get("imc"))),(String) hm.get("notas"),Double.parseDouble(String.valueOf(hm.get("valor"))) );
                    lstPeso.add(p);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        p = lstPeso.get(lstPeso.size()-1);
        return p;
    }

    public Peso2 recibirPeso(){
        final List<Peso2> lstPeso = new ArrayList<>();
        Peso2 p;
        crearReferencia().child("pesos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstPeso.removeAll(lstPeso);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Peso2 p = new Peso2((String) hm.get("fecha"),Integer.parseInt(String.valueOf(hm.get("variacion"))),Double.parseDouble(String.valueOf(hm.get("imc"))),(String) hm.get("notas"),Double.parseDouble(String.valueOf(hm.get("valor"))) );
                    lstPeso.add(p);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        p = lstPeso.get(lstPeso.size()-1);
        return p;
    }


    public List<Peso2> recibirPesoHistorico(final List<Peso2> lstPeso){
        Peso2 p;
        crearReferencia().child("pesos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstPeso.removeAll(lstPeso);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Peso2 p = new Peso2((String) hm.get("fecha"),Integer.parseInt(String.valueOf(hm.get("variacion"))),Double.parseDouble(String.valueOf(hm.get("imc"))),(String) hm.get("notas"),Double.parseDouble(String.valueOf(hm.get("valor"))) );
                    lstPeso.add(p);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return lstPeso;
    }
    public Usuario2 recibirUsuario(final List<Usuario2> lstUser){
        Usuario2 u;
        crearReferencia().child("perfil").addValueEventListener(new ValueEventListener() {
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
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        u = lstUser.get(lstUser.size()-1);
        return u;
    }

    public Usuario2 recibirUsuario(){
        final List<Usuario2> lstUser = new ArrayList<>();
        Usuario2 u;
        crearReferencia().child("perfil").addValueEventListener(new ValueEventListener() {
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
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        u = lstUser.get(lstUser.size()-1);
        return u;
    }

    public List<Usuario2> recibirUsuarioHistorico(final List<Usuario2> lstUser){
        Usuario2 u;
        crearReferencia().child("perfil").addValueEventListener(new ValueEventListener() {
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
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return lstUser;
    }
    public Visita2 recibirVisita(final List<Visita2> lstVst){
        Visita2 v;
        crearReferencia().child("visitas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstVst.removeAll(lstVst);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Visita2 v = new Visita2((String) hm.get("fecha"),(String) hm.get("lugar"),(String) hm.get("doctor"),(String) hm.get("notas"));
                    lstVst.add(v);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        v = lstVst.get(lstVst.size()-1);
        return v;
    }
    public List<Visita2> recibirVisitaHistorico(final List<Visita2> lstVst){
        Visita2 v;
        crearReferencia().child("visitas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lstVst.removeAll(lstVst);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HashMap<String, Object> hm = (HashMap<String, Object>) (snapshot.getValue());
                    Visita2 v = new Visita2((String) hm.get("fecha"),(String) hm.get("lugar"),(String) hm.get("doctor"),(String) hm.get("notas"));
                    lstVst.add(v);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return lstVst;
    }
}

class Visita2 {
    //variables
    private String fecha;

    private String lugar;

    private String doctor;

    private String notas;
    //constructores
    public Visita2() {
    }

    public Visita2(String fecha, String lugar, String doctor, String notas) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.doctor = doctor;
        this.notas = notas;
    }
    public Visita2(Visita v){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        this.fecha = sdf.format(v.getFecha().getTime());
        this.lugar = v.getLugar();
        this.doctor = v.getDoctor();
        this.notas = v.getNotas();
    }
    //getters y setters
    public String getFecha() {
        // TODO implement here
        return fecha;
    }


    public void setFecha(String fecha) {
        // TODO implement here
        this.fecha = fecha;
    }


    public String getLugar() {
        // TODO implement here
        return lugar;
    }


    public void setLugar(String lugar) {
        // TODO implement here
        this.lugar = lugar;
    }


    public String getDoctor() {
        // TODO implement here
        return doctor;
    }


    public void setDoctor(String doctor) {
        // TODO implement here
        this.doctor = doctor;
    }


    public String getNotas() {
        // TODO implement here
        return notas;
    }

    public void setNotas(String notas) {
        // TODO implement here
        this.notas = notas;
    }

}
class Peso2 {
    //variables
    public String fecha;

    public double variacion;

    public double imc;

    public String notas;

    public double valor;
    //constructores
    public Peso2() {
    }

    public Peso2(String fecha, double variacion, double imc, String notas, double valor ) {
        this.fecha = fecha;
        this.variacion = variacion;
        this.imc = imc;
        this.notas = notas;
        this.valor = valor;
    }
    public Peso2(Peso p){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        this.fecha = sdf.format(p.getFecha().getTime());
        this.variacion = p.getVariacion();
        this.imc = p.getImc();
        this.notas = p.getNotas();
        this.valor = p.getValor();
    }
    public String getFecha() {
        // TODO implement here
        return fecha;
    }

    public void setFecha(String value) {
        // TODO implement here
    }

    public double getVariacion() {
        // TODO implement here
        return variacion;
    }

    public void setVariacion(double value) {
        // TODO implement here
    }

    public double getImc() {
        // TODO implement here pendiente
        return imc;
    }

    public void setImc(double value) {
        // TODO implement here
    }

    public String getNotas() {
        // TODO implement here
        return notas;
    }

    public void setNotas(String value) {
        // TODO implement here
    }

    public double getValor() {
        // TODO implement here
        return valor;
    }

    public void setValor(double value) {
        // TODO implement here
    }

}
class Usuario2 {
    //variables
    private String nombre;
    private String apellidos;
    private int altura;
    private String sexo;
    //constructores
    public Usuario2() {
    }
    public Usuario2(String nombre, String apellidos, int altura, String sexo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.altura = altura;
        this.sexo = sexo;
    }
    public Usuario2(Usuario u){
        this.nombre = u.getNombre();
        this.apellidos = u.getApellidos();
        this.altura = u.getAltura();
        this.sexo = Character.toString(u.getSexo());
    }
    //getters y setters
    /**
     * @return
     */
    public String getNombre() {
        // TODO implement here
        return nombre;
    }
    /**
     * @param value
     */
    public void setNombre(String value) {
        // TODO implement here
    }
    /**
     * @return
     */
    public String getApellidos() {
        // TODO implement here
        return apellidos;
    }
    /**
     * @param value
     */
    public void setApellidos(String value) {
        // TODO implement here
    }
    /**
     * @return
     */
    public int getAltura() {
        // TODO implement here
        return altura;
    }

    /**
     * @param value
     */
    public void setAltura(int value) {
        // TODO implement here
    }
    public String getSexo() {
        // TODO implement here
        return sexo;
    }
    /**
     * @param value
     */
    public void setSexo(String value) {
        // TODO implement here
    }
}