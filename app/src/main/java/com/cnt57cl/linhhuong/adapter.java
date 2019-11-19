package com.cnt57cl.linhhuong;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<phim> dsphim;

    adapter(Context context,ArrayList<phim> dsphim)
    {
        this.context=context;
        this.dsphim=dsphim;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return dsphim.size();
    }

    @Override
    public Object getItem(int position) {
        return dsphim.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(convertView==null)
           convertView= inflater.inflate(R.layout.row,null,false);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"huongcute.ttf");
        Typeface cutehuong=Typeface.createFromAsset(context.getAssets(),"cutehuong.ttf");
        Animation animation= AnimationUtils.loadAnimation(context,R.anim.huong_animation);
        TextView tv_ten= convertView.findViewById(R.id.tv_name);
        TextView tv_daodien= convertView.findViewById(R.id.tv_daodien);
        ImageView img= convertView.findViewById(R.id.img_avatar);
        tv_ten.setTypeface(typeface);
        tv_daodien.setTypeface(cutehuong);
        tv_ten.setText(dsphim.get(position).getName());
        tv_daodien.setText(dsphim.get(position).getDaodien());
        Picasso.get().load(dsphim.get(position).getBanner()).error(R.drawable.ic_launcher_background).into(img);
        convertView.startAnimation(animation);
        return convertView;
    }
}
