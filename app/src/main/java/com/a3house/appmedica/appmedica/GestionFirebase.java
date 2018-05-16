package com.a3house.appmedica.appmedica;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    final public static String VISITA_REFERENCE = "visitas";
    final public static String PESO_REFERENCE = "pesos";
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
        Usuario usr = new Usuario("Gabriel","Tello",1.70);
        System.out.println(usr.getAltura()+usr.getNombre()+usr.getApellidos());
        Usuario usr2 = new Usuario(usr.getNombre(),usr.getApellidos(),usr.getAltura());
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
        Calendar calendar2 = new GregorianCalendar(2013,0,31);
        Visita vst = new Visita(calendar2,"Barcelona","Doctor1","Nota1");
        System.out.println(vst.getFecha()+vst.getLugar()+vst.getDoctor()+vst.getNotas());
        Visita vst2 = new Visita(vst.getFecha(),vst.getLugar(),vst.getDoctor(),vst.getNotas());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DEMO_REFERENCE);
        myRef.child(VISITA_REFERENCE).push().setValue(vst2);
    }
    public void enviarDatosPeso() {
        // TODO implement here
       /* Peso pes = new Peso("Gabriel","Tello",1.70);
        System.out.println(usr.getAltura()+usr.getNombre()+usr.getApellidos());
        Peso pes2 = new Peso(usr.getNombre(),usr.getApellidos(),usr.getAltura());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DEMO_REFERENCE);
        myRef.child(PESO_REFERENCE).push().setValue(pes2);*/
    }
    /**
     *
     */
    public void recibirDatos() {
        // TODO implement here
/*
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Usuario user = snapshot.getValue(Usuario.class);
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
/*
    class Adapter extends  RecyclerView.Adapter<Adapter.ViewHolder> {
        List<Usuario> lstUser;

        public Adapter(List<Usuario> lstUser) {
            this.lstUser = lstUser;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,parent,false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Usuario usr = lstUser.get(position);
            holder.txt.setText(usr.getNombre());
        }

        @Override
        public int getItemCount() {
            return lstUser.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        public ViewHolder(View item) {
            super(item);
            txt = item.findViewById(R.id.tvTexto);
        }
    }
    }
*/
