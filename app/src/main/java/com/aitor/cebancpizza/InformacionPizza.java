package com.aitor.cebancpizza;

import java.io.Serializable;

/**
 * Created by ingae on 05/01/2017.
 *
 * Esta clase guarda la información de las pizzas para que sean guardadas en los arrays del pedido
 * Tiene getters y setters para guardar y recoger la información
 */

public class InformacionPizza implements Serializable{
    private String tipo, masa, tamano;
    private int cantidad;
    private float total;

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