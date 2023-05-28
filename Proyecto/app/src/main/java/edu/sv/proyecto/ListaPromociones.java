package edu.sv.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import edu.sv.proyecto.Adaptadores.listaPromocionesAdapter;
import edu.sv.proyecto.DB.DbPromociones;
import edu.sv.proyecto.Entidades.Promociones;

public class ListaPromociones extends AppCompatActivity {

    RecyclerView listaPromociones;
    ArrayList<Promociones> listaArrayPromociones;
    Button btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_promociones);
        listaPromociones = findViewById(R.id.listaPromociones);
        btnSalir = findViewById(R.id.btnSalir4);
        listaPromociones.setLayoutManager(new LinearLayoutManager(this));

        DbPromociones dbPromociones = new DbPromociones(ListaPromociones.this);

        listaArrayPromociones = new ArrayList<>();

        listaPromocionesAdapter adapter = new listaPromocionesAdapter(dbPromociones.mostrarPromociones());
        listaPromociones.setAdapter(adapter);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevo = new Intent(getApplicationContext(), NuevoPromo.class);
                startActivity(nuevo);
            }
        });
    }
}
