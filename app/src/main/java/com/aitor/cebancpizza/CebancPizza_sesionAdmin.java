package com.aitor.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jaliko on 11/02/2017.
 */

public class CebancPizza_sesionAdmin extends AppCompatActivity{
    EditText e1, e2;
    Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_loginadmin);
        e1 = (EditText) findViewById(R.id.useradmin);
        e2 = (EditText) findViewById(R.id.passadmin);
        b1 = (Button) findViewById(R.id.loginbtn);
        b2 = (Button) findViewById(R.id.logoutbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicioSesion();
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void inicioSesion(){
        if(e1.getText().toString().equals("admin") && e2.getText().toString().equals("adminpassguord")){
            Intent i = new Intent(this,CebancPizza_admin.class);
            startActivity(i);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
