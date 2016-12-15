package com.aitor.cebancpizza;

import java.io.Serializable;

/**
 * Created by adminportatil on 09/12/2016.
 */

public class InformacionCliente implements Serializable{
    String nombre, direccion;
    int telefono;


    public void setNombre(String n){
        nombre = n;
    }
    public void setDireccion(String dir){
        direccion = dir;
    }
    public void setTelefono(int tlf){
        telefono = tlf;
    }
    public String getNombre(){
        return nombre;
    }
    public String getDireccion(){
        return direccion;
    }
    public int getTelefono(){
        return telefono;
    }

}
