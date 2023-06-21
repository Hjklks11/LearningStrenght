package com.example.learningstrenght.usuario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningstrenght.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class AdapterRms extends RecyclerView.Adapter<AdapterRms.ViewHolder> {
    private Map<String, String> mapaRms;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtEj, txtSuf;
        private final EditText eTRm;
        private final FloatingActionButton btnEditar, btnEliminar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEj = itemView.findViewById(R.id.txtDescripcionRmsview);
            eTRm = itemView.findViewById(R.id.editTextNumeroRmsview);
            txtSuf = itemView.findViewById(R.id.txtSufijoRmsview);
            btnEditar = itemView.findViewById(R.id.btnEditarRmsview);
            btnEliminar = itemView.findViewById(R.id.btnEliminarRmsview);
        }

        public TextView getTxtEj() {
            return txtEj;
        }

        public EditText geteTRm() {
            return eTRm;
        }

        public TextView getTxtSuf() {
            return txtSuf;
        }

        public FloatingActionButton getBtnEditar() {
            return btnEditar;
        }

        public FloatingActionButton getBtnEliminar() {
            return btnEliminar;
        }
    }

    public AdapterRms(Map<String, String> mapaRms) {
        this.mapaRms = mapaRms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rmsview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer i = 0;
        for(Map.Entry<String, String> entry : mapaRms.entrySet()){
            if (i == position) {
                String ejercicio = entry.getKey();
                String rm = entry.getValue().split(" ")[0];
                String sufijo = entry.getValue().split(" ")[1];
                holder.getTxtEj().setText(ejercicio);
                holder.geteTRm().setText(rm);
                holder.getTxtSuf().setText(sufijo);
            }
            i++;
        }
        holder.geteTRm().setSelectAllOnFocus(true);
        holder.getBtnEditar().setOnClickListener(view -> {
            mapaRms.put(holder.getTxtEj().getText().toString().trim(), holder.geteTRm().getText().toString().trim()
                    + " " + holder.getTxtSuf().getText().toString().trim());
            notifyItemInserted(position);
        });
        holder.getBtnEliminar().setOnClickListener(view -> {
            System.out.println("Holder: " + holder.getTxtEj().getText().toString());
            System.out.println("Remove: " + mapaRms.remove(holder.getTxtEj().getText().toString()));
            System.out.println("Get: " + mapaRms.get(holder.getTxtEj().getText().toString()));
            for(Map.Entry<String, String> entry : mapaRms.entrySet()){
                System.out.println("mapaRms: ejercicio -> " + entry.getKey() + " | rm -> " + entry.getValue());
                }
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return mapaRms.size();
    }

    public Map<String, String> getMapaRms() {
        return mapaRms;
    }
}


