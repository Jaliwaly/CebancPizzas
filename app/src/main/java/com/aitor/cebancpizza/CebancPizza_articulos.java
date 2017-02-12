package com.aitor.cebancpizza;

import android.content.Intent;
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

public class CebancPizza_articulos extends AppCompatActivity {

    private Button anadir, modificar, borrar, salir;
    private ArrayAdapter<String> adapter;
    private ListView lista;
    private ArrayList<String> articulos = new ArrayList<String>();
    private ArrayList<Integer> idArticulo = new ArrayList<Integer>();
    private int posicion, antigua;
    CebancPizza_BD db;
    SQLiteDatabase sql;
    String nombre = "", tipo = "";
    double precio = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_articulos);

        modificar=(Button) findViewById(R.id.btnModificarART);
        anadir=(Button) findViewById(R.id.btnAnadirART);
        borrar=(Button) findViewById(R.id.btnBorrarART);
        salir=(Button) findViewById(R.id.btnSalirART);
        lista=(ListView) findViewById(R.id.listART);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, articulos);
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
        Cursor c = sql.rawQuery("SELECT IDARTICULO, NOMBRE, TIPO FROM ARTICULOS",null);
        while(c.moveToNext()){
            idArticulo.add(c.getInt(0));
            articulos.add(c.getInt(0)+" - "+c.getString(1)+" - "+c.getString(2));
            adapter.notifyDataSetChanged();
        }

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguienteModificar(posicion);
            }
        });
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuNewArt();
            }
        });
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
    private void siguienteModificar(int pos){
        Intent i = new Intent(this,CebancPIzza_newarticulo.class);
        startActivityForResult(i,12345);
    }
    private void eliminar(int pos){
        try{
            sql.execSQL("DELETE FROM ARTICULOS WHERE IDARTICULO = " + idArticulo.get(pos));
            articulos.remove(pos);
            idArticulo.remove(pos);
            adapter.notifyDataSetChanged();
        }catch (IndexOutOfBoundsException e){
            Toast.makeText(this,"No hay elementos seleccionados",Toast.LENGTH_SHORT).show();
        }
    }
    private void menuNewArt(){
        Intent i = new Intent(this,CebancPIzza_newarticulo.class);
        startActivityForResult(i,1234);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            nombre = data.getExtras().getString("Nombre");
            tipo = data.getExtras().getString("Tipo");
            precio = data.getExtras().getDouble("Precio");
            Cursor c = sql.rawQuery("SELECT MAX(IDARTICULO) FROM ARTICULOS",null);
            c.moveToFirst();
            int articuloNuevo = c.getInt(0)+1;
            sql.execSQL("INSERT INTO ARTICULOS VALUES("+articuloNuevo+",'"+nombre+"','"+tipo+"',"+precio+","+R.drawable.ppd);
            idArticulo.clear();
            articulos.clear();
            c = sql.rawQuery("SELECT IDARTICULO, NOMBRE, TIPO FROM ARTICULOS",null);
            while(c.moveToNext()){
                idArticulo.add(c.getInt(0));
                articulos.add(c.getInt(0)+" - "+c.getString(1)+" - "+c.getString(2));
                adapter.notifyDataSetChanged();
            }
        }else{
            nombre = data.getExtras().getString("Nombre");
            tipo = data.getExtras().getString("Tipo");
            precio = data.getExtras().getDouble("Precio");
            Cursor c = sql.rawQuery("SELECT IDARTICULO FROM ARTICULOS WHERE IDARTICULO = "+idArticulo.get(posicion),null);
            c.moveToFirst();
            sql.execSQL("UPDATE ARTICULOS SET NOMBRE = '"+nombre+"', TIPO = '"+tipo+"', PRECIO = "+precio+" WHERE IDARTICULO = "+idArticulo.get(posicion));
            idArticulo.clear();
            articulos.clear();
            c = sql.rawQuery("SELECT IDARTICULO, NOMBRE, TIPO FROM ARTICULOS",null);
            while(c.moveToNext()){
                idArticulo.add(c.getInt(0));
                articulos.add(c.getInt(0)+" - "+c.getString(1)+" - "+c.getString(2));
                adapter.notifyDataSetChanged();
            }
        }
    }
}