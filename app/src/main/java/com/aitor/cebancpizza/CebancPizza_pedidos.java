package com.aitor.cebancpizza;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Inziel on 10/02/2017.
 */

public class CebancPizza_pedidos extends AppCompatActivity {

    Button borrar, salir;
    private ListView lista;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> pedidos = new ArrayList<String>();
    private ArrayList<Integer> idPedido = new ArrayList<Integer>();
    private int posicion, antigua;
    CebancPizza_BD db;
    SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_gestor);

        borrar = (Button) findViewById(R.id.btnBorrar);
        salir = (Button) findViewById(R.id.btnSalir);
        lista = (ListView) findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, pedidos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lista.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.azul));
                posicion=position;
                if(antigua!=posicion && lista.getCount()> 1){
                    lista.getChildAt(antigua).setBackgroundColor(getResources().getColor(R.color.transparente));
                    antigua=position;
                }
            }
        });

        db = new CebancPizza_BD(this,"CebancPizza",null,1);
        sql = db.getWritableDatabase();
        Cursor c = sql.rawQuery("SELECT IDPEDIDO, IDCLIENTE FROM CABECERA",null);
        while(c.moveToNext()){
            idPedido.add(c.getInt(0));
            pedidos.add(c.getInt(0)+" - "+c.getInt(1));
            adapter.notifyDataSetChanged();
        }

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar(posicion);
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
        try{
            sql.execSQL("DELETE FROM CABECERAS WHERE IDCABECERA = " + idPedido.get(pos));
            sql.execSQL("DELETE FROM LINEAS WHERE IDCABECERA = " + idPedido.get(pos));
            sql.execSQL("DELETE FROM PIZZA_PEDIDA WHERE IDCABECERA = " + idPedido.get(pos));
            pedidos.remove(pos);
            idPedido.remove(pos);
            adapter.notifyDataSetChanged();
        }catch (IndexOutOfBoundsException e){
            Toast.makeText(this,"No hay elementos seleccionados",Toast.LENGTH_SHORT).show();
        }
    }
}