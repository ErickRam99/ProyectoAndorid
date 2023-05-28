package edu.sv.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import edu.sv.proyecto.DB.DbPromociones;

public class NuevoPromo extends AppCompatActivity {

    EditText txtNombrePromo, txtDescripcionPromo, txtPrecioPromo;
    Button btnguardar,btnlistado, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_promo);

        txtNombrePromo = findViewById(R.id.edtNombrePromo);
        txtDescripcionPromo = findViewById(R.id.edtDescripcionPromo);
        txtPrecioPromo = findViewById(R.id.edtPrecioPromo);

        btnguardar = findViewById(R.id.btnRegistrar1);
        btnlistado = findViewById(R.id.btnlistaPromo);
        btnVolver = findViewById(R.id.btnVolver1);

        btnlistado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lista = new Intent(getApplicationContext(), ListaPromociones.class);
                startActivity(lista);
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtNombrePromo.getText().toString().equals("") && !txtDescripcionPromo.getText().toString().equals("")) {
                    DbPromociones dbPromociones = new DbPromociones(NuevoPromo.this);
                    long id = dbPromociones.insertarPromocion(txtNombrePromo.getText().toString()
                            , txtDescripcionPromo.getText().toString(), Double.parseDouble(txtPrecioPromo.getText().toString()));

                    if (id > 0) {
                        Toast.makeText(NuevoPromo.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoPromo.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoPromo.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
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
        txtNombrePromo.setText("");
        txtDescripcionPromo.setText("");
        txtPrecioPromo.setText("");
    }
}
