package com.aitor.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Jaliko on 06/01/2017.
 */

public class CebancPizza_bebidas extends AppCompatActivity{
    private Button cocacola, limon, naranja, nestea, cerveza, agua;
    private ArrayList bebidas = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_bebidas);
        cocacola=(Button) findViewById(R.id.btnCocacola);
        limon=(Button) findViewById(R.id.btnLimon);
        naranja=(Button) findViewById(R.id.btnNaranja);
        nestea=(Button) findViewById(R.id.btnNest);
        cerveza=(Button) findViewById(R.id.btnCerveza);
        agua = (Button) findViewById(R.id.btnAgua);

    }
    private void anadir(String tipo){

    }
}
