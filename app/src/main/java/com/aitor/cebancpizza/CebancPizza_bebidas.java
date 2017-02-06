package com.aitor.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Clase para escojer las bebidas del pedido
 */

public class CebancPizza_bebidas extends AppCompatActivity{
    private Button finalizar, cesta, salir;
    private ArrayList<InformacionBebidas> bebidas = new ArrayList();
    private InformacionBebidas elemento;
    private Bundle extras;
    private ArrayList<EstructuraArray> datos;
    private ArrayList<InformacionPizza> pizza = new ArrayList();
    private EstructuraArray pizzas, datosBebidas;
    private ListView lstB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_bebidas);
        extras = getIntent().getExtras();
        datos = (ArrayList<EstructuraArray>) extras.getSerializable("datos");
        pizza = (ArrayList<InformacionPizza>) datos.get(1).getObj();
        salir = (Button) findViewById(R.id.btnSalirBebidas);
        cesta = (Button) findViewById(R.id.carritoB);
        finalizar = (Button) findViewById(R.id.resumenPedido);
        lstB = (ListView) findViewById(R.id.lstB);
        lstB.setAdapter(new gridAdapter(this,false));

        /**
         * Boton de la cesta que al hacer click ejecuta el metodo
         * carrito
         */
        cesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carrito();
            }
        });
        /**
         * Boton el cual nos ejecuta el metodo finalizarCompra
         */
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizarCompra();
            }
        });
        /**
         * Boton para salir de la aplicacion
         */
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Metodo que se utiliza para ir añadiendo al arraylist la informacion de cada bebida
     */
    /*private void anadir(String tipo, int cantidad,float precio){
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
    }*/
    //Función que escribe mensajes Toast
    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo para pasar al ultimo layout con el resumen del pedido en el cual se pasa el array list
     * datos el cual contiene los diferentes arraylist con todos los datos del pedido
     */
    public void finalizarCompra(){
        Intent i = new Intent(this,CebancPizza_pedido.class);
        datosBebidas = new EstructuraArray("Bebidas",bebidas);
        pizzas = new EstructuraArray("Pizzas",pizza);
        datos.set(1,pizzas);
        datos.add(datosBebidas);
        i.putExtra("datos",datos);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para pasar al layout del carrito en el cual pasamos los arraylist de las pizzas y el
     * de las bebidas junto con el requestCode.
     */
    public void carrito(){
        int requestCode = 12344;
        Intent i = new Intent(this,CebancPizza_carrito.class);
        i.putExtra("bebidas", bebidas);
        i.putExtra("pizza",pizza);
        i.putExtra("requestCode",requestCode);
        startActivityForResult(i,requestCode);
    }

    /**
     *Metodo para recojer del carrito los dos arraylist
     */
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode==12344 && resultCode==RESULT_OK) {
            pizza=(ArrayList<InformacionPizza>) data.getExtras().getSerializable("pizza");
            bebidas=(ArrayList<InformacionBebidas>) data.getExtras().getSerializable("bebidas");
        }
    }
}