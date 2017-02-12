package com.aitor.cebancpizza;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Clase que refleja el pedido y da la opción de realizar el pedido o salir de la aplicación
 */

public class CebancPizza_pedido extends AppCompatActivity {
    private Bundle extras;
    private int cabecera;
    private AlertDialog.Builder confirmacion;
    private TextView infoCliente,infoFactura,infoPrecio,infoTotal,infoRegalo,infoCantidad;
    private Button aceptar,salir;
    private String factura,precio,cantidad;
    private double total=0;
    private CebancPizza_BD db;
    private SQLiteDatabase sql;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_pedido);
        confirmacion = new AlertDialog.Builder(this);
        extras = getIntent().getExtras();
        cabecera=extras.getInt("pedido");
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

        db = new CebancPizza_BD(this,"CebancPizza",null,1);
        sql = db.getReadableDatabase();

        c=sql.rawQuery("SELECT NOMBRE, TELEFONO, DIRECCION FROM CLIENTES, CABECERAS WHERE CLIENTES.IDCLIENTE=CABECERAS.IDCLIENTE AND IDCABECERA = "+cabecera,null);
        c.moveToFirst();

        infoCliente.setText("Información del cliente:\n\nNombre: "+c.getString(0)+"\nDirección: "+c.getInt(2)+"\nTeléfono: "+c.getString(1)+"\n\n");
        //En estos tres Text View se añaden los títulos de cada apartado
        factura="Artículos pedidos\n\n";
        cantidad="Cant.\n\n";
        precio="Precio\n\n";

        c=sql.rawQuery("SELECT NOMBRE, MASA.DESCRIPCION, TAMANO.DESCRIPCION, CANTIDAD, PR_VENT, MASA.PRECIO, TAMANO.PRECIO FROM PIZZA_PEDIDA, MASA, TAMANO, LINEAS, ARTICULOS WHERE ARTICULOS.IDARTICULO=LINEAS.IDARTICULO AND LINEAS.IDLINEA=PIZZA_PEDIDA.LINEA AND PIZZA_PEDIDA.MASA=MASA.IDMASA AND PIZZA_PEDIDA.TAMANO = TAMANO.IDTAMANO AND TIPO = 'PIZZA' AND IDCABECERA = "+cabecera,null);

        while (c.moveToNext()){
            factura+=c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+"\n";
            cantidad+=c.getInt(3)+"\n";
            precio+=String.format("%.02f", c.getInt(3)*(c.getFloat(4)+c.getInt(5)+c.getInt(6)))+"€\n";
            total+=c.getInt(3)*(c.getFloat(4)+c.getInt(5)+c.getInt(6));
        }

        c=sql.rawQuery("SELECT NOMBRE, CANTIDAD, PR_VENT FROM LINEAS, ARTICULOS WHERE ARTICULOS.IDARTICULO=LINEAS.IDARTICULO AND TIPO = 'BEBIDA' AND IDCABECERA = "+cabecera,null);

        while (c.moveToNext()){
            factura +=c.getString(0)+"\n";
            cantidad+=c.getInt(1)+"\n";
            precio+=String.format("%.02f", c.getFloat(2)*c.getInt(1))+"€\n";
            total+=c.getFloat(2)*c.getInt(1);
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
