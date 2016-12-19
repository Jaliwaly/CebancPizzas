package com.aitor.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ingae on 14/12/2016.
 */

public class CebancPizza_cantidad_pizza extends AppCompatActivity {
    private Spinner tamano,masa;
    private ArrayAdapter<CharSequence> adaptador;
    private int cantidad=1;
    private TextView total;
    private EditText cant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_cantidad);
        adaptador = ArrayAdapter.createFromResource(this, R.array.tipoMasa, android.R.layout.simple_spinner_item);
        masa = (Spinner) findViewById(R.id.spnMasa);
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        masa.setAdapter(adaptador);
        masa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adaptador = ArrayAdapter.createFromResource(this, R.array.tamano, android.R.layout.simple_spinner_item);
        tamano = (Spinner) findViewById(R.id.spnTamano);
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        tamano.setAdapter(adaptador);
        tamano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        total = (TextView) findViewById(R.id.lblTotal);
        cant= (EditText) findViewById(R.id.txtCantidad);
        total.setText(total.getText() + " " + cantidad);
    }

    private void sumar() {
        cantidad++;
        cant.setText(Integer.toString(cantidad));
        total.setText(total.getText() + " " + calculaTotal());
    }
    private void restar(){
        if(cantidad>1) {
            cantidad--;
            cant.setText(Integer.toString(cantidad));
            total.setText(total.getText() + " " + calculaTotal());
        }else{
            Toast.makeText(this,"No puede pedir cero elementos",Toast.LENGTH_SHORT).show();
        }
    }

    private int calculaTotal(){
        int suma=0;
        return suma;
    }
}