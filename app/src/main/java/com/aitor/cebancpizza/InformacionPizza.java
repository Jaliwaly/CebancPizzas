package com.aitor.cebancpizza;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ingae on 05/01/2017.
 */

public class InformacionPizza implements Parcelable{
    String tipo, masa, tamano;
    int cantidad;
    float total;

    InformacionPizza(){}

    protected InformacionPizza(Parcel in) {
        tipo = in.readString();
        masa = in.readString();
        tamano = in.readString();
        cantidad = in.readInt();
        total = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tipo);
        dest.writeString(masa);
        dest.writeString(tamano);
        dest.writeInt(cantidad);
        dest.writeFloat(total);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InformacionPizza> CREATOR = new Creator<InformacionPizza>() {
        @Override
        public InformacionPizza createFromParcel(Parcel in) {
            return new InformacionPizza(in);
        }

        @Override
        public InformacionPizza[] newArray(int size) {
            return new InformacionPizza[size];
        }
    };

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
