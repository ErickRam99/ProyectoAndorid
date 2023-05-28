package edu.sv.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.sv.proyecto.DB.DbEntradas;

public class NuevaRopa extends AppCompatActivity {

    EditText txtNombreEntrada, txtDescripcionEntrada, txtPrecioEntrada;
    Button btnguardar,btnlistado, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_entrada);

        txtNombreEntrada = findViewById(R.id.edtNombreEntrada);
        txtDescripcionEntrada = findViewById(R.id.edtDescripcionEntrada);
        txtPrecioEntrada = findViewById(R.id.edtPrecioEntrada);

        btnguardar = findViewById(R.id.btnRegistrar2);
        btnlistado = findViewById(R.id.btnlistaEntrada);
        btnVolver = findViewById(R.id.btnVolver2);

        btnlistado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lista = new Intent(getApplicationContext(), ListaRopa.class);
                startActivity(lista);
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtNombreEntrada.getText().toString().equals("") && !txtDescripcionEntrada.getText().toString().equals("")) {
                    DbEntradas dbEntradas = new DbEntradas(NuevaRopa.this);
                    long id = dbEntradas.insertarEntrada(txtNombreEntrada.getText().toString()
                            , txtDescripcionEntrada.getText().toString(), Double.parseDouble(txtPrecioEntrada.getText().toString()));

                    if (id > 0) {
                        Toast.makeText(NuevaRopa.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevaRopa.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevaRopa.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(registro);
            }
        });
    }
    private void limpiar(){
        txtNombreEntrada.setText("");
        txtDescripcionEntrada.setText("");
        txtPrecioEntrada.setText("");
    }
}
