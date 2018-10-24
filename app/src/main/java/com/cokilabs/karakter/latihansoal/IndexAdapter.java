package com.cokilabs.karakter.latihansoal;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IndexAdapter extends BaseAdapter {
    private Context context;
    private String[] list;
    private ArrayList<Integer> terjawabs;

    public IndexAdapter(Context context, String[] list, ArrayList<Integer> terjawabs) {
        this.context = context;
        this.list = list;
        this.terjawabs = terjawabs;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1;
        if(view == null){
            view1 = inflater.inflate(R.layout.nomor_item, null);
        }else{
            view1 =  view;
        }

        TextView tv = view1.findViewById(R.id.nomor);
        tv.setText(list[i]);
        if(terjawabs.contains(i)){
            tv.setBackground(context.getResources().getDrawable(R.drawable.bg_rounded_orange));;
        }

        return view1;
    }
}
