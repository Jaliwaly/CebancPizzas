package com.aitor.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adminportatil on 09/12/2016.
 */

public class CebancPizza_Carta extends AppCompatActivity{
    TextView textopru;
    String texto;
    InformacionCliente client;
    ArrayList<EstructuraArray> datosPizza = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_pizzas);
        textopru = (TextView) findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        datosPizza = (ArrayList<EstructuraArray>) extras.getSerializable("datos");
        client = (InformacionCliente) datosPizza.get(0).getObj();
        texto = "Escoja su pizza, "+client.getNombre();
        textopru.setText(texto);
    }
}