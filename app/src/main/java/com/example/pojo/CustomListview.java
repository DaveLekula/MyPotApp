package com.example.pojo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mypotapp.R;

public class CustomListview extends ArrayAdapter<String> {

    private String [] data;
    private String [] num;
    private int[] image;
    private Activity context;

    public CustomListview(Activity context,String[] data,String[] num,int [] image) {
        super(context, R.layout.row_layout,data);

        this.context = context;
        this.data = data;
        this.num =num;
        this.image = image;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View vi= convertView;
        ViewHolder viewHolder = null;

        if (vi==null){

            LayoutInflater layoutInflater = context.getLayoutInflater();
            vi = layoutInflater.inflate(R.layout.row_layout,null,true);
            viewHolder = new ViewHolder(vi);
            vi.setTag(viewHolder);
        }
        else{

            viewHolder =(ViewHolder) vi.getTag();

        }
       // Bitmap mybitmap = BitmapFactory.decodeFile(image[position]);
        //viewHolder.imageView1.setImageBitmap(mybitmap);
        viewHolder.imageView1.setImageResource(image[position]);
        viewHolder.tvData.setText(data[position]);
        viewHolder.tvDesc.setText(num[position]);

        return vi;
    }

    class ViewHolder
    {
        TextView tvData;
        TextView tvDesc;
        ImageView imageView1;

        ViewHolder(View view){
            tvData =(TextView) view.findViewById(R.id.rowItemTV);
            tvDesc =(TextView) view.findViewById(R.id.rowItemDescTv);
            imageView1=(ImageView) view.findViewById(R.id.rowItemImage);
        }

    }
}
