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
    private Button cocacola, limon, naranja, nestea, cerveza, agua, finalizar, cesta;
    private EditText txtCocacola, txtLimon, txtNaranja, txtNestea, txtCerveza, txtAgua;
    private ArrayList<InformacionBebidas> bebidas = new ArrayList();
    private InformacionBebidas elemento;
    private Bundle extras;
    private ArrayList<EstructuraArray> datos;
    ArrayList<InformacionPizza> pizza = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_bebidas);
        extras = getIntent().getExtras();
        datos = (ArrayList<EstructuraArray>) extras.getSerializable("datos");
        cesta = (Button) findViewById(R.id.carritoB);
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
        cocacola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(txtCocacola.getText().toString()) > 0) {
                        anadir("Coca Cola", Integer.parseInt(txtCocacola.getText().toString()),1.95);
                        txtCocacola.setText("");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtCocacola.setText("");
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
                        anadir("Limon Soda", Integer.parseInt(txtLimon.getText().toString()),1.50);
                        txtLimon.setText("");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtLimon.setText("");
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
                        anadir("Fanta Naranja", Integer.parseInt(txtNaranja.getText().toString()),1.95);
                        txtNaranja.setText("");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtNaranja.setText("");
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
                        anadir("Nestea", Integer.parseInt(txtNestea.getText().toString()),1.85);
                        txtNestea.setText("");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtNestea.setText("");
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
                        anadir("Cerveza Cruzcampo", Integer.parseInt(txtCerveza.getText().toString()),1.55);
                        txtCerveza.setText("");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtCerveza.setText("");
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
                        anadir("Agua", Integer.parseInt(txtAgua.getText().toString()),1.35);
                        txtAgua.setText("");
                        mensaje("Se ha añadido el pedido a la cesta");
                    } else {
                        txtAgua.setText("");
                        mensaje("No puede pedir cero elementos o menos");
                    }
                }catch(NumberFormatException e){
                    mensaje("Debe escribir una cantidad");
                }
            }
        });
        cesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carrito();
            }
        });
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizarCompra();
            }
        });
    }
    private void anadir(String tipo, int cantidad,double precio){
        int cant;
        boolean encontrado=false;
        elemento = new InformacionBebidas();
        for(int cont=0;cont<bebidas.size()&&encontrado==false;cont++){
            elemento=bebidas.get(0);
            if(elemento.getTipo().equals(tipo)){
                cant=elemento.getCantidad();
                elemento.setCantidad(cant+cantidad);
                elemento.setTotal(elemento.getCantidad()*precio);
                bebidas.set(cont,elemento);
                encontrado = true;
            }
        }
        if (encontrado == false) {
            elemento = new InformacionBebidas();
            elemento.setTipo(tipo);
            elemento.setCantidad(cantidad);
            elemento.setTotal(precio*cantidad);
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
        finish();
    }
    public void carrito(){
        Intent i = new Intent(this,CebancPizza_carrito.class);
        i.putExtra("bebidas", bebidas);
        startActivityForResult(i,123456);
    }
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode==123456 && resultCode==RESULT_OK) {
            pizza=(ArrayList<InformacionPizza>) data.getExtras().getSerializable("pizza");
            bebidas=(ArrayList<InformacionBebidas>) data.getExtras().getSerializable("bebidas");
        }
    }
}