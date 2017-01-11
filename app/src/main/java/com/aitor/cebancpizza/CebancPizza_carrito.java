package com.aitor.cebancpizza;

import android.content.Intent;
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
 * Created by Jaliko on 06/01/2017.
 */

public class CebancPizza_carrito extends AppCompatActivity {
    Spinner spnPizzas;
    TextView precioTot;
    Button btnVolver, btnSupPizza, btnMasPizza, btnMenosPizza, btnSupBebida, btnMasBebida, btnMenosBebida;
    private Bundle extras;
    private ArrayList<InformacionBebidas> bebidas;
    private ArrayList<InformacionPizza> pizzas;
    private ArrayAdapter<String> adaptador;
    private List<String> carroPizzas = new ArrayList<String>();
    private List<String> carroBebidas = new ArrayList<String>();
    int pos, numCantidad, total = 0;

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
        extras = getIntent().getExtras();
        pizzas = (ArrayList<InformacionPizza>) extras.getSerializable("pizza");
        bebidas = (ArrayList<InformacionBebidas>) extras.getSerializable("bebidas");
        for(int cont=0;cont<pizzas.size();cont++){
            carroPizzas.add(pizzas.get(cont).getTipo()+" tamaño "+pizzas.get(cont).getTamano()+" "+pizzas.get(cont).getMasa()+" X "+pizzas.get(cont).getCantidad());
            total+=pizzas.get(cont).getTotal();
        }
        for(int cont=0;cont<bebidas.size();cont++){
            carroBebidas.add(bebidas.get(cont).getTipo()+" X "+bebidas.get(cont).getCantidad());
            total+=bebidas.get(cont).getTotal();
        }
        adaptador = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, carroPizzas);
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
        btnSupPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarPizza();
                actuListaPizzas();
            }
        });
        btnSupBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarBebida();
            }
        });
        btnMasPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               añadirPizza();
            }
        });
        btnMenosPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restarPizza();
            }
        });
        btnMasBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirBebida();
            }
        });
        btnMenosBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restarBebida();
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atras();
            }
        });

    }
    public void atras(){
        Intent intent = new Intent();
        intent.putExtra("pizza",pizzas);
        setResult(RESULT_OK, intent);
        finish();
    }
    private int calculaTotal(){
        int suma = 0;
        for(int cont=0;cont<pizzas.size();cont++) {
            suma += pizzas.get(cont).getTotal();
        }
        return suma;
    }
    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    public void actuListaPizzas(){
        carroPizzas.clear();
        for(int cont=0;cont<pizzas.size();cont++) {
            carroPizzas.add(pizzas.get(cont).getTipo() + " tamaño " + pizzas.get(cont).getTamano() + " " + pizzas.get(cont).getMasa() + " X " + pizzas.get(cont).getCantidad());
        }
    }

    public void actuListaBebidas(){
        carroBebidas.clear();
        for(int cont=0;cont<pizzas.size();cont++) {
            carroBebidas.add(bebidas.get(cont).getTipo() + " X " + bebidas.get(cont).getCantidad());
        }
    }

    public void eliminarPizza(){
        carroPizzas.remove(pos);
        pizzas.remove(pos);
        mensaje("Se ha eliminado la pizza seleccionada de tu pedido");
        actuListaPizzas();
        precioTot.setText("Precio de tu pedido: \n"+ Integer.toString(calculaTotal())+" €");
    }
    public void añadirPizza(){
        numCantidad = pizzas.get(pos).getCantidad();
        numCantidad += 1;
        pizzas.get(pos).setCantidad(numCantidad);
        mensaje("Se ha añadido una unidad mas de la pizza seleccionada");
        precioTot.setText(precioTot.getText()+ "\n"+Integer.toString(calculaTotal())+" €");
    }
    public void restarPizza(){
        if(pizzas.get(pos).getCantidad()>1) {
            numCantidad = pizzas.get(pos).getCantidad();
            numCantidad -= 1;
            pizzas.get(pos).setCantidad(numCantidad);
            precioTot.setText("Precio de tu pedido: \n"+ Integer.toString(calculaTotal()) + " €");
        }else{
            carroPizzas.remove(pos);
            pizzas.remove(pos);
            actuListaPizzas();
        }
    }
    public void eliminarBebida(){
        carroBebidas.remove(pos);
        bebidas.remove(pos);
        mensaje("Se ha eliminado la bebida seleccionada de tu pedido");
        actuListaBebidas();
        precioTot.setText("Precio de tu pedido: \n"+ Integer.toString(calculaTotal())+" €");
    }
    public void añadirBebida(){
        numCantidad = bebidas.get(pos).getCantidad();
        numCantidad += 1;
        bebidas.get(pos).setCantidad(numCantidad);
        mensaje("Se ha añadido una unidad mas de la bebida seleccionada");
        precioTot.setText(precioTot.getText()+ "\n"+Integer.toString(calculaTotal())+" €");
    }
    public void restarBebida(){
        if(bebidas.get(pos).getCantidad()>1) {
            numCantidad = bebidas.get(pos).getCantidad();
            numCantidad -= 1;
            bebidas.get(pos).setCantidad(numCantidad);
            precioTot.setText("Precio de tu pedido: \n"+ Integer.toString(calculaTotal()) + " €");
        }else{
            carroBebidas.remove(pos);
            bebidas.remove(pos);
            actuListaPizzas();
        }
    }
}
