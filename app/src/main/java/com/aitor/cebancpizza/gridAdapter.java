package com.aitor.cebancpizza;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adminportatil on 06/02/2017.
 */

public class gridAdapter extends BaseAdapter {

    private ArrayList<InformacionPizza> lpizza;
    private ArrayList<InformacionBebidas> lbebidas;
    private Context context;
    private String[] nombres;
    private boolean tp;

    gridAdapter(Context context,boolean tipo){
        tp = tipo;
        lpizza=new ArrayList<InformacionPizza>();
        lbebidas=new ArrayList<InformacionBebidas>();
        Resources res=context.getResources();
        this.context=context;

        if (tipo){
            nombres=res.getStringArray(R.array.pizzas);
            for (int i=1;i<=nombres.length;i++){
                lpizza.add(new InformacionPizza(nombres[i-1],res.getIdentifier("p"+String.valueOf(i),"drawable",context.getPackageName())));
            }
        }else{
            nombres=res.getStringArray(R.array.bebidas);
            for (int i=1;i<=nombres.length;i++){
                lbebidas.add(new InformacionBebidas(nombres[i-1],res.getIdentifier("b"+String.valueOf(i),"drawable",context.getPackageName())));
            }
        }

    }
    public int getCount() {
        return lpizza.size();
    }

    @Override
    public Object getItem(int i) {
        return lpizza.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row=view;
        viewHolder holder=null;
        if(row==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row =inflater.inflate(R.layout.activity_cebanc_pizza_item,viewGroup,false);
            holder=new viewHolder(row);
            row.setTag(holder);
        }
        else{
            holder=(viewHolder)row.getTag();
        }
        if(tp) {
            InformacionPizza temp = lpizza.get(i);
            holder.nombre.setText(nombres[i]);
            holder.imagen.setImageResource(temp.getImg());
            return row;
        }else{
            InformacionBebidas temp = lbebidas.get(i);
            holder.nombre.setText(nombres[i]);
            holder.imagen.setImageResource(temp.getImg());
            return row;
        }
    }

    class viewHolder{
        TextView nombre;
        ImageView imagen;
        viewHolder(View v){
            nombre=(TextView) v.findViewById(R.id.txtNombre);
            imagen=(ImageView) v.findViewById(R.id.imageView);
        }
    }
}