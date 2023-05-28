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
import edu.sv.proyecto.DB.DbPromociones;
import edu.sv.proyecto.Entidades.Promociones;

public class MostrarPromociones extends AppCompatActivity {

    EditText txtNombrePromo, txtDescripcionPromo, txtPrecioPromo;
    Button btnGuardar;
    FloatingActionButton fabEditar, fabEliminar;

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
        btnGuardar.setVisibility(View.INVISIBLE);
        fabEditar = findViewById(R.id.fabEditar1);
        fabEliminar = findViewById(R.id.fabEliminar1);

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

        DbPromociones dbPromociones = new DbPromociones(MostrarPromociones.this);
        promociones = dbPromociones.verPromocion(id);

        if(promociones != null){
            txtNombrePromo.setText(promociones.getNombrePromocion());
            txtDescripcionPromo.setText(promociones.getDescripcionPromocion());
            txtPrecioPromo.setText(promociones.getPrecio().toString());

            txtNombrePromo.setInputType(InputType.TYPE_NULL);
            txtDescripcionPromo.setInputType(InputType.TYPE_NULL);
            txtPrecioPromo.setInputType(InputType.TYPE_NULL);
        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarPromociones.this, EditarPromociones.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MostrarPromociones.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbPromociones.eliminarPromocion(id)){
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
        Intent intent = new Intent(this, ListaPromociones.class);
        startActivity(intent);
    }
}
