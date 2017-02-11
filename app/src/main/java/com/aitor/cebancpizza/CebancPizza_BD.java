package com.aitor.cebancpizza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adminportatil on 08/02/2017.
 */

public class CebancPizza_BD extends SQLiteOpenHelper {

    String clientes ="CREATE TABLE IF NOT EXISTS CLIENTES (IDCLIENTE NUMBER NOT NULL, NOMBRE TEXT, DIRECCION TEXT, TELEFONO NUMBER, PRIMARY KEY(IDCLIENTE))";
    String articulos ="CREATE TABLE IF NOT EXISTS ARTICULOS (IDARTICULO NUMBER NOT NULL, NOMBRE TEXT, TIPO TEXT, PRVENT NUMBER(4,2), IMAGEN NUMBER, PRIMARY KEY(IDARTICULO))";
    String cabeceras ="CREATE TABLE IF NOT EXISTS CABECERAS (IDCABECERA NUMBER PRIMARY KEY NOT NULL, IDCLIENTE NUMBER NOT NULL, FECHAPEDIDO DATE, FOREIGN KEY(IDCLIENTE) REFERENCES CLIENTES(IDCLIENTE))";
    String lineas ="CREATE TABLE IF NOT EXISTS LINEAS (IDLINEA NUMBER PRIMARY KEY NOT NULL, IDCABECERA NUMBER NOT NULL, IDARTICULO NUMBER NOT NULL, CANTIDAD NUMBER, FOREIGN KEY(IDCABECERA) REFERENCES CABECERAS(IDCABECERA))";
    String pizza_pedida ="CREATE TABLE IF NOT EXISTS PIZZA_PEDIDA (IDLINEA NUMBER NOT NULL, MASA NUMBER, TAMANO NUMBER, FOREIGN KEY(IDLINEA) REFERENCES LINEAS(IDLINEA),FOREIGN KEY(MASA) REFERENCES MASA(IDMASA),FOREIGN KEY(TAMANO) REFERENCES TAMANO(IDTAMANO))";
    String masa ="CREATE TABLE IF NOT EXISTS MASA(IDMASA NUMBER NOT NULL, DESCRIPCION TEXT, PRECIO NUMBER, PRIMARY KEY(IDMASA))";
    String tamano ="CREATE TABLE IF NOT EXISTS TAMANO(IDTAMANO NUMBER NOT NULL, DESCRIPCION TEXT, PRECIO NUMBER, PRIMARY KEY(IDTAMANO))";


    public CebancPizza_BD(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(clientes);
        db.execSQL(articulos);
        db.execSQL(cabeceras);
        db.execSQL(lineas);
        db.execSQL(masa);
        db.execSQL(tamano);
        db.execSQL(pizza_pedida);
        insertInicial(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE CLIENTES");
        db.execSQL("DROP TABLE ARTICULOS");
        db.execSQL("DROP TABLE CABECERAS");
        db.execSQL("DROP TABLE LINEAS");
        db.execSQL("DROP TABLE MASA");
        db.execSQL("DROP TABLE TAMANO");
        db.execSQL("DROP TABLE PIZZA_PEDIDA");
        onCreate(db);
    }

    public void insertInicial(SQLiteDatabase db){
        ContentValues registros = new ContentValues();
        registros.put("IDARTICULO",1);
        registros.put("NOMBRE","CARBONARA");
        registros.put("TIPO","PIZZA");
        registros.put("PRVENT",10);
        registros.put("IMAGEN",R.drawable.p1);
        db.insert("ARTICULOS",null,registros);

        registros = new ContentValues();
        registros.put("IDARTICULO",2);
        registros.put("NOMBRE","VEGETAL");
        registros.put("TIPO","PIZZA");
        registros.put("PRVENT",12);
        registros.put("IMAGEN",R.drawable.p2);
        db.insert("ARTICULOS",null,registros);

        registros = new ContentValues();
        registros.put("IDARTICULO",3);
        registros.put("NOMBRE","BARBACOA");
        registros.put("TIPO","PIZZA");
        registros.put("PRVENT",10);
        registros.put("IMAGEN",R.drawable.p3);
        db.insert("ARTICULOS",null,registros);

        registros = new ContentValues();
        registros.put("IDARTICULO",4);
        registros.put("NOMBRE","4QUESOS");
        registros.put("TIPO","PIZZA");
        registros.put("PRVENT",9);
        registros.put("IMAGEN",R.drawable.p4);
        db.insert("ARTICULOS",null,registros);

        registros = new ContentValues();
        registros.put("IDARTICULO",5);
        registros.put("NOMBRE","TROPICAL");
        registros.put("TIPO","PIZZA");
        registros.put("PRVENT",12);
        registros.put("IMAGEN",R.drawable.p5);
        db.insert("ARTICULOS",null,registros);

        registros = new ContentValues();
        registros.put("IDARTICULO",6);
        registros.put("NOMBRE","COCACOLA");
        registros.put("TIPO","BEBIDA");
        registros.put("PRVENT",1.95);
        registros.put("IMAGEN",R.drawable.b2);
        db.insert("ARTICULOS",null,registros);

        registros = new ContentValues();
        registros.put("IDARTICULO",7);
        registros.put("NOMBRE","LIMON SODA");
        registros.put("TIPO","BEBIDA");
        registros.put("PRVENT",1.50);
        registros.put("IMAGEN",R.drawable.b6);
        db.insert("ARTICULOS",null,registros);

        registros = new ContentValues();
        registros.put("IDARTICULO",8);
        registros.put("NOMBRE","FANTA");
        registros.put("TIPO","BEBIDA");
        registros.put("PRVENT",1.95);
        registros.put("IMAGEN",R.drawable.b3);
        db.insert("ARTICULOS",null,registros);

        registros = new ContentValues();
        registros.put("IDARTICULO",9);
        registros.put("NOMBRE","NESTEA");
        registros.put("TIPO","BEBIDA");
        registros.put("PRVENT",1.85);
        registros.put("IMAGEN",R.drawable.b4);
        db.insert("ARTICULOS",null,registros);

        registros = new ContentValues();
        registros.put("IDARTICULO",10);
        registros.put("NOMBRE","CERVEZA");
        registros.put("TIPO","BEBIDA");
        registros.put("PRVENT",1.55);
        registros.put("IMAGEN",R.drawable.b5);
        db.insert("ARTICULOS",null,registros);

        registros = new ContentValues();
        registros.put("IDARTICULO",11);
        registros.put("NOMBRE","AGUA");
        registros.put("TIPO","BEBIDA");
        registros.put("PRVENT",1.35);
        registros.put("IMAGEN",R.drawable.b1);
        db.insert("ARTICULOS",null,registros);
    }

}
