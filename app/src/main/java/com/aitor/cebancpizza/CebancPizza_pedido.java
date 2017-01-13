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
 * Created by ingae on 06/01/2017.
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

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

        infoCliente.setText("Información del cliente:\n\nNombre: "+cliente.getNombre()+"\nDirección: "+cliente.getDireccion()+"\nTeléfono: "+cliente.getTelefono()+"\n\n");
        factura="Artículos pedidos\n\n";
        cantidad="Cant.\n\n";
        precio="Precio\n\n";
        for(int cont=0;cont<pizzas.size();cont++){
            factura+=pizzas.get(cont).getTipo()+" "+pizzas.get(cont).getTamano()+" "+pizzas.get(cont).getMasa()+"\n";
            cantidad+=pizzas.get(cont).getCantidad()+"\n";
            precio+=pizzas.get(cont).getTotal()+"€\n";
            total+=pizzas.get(cont).getTotal();
        }
        for(int cont=0;cont<bebidas.size();cont++){
            factura +=bebidas.get(cont).getTipo()+"\n";
            cantidad+=bebidas.get(cont).getCantidad()+"\n";
            precio+=bebidas.get(cont).getTotal()+"€\n";
            total+=bebidas.get(cont).getTotal();
        }
        infoFactura.setText(factura);
        infoCantidad.setText(cantidad);
        infoPrecio.setText(precio);
        infoTotal.setText("Total: "+String.valueOf(total)+"€");
        if(total>33)
            infoRegalo.setText("Regalos especiales por su compra:\n\nPeluche Android\nVale para comer en el comedor de Cebanc");
        else if(total >20)
            infoRegalo.setText("Regalo especial por su compra:\n\nPeluche Android");
    }
    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
}
