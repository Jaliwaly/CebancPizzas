package com.aitor.cebancpizza;

import android.os.Parcelable;

/**
 * Created by ingae on 05/01/2017.
 */

public abstract class InformacionPizza implements Parcelable {
    String tipo, masa, tamano;
    int cantidad;
    float total;

    public void setTipo(String tipo){this.tipo=tipo;}
    public void setMasa(String masa){this.masa=masa;}
    public void setTamano(String tamano){this.tamano=tamano;}
    public void setCantidad(int cantidad){this.cantidad=cantidad;}
    public void setTotal(float total){this.total=total;}
    public String getTipo(){return tipo;}
    public String getMasa(){return masa;}
    public String getTamano(){return tamano;}
    public int getCantidad(){return cantidad;}
    public float getTotal(){return total;}

}
