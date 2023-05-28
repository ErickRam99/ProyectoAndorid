package edu.sv.proyecto.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.sv.proyecto.Entidades.Entradas;
import edu.sv.proyecto.MostrarRopa;
import edu.sv.proyecto.R;

public class listaRopaAdapter extends RecyclerView.Adapter<listaRopaAdapter.EntradasViewHolder> {

    ArrayList<Entradas> listaEntradas;

    public listaRopaAdapter(ArrayList<Entradas> listaEntradas){
        this.listaEntradas = listaEntradas;
    }

    @NonNull
    @Override
    public EntradasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_items_ropa, null, false);
        return new EntradasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntradasViewHolder holder, int position) {
        holder.viewNombreEntrada.setText(listaEntradas.get(position).getNombreEntrada());
        holder.viewDescripcionEntrada.setText(listaEntradas.get(position).getDescripcionEntrada());
        holder.viewPrecioEntrada.setText(listaEntradas.get(position).getPrecioEntrada().toString());


    }

    @Override
    public int getItemCount() {
        return listaEntradas.size();
    }

    public class EntradasViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombreEntrada, viewDescripcionEntrada, viewPrecioEntrada;

        public EntradasViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombreEntrada = itemView.findViewById(R.id.viewNombreEntrada);
            viewDescripcionEntrada = itemView.findViewById(R.id.viewDescripcionEntrada);
            viewPrecioEntrada = itemView.findViewById(R.id.viewPrecioEntrada);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context contex = view.getContext();
                    Intent intent = new Intent(contex, MostrarRopa.class);
                    intent.putExtra("ID", listaEntradas.get(getAdapterPosition()).getId());
                    contex.startActivity(intent);
                }
            });
        }
    }
}

