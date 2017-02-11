package com.aitor.cebancpizza;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Clase para escojer las bebidas del pedido
 */

public class CebancPizza_bebidas extends AppCompatActivity{
    private Button finalizar, cesta, salir;
    private ArrayList<InformacionBebidas> bebidas = new ArrayList();
    private Bundle extras;
    private ArrayList<InformacionPizza> pizza = new ArrayList();
    private LinearLayout svb;
    private int cabecera;
    ArrayList<VistasArticulosB> bebidasVista = new ArrayList<VistasArticulosB>();
    CebancPizza_BD db;
    SQLiteDatabase sq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_bebidas);
        extras = getIntent().getExtras();
        cabecera = extras.getInt("pedido");
        salir = (Button) findViewById(R.id.btnSalirBebidas);
        cesta = (Button) findViewById(R.id.carritoB);
        finalizar = (Button) findViewById(R.id.resumenPedido);
        svb = (LinearLayout) findViewById(R.id.svb);

        db = new CebancPizza_BD(this,"CebancPizza",null,1);
        sq = db.getReadableDatabase();

        final Cursor c = sq.rawQuery("SELECT * FROM ARTICULOS WHERE TIPO = 'BEBIDA'",null);
        while(c.moveToNext()){
            bebidasVista.add(new VistasArticulosB(c.getInt(0),c.getString(1),c.getInt(4),c.getFloat(3),c.getString(2)));
        }
        for(final VistasArticulosB vista:bebidasVista){
                TextView tv = new TextView(getApplicationContext());
                ImageView iv = new ImageView(getApplicationContext());
                final EditText et = new EditText(getApplicationContext());
                final Button btn = new Button(getApplicationContext());
                tv.setText(vista.getNombre());
                tv.setTextColor((getResources().getColor(R.color.black)));
                iv.setImageResource(vista.getImagen());
                btn.setId(vista.getIdarticulo());
                et.setText(0);
                btn.setText("AÑADIR");
                svb.addView(tv);
                svb.addView(iv);
                svb.addView(et);
                svb.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(et.getText().toString().equals("")){
                            mensaje("Escoja una cantidad");
                        }else{
                            pedirBebida(Integer.parseInt(et.getText().toString()), btn.getId());
                        }
                    }
                });
        }
        /**
         * Boton de la cesta que al hacer click ejecuta el metodo
         * carrito
         */
        cesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carrito();
            }
        });
        /**
         * Boton el cual nos ejecuta el metodo finalizarCompra
         */
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizarCompra();
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
    //Función que escribe mensajes Toast
    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo para pasar al ultimo layout con el resumen del pedido en el cual se pasa el array list
     * datos el cual contiene los diferentes arraylist con todos los datos del pedido
     */
    public void finalizarCompra(){
        Intent i = new Intent(this,CebancPizza_pedido.class);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para pasar al layout del carrito en el cual pasamos los arraylist de las pizzas y el
     * de las bebidas junto con el requestCode.
     */
    public void carrito(){
        int requestCode = 12344;
        Intent i = new Intent(this,CebancPizza_carrito.class);
        i.putExtra("bebidas", bebidas);
        i.putExtra("pizza",pizza);
        i.putExtra("requestCode",requestCode);
        startActivityForResult(i,requestCode);
    }

    /**
     *Metodo para recojer del carrito los dos arraylist
     */
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode==12344 && resultCode==RESULT_OK) {

        }
    }

    public void pedirBebida(int cantidad, int id){
        int linea;
        Cursor c = sq.rawQuery("SELECT MAX(IDLINEA) FROM LINEAS",null);
        c.moveToFirst();
        linea=c.getInt(0) + 1;
        sq.execSQL("INSERT INTO LINEAS VALUES("+linea+","+cabecera+","+id+","+cantidad+")");
        mensaje("Bebida añadida");
    }

    class VistasArticulosB {
        int idarticulo, imagen;
        String nombre, tipo;
        float prVent;

        VistasArticulosB(int idarticulo, String nombre, int imagen, float prVent, String tipo){
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