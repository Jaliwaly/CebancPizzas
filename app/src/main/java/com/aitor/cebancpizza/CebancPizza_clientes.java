package com.aitor.cebancpizza;

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

public class CebancPizza_clientes extends AppCompatActivity {

    Button modificar, borrar, salir;
    private ListView lista;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_gestor);

        borrar = (Button) findViewById(R.id.btnBorrar);
        salir = (Button) findViewById(R.id.btnSalir);
        lista = (ListView) findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
        lista.setAdapter(adapter);

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
    private void eliminar(int pos){
        clientes.remove(pos);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
        adapter.notifyDataSetChanged();
    }
}