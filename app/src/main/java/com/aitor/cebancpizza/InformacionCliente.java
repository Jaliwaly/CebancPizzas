package com.aitor.cebancpizza;

import java.io.Serializable;

/**
 *
 * Esta clase guarda la información del cliente para posteriormente añadirla a un array-list con todos los datos.
 * La clase esta compuesta por setters y getters para guardar y recoger la información
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
