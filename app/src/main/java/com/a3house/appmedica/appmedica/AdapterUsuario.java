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
        List<Peso2> lstPeso = new ArrayList<>();
        List<Visita2> lstVst = new ArrayList<>();
        List<String> lstIds = new ArrayList<>();

        /*public Adapter(List<Usuario2> lstUser,List<Peso2> lstPeso,List<Visita2> lstVst) {
            this.lstUser = lstUser;
            this.lstPeso = lstPeso;
            this.lstVst = lstVst;
        }*/
    public AdapterUsuario(List<Usuario2> lstUser) {
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
                tvnombre = (TextView) itemView.findViewById(R.id.tvNombre);
                tvaltura = (TextView) itemView.findViewById(R.id.tvAlto);
                tvapellido = (TextView) itemView.findViewById(R.id.tvApellido);
                tvsexo = (TextView) itemView.findViewById(R.id.tvSexo);
            }
        }
    }
