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
        sql = db.getWritableDatabase();
        Cursor c = sql.rawQuery("SELECT NOMBRE FROM CLIENTES",null);
        while(c.moveToNext() && existe == false){
            if (nombre.equalsIgnoreCase(c.getString(0))){
                sql.execSQL("UPDATE CLIENTES SET TELEFONO = "+ Integer.parseInt(tlf.getText().toString()) +" , DIRECCION = '" + dir.getText().toString()+"' WHERE NOMBRE = '"+nombre+"'");
                existe=true;
                mensaje("Tu información ha sido actualizada");
            }
        }
        if(existe) {
            c =sql.rawQuery("SELECT IDCLIENTE FROM CLIENTES WHERE NOMBRE = '"+nombre+"'",null);
            c.moveToNext();
            numCli=c.getInt(0);
        }else{
            c =sql.rawQuery("SELECT MAX(IDCLIENTE) FROM CLIENTES",null);
            c.moveToNext();
            numCli=c.getInt(0)+1;
            sql.execSQL("INSERT INTO CLIENTES VALUES("+numCli+",'"+nom.getText().toString()+"','"+dir.getText().toString()+"','"+tlf.getText().toString()+"')");
            mensaje("Tu información se ha guardado correctamente");
        }

        c =sql.rawQuery("SELECT IFNULL(MAX(IDCABECERA),0) FROM CABECERAS",null);
        c.moveToNext();
        numPedido=c.getInt(0)+1;

        sql.execSQL("INSERT INTO CABECERAS VALUES("+numPedido+","+numCli+",DATETIME())");

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
    private void mensaje(String msj){
        Toast.makeText(this,msj,Toast.LENGTH_LONG).show();
    }
}