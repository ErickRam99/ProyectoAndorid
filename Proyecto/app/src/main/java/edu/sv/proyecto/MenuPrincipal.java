package edu.sv.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    Button btnPromos, btnSalir, btnPostres, btnDelivery, btnEntradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        btnPromos = findViewById(R.id.btnpromos);
        btnDelivery = findViewById(R.id.btndelivery);
        btnEntradas = findViewById(R.id.btnropa);
        btnSalir = findViewById(R.id.btnSalir);


        btnPromos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent promos = new Intent(getApplicationContext(), NuevoPromo.class);
                startActivity(promos);
            }
        });

        btnEntradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent entradas = new Intent(getApplicationContext(), NuevaRopa.class);
                startActivity(entradas);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);
            }
        });
    }
}
