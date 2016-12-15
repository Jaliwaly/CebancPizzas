package com.aitor.cebancpizza;

import java.io.Serializable;

/**
 * Created by adminportatil on 09/12/2016.
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
