package com.aitor.cebancpizza;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Clase con las diferentes pizzas
 */

public class CebancPizza_Carta extends AppCompatActivity{
    InformacionCliente client;
    Button nextBebidas, salir;
    Button carro;
    ArrayList<EstructuraArray> datosPizza = new ArrayList();
    ArrayList<InformacionPizza> pizza = new ArrayList();
    ArrayList<VistasArticulos> vaa = new ArrayList<VistasArticulos>();
    ScrollView sc;
    CebancPizza_BD db;
    SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_pizzas);
        salir = (Button) findViewById(R.id.btnSalirPizzas);
        carro = (Button) findViewById(R.id.carrito);
        nextBebidas = (Button) findViewById(R.id.sigBebidas);
        sc = (ScrollView) findViewById(R.id.scroll);
        db = new CebancPizza_BD(this,"CebancPizza.db",null,1);
        sql = db.getReadableDatabase();

        final Cursor c = sql.rawQuery("Select * from articulos",null);
        while(c.moveToNext()){
            vaa.add(new VistasArticulos(c.getInt(0),c.getString(1),c.getInt(5),c.getFloat(3),c.getString(2)));
        }
        for(final VistasArticulos vista:vaa){
            if(vista.getTipo() == "PIZZA") {
                TextView tv = new TextView(getApplicationContext());
                ImageView iv = new ImageView(getApplicationContext());
                Button btn = new Button(getApplicationContext());
                tv.setText(vista.getNombre());
                iv.setId(vista.getImagen());
                btn.setId(vista.getIdarticulo());
                sc.addView(tv);
                sc.addView(iv);
                sc.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        anadir(vista.getIdarticulo());
                    }
                });
            }
        }
        nextBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguienteBebidas();
            }
        });
        /**
         * Boton de la cesta que al hacer click ejecuta el metodo
         * carrito (abajo explicamos su funcionamiento y el de todos los metodos)
         */
        carro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carrito();
            }
        });
        /**
         * Boton para salir de la aplicacion
         */
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     *Metodo para abrir la actividad cantidad pizza y en ella especificar los diferentes atriutos de
     * la pizza
     */
    public void anadir(int articulo){

        Intent i = new Intent(this,CebancPizza_cantidad_pizza.class);
        i.putExtra("tipo",articulo);
        startActivityForResult(i,1234);
    }

    /**
     * Metodo para pasar a la siguiente actividad de bebidas
     */
    public void siguienteBebidas(){
        Intent i = new Intent(this,CebancPizza_bebidas.class);
        startActivity(i);
        finish();
    }
    /**
     * Metodo para pasar al layout del carrito en el cual pasamos los arraylist de las pizzas
     */
    public void carrito(){
        int requestCode = 1233;
        Intent i = new Intent(this,CebancPizza_carrito.class);
        i.putExtra("requestCode",requestCode);
        startActivityForResult(i,requestCode);
    }
    /**
     *Metodo para recojer del carrito los el arraylist de pizza
     */
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK) {
            pizza=(ArrayList<InformacionPizza>) data.getExtras().getSerializable("pizza");
        }
    }
    class VistasArticulos {
        int idarticulo, imagen;
        String nombre, tipo;
        float prVent;

        VistasArticulos(int idarticulo, String nombre, int imagen, float prVent, String tipo){
            this.idarticulo = idarticulo;
            this.nombre = nombre;
            this.imagen = imagen;
            this.prVent = prVent;
            this.tipo = tipo;
        }
        public void setIdArticulo(int idarticulo){
            this.idarticulo = idarticulo;
        }
        public int getIdarticulo(){
            return idarticulo;
        }
        public void setNombreArticulo(String nombre){
            this.nombre = nombre;
        }
        public String getNombre(){
            return nombre;
        }
        public void setImagen(int imagen){
            this.imagen = imagen;
        }
        public int getImagen(){
            return imagen;
        }
        public void setPrVent(float prVent){
            this.prVent = prVent;
        }
        public float getPrVent(){
            return prVent;
        }
        public void setTipo(String tipo){
            this.tipo = tipo;
        }
        public String getTipo(){
            return tipo;
        }
    }
}