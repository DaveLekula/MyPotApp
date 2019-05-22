package com.example.pojo;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mypotapp.R;

public class CustomListview extends ArrayAdapter<String> {

    private String [] data;
    private String [] num;
    private int [] image;
    private Activity context;

    public CustomListview(Activity context, String [] data,String [] num,int [] image) {
        super(context, R.layout.row_layout,data);

        this.context = context;
        this.data =data;
        this.num = num;
        this.image = image;


    }

    @androidx.annotation.NonNull
    @Override
    public View getView(int position, @androidx.annotation.Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
