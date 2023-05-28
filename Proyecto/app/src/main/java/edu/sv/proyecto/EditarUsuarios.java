package edu.sv.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.sv.proyecto.DB.DbUsuarios;
import edu.sv.proyecto.Entidades.Usuarios;

public class EditarUsuarios extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtCelular, txtuser, txtEmail, txtPass;
    Button btnGuardar;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Usuarios usuario;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuarios);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCelular = findViewById(R.id.txtCelular);
        txtuser = findViewById(R.id.txtUsuario);
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        btnGuardar = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
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

        DbUsuarios dbUsuarios = new DbUsuarios(EditarUsuarios.this);
        usuario = dbUsuarios.verUsuario(id);

        if(usuario != null){
            txtNombre.setText(usuario.getNombre());
            txtApellido.setText(usuario.getApellido());
            txtCelular.setText(usuario.getCelular());
            txtuser.setText(usuario.getUser());
            txtEmail.setText(usuario.getEmail());
            txtPass.setText(usuario.getPassword());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtApellido.getText().toString().equals("")){
                    correcto = dbUsuarios.EditarUsuarios(id, txtNombre.getText().toString(),txtApellido.getText().toString(),txtCelular.getText().toString(),txtuser.getText().toString(),
                            txtEmail.getText().toString(),txtPass.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarUsuarios.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();

                    }else {
                        Toast.makeText(EditarUsuarios.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();

                    }
                }else {
                    Toast.makeText(EditarUsuarios.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, ListaUsuarios.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
