package com.aitor.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Inziel on 10/02/2017.
 */

public class CebancPizza_articulos extends AppCompatActivity {

    private Button anadir, modificar, borrar, salir;
    private ArrayAdapter<String> adapter;
    private ListView lista;
    private ArrayList<String> articulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_articulos);

        modificar=(Button) findViewById(R.id.btnModificar);
        anadir=(Button) findViewById(R.id.btnAnadir);
        borrar=(Button) findViewById(R.id.btnBorrar);
        salir=(Button) findViewById(R.id.btnSalir);
        lista=(ListView) findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, articulos);

        lista.setAdapter(adapter);

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguienteModificar(lista.getSelectedItemPosition());
            }
        });
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguienteAnadir();
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar(lista.getSelectedItemPosition());
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void siguienteModificar(int pos){
        Intent i = new Intent(this,CebancPizza_bebidas.class);
        i.putExtra("num",pos);
        startActivity(i);
    }
    private void siguienteAnadir(){
        Intent i = new Intent(this,CebancPizza_bebidas.class);
        startActivity(i);
    }
    private void eliminar(int pos){
        articulos.remove(pos);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, articulos);
        adapter.notifyDataSetChanged();
    }
}