package com.aitor.cebancpizza;

import java.io.Serializable;

/**
 * Created by ingae on 06/01/2017.
 */

public class InformacionBebidas implements Serializable{
    private String tipo;
    private int cantidad;
    private double total;

    public void setTipo(String tipo){this.tipo=tipo;}
    public void setCantidad(int cantidad){this.cantidad=cantidad;}
    public void setTotal(double precio){this.total=precio;}
    public String getTipo(){return tipo;}
    public int getCantidad(){return cantidad;}
    public double getTotal(){return total;}
}
