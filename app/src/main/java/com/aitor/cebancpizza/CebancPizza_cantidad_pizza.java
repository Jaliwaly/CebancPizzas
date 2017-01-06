package com.aitor.cebancpizza;

import android.content.Intent;
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

import java.util.ArrayList;

/**
 * Created by ingae on 14/12/2016.
 */

public class CebancPizza_cantidad_pizza extends AppCompatActivity {
    private Spinner tamano,masa;
    private ArrayAdapter<CharSequence> adaptador;
    private int cantidad=1;
    private TextView total;
    private EditText cant;
    private Button mas, menos, anadir, cancelar;
    private String nomMasa, nomTamano;
    InformacionPizza pizza = new InformacionPizza();
    ArrayList<InformacionPizza> pizzas;
    Bundle extras;
    int prMasa,prTipo;

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
        pizzas = (ArrayList<InformacionPizza>) extras.getSerializable("pizza");

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidad++;
                cant.setText(Integer.toString(cantidad));
                total.setText("Total: " + Integer.toString(calculaTotal()));
            }
        });
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cantidad>1) {
                    cantidad--;
                    cant.setText(Integer.toString(cantidad));
                    total.setText("Total: " + Integer.toString(calculaTotal()));
                }else{
                    mensajeError();
                }
            }
        });
        adaptador = ArrayAdapter.createFromResource(this, R.array.tipoMasa, android.R.layout.simple_spinner_item);
        masa = (Spinner) findViewById(R.id.spnMasa);
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        masa.setAdapter(adaptador);
        masa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                switch (position){
                    case 0:
                        nomMasa="Masa Fina";
                        prMasa=3;
                        break;
                    case 1:
                        nomMasa="Masa Normal";
                        prMasa=2;
                        break;
                }
                total.setText("Total: " + Integer.toString(calculaTotal()));
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
                switch (position) {
                    case 0:
                        nomTamano="Individual";
                        prTipo=1;
                        break;
                    case 1:
                        nomTamano="Mediana";
                        prTipo=2;
                        break;
                    case 2:
                        nomTamano="Familiar";
                        prTipo=3;
                        break;
                }
                total.setText("Total: " + Integer.toString(calculaTotal()));
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        anadir = (Button) findViewById(R.id.btnAnadir);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pizza.setCantidad(cantidad);
                pizza.setMasa(nomMasa);
                pizza.setTamano(nomTamano);
                pizza.setTipo(extras.getString("tipo"));
                pizza.setTotal(calculaTotal());
                pizzas.add(pizza);
                Intent intent = new Intent();
                intent.putExtra("pizza",pizzas);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        cancelar=(Button) findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void mensajeError(){
        Toast.makeText(this,"No puede pedir cero elementos",Toast.LENGTH_SHORT).show();
    }

    private int calculaTotal(){
        int suma = 0;
        suma = (prMasa+prTipo)*cantidad;
        return suma;
    }
}