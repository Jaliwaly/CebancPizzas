package com.aitor.cebancpizza;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CebancPizza_Cliente extends AppCompatActivity {
    EditText nom, dir,tlf;
    Button sig, salir;
    int numCli, numPedido;
    boolean existe = false;
    String nombre;
    CebancPizza_BD db;
    SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza__cliente);
        nom = (EditText) findViewById(R.id.nombre);
        dir = (EditText) findViewById(R.id.direccion);
        tlf = (EditText) findViewById(R.id.tlf);
        sig = (Button) findViewById(R.id.siguientePantalla1);
        salir = (Button) findViewById(R.id.salirPantalla1);

        //Botón para ir a la siguiente actividad
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidarDatos() == true){
                    siguienteP1();
                }else{
                    Toast ts = Toast.makeText(getApplicationContext(),"Introduce todos los campos",Toast.LENGTH_LONG);
                    ts.show();
                }
            }
        });

        //Botón para salir de la aplicación
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Función que guarda la información del cliente en un array trás meterlo en un objeto de la clase EstructuraArray y lo pasa a la siguiente actividad
    public void siguienteP1(){
        nombre = nom.getText().toString();
        db = new CebancPizza_BD(this,"CebancPizza",null,1);
        sql = db.getReadableDatabase();
        Cursor c = sql.rawQuery("SELECT NOMBRE FROM CLIENTES",null);
        while(c.moveToNext() && existe == false){
            if (nombre.equalsIgnoreCase(c.getString(0))){
                sql.execSQL("UPDATE CLIENTES SET TELEFONO = '"+tlf.getText().toString()+"' , DIRECCION = '" + dir.getText().toString()+"' WHERE NOMBRE = '"+c.getString(0)+"'",null);
                existe=true;
            }
        }
        if(existe) {
            c =sql.rawQuery("SELECT IDCLIENTE FROM CLIENTES WHERE NOMBRE = '"+nombre+"'",null);
            c.moveToFirst();
            numCli=c.getInt(0);
        }else{
            c =sql.rawQuery("SELECT MAX(*) FROM CLIENTES",null);
            c.moveToFirst();
            numCli=c.getInt(0)+1;
            sql.execSQL("INSERT INTO CLIENTES VALUES("+numCli+",'"+dir.getText().toString()+"','"+tlf.getText().toString()+"')");
        }

        c =sql.rawQuery("SELECT NVL(MAX(*),0) FROM CLIENTES",null);
        c.moveToFirst();
        numPedido=c.getInt(0)+1;

        sql.execSQL("INSERT INTO CABECERAS VALUES("+numPedido+","+numCli+",CURDATE())");

        Intent i = new Intent(this,CebancPizza_Carta.class);
        i.putExtra("pedido",numPedido);
        startActivity(i);
        finish();
    }

    //Valida que los datos están llenos y si el teléfono tiene 9 dígitos
    private boolean ValidarDatos(){
        if (nom.getText().toString().equals("") || dir.getText().toString().equals("") || tlf.getText().toString().equals("")||tlf.getText().length()!=9){
            return false;
        }else{
            return true;
        }
    }
}