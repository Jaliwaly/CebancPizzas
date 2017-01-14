package com.aitor.cebancpizza;

import java.io.Serializable;

/**
 * Clase compuesta de dos atributos, un String identificador para ponerle a cada arraylist una
 * especie de ID y un Object obj en el cual guardaremos los diferentes arraylist y su informacion
 */

public class EstructuraArray implements Serializable{
    String identificador;
    Object obj;

    EstructuraArray(String id, Object ob){
        identificador = id;
        obj = ob;
    }
    public String getIdentificador(){
        return identificador;
    }
    public Object getObj(){
        return obj;
    }
}
