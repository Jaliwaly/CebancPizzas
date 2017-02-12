package com.aitor.cebancpizza;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para modificar los datos de la compra
 */

public class CebancPizza_carrito extends AppCompatActivity {
    Spinner spnPizzas,spnBebidas;
    TextView precioTot;
    Button btnVolver, btnSupPizza, btnMasPizza, btnMenosPizza, btnSupBebida, btnMasBebida, btnMenosBebida;
    private Bundle extras;
    private ArrayAdapter<String> adaptador,adaptador2;
    private List<String> carroPizzas = new ArrayList<String>();
    private List<String> carroBebidas = new ArrayList<String>();
    private ArrayList<Integer> lineasp = new ArrayList<Integer>();
    private ArrayList<Integer> lineasb = new ArrayList<Integer>();
    private Cursor c;
    int pos, pos2, numCantidad, cabecera;
    CebancPizza_BD db;
    SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_carrito);
        precioTot = (TextView) findViewById(R.id.precioTot);
        btnVolver = (Button) findViewById(R.id.volver);
        btnSupPizza = (Button) findViewById(R.id.eliminarPizza);
        btnMasPizza = (Button) findViewById(R.id.masPizza);
        btnMenosPizza = (Button) findViewById(R.id.menosPizza);
        btnSupBebida = (Button) findViewById(R.id.eliminarBebida);
        btnMasBebida = (Button) findViewById(R.id.masBebida);
        btnMenosBebida = (Button) findViewById(R.id.menosBebida);
        spnPizzas = (Spinner) findViewById(R.id.spnPizzas);
        spnBebidas = (Spinner) findViewById(R.id.spnBebidas);
        extras = getIntent().getExtras();
        cabecera=extras.getInt("pedido");
        db = new CebancPizza_BD(this,"CebancPizza",null,1);
        sql = db.getWritableDatabase();

        c = sql.rawQuery("SELECT NOMBRE, TAMANO.DESCRIPCION, MASA.DESCRIPCION, CANTIDAD, LINEAS.IDLINEA FROM LINEAS, ARTICULOS, MASA, TAMANO, PIZZA_PEDIDA WHERE MASA.IDMASA=PIZZA_PEDIDA.MASA AND TAMANO.IDTAMANO=PIZZA_PEDIDA.TAMANO AND LINEAS.IDLINEA=PIZZA_PEDIDA.IDLINEA AND LINEAS.IDARTICULO=ARTICULOS.IDARTICULO AND IDCABECERA = "+cabecera+" AND TIPO='PIZZA'",null);

        while (c.moveToNext()){
            carroPizzas.add(c.getString(0) + " tamaño " + c.getString(1) + " " + c.getString(2) + " X " + c.getInt(3));
            lineasp.add(c.getInt(4));
        }

        //Se inicializa el spinner y se le da añde los elementos
        adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, carroPizzas);
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnPizzas.setAdapter(adaptador);
        spnPizzas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        c = sql.rawQuery("SELECT NOMBRE, CANTIDAD, IDLINEA FROM LINEAS, ARTICULOS WHERE LINEAS.IDARTICULO=ARTICULOS.IDARTICULO AND IDCABECERA = "+cabecera+" AND TIPO='BEBIDA'",null);

        while (c.moveToNext()){
            carroPizzas.add(c.getString(0) + " X " + c.getInt(1));
            lineasb.add(c.getInt(2));
        }

            //Se inicializa el spinner y se le da añde los elementos
            adaptador2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, carroBebidas);
            adaptador2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spnBebidas.setAdapter(adaptador2);
            spnBebidas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    pos2 = position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        //Boton para borrar la pizza seleccionada en el spinner de pizzas
        btnSupPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    eliminarPizza();
                }catch (IndexOutOfBoundsException e){
                    mensaje("No hay elementos seleccionados");
                }
            }
        });

        //Botón para borrar la bebia seleccionada en el spinner de bebidas
        btnSupBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    eliminarBebida();
                }catch (IndexOutOfBoundsException e){
                    mensaje("No hay elementos seleccionados");
                }
            }
        });

        //Botón para añadir una pizza más al elemento seleccionado en el spinner de pizzas
        btnMasPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    anadirPizza();
                }catch (IndexOutOfBoundsException e){
                    mensaje("No hay elementos seleccionados");
                }
            }
        });

        //Botón para restar una pizza al elemento seleccionado en el spinner de pizzas
        btnMenosPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    restarPizza();
                }catch (IndexOutOfBoundsException e){
                    mensaje("No hay elementos seleccionados");
                }
            }
        });

        //Botón para añadir una bebida más al elemento seleccionado en el spinner de bebidas
        btnMasBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    anadirBebida();
                }catch (IndexOutOfBoundsException | NullPointerException e){
                    mensaje("No hay elementos seleccionados");
                }
            }
        });

        //Botón para restar una bebida al elemento seleccionado en el spinner de bebidas
        btnMenosBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    restarBebida();
                }catch (IndexOutOfBoundsException | NullPointerException e){
                    mensaje("No hay elementos seleccionados");
                }
            }
        });

        //Botón con el que se vuelve a la actividad anterior
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Se añade el precio actual de la cesta
        precioTot.setText("Precio de tu pedido: " + String.format("%.02f",calculaTotal()) + " €");
    }

    //Función para calcular el precio actual de la cesta
    private float calculaTotal(){
        float suma = 0;
        c=sql.rawQuery("SELECT CANTIDAD, PRVENT, MASA.PRECIO, TAMANO.PRECIO FROM PIZZA_PEDIDA, MASA, TAMANO, LINEAS, ARTICULOS WHERE ARTICULOS.IDARTICULO=LINEAS.IDARTICULO AND LINEAS.IDLINEA=PIZZA_PEDIDA.IDLINEA AND PIZZA_PEDIDA.MASA=MASA.IDMASA AND PIZZA_PEDIDA.TAMANO = TAMANO.IDTAMANO AND TIPO = 'PIZZA' AND IDCABECERA = "+cabecera,null);

        while(c.moveToNext()){
            suma+=c.getInt(0)*(c.getFloat(1)+c.getInt(2)+c.getInt(3));
        }

        c=sql.rawQuery("SELECT CANTIDAD, PRVENT FROM LINEAS, ARTICULOS WHERE ARTICULOS.IDARTICULO=LINEAS.IDARTICULO AND TIPO = 'BEBIDA' AND IDCABECERA = "+cabecera,null);

        while(c.moveToNext()){
            suma+=c.getInt(0)*c.getFloat(1);
        }

        return suma;
    }

    //Función para escribir mensajes
    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    //Función para eliminar la pizza seleccionada en el spinner de pizzas
    public void eliminarPizza(){

        sql.execSQL("DELETE FROM PIZZA_PEDIDA WHERE IDLINEA = "+ lineasp.get(pos));
        sql.execSQL("DELETE FROM LINEAS WHERE IDLINEA = "+ lineasp.get(pos));
        lineasp.remove(pos);
        carroPizzas.remove(pos);
        mensaje("Se ha eliminado la pizza seleccionada de tu pedido");
        precioTot.setText("Precio de tu pedido: " + String.format("%.02f",calculaTotal()) + " €");
        adaptador.notifyDataSetChanged();
    }

    //Función para añadir una pizza más del tipo seleccionado
    public void anadirPizza(){

        sql.execSQL("UPDATE LINEAS SET CANTIDAD = CANTIDAD + 1 WHERE IDLINEA = " +lineasp.get(pos));
        c=sql.rawQuery("SELECT NOMBRE, CANTIDAD, TAMANO.DESCRIPCION, MASA.DESCRIPCION FROM LINEAS, ARTICULOS, PIZZA_PEDIDA, MASA, TAMANO WHERE MASA.IDMASA=PIZZA_PEDIDA.MASA AND TAMANO.IDTAMANO=PIZZA_PEDIDA.TAMANO AND LINEAS.IDLINEA=PIZZA_PEDIDA.IDLINEA AND LINEAS.IDARTICULO=ARTICULOS.IDARTICULO AND LINEAS.IDLINEA = " + lineasp.get(pos),null);

        c.moveToFirst();

        carroPizzas.set(pos,c.getString(0) + " tamaño " + c.getString(2) + " " + c.getString(3) + " X " + c.getInt(1));

        mensaje("Se ha añadido una unidad mas de la pizza seleccionada");
        precioTot.setText("Precio de tu pedido: "+ String.format("%.02f",calculaTotal()) +" €");
        adaptador.notifyDataSetChanged();
    }

    //Función para restar una pizza del tipo seleccionado
    public void restarPizza(){

        c=sql.rawQuery("SELECT NOMBRE, CANTIDAD, TAMANO.DESCRIPCION, MASA.DESCRIPCION FROM LINEAS, ARTICULOS, PIZZA_PEDIDA, MASA, TAMANO WHERE MASA.IDMASA=PIZZA_PEDIDA.MASA AND TAMANO.IDTAMANO=PIZZA_PEDIDA.TAMANO AND LINEAS.IDLINEA=PIZZA_PEDIDA.IDLINEA AND LINEAS.IDARTICULO=ARTICULOS.IDARTICULO AND LINEAS.IDLINEA = " + lineasp.get(pos),null);
        c.moveToFirst();

        if(c.getInt(1)>1) {
            carroPizzas.set(pos,c.getString(0) + " tamaño " + c.getString(2) + " " + c.getString(3) + " X " + (c.getInt(1)-1));
            sql.execSQL("UPDATE LINEAS SET CANTIDAD = CANTIDAD - 1 WHERE IDLINEA = " +lineasp.get(pos));
            precioTot.setText("Precio de tu pedido: "+ String.format("%.02f",calculaTotal()) +" €");
            adaptador.notifyDataSetChanged();
        }else{
            eliminarPizza();
        }
    }

    //Función para eliminar una bebida del spinner de bebidas
    public void eliminarBebida(){

        sql.execSQL("DELETE FROM LINEAS WHERE IDLINEA = "+ lineasb.get(pos2));

        lineasb.remove(pos2);
        carroBebidas.remove(pos2);
        mensaje("Se ha eliminado la bebida seleccionada de tu pedido");
        precioTot.setText("Precio de tu pedido: "+ String.format("%.02f",calculaTotal()) +" €");
        adaptador2.notifyDataSetChanged();
    }

    //Función para añadir una bebida más del tipo seleccionado
    public void anadirBebida(){
        sql.execSQL("UPDATE LINEAS SET CANTIDAD = CANTIDAD + 1 WHERE IDLINEA = "+ lineasb.get(pos2));
        c=sql.rawQuery("SELECT NOMBRE, CANTIDAD FROM LINEAS, ARTICULOS WHERE ARTICULOS.IDARTICULO=LINEAS.IDARTICULO AND WHERE IDLINEA = " + lineasb.get(pos2),null);

        c.moveToFirst();

        carroBebidas.set(pos2,c.getString(0) + " X " + c.getInt(1));

        mensaje("Se ha añadido una unidad mas de la pizza seleccionada");
        precioTot.setText("Precio de tu pedido: "+ String.format("%.02f",calculaTotal()) +" €");
        adaptador2.notifyDataSetChanged();
    }

    //Función para restar una bebida del tipo seleccionado
    public void restarBebida(){
        c=sql.rawQuery("SELECT NOMBRE, CANTIDAD, ARTICULOS WHERE ARTICULOS.IDARTICULO=LINEAS.IDARTICULO AND FROM LINEAS WHERE IDLINEA = " + lineasb.get(pos2),null);
        c.moveToNext();

        if(c.getInt(1)>1) {
            carroBebidas.set(pos2,c.getString(0) + " X " + (c.getInt(1)-1));
            sql.execSQL("UPDATE LINEAS SET CANTIDAD = CANTIDAD - 1 WHERE IDLINEA = " +lineasb.get(pos2));
            precioTot.setText("Precio de tu pedido: "+ String.format("%.02f",calculaTotal()) +" €");
            adaptador2.notifyDataSetChanged();
        }else{
            eliminarBebida();
        }
    }
}