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
import edu.sv.proyecto.R;
import edu.sv.proyecto.MostrarUsuarios;
import edu.sv.proyecto.Entidades.Usuarios;

public class listaUsuariosAdapter extends RecyclerView.Adapter<listaUsuariosAdapter.UsuariosViewHolder> {

    ArrayList<Usuarios> listaUsuarios;

    public listaUsuariosAdapter(ArrayList<Usuarios> listaUsuarios){
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_items_usuarios, null, false);
        return new UsuariosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosViewHolder holder, int position) {
        holder.viewNombre.setText(listaUsuarios.get(position).getNombre());
        holder.viewApellido.setText(listaUsuarios.get(position).getApellido());
        holder.viewCelular.setText(listaUsuarios.get(position).getCelular());
        holder.viewUser.setText(listaUsuarios.get(position).getUser());
        holder.viewEmail.setText(listaUsuarios.get(position).getEmail());
        holder.viewPassword.setText(listaUsuarios.get(position).getPassword());


    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuariosViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewApellido, viewCelular, viewUser, viewEmail, viewPassword;

        public UsuariosViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellido = itemView.findViewById(R.id.viewApellido);
            viewCelular = itemView.findViewById(R.id.viewcelular);
            viewUser = itemView.findViewById(R.id.viewUser);
            viewEmail = itemView.findViewById(R.id.viewEmail);
            viewPassword = itemView.findViewById(R.id.viewPassword);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context contex = view.getContext();
                    Intent intent = new Intent(contex, MostrarUsuarios.class);
                    intent.putExtra("ID", listaUsuarios.get(getAdapterPosition()).getId());
                    contex.startActivity(intent);
                }
            });
        }
    }
}
