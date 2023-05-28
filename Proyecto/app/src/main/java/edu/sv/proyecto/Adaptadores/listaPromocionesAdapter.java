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
import edu.sv.proyecto.Entidades.Promociones;
import edu.sv.proyecto.MostrarPromociones;
import edu.sv.proyecto.R;

public class listaPromocionesAdapter extends RecyclerView.Adapter<listaPromocionesAdapter.PromocionesViewHolder> {

    ArrayList<Promociones> listaPromociones;

    public listaPromocionesAdapter(ArrayList<Promociones> listaPromociones){
        this.listaPromociones = listaPromociones;
    }

    @NonNull
    @Override
    public PromocionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_items_promociones, null, false);
        return new PromocionesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromocionesViewHolder holder, int position) {
        holder.viewNombrePromocion.setText(listaPromociones.get(position).getNombrePromocion());
        holder.viewDescripcionPromocion.setText(listaPromociones.get(position).getDescripcionPromocion());
        holder.viewPrecioPromo.setText(listaPromociones.get(position).getPrecio().toString());


    }

    @Override
    public int getItemCount() {
        return listaPromociones.size();
    }

    public class PromocionesViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombrePromocion, viewDescripcionPromocion, viewPrecioPromo;

        public PromocionesViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombrePromocion = itemView.findViewById(R.id.viewNombrePromocion);
            viewDescripcionPromocion = itemView.findViewById(R.id.viewDescripcionPromocion);
            viewPrecioPromo = itemView.findViewById(R.id.viewPrecioPromo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context contex = view.getContext();
                    Intent intent = new Intent(contex, MostrarPromociones.class);
                    intent.putExtra("ID", listaPromociones.get(getAdapterPosition()).getId());
                    contex.startActivity(intent);
                }
            });
        }
    }
}
