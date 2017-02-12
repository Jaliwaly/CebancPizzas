package com.aitor.cebancpizza;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Clase para añadir las especificaciones de las pizzas seleccionadas
 */

public class CebancPizza_cantidad_pizza extends AppCompatActivity {
    private Spinner sptamano,spmasa;
    private ArrayAdapter<String> adaptadorMasa, adaptadorTamano;
    private ArrayList<String> masa = new ArrayList<String>();
    private ArrayList<String> tamano = new ArrayList<String>();
    private ArrayList<Integer> prMasa = new ArrayList<Integer>(), prTamano = new ArrayList<Integer>();
    private TextView total;
    private EditText cant;
    private Button mas, menos, anadir, cancelar;
    Bundle extras;
    int cabecera, idPizza, cantidad=1, prPizza, posMasa, posTamano;
    CebancPizza_BD db;
    SQLiteDatabase sql;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_cantidad);
        total = (TextView) findViewById(R.id.lblTotal);
        cant= (EditText) findViewById(R.id.txtCantidad);
        cant.setText(Integer.toString(cantidad));
        total.setText(total.getText() + " " + cantidad);
        mas=(Button) findViewById(R.id.btnMas);
        menos=(Button) findViewById(R.id.btnMenos);
        extras = getIntent().getExtras();
        cabecera = extras.getInt("pedido");
        idPizza = extras.getInt("tipo");
        //Añade al texto de cantidad uno
        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidad++;
                cant.setText(Integer.toString(cantidad));
                total.setText("Total: "+calculaTotal()+"€");
            }
        });

        //Resta al texto de cantidad uno, pero sin dejarle ser menor que uno
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cantidad>1) {
                    cantidad--;
                    cant.setText(Integer.toString(cantidad));
                    total.setText("Total: "+calculaTotal()+"€");
                }else{
                    mensaje("No puede pedir cero elementos");
                }
            }
        });

        db = new CebancPizza_BD(this,"CebancPizza",null,1);
        sql = db.getWritableDatabase();

        c=sql.rawQuery("SELECT PRVENT FROM ARTICULOS WHERE IDARTICULO = "+idPizza,null);
        c.moveToFirst();
        prPizza = c.getInt(0);

        //Creación del spinner para la masa de la pizza
        c = sql.rawQuery("SELECT DESCRIPCION, PRECIO FROM MASA",null);
        while(c.moveToNext()){
            masa.add(c.getString(0)+" - "+c.getInt(1));
            prMasa.add(c.getInt(1));
        }

        c = sql.rawQuery("SELECT DESCRIPCION, PRECIO FROM TAMANO",null);
        while(c.moveToNext()){
            tamano.add(c.getString(0)+" - "+c.getInt(1));
            prTamano.add(c.getInt(1));
        }

        adaptadorMasa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, masa);
        adaptadorTamano = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, tamano);
        spmasa = (Spinner) findViewById(R.id.spnMasa);
        spmasa.setAdapter(adaptadorMasa);
        spmasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                posMasa=position;
                total.setText("Total: "+calculaTotal()+"€");
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Creación del spinner para el tamaño de la pizza
        sptamano = (Spinner) findViewById(R.id.spnTamano);
        sptamano.setAdapter(adaptadorTamano);
        sptamano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                posTamano=position;
                total.setText("Total: "+calculaTotal()+"€");
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Boton para aceptar los cambios y pasar de nuevo a la carta de pizzas
        anadir = (Button) findViewById(R.id.btnAnadir);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int linea;
                c=sql.rawQuery("SELECT MAX(IDLINEA) FROM LINEAS",null);
                c.moveToFirst();
                linea=c.getInt(0) + 1;
                sql.execSQL("INSERT INTO LINEAS VALUES("+linea+","+cabecera+","+idPizza+","+cantidad+")");
                sql.execSQL("INSERT INTO PIZZA_PEDIDA VALUES("+linea+","+(posMasa + 1)+","+(posTamano + 1)+")");
                mensaje("Pizza añadida");
                finish();
            }
        });

        //Boton para pasar de nuevo a la carta de pizzas sin realizar cambios
        cancelar=(Button) findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String calculaTotal(){
        float total=(prPizza+prTamano.get(posTamano)+prMasa.get(posMasa))*cantidad;
        return Float.toString(total);
    }

    //Función que escribe mensajes Toast
    private void mensaje(String msj){
        Toast.makeText(this,msj,Toast.LENGTH_SHORT).show();
    }
}