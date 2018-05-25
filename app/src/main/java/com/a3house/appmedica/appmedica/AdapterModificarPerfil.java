package com.a3house.appmedica.appmedica;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterModificarPerfil extends RecyclerView.Adapter<AdapterModificarPerfil.AdapViewHolder> {
    List<Usuario2> lstUser = new ArrayList<>();
    private RadioGroup lastCheckedRadioGroup = null;
    private Context context;
    public AdapterModificarPerfil(List<Usuario2> lstUser, Context ctx) {
        this.lstUser = lstUser;
        this.context = ctx;
    }

    @Override
    public AdapterModificarPerfil.AdapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mod_perfil_recycler,parent,false);
        AdapViewHolder holder = new AdapViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterModificarPerfil.AdapViewHolder holder, int position) {
        Usuario2 usr = lstUser.get(position);
        holder.tvnombre.setText(usr.getNombre());
        holder.tvaltura.setText(String.valueOf(usr.getAltura()));
        holder.tvapellido.setText(usr.getApellidos());
        holder.tvsexo.setText(usr.getSexo());
    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    public class AdapViewHolder extends RecyclerView.ViewHolder {
        EditText tvnombre,tvaltura,tvapellido,tvsexo;

        public AdapViewHolder(View itemView) {
            super(itemView);
            tvnombre = (EditText) itemView.findViewById(R.id.edtNombreMod);
            tvaltura = (EditText) itemView.findViewById(R.id.edtAlturaMod);
            tvapellido = (EditText) itemView.findViewById(R.id.edtApellidosMod);
            tvsexo = (EditText) itemView.findViewById(R.id.edtSexo);
        }
    }
}
