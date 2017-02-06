package com.aitor.cebancpizza;

import java.io.Serializable;

/**
 * Created by ingae on 06/01/2017.
 *
 * Esta clase guarda la información de las bebidas para que sean guardadas en los arrays del pedido
 * Tiene getters y setters para guardar y recoger la información
 */

public class InformacionBebidas implements Serializable{
    private String tipo;
    private int cantidad, imagen;
    private float total;

    InformacionBebidas(String nombre, int imagen){
        this.tipo = nombre;
        this.imagen = imagen;
    }

    public void setTipo(String tipo){this.tipo=tipo;}
    public void setCantidad(int cantidad){this.cantidad=cantidad;}
    public void setTotal(float precio){this.total=precio;}
    public void setImg(){this.imagen=imagen;}
    public String getTipo(){return tipo;}
    public int getCantidad(){return cantidad;}
    public float getTotal(){return total;}
    public int getImg(){return imagen;}
}
