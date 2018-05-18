package com.a3house.appmedica.appmedica;

import android.content.Context;
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

/**
 *
 */
public class GestionFirebase {
    //final public static String DEMO_REFERENCE = "clase_padre";
    final public static String DEMO_REFERENCE = "usuario_prueba";
    final public static String USUARIO_REFERENCE = "perfil";
    final public static String VISITA2_REFERENCE = "visitas";
    final public static String PESO2_REFERENCE = "pesos";
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
    public void enviarDatosUsuario() {
        // TODO implement here
        Usuario2 usr = new Usuario2("Gabriel","Tello",170,"M");
        Usuario2 usr2 = new Usuario2(usr.getNombre(),usr.getApellidos(),usr.getAltura(),usr.getSexo());
        crearReferencia().child(USUARIO_REFERENCE).push().setValue(usr2);
    }
    public void enviarDatosUsuario(Usuario u) {
        // TODO implement here
        Usuario usr = new Usuario("Gabriel","Tello",170,'M');
        Usuario usr2 = new Usuario(usr.getNombre(),usr.getApellidos(),usr.getAltura(),usr.getSexo());
        crearReferencia().child(USUARIO_REFERENCE).push().setValue(usr2);
    }
    public void enviarDatosVisita() {
        // TODO implement here
        Calendar calendar = Calendar.getInstance();
        Date date1 =  calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date2 = sdf.format(date1);
       // Calendar calendar2 = new GregorianCalendar(2013,0,31);
        Visita2 vst = new Visita2(date2,"Barcelona","Doctor1","Nota1");
        Visita2 vst2 = new Visita2(vst.getFecha(),vst.getLugar(),vst.getDoctor(),vst.getNotas());
        crearReferencia().child(VISITA2_REFERENCE).push().setValue(vst2);
    }
    public void enviarDatosVisita(Visita v) {
        // TODO implement here
        Calendar calendar = Calendar.getInstance();
        Date date1 =  calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date2 = sdf.format(date1);
        // Calendar calendar2 = new GregorianCalendar(2013,0,31);
        Visita2 vst = new Visita2(date2,"Barcelona","Doctor1","Nota1");
        Visita2 vst2 = new Visita2(vst.getFecha(),vst.getLugar(),vst.getDoctor(),vst.getNotas());
        crearReferencia().child(VISITA2_REFERENCE).push().setValue(vst2);
    }
    public void enviarDatosPeso() {
        // TODO implement here
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date2 = sdf.format(new Date());
        Peso2 pes = new Peso2(date2,2,2.5,"notas",3.5);
        Peso2 pes2 = new Peso2(pes.getFecha(),pes.getVariacion(),pes.getImc(),pes.getNotas(),pes.getValor());
        crearReferencia().child(PESO2_REFERENCE).push().setValue(pes2);
    }
    public void enviarDatosPeso(Peso p) {
        // TODO implement here
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date2 = sdf.format(new Date());
        Peso2 pes = new Peso2(date2,2,2.5,"notas",3.5);
        Peso2 pes2 = new Peso2(pes.getFecha(),pes.getVariacion(),pes.getImc(),pes.getNotas(),pes.getValor());
        crearReferencia().child(PESO2_REFERENCE).push().setValue(pes2);
    }
    /**
     *
     */
    public void recibirDatos() {
        // TODO implement here
        final List<Usuario> lstUser  = new ArrayList<>();
        final List<String> lstIds = new ArrayList<>();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = db.getReference(DEMO_REFERENCE);

    }
}

class Visita2 {

    private String fecha;

    private String lugar;

    private String doctor;

    private String notas;

    public Visita2() {
    }

    public Visita2(String fecha, String lugar, String doctor, String notas) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.doctor = doctor;
        this.notas = notas;
    }


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


    public String fecha;

    public int variacion;

    public double imc;

    public String notas;

    public double valor;

    public Peso2() {
    }

    public Peso2(String fecha, int variacion, double imc, String notas, double valor ) {

        this.fecha = fecha;
        this.variacion = variacion;
        this.imc = imc;
        this.notas = notas;
        this.valor = valor;

    }

    public String getFecha() {
        // TODO implement here
        return fecha;
    }

    public void setFecha(String value) {
        // TODO implement here
    }

    public int getVariacion() {
        // TODO implement here
        return variacion;
    }

    public void setVariacion(int value) {
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

    private String nombre;

    /**
     *
     */
    private String apellidos;

    /**
     *
     */
    private int altura;

    /**
     *
     */
    private String sexo;

    public Usuario2() {
    }

    /**
     * Default constructor
     */

    public Usuario2(String nombre, String apellidos, int altura, String sexo){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.altura = altura;
        this.sexo = sexo;
    }


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