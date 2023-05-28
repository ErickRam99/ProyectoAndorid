package edu.sv.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.sv.proyecto.DB.DbEntradas;
import edu.sv.proyecto.Entidades.Entradas;

public class EditarRopa extends AppCompatActivity {

    EditText txtNombreEntrada, txtDescripcionEntrada, txtPrecioEntrada;
    Button btnGuardar;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
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
        fabEditar = findViewById(R.id.fabEditar2);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar2);
        fabEliminar.setVisibility(View.INVISIBLE);

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

        DbEntradas dbEntradas = new DbEntradas(EditarRopa.this);
        entradas = dbEntradas.verEntradas(id);

        if(entradas != null){
            txtNombreEntrada.setText(entradas.getNombreEntrada());
            txtDescripcionEntrada.setText(entradas.getDescripcionEntrada());
            txtPrecioEntrada.setText(entradas.getPrecioEntrada().toString());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombreEntrada.getText().toString().equals("") && !txtDescripcionEntrada.getText().toString().equals("") && !txtPrecioEntrada.getText().toString().equals("")){
                    correcto = dbEntradas.EditarEntrada(id, txtNombreEntrada.getText().toString(),txtDescripcionEntrada.getText().toString(),Double.parseDouble(txtPrecioEntrada.getText().toString()));

                    if(correcto){
                        Toast.makeText(EditarRopa.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();

                    }else {
                        Toast.makeText(EditarRopa.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();

                    }
                }else {
                    Toast.makeText(EditarRopa.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, ListaRopa.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
