package com.aitor.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by ingae on 06/01/2017.
 */

public class CebancPizza_pedido extends AppCompatActivity {
    private Bundle extras;
    private ArrayList<EstructuraArray> datos;
    private ArrayList<InformacionBebidas> bebidas;
    private InformacionCliente cliente;
    private ArrayList<InformacionPizza> pizzas;
    private EditText texto;
    private String contenido;
    private float total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_pedido);
        extras = getIntent().getExtras();
        datos = (ArrayList<EstructuraArray>) extras.getSerializable("datos");
        cliente = (InformacionCliente) datos.get(0).getObj();
        pizzas = (ArrayList<InformacionPizza>) datos.get(1).getObj();
        bebidas = (ArrayList<InformacionBebidas>) datos.get(2).getObj();
        texto = (EditText) findViewById(R.id.txtResumen);
        contenido="Información del cliente:\n\nNombre: "+cliente.getNombre()+"\nDirección: "+cliente.getDireccion()+"\nTeléfono: "+cliente.getTelefono()+"\n\nPedido:\n\n";
        for(int cont=0;cont<pizzas.size();cont++){
            contenido+=pizzas.get(cont).getTipo()+" tamaño "+pizzas.get(cont).getTamano()+" "+pizzas.get(cont).getMasa()+" X "+pizzas.get(cont).getCantidad()+" -> "+pizzas.get(cont).getTotal()+"\n";
            total+=pizzas.get(cont).getTotal();
        }
        texto.setText(contenido);
    }
}
