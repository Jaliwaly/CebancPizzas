package com.aitor.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by ingae on 14/12/2016.
 */

public class CebancPizza_cantidad_pizza extends AppCompatActivity {
    private Spinner cmbOpciones;
    private ArrayAdapter<CharSequence> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_cantidad);
        adaptador = ArrayAdapter.createFromResource(this,R.array.tipoMasa, android.R.layout.simple_spinner_item);
        cmbOpciones=(Spinner) findViewById(R.id.spnMasa);
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        cmbOpciones.setAdapter(adaptador);
        cmbOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id){

            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adaptador = ArrayAdapter.createFromResource(this,R.array.tamano, android.R.layout.simple_spinner_item);
        cmbOpciones=(Spinner) findViewById(R.id.spnTamano);
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        cmbOpciones.setAdapter(adaptador);
        cmbOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id){

            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
