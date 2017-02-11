package com.aitor.cebancpizza;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    CebancPizza_BD db;
    SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_gestor);

        borrar = (Button) findViewById(R.id.btnBorrar);
        salir = (Button) findViewById(R.id.btnSalir);
        lista = (ListView) findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
        lista.setAdapter(adapter);

        db = new CebancPizza_BD(this,"CebancPizza",null,1);
        sql = db.getWritableDatabase();
        Cursor c = sql.rawQuery("SELECT IDCLIENTE, NOMBRE FROM CLIENTES",null);
        while(c.moveToNext()){
            clientes.add(c.getInt(0),c.getInt(0)+" - "+c.getString(1));
            adapter.notifyDataSetChanged();
        }

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
        Cursor c = sql.rawQuery("DELETE FROM CLIENTES WHERE IDCLIENTE = "+clientes.get(pos),null);
        clientes.remove(pos);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
        adapter.notifyDataSetChanged();
    }
}