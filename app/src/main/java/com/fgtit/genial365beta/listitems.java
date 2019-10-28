package com.fgtit.genial365beta;


import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by jaiso on 13-02-2018.
 */

public class listitems extends ArrayAdapter<String>{

    private String[] name;
    private String[] timein;
    private String[] timeout;
    private Activity context;
    Bitmap bitmap;

    public listitems(Activity context,String[] name,String[] timein,String[] timeout) {
        super(context, R.layout.listitem,name);
        this.context=context;
        this.name=name;
        this.timein=timein;
        this.timeout=timeout;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listitem,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();

        }

        viewHolder.tvw1.setText(name[position]);
        viewHolder.tvw2.setText(timein[position]);
       viewHolder.tvw3.setText(timeout[position]);

        return r;
    }

    class ViewHolder{

        TextView tvw1;
        TextView tvw2;
        TextView tvw3;

        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.name);
            tvw2=(TextView)v.findViewById(R.id.time_in);
            tvw3=(TextView)v.findViewById(R.id.time_out);
        }

    }




}

