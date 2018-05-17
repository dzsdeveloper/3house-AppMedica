package com.a3house.appmedica.appmedica;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

class Adapter extends RecyclerView.Adapter<com.a3house.appmedica.appmedica.Adapter.AdapViewHolder> {
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
