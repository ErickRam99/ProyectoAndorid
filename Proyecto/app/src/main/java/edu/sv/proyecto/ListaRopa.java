package edu.sv.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.sv.proyecto.Adaptadores.listaRopaAdapter;
import edu.sv.proyecto.DB.DbEntradas;
import edu.sv.proyecto.Entidades.Entradas;

public class ListaRopa extends AppCompatActivity {

    RecyclerView listaEntradas;
    ArrayList<Entradas> listaArrayEntradas;
    Button btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ropa);
        listaEntradas = findViewById(R.id.listaEntradas);
        btnVolver = findViewById(R.id.btnSalir5);
        listaEntradas.setLayoutManager(new LinearLayoutManager(this));

        DbEntradas dbPromociones = new DbEntradas(ListaRopa.this);

        listaArrayEntradas = new ArrayList<>();

        listaRopaAdapter adapter = new listaRopaAdapter(dbPromociones.mostrarEntradas());
        listaEntradas.setAdapter(adapter);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevo = new Intent(getApplicationContext(), NuevaRopa.class);
                startActivity(nuevo);
            }
        });
    }
}
