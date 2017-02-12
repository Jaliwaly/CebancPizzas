package com.aitor.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Jaliko on 12/02/2017.
 */

public class CebancPIzza_newarticulo extends AppCompatActivity{
    EditText e1, e2;
    Spinner s1;
    Button btn1;
    private ArrayAdapter<String> adaptador;
    String[] tipoart = {"PIZZA","BEBIDA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_articulonuevo);
        e1 = (EditText) findViewById(R.id.newart);
        e2 = (EditText) findViewById(R.id.newprecioart);
        btn1 = (Button) findViewById(R.id.añadirart);
        s1 = (Spinner) findViewById(R.id.spntipoart);

        adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, tipoart);
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        s1.setAdapter(adaptador);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirArt();
            }
        });
    }
    public void añadirArt(){
        Intent i = new Intent();
        i.putExtra("Nombre",e1.getText().toString());
        i.putExtra("Tipo",s1.getSelectedItem().toString());
        i.putExtra("Precio",Double.parseDouble(e2.getText().toString()));
        setResult(RESULT_OK, i);
        finish();
    }
}
