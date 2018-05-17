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
    public void enviarDatosUsuario() {
        // TODO implement here
        Usuario usr = new Usuario("Gabriel","Tello",170,'M');
        System.out.println("*********************************************************"+usr.getAltura()+usr.getNombre()+usr.getApellidos());
        Usuario usr2 = new Usuario(usr.getNombre(),usr.getApellidos(),usr.getAltura(),usr.getSexo());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DEMO_REFERENCE);
        myRef.child(USUARIO_REFERENCE).push().setValue(usr2);
    }
    public void enviarDatosUsuario(Usuario u) {
        // TODO implement here
        Usuario usr = new Usuario("Gabriel","Tello",170,'M');
        System.out.println("*********************************************************"+usr.getAltura()+usr.getNombre()+usr.getApellidos());
        Usuario usr2 = new Usuario(usr.getNombre(),usr.getApellidos(),usr.getAltura(),usr.getSexo());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DEMO_REFERENCE);
        myRef.child(USUARIO_REFERENCE).push().setValue(usr2);
    }
    public void enviarDatosVisita() {
        // TODO implement here
        Calendar calendar = Calendar.getInstance();
        Date date1 =  calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date2 = sdf.format(date1);
       // Calendar calendar2 = new GregorianCalendar(2013,0,31);
        Visita2 vst = new Visita2(date2,"Barcelona","Doctor1","Nota1");
       // System.out.println(vst.getFecha()+vst.getLugar()+vst.getDoctor()+vst.getNotas());
        Visita2 vst2 = new Visita2(vst.getFecha(),vst.getLugar(),vst.getDoctor(),vst.getNotas());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DEMO_REFERENCE);
        myRef.child(VISITA2_REFERENCE).push().setValue(vst2);
    }
    public void enviarDatosVisita(Visita v) {
        // TODO implement here
        Calendar calendar = Calendar.getInstance();
        Date date1 =  calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date2 = sdf.format(date1);
        // Calendar calendar2 = new GregorianCalendar(2013,0,31);
        Visita2 vst = new Visita2(date2,"Barcelona","Doctor1","Nota1");
        // System.out.println(vst.getFecha()+vst.getLugar()+vst.getDoctor()+vst.getNotas());
        Visita2 vst2 = new Visita2(vst.getFecha(),vst.getLugar(),vst.getDoctor(),vst.getNotas());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DEMO_REFERENCE);
        myRef.child(VISITA2_REFERENCE).push().setValue(vst2);
    }
    public void enviarDatosPeso() {
        // TODO implement here
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date2 = sdf.format(new Date());
        Peso2 pes = new Peso2(date2,2,2.5,"notas",3.5);
        //System.out.println(pes.getFecha()+pes.getVariacion()+pes.getImc()+pes.getNotas()+pes.getValor());
        Peso2 pes2 = new Peso2(pes.getFecha(),pes.getVariacion(),pes.getImc(),pes.getNotas(),pes.getValor());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DEMO_REFERENCE);
        myRef.child(PESO2_REFERENCE).push().setValue(pes2);
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
        /*
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Usuario user = snapshot.getValue(Usuario.class);
                    //System.out.println(user.getNombre()+myRef.child("perfil").push().getKey()+user.toString());

                    lstUser.add(user);
                }
                int i=0;
                for (Usuario u:lstUser){
                    System.out.println("*****" +u.getAltura()+"******"+ i++);
                }
                //Usuario post = dataSnapshot.getValue(Usuario.class);
                //System.out.println("++++++++++"+post.toString()+myRef.child("perfil").push().getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("loadPost:onCancelled" + databaseError.toException());
            }
        };
        myRef.addValueEventListener(valueEventListener);
        */
        /*
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Usuario user = dataSnapshot.getValue(Usuario.class);
                lstUser.add(user);
                lstIds.add(dataSnapshot.getKey());
                //notifyItemInserted(lstUser.size()-1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        */
       /* db.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Usuario user = snapshot.getValue(Usuario.class);
                    System.out.println(user.getNombre()+myRef.child("perfil").push().getKey());
                    List<Usuario> lstUser  = new ArrayList<>();
                    lstUser.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
class Adapter extends RecyclerView.Adapter<Adapter.AdapViewHolder> {
    List<Usuario> lstUser = new ArrayList<>();
    List<String> lstIds = new ArrayList<>();

    public Adapter(List<Usuario> lstUser) {
        this.lstUser = lstUser;
    }

    @Override
    public AdapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,parent,false);
        AdapViewHolder holder = new AdapViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapViewHolder holder, int position) {
        Usuario usr = lstUser.get(position);
        holder.tvnombre.setText(usr.getNombre());
        holder.tvaltura.setText("alto" +usr.getAltura());
    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    class AdapViewHolder extends RecyclerView.ViewHolder {

        TextView tvnombre,tvaltura;

        public AdapViewHolder(View itemView) {
            super(itemView);
            tvnombre = (TextView) itemView.findViewById(R.id.tvTexto);
            tvaltura = (TextView) itemView.findViewById(R.id.tvAlto);
        }
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