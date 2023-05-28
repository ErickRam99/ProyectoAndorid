package edu.sv.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import edu.sv.proyecto.Adaptadores.listaUsuariosAdapter;
import edu.sv.proyecto.DB.DbUsuarios;
import edu.sv.proyecto.Entidades.Usuarios;

public class ListaUsuarios extends AppCompatActivity {

    RecyclerView listaUsuarios;
    ArrayList<Usuarios> listaArrayUsuarios;
    Button btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        listaUsuarios = findViewById(R.id.listaUsuarios);
        btnVolver = findViewById(R.id.btnSalir2);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));

        DbUsuarios dbUsuarios = new DbUsuarios(ListaUsuarios.this);

        listaArrayUsuarios = new ArrayList<>();

        listaUsuariosAdapter adapter = new listaUsuariosAdapter(dbUsuarios.mostrarUsuarios());
        listaUsuarios.setAdapter(adapter);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevo = new Intent(getApplicationContext(), NuevoUsuario.class);
                startActivity(nuevo);
            }
        });
    }
}
