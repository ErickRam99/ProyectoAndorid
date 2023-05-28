package edu.sv.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.sv.proyecto.BD.DbPromociones;
import edu.sv.proyecto.Entidades.Promociones;

public class EditarPromociones extends AppCompatActivity {

    EditText txtNombrePromo, txtDescripcionPromo, txtPrecioPromo;
    Button btnGuardar;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Promociones promociones;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_promociones);

        txtNombrePromo = findViewById(R.id.txtNombrePromocion1);
        txtDescripcionPromo = findViewById(R.id.txtDescripcionPromo1);
        txtPrecioPromo = findViewById(R.id.txtPrecioPromo1);

        btnGuardar = findViewById(R.id.btnGuarda1);
        fabEditar = findViewById(R.id.fabEditar1);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar1);
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

        DbPromociones dbPromociones = new DbPromociones(EditarPromociones.this);
        promociones = dbPromociones.verPromocion(id);

        if(promociones != null){
            txtNombrePromo.setText(promociones.getNombrePromocion());
            txtDescripcionPromo.setText(promociones.getDescripcionPromocion());
            txtPrecioPromo.setText(promociones.getPrecio().toString());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombrePromo.getText().toString().equals("") && !txtDescripcionPromo.getText().toString().equals("") && !txtPrecioPromo.getText().toString().equals("")){
                    correcto = dbPromociones.EditarPromocion(id, txtNombrePromo.getText().toString(),txtDescripcionPromo.getText().toString(),Double.parseDouble(txtPrecioPromo.getText().toString()));

                    if(correcto){
                        Toast.makeText(EditarPromociones.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();

                    }else {
                        Toast.makeText(EditarPromociones.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();

                    }
                }else {
                    Toast.makeText(EditarPromociones.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, ListaPromociones.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
