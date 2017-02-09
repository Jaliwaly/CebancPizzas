package com.aitor.cebancpizza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adminportatil on 08/02/2017.
 */

public class CebancPizza_BD extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="CebancPizza.db";

    String clientes ="CREATE TABLE IF NOT EXISTS CLIENTES (IDCLIENTE NUMBER NOT NULL, NOMBRE TEXT, DIRECCION TEXT, TELEFONO NUMBER, PRIMARY KEY(IDCLIENTE))";
    String articulos ="CREATE TABLE IF NOT EXISTS ARTICULOS (IDARTICULO NUMBER NOT NULL, NOMBRE TEXT, TIPO TEXT, PRIMARY KEY(IDARTICULO))";
    String cabeceras ="CREATE TABLE IF NOT EXISTS CABECERAS (IDCABECERA NUMBER NOT NULL, IDCLIENTE NUMBER NOT NULL, FECHAPEDIDO DATE, FOREIGN KEY(IDCLIENTE) REFERENCES CLIENTES(IDCLIENTE), PRIMARY KEY(IDCABECERA)";
    String lineas ="CREATE TABLE IF NOT EXISTS LINEAS (IDLINEA NUMBER NOT NULL, IDCABECERA NUMBER NOT NULL, IDARTICULO NUMBER NOT NULL, CANTIDAD NUMBER, FOREIGN KEY(IDCABECERA) REFERENCES(IDCABECERA), PRIMARY KEY(IDLINEA,IDCABECERA))";
    String pizza_pedida ="CREATE TABLE IF NOT EXISTS PIZZA_PEDIDA (IDLINEA NUMBER NOT NULL, MASA NUMBER, TAMANO NUMBER, FOREIGN KEY(IDLINEA) REFERENCES LINEAS(IDLINEA),FOREIGN KEY(MASA) REFERENCES MASA(IDMASA),FOREIGN KEY(TAMANO) REFERENCES TAMANO(IDTAMANO))";
    String masa ="CREATE TABLE IF NOT EXISTS MASA(IDMASA NUMBER NOT NULL, DESCRIPCION TEXT, PRECIO NUMBER, PRIMARY KEY(IDMASA))";
    String tamano ="CREATE TABLE IF NOT EXISTS TAMANO(IDTAMANO NUMBER NOT NULL, DESCRIPCION TEXT, PRECIO, PRIMARY KEY(IDTAMANO))";


    public CebancPizza_BD(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.getWritableDatabase();
        db.execSQL(clientes);
        db.execSQL(articulos);
        db.execSQL(cabeceras);
        db.execSQL(lineas);
        db.execSQL(masa);
        db.execSQL(tamano);
        db.execSQL(pizza_pedida);
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
}
