package com.aitor.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Clase con las diferentes pizzas
 */

public class CebancPizza_Carta extends AppCompatActivity{
    TextView textopru;
    String texto;
    InformacionCliente client;
    Button carbonara, barbacoa, quesos, vegetal, tropical, nextBebidas, salir;
    Button carro;
    ArrayList<EstructuraArray> datosPizza = new ArrayList();
    ArrayList<InformacionPizza> pizza = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_pizzas);
        textopru = (TextView) findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        datosPizza = (ArrayList<EstructuraArray>) extras.getSerializable("datos");
        client = (InformacionCliente) datosPizza.get(0).getObj();
        texto = "Escoja su pizza, "+client.getNombre();
        textopru.setText(texto);
        salir = (Button) findViewById(R.id.btnSalirPizzas);
        carro = (Button) findViewById(R.id.carrito);
        carbonara=(Button) findViewById(R.id.btnCarbonara);
        barbacoa = (Button) findViewById(R.id.btnBarbacoa);
        quesos = (Button) findViewById(R.id.btnQuesos);
        vegetal = (Button) findViewById(R.id.btnVegetal);
        tropical = (Button) findViewById(R.id.btnTropical);
        nextBebidas = (Button) findViewById(R.id.sigBebidas);
        /**
         * Los diferentes ClickListeners con sus metodos onClick de los botones, llamando al metodo
         * añadir que se le pasa el nombre de cada pizza  (abajo explicamos su funcionamiento y el
         * de todos los metodos)
         */
        carbonara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir("Carbonara");
            }
        });
        barbacoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir("Barbacoa");
            }
        });
        quesos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir("4 quesos");
            }
        });
        vegetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir("Vegetal");
            }
        });
        tropical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir("Tropical");
            }
        });
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
    public void anadir(String tipo){

        Intent i = new Intent(this,CebancPizza_cantidad_pizza.class);
        i.putExtra("pizza", pizza);
        i.putExtra("tipo",tipo);
        startActivityForResult(i,1234);
    }

    /**
     * Metodo para pasar a la siguiente actividad de bebidas
     */
    public void siguienteBebidas(){
        Intent i = new Intent(this,CebancPizza_bebidas.class);
        EstructuraArray pizzas = new EstructuraArray("Pizzas",pizza);
        datosPizza.add(pizzas);
        i.putExtra("datos",datosPizza);
        startActivity(i);
        finish();
    }
    /**
     * Metodo para pasar al layout del carrito en el cual pasamos los arraylist de las pizzas
     */
    public void carrito(){
        int requestCode = 1233;
        Intent i = new Intent(this,CebancPizza_carrito.class);
        i.putExtra("pizza", pizza);
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
}