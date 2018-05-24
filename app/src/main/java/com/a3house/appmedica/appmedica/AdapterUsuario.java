package com.a3house.appmedica.appmedica;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.AdapViewHolder> {
        List<Usuario2> lstUser = new ArrayList<>();

    public AdapterUsuario(List<Usuario2> lstUser) {
        this.lstUser = lstUser;
    }
        @Override
        public AdapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.perfil_recycler,parent,false);
            AdapViewHolder holder = new AdapViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(AdapViewHolder holder, int position) {
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

        class AdapViewHolder extends RecyclerView.ViewHolder {

            TextView tvnombre,tvaltura,tvapellido,tvsexo;

            public AdapViewHolder(View itemView) {
                super(itemView);
                tvnombre = (TextView) itemView.findViewById(R.id.tvNombrePerfil);
                tvaltura = (TextView) itemView.findViewById(R.id.tvAlturaPerfil);
                tvapellido = (TextView) itemView.findViewById(R.id.tvApellidosPerfil);
                tvsexo = (TextView) itemView.findViewById(R.id.tvSexoPerfil);
            }
        }
    }
