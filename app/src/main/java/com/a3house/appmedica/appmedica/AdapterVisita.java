package com.a3house.appmedica.appmedica;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class AdapterVisita extends RecyclerView.Adapter<AdapterVisita.AdapViewHolder> {
    List<Visita2> lstVst = new ArrayList<>();

    public AdapterVisita(List<Visita2> lstVst) {
        this.lstVst = lstVst;
    }
    @Override
    public AdapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.visita_recycler,parent,false);
        AdapViewHolder holder = new AdapViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapViewHolder holder, int position) {
        Visita2 vst = lstVst.get(position);
        holder.tvdoctor.setText(vst.getDoctor());
        holder.tvfechavisita.setText(vst.getFecha());
        holder.tvlugar.setText(vst.getLugar());
        holder.tvnotasvisita.setText(vst.getNotas());
    }

    @Override
    public int getItemCount() {
        return lstVst.size();
    }

    class AdapViewHolder extends RecyclerView.ViewHolder {

        TextView tvdoctor,tvfechavisita,tvlugar,tvnotasvisita;

        public AdapViewHolder(View itemView) {
            super(itemView);
                tvdoctor = (TextView) itemView.findViewById(R.id.tvDoctor);
                tvfechavisita = (TextView) itemView.findViewById(R.id.tvFechaVisita);
                tvlugar = (TextView) itemView.findViewById(R.id.tvLugar);
                tvnotasvisita = (TextView) itemView.findViewById(R.id.tvNotasVisita);
        }
    }
}
