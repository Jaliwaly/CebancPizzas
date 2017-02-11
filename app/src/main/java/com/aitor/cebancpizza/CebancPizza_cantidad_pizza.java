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
    private ArrayAdapter<String> masa;
    private ArrayAdapter<String> tamano;
    private ArrayList<Integer> prMasa, prTamano;

    private int cantidad=1;
    private TextView total;
    private EditText cant;
    private Button mas, menos, anadir, cancelar;
    private String nomMasa, nomTamano;
    Bundle extras;
    int prTipo,cabecera, idPizza;
    CebancPizza_BD db;
    SQLiteDatabase sql;
    Cursor c;
    float precio;

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
                total.setText("Total: "+"€");
            }
        });

        //Resta al texto de cantidad uno, pero sin dejarle ser menor que uno
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cantidad>1) {
                    cantidad--;
                    cant.setText(Integer.toString(cantidad));
                    total.setText("Total: "+"€");
                }else{
                    mensajeError();
                }
            }
        });

        db = new CebancPizza_BD(this,"CebancPizza",null,1);
        sql = db.getWritableDatabase();

        //Creación del spinner para la masa de la pizza
        c = sql.rawQuery("SELECT MASA.DESCRIPCION, MASA.PRECIO,TAMANO.DESCRIPCION, TAMANO.PRECIO FROM MASA, TAMANO",null);
        while(c.moveToNext()){
            masa.add(c.getString(0)+" - "+c.getInt(1));
            tamano.add(c.getString(2)+" - "+c.getInt(3));
            prMasa.add(c.getInt(1));
            prTamano.add(c.getInt(3));
        }

        spmasa = (Spinner) findViewById(R.id.spnMasa);
        spmasa.setAdapter(masa);
        spmasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Creación del spinner para el tamaño de la pizza
        sptamano = (Spinner) findViewById(R.id.spnTamano);
        sptamano.setAdapter(tamano);
        sptamano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                switch (position) {
                    case 0:
                        nomTamano="Individual";
                        prTipo=0;
                        break;
                    case 1:
                        nomTamano="Mediana";
                        prTipo=2;
                        break;
                    case 2:
                        nomTamano="Familiar";
                        prTipo=5;
                        break;
                }
                total.setText("Total: " +"€");
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Boton para aceptar los cambios y pasar de nuevo a la carta de pizzas
        anadir = (Button) findViewById(R.id.btnAnadir);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //        pizza.setCantidad(cantidad);
          //      pizza.setMasa(nomMasa);
            //    pizza.setTamano(nomTamano);
              //  pizza.setTipo(extras.getString("tipo"));
          //      pizzas.add(pizza);
                Intent intent = new Intent();
            //    intent.putExtra("pizza",pizzas);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //Boton para pasar de nuevo a la carta de pizzas sin realizar cambios
        cancelar=(Button) findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    //Función que escribe mensajes Toast
    private void mensajeError(){
        Toast.makeText(this,"No puede pedir cero elementos",Toast.LENGTH_SHORT).show();
    }
}