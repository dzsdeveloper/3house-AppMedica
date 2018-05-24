package com.a3house.appmedica.appmedica;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class AdapterPeso extends RecyclerView.Adapter<AdapterPeso.AdapViewHolder> {
    List<Peso2> lstPeso = new ArrayList<>();

    public AdapterPeso(List<Peso2> lstPeso) {
        this.lstPeso = lstPeso;
    }

    @Override
    public AdapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.peso_recycler,parent,false);
        AdapViewHolder holder = new AdapViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapViewHolder holder, int position) {
             Peso2 pes = lstPeso.get(position);
            holder.tvfechapeso.setText(pes.getFecha());
            holder.tvimc.setText(String.valueOf(pes.getImc()));
            //holder.tvnotapeso.setText(pes.getNotas());
            holder.tvvalor.setText(String.valueOf(pes.getValor()));
            holder.tvvariacion.setText(String.valueOf(pes.getVariacion()));
    }

    @Override
    public int getItemCount() {
        return lstPeso.size();
    }

    class AdapViewHolder extends RecyclerView.ViewHolder {

        TextView tvfechapeso,tvimc,tvvalor,tvvariacion,tvnotapeso;
        public AdapViewHolder(View itemView) {
            super(itemView);
                tvfechapeso = (TextView) itemView.findViewById(R.id.tvFechaPeso);
                tvimc = (TextView) itemView.findViewById(R.id.tvImc);
                //tvnotapeso = (TextView) itemView.findViewById(R.id.tvNotasPeso);
                tvvalor = (TextView) itemView.findViewById(R.id.tvValor);
                tvvariacion = (TextView) itemView.findViewById(R.id.tvVariacion);
        }
    }
}
