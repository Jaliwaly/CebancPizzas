package com.aitor.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adminportatil on 09/12/2016.
 */

public class CebancPizza_Carta extends AppCompatActivity {
    TextView textopru;
    ArrayList<EstructuraArray> datosPizza = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_pizzas);
        textopru = (TextView) findViewById(R.id.textView);
    }

    public void datosCliente(View v) {
        String texto;
        datosPizza = (ArrayList<EstructuraArray>) getIntent().getExtras().getSerializable("datos");
        texto = "Hola";
        textopru.setText(texto);
    }
}