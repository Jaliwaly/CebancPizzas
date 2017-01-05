package com.aitor.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adminportatil on 09/12/2016.
 */

public class CebancPizza_Carta extends AppCompatActivity{
    TextView textopru;
    String texto;
    InformacionCliente client;
    Button carbonara, barbacoa, quesos, vegetal, tropical;
    ArrayList<EstructuraArray> datosPizza = new ArrayList();
    ArrayList<InformacionPizza> pizza = new ArrayList();

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
        carbonara=(Button) findViewById(R.id.btnCarbonara);
        barbacoa = (Button) findViewById(R.id.btnBarbacoa);
        quesos = (Button) findViewById(R.id.btnQuesos);
        vegetal = (Button) findViewById(R.id.btnVegetal);
        tropical = (Button) findViewById(R.id.btnTropical);
        carbonara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir("Carbonara");
            }
        });
        barbacoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir("Barbacoa");
            }
        });
        quesos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir("Cuatro quesos");
            }
        });
        vegetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir("Vegetal");
            }
        });
        tropical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir("Tropical");
            }
        });
    }
    public void anadir(String tipo){

        Intent i = new Intent(this,CebancPizza_cantidad_pizza.class);
        i.putExtra("pizza",pizza);
        i.putExtra("tipo",tipo);
        startActivityForResult(i,1234);
    }
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode==1234 && resultCode==RESULT_OK) {
            pizza=(ArrayList<InformacionPizza>)data.getExtras().getSerializable("pizza");
        }
    }
}