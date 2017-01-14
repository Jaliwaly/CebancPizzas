package com.aitor.cebancpizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CebancPizza_Cliente extends AppCompatActivity {
    EditText nom = null;
    EditText dir = null;
    EditText tlf = null;
    Button sig = null;
    Button salir = null;
    InformacionCliente inf;
    ArrayList<EstructuraArray> datos = new ArrayList<EstructuraArray>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza__cliente);
        nom = (EditText) findViewById(R.id.nombre);
        dir = (EditText) findViewById(R.id.direccion);
        tlf = (EditText) findViewById(R.id.tlf);
        sig = (Button) findViewById(R.id.siguientePantalla1);
        salir = (Button) findViewById(R.id.salirPantalla1);

        //Botón para ir a la siguiente actividad
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidarDatos() == true){
                    siguienteP1();
                }else{
                    Toast ts = Toast.makeText(getApplicationContext(),"Introduce todos los campos",Toast.LENGTH_LONG);
                    ts.show();
                }
            }
        });

        //Botón para salir de la aplicación
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Función que guarda la información del cliente en un array trás meterlo en un objeto de la clase EstructuraArray y lo pasa a la siguiente actividad
    public void siguienteP1(){
        inf = new InformacionCliente();
        inf.setNombre(nom.getText().toString());
        inf.setDireccion(dir.getText().toString());
        inf.setTelefono(Integer.parseInt(tlf.getText().toString()));
        EstructuraArray cliente = new EstructuraArray("Datos",inf);
        datos.add(cliente);

        Intent i = new Intent(this,CebancPizza_Carta.class);
        i.putExtra("datos",datos);
        startActivity(i);
        finish();
    }

    //Valida que los datos están llenos y si el teléfono tiene 9 dígitos
    private boolean ValidarDatos(){
        if (nom.getText().toString().equals("") || dir.getText().toString().equals("") || tlf.getText().toString().equals("")||tlf.getText().length()!=9){
            return false;
        }else{
            return true;
        }
    }
}
