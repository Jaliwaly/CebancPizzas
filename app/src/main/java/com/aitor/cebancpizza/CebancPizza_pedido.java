package com.aitor.cebancpizza;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Clase que refleja el pedido y da la opción de realizar el pedido o salir de la aplicación
 */

public class CebancPizza_pedido extends AppCompatActivity {
    private Bundle extras;
    private ArrayList<EstructuraArray> datos;
    private AlertDialog.Builder confirmacion;
    private ArrayList<InformacionBebidas> bebidas;
    private InformacionCliente cliente;
    private ArrayList<InformacionPizza> pizzas;
    private TextView infoCliente,infoFactura,infoPrecio,infoTotal,infoRegalo,infoCantidad;
    private Button aceptar,salir;
    private String factura,precio,cantidad;
    private double total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_pedido);
        confirmacion = new AlertDialog.Builder(this);
        extras = getIntent().getExtras();
        datos = (ArrayList<EstructuraArray>) extras.getSerializable("datos");
        cliente = (InformacionCliente) datos.get(0).getObj();
        pizzas = (ArrayList<InformacionPizza>) datos.get(1).getObj();
        bebidas = (ArrayList<InformacionBebidas>) datos.get(2).getObj();
        infoCliente = (TextView) findViewById(R.id.txtCliente);
        infoFactura = (TextView) findViewById(R.id.txtArticulo);
        infoPrecio = (TextView) findViewById(R.id.txtPrecio);
        infoTotal = (TextView) findViewById(R.id.txtTotal);
        infoRegalo = (TextView) findViewById(R.id.txtRegalos);
        infoCantidad = (TextView) findViewById(R.id.txtCantidad);
        aceptar = (Button) findViewById(R.id.btnAceptar);
        salir = (Button) findViewById(R.id.btnSalir);

        //Botón para slir d ela aplicación
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Botón para aceptar el pedido. Aparecerá un Mesaje box para indicar el éxito de la compra, y luego se cerrará la aplicación
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmacion.setMessage("Gracias por realizar su compra, Buen provecho");
                confirmacion.setTitle("Entrega confirmada");
                confirmacion.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                });
                confirmacion.setCancelable(true);
                confirmacion.create().show();
            }
        });

        //Text View en el que se mostrará la información del cliente
        infoCliente.setText("Información del cliente:\n\nNombre: "+cliente.getNombre()+"\nDirección: "+cliente.getDireccion()+"\nTeléfono: "+cliente.getTelefono()+"\n\n");
        //En estos tres Text View se añaden los títulos de cada apartado
        factura="Artículos pedidos\n\n";
        cantidad="Cant.\n\n";
        precio="Precio\n\n";

        //Bucle en el que se añade a tres string todas las pizzas pedidas y su información, también calcula el precio total
        for(int cont=0;cont<pizzas.size();cont++){
            factura+=pizzas.get(cont).getTipo()+" "+pizzas.get(cont).getTamano()+" "+pizzas.get(cont).getMasa()+"\n";
            cantidad+=pizzas.get(cont).getCantidad()+"\n";
            precio+=String.format("%.02f", pizzas.get(cont).getTotal())+"€\n";
            total+=pizzas.get(cont).getTotal();
        }

        //Bucle en el que se añade a tres string todas las bebidas pedidas y su información, también calcula el precio total
        for(int cont=0;cont<bebidas.size();cont++){
            factura +=bebidas.get(cont).getTipo()+"\n";
            cantidad+=bebidas.get(cont).getCantidad()+"\n";
            precio+=String.format("%.02f", bebidas.get(cont).getTotal())+"€\n";
            total+=bebidas.get(cont).getTotal();
        }

        //Se añade a los Text View los respectivos strings para que se muestre la información
        infoFactura.setText(factura);
        infoCantidad.setText(cantidad);
        infoPrecio.setText(precio);
        infoTotal.setText("Total: "+String.format("%.02f",total)+"€");

        //Se añaden los regalos en caso de que el total sea superior a unas cantidades determinadas
        if(total>33)
            infoRegalo.setText("Regalos especiales por su compra:\n\nPeluche Android\nVale para comer en el comedor de Cebanc");
        else if(total >20)
            infoRegalo.setText("Regalo especial por su compra:\n\nPeluche Android");
    }
}
