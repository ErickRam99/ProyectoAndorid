package edu.sv.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText txtuser, txtpass;
    Button btningresar, btnregistro;
    Toast msj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtuser = findViewById(R.id.edtUsuario);
        txtpass = findViewById(R.id.edtPassword);

        btningresar = findViewById(R.id.btnaceptar);
        btnregistro = findViewById(R.id.btnregistarse);

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast mensaje;
                    String user, pass;
                    user = txtuser.getText().toString();
                    pass = txtpass.getText().toString();
                    if (user.equals("admin")) {
                        if (pass.equals("123")) {
                            Intent menu = new Intent(getApplicationContext(), MenuPrincipal.class);
                            mensaje = Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG);
                            //menu.putExtra("Usuario", user);
                            startActivity(menu);
                            closeContextMenu();
                        } else {
                            mensaje = Toast.makeText(getApplicationContext(), "Usuario y Contraseña no Valido", Toast.LENGTH_LONG);
                        }
                    } else {
                        mensaje = Toast.makeText(getApplicationContext(), "Usuario no valido", Toast.LENGTH_LONG);
                    }
                    mensaje.show();
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });
        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(getApplicationContext(), NuevoUsuario.class);
                startActivity(registro);
            }
        });
    }
}
