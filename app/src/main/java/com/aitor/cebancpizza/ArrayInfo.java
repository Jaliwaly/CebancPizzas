package com.aitor.cebancpizza;

/**
 * Created by adminportatil on 09/12/2016.
 */

public class ArrayInfo{
    String identificador;
    Object obj;

    ArrayInfo(String ident, Object o){
        identificador = ident;
        obj = o;
    }
    public String getIdentificador(){
        return identificador;
    }
}
