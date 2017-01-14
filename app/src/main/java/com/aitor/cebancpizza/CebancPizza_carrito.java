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
    Spinner spnPizzas,spnBebidas;
    TextView precioTot;
    Button btnVolver, btnSupPizza, btnMasPizza, btnMenosPizza, btnSupBebida, btnMasBebida, btnMenosBebida;
    private Bundle extras;
    private ArrayList<InformacionBebidas> bebidas;
    private ArrayList<InformacionPizza> pizzas;
    private ArrayAdapter<String> adaptador,adaptador2;
    private List<String> carroPizzas = new ArrayList<String>();
    private List<String> carroBebidas = new ArrayList<String>();
    int pos, pos2, numCantidad;
    int ventana;

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
        ventana = extras.getInt("requestCode");
        pizzas = (ArrayList<InformacionPizza>) extras.getSerializable("pizza");
        for (int cont = 0; cont < pizzas.size(); cont++) {
            carroPizzas.add(pizzas.get(cont).getTipo() + " tamaño " + pizzas.get(cont).getTamano() + " " + pizzas.get(cont).getMasa() + " X " + pizzas.get(cont).getCantidad());
        }
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
        if (ventana == 12344) {
            bebidas = (ArrayList<InformacionBebidas>) extras.getSerializable("bebidas");
            for (int cont = 0; cont < bebidas.size(); cont++) {
                carroBebidas.add(bebidas.get(cont).getTipo() + " X " + bebidas.get(cont).getCantidad());
            }
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
        }
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
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atras();
            }
        });
        precioTot.setText("Precio de tu pedido: " + String.format("%.02f",calculaTotal()) + " €");
    }
    public void atras(){
        Intent intent = new Intent();
        intent.putExtra("pizza",pizzas);
        intent.putExtra("bebidas", bebidas);
        setResult(RESULT_OK, intent);
        finish();
    }
    private float calculaTotal(){
        float suma = 0;
        for(int cont=0;cont<pizzas.size();cont++) {
            suma += pizzas.get(cont).getTotal();
        }
        if (ventana==12344){
            for(int cont=0;cont<bebidas.size();cont++){
                suma+=bebidas.get(cont).getTotal();
            }
        }
        return suma;
    }
    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    public void eliminarPizza(){
            carroPizzas.remove(pos);
            pizzas.remove(pos);
            mensaje("Se ha eliminado la pizza seleccionada de tu pedido");
            precioTot.setText("Precio de tu pedido: " + String.format("%.02f",calculaTotal()) + " €");
            adaptador.notifyDataSetChanged();
    }
    public void anadirPizza(){
        float precio = pizzas.get(pos).getTotal();
        numCantidad = pizzas.get(pos).getCantidad();
        precio /= numCantidad;
        numCantidad += 1;
        precio*=numCantidad;
        pizzas.get(pos).setTotal(precio);
        pizzas.get(pos).setCantidad(numCantidad);
        mensaje("Se ha añadido una unidad mas de la pizza seleccionada");
        precioTot.setText("Precio de tu pedido: "+ String.format("%.02f",calculaTotal()) +" €");
        carroPizzas.set(pos,pizzas.get(pos).getTipo() + " tamaño " + pizzas.get(pos).getTamano() + " " + pizzas.get(pos).getMasa() + " X " + pizzas.get(pos).getCantidad());
        adaptador.notifyDataSetChanged();
    }
    public void restarPizza(){
        if(pizzas.get(pos).getCantidad()>1) {
            float precio = pizzas.get(pos).getTotal();
            numCantidad = pizzas.get(pos).getCantidad();
            precio /= numCantidad;
            numCantidad -= 1;
            precio*=numCantidad;
            pizzas.get(pos).setTotal(precio);
            pizzas.get(pos).setCantidad(numCantidad);
            precioTot.setText("Precio de tu pedido: "+ String.format("%.02f",calculaTotal()) +" €");
            carroPizzas.set(pos,pizzas.get(pos).getTipo() + " tamaño " + pizzas.get(pos).getTamano() + " " + pizzas.get(pos).getMasa() + " X " + pizzas.get(pos).getCantidad());
            adaptador.notifyDataSetChanged();
        }else{
            eliminarPizza();
        }
    }
    public void eliminarBebida(){
        carroBebidas.remove(pos2);
        bebidas.remove(pos2);
        mensaje("Se ha eliminado la bebida seleccionada de tu pedido");
        precioTot.setText("Precio de tu pedido: "+ String.format("%.02f",calculaTotal()) +" €");
        adaptador2.notifyDataSetChanged();
    }
    public void anadirBebida(){
        float precio = bebidas.get(pos2).getTotal();
        numCantidad = bebidas.get(pos2).getCantidad();
        precio /= numCantidad;
        numCantidad += 1;
        precio*=numCantidad;
        bebidas.get(pos2).setTotal(precio);
        bebidas.get(pos2).setCantidad(numCantidad);
        mensaje("Se ha añadido una unidad mas de la bebida seleccionada");
        precioTot.setText("Precio de tu pedido: "+ String.format("%.02f",calculaTotal()) +" €");
        carroBebidas.set(pos2,bebidas.get(pos2).getTipo() + " X " + bebidas.get(pos2).getCantidad());
        adaptador2.notifyDataSetChanged();
    }
    public void restarBebida(){
        if(bebidas.get(pos2).getCantidad()>1) {
            float precio = bebidas.get(pos2).getTotal();
            numCantidad = bebidas.get(pos2).getCantidad();
            precio /= numCantidad;
            numCantidad -= 1;
            precio*=numCantidad;
            bebidas.get(pos2).setTotal(precio);
            bebidas.get(pos2).setCantidad(numCantidad);
            precioTot.setText("Precio de tu pedido: "+ String.format("%.02f",calculaTotal()) + " €");
            carroBebidas.set(pos2,bebidas.get(pos2).getTipo() + " X " + bebidas.get(pos2).getCantidad());
            adaptador2.notifyDataSetChanged();
        }else{
            eliminarBebida();
        }
    }
}
