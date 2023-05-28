package edu.sv.proyecto;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.sv.proyecto.DB.DbEntradas;
import edu.sv.proyecto.Entidades.Entradas;

public class MostrarRopa extends AppCompatActivity {

    EditText txtNombreEntrada, txtDescripcionEntrada, txtPrecioEntrada;
    Button btnGuardar;
    FloatingActionButton fabEditar, fabEliminar;

    Entradas entradas;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ropas);

        txtNombreEntrada = findViewById(R.id.txtNombreEntrada);
        txtDescripcionEntrada = findViewById(R.id.txtDescripcionEntrada);
        txtPrecioEntrada = findViewById(R.id.txtPrecioEntrada);

        btnGuardar = findViewById(R.id.btnGuarda2);
        btnGuardar.setVisibility(View.INVISIBLE);
        fabEditar = findViewById(R.id.fabEditar2);
        fabEliminar = findViewById(R.id.fabEliminar2);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbEntradas dbEntradas = new DbEntradas(MostrarRopa.this);
        entradas = dbEntradas.verEntradas(id);

        if(entradas != null){
            txtNombreEntrada.setText(entradas.getNombreEntrada());
            txtDescripcionEntrada.setText(entradas.getDescripcionEntrada());
            txtPrecioEntrada.setText(entradas.getPrecioEntrada().toString());

            txtNombreEntrada.setInputType(InputType.TYPE_NULL);
            txtDescripcionEntrada.setInputType(InputType.TYPE_NULL);
            txtPrecioEntrada.setInputType(InputType.TYPE_NULL);
        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarRopa.this, EditarRopa.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MostrarRopa.this);
                builder.setMessage("¿Desea eliminar esta entrada?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbEntradas.eliminarEntrada(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, ListaRopa.class);
        startActivity(intent);
    }
}
