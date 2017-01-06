package com.aitor.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jaliko on 06/01/2017.
 */

public class CebancPizza_bebidas extends AppCompatActivity{
    private Button cocacola, limon, naranja, nestea, cerveza, agua, finalizar;
    private EditText txtCocacola, txtLimon, txtNaranja, txtNestea, txtCerveza, txtAgua;
    private ArrayList<InformacionBebidas> bebidas = new ArrayList();
    private InformacionBebidas elemento;
    private Bundle extras;
    private ArrayList<EstructuraArray> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_bebidas);
        extras = getIntent().getExtras();
        datos = (ArrayList<EstructuraArray>) extras.getSerializable("datos");
        cocacola=(Button) findViewById(R.id.btnCocacola);
        limon=(Button) findViewById(R.id.btnLimon);
        naranja=(Button) findViewById(R.id.btnNaranja);
        nestea=(Button) findViewById(R.id.btnNest);
        cerveza=(Button) findViewById(R.id.btnCerveza);
        agua = (Button) findViewById(R.id.btnAgua);
        finalizar = (Button) findViewById(R.id.resumenPedido);
        txtCocacola = (EditText) findViewById(R.id.txtCocacola);
        txtLimon = (EditText) findViewById(R.id.txtLimon);
        txtNaranja = (EditText) findViewById(R.id.txtNaranja);
        txtNestea = (EditText) findViewById(R.id.txtNest);
        txtCerveza = (EditText) findViewById(R.id.txtCerveza);
        txtAgua = (EditText) findViewById(R.id.txtAgua);
        txtCocacola.setText("0");
        txtLimon.setText("0");
        txtNaranja.setText("0");
        txtNestea.setText("0");
        txtCerveza.setText("0");
        txtAgua.setText("0");
        cocacola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(txtCocacola.getText().toString()) > 0) {
                        anadir("Coca Cola", Integer.parseInt(txtCocacola.getText().toString()));
                        txtCocacola.setText("0");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtCocacola.setText("0");
                        mensaje("No puede pedir cero elementos o menos");
                    }
                }catch(NumberFormatException e){
                    mensaje("Debe escribir una cantidad");
                }
            }
        });
        limon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(txtLimon.getText().toString()) > 0) {
                        anadir("Limon Soda", Integer.parseInt(txtLimon.getText().toString()));
                        txtLimon.setText("0");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtLimon.setText("0");
                        mensaje("No puede pedir cero elementos o menos");
                    }
                }catch(NumberFormatException e){
                    mensaje("Debe escribir una cantidad");
                }
            }
        });
        naranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(txtNaranja.getText().toString()) > 0) {
                        anadir("Fanta Naranja", Integer.parseInt(txtNaranja.getText().toString()));
                        txtNaranja.setText("0");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtNaranja.setText("0");
                        mensaje("No puede pedir cero elementos o menos");
                    }
                }catch(NumberFormatException e){
                    mensaje("Debe escribir una cantidad");
                }
            }
        });
        nestea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(txtNestea.getText().toString()) > 0) {
                        anadir("Nestea", Integer.parseInt(txtNestea.getText().toString()));
                        txtNestea.setText("0");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtNestea.setText("0");
                        mensaje("No puede pedir cero elementos o menos");
                    }
                }catch(NumberFormatException e){
                    mensaje("Debe escribir una cantidad");
                }
            }
        });
        cerveza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(txtCerveza.getText().toString()) > 0) {
                        anadir("Cerveza Cruzcampo", Integer.parseInt(txtCerveza.getText().toString()));
                        txtCerveza.setText("0");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtCerveza.setText("0");
                        mensaje("No puede pedir cero elementos o menos");
                    }
                }catch(NumberFormatException e){
                    mensaje("Debe escribir una cantidad");
                }
            }
        });
        agua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(txtAgua.getText().toString()) > 0) {
                        anadir("Agua", Integer.parseInt(txtAgua.getText().toString()));
                        txtAgua.setText("0");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtAgua.setText("0");
                        mensaje("No puede pedir cero elementos o menos");
                    }
                }catch(NumberFormatException e){
                    mensaje("Debe escribir una cantidad");
                }
            }
        });
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizarCompra();
            }
        });
    }
    private void anadir(String tipo, int cantidad){
        int cant;
        boolean encontrado=false;
        elemento = new InformacionBebidas();
        for(int cont=0;cont<bebidas.size()&&encontrado==false;cont++){
            elemento=bebidas.get(0);
            if(elemento.getTipo().equals(tipo)){
                cant=elemento.getCantidad();
                elemento.setCantidad(cant+cantidad);
                bebidas.set(cont,elemento);
                encontrado = true;
            }
        }
        if (encontrado == false) {
            elemento = new InformacionBebidas();
            elemento.setTipo(tipo);
            elemento.setCantidad(cantidad);
            bebidas.add(elemento);
        }
    }
    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
    public void finalizarCompra(){
        Intent i = new Intent(this,CebancPizza_pedido.class);
        EstructuraArray datosBebidas = new EstructuraArray("Bebidas",bebidas);
        datos.add(datosBebidas);
        i.putExtra("datos",datos);
        startActivity(i);
    }
}