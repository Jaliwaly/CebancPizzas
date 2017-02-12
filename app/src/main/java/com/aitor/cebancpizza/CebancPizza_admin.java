package com.aitor.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Inziel on 10/02/2017.
 */

public class CebancPizza_admin extends AppCompatActivity {

    private Button clientes, articulos, pedidos, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_admin);

        clientes=(Button) findViewById(R.id.btnClientes);
        articulos=(Button) findViewById(R.id.btnArticulos);
        pedidos=(Button) findViewById(R.id.btnPedidos);
        salir=(Button) findViewById(R.id.btnSalir);

        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguienteClientes();
            }
        });
        articulos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguienteArticulos();
            }
        });
        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguientePedidos();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void siguienteClientes(){
        Intent i = new Intent(this,CebancPizza_clientes.class);
        startActivity(i);
    }
    private void siguienteArticulos(){
        Intent i = new Intent(this,CebancPizza_articulos.class);
        startActivity(i);
    }
    private void siguientePedidos(){
        Intent i = new Intent(this,CebancPizza_pedidos.class);
        startActivity(i);
    }
}
