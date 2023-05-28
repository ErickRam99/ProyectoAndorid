package edu.sv.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.sv.proyecto.DB.DbUsuarios;

public class NuevoUsuario extends AppCompatActivity {

    EditText txtnombre, txtapellido, txtcelular, txtusuario, txtemail, txtpass;
    Button btnguardar,btnlistado, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        txtnombre = findViewById(R.id.edtNombre);
        txtapellido = findViewById(R.id.edtApellido);
        txtcelular = findViewById(R.id.edtcelular);
        txtusuario = findViewById(R.id.edtUser);
        txtemail = findViewById(R.id.edtCorreo);
        txtpass = findViewById(R.id.edtpass);
        btnguardar = findViewById(R.id.btnRegirstrar);
        btnlistado = findViewById(R.id.btnlistaUser);
        btnSalir = findViewById(R.id.btnSalir3);

        btnlistado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lista = new Intent(getApplicationContext(), ListaUsuarios.class);
                startActivity(lista);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtnombre.getText().toString().equals("") && !txtapellido.getText().toString().equals("")) {
                    DbUsuarios dbUsuarios = new DbUsuarios(NuevoUsuario.this);
                    long id = dbUsuarios.insertarUsuarios(txtnombre.getText().toString()
                            , txtapellido.getText().toString(), txtcelular.getText().toString(), txtusuario.getText().toString()
                            , txtemail.getText().toString(), txtpass.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoUsuario.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoUsuario.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoUsuario.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void limpiar(){
        txtnombre.setText("");
        txtapellido.setText("");
        txtcelular.setText("");
        txtusuario.setText("");
        txtemail.setText("");
        txtpass.setText("");
    }
}
