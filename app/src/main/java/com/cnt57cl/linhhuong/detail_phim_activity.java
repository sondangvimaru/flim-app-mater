package com.cnt57cl.linhhuong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mostafaaryan.transitionalimageview.TransitionalImageView;
import com.mostafaaryan.transitionalimageview.model.TransitionalImage;
import com.squareup.picasso.Picasso;

public class detail_phim_activity extends AppCompatActivity {

   ImageView img_phim;
    TextView tv_tenphim,tv_daodien,tv_thoiluong,tv_mota,tv_ngayphathanh;
    Button btn_xemphim;
    phim p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phim_activity);
        init();
    }

    public  void init()
    {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f1c40f")));
        img_phim= findViewById(R.id.img_view);
        tv_tenphim=findViewById(R.id.tv_tenphim);
        tv_daodien=findViewById(R.id.tv_daodien);
        tv_thoiluong=findViewById(R.id.tv_thoiluong);
        tv_mota=findViewById(R.id.tv_mota);
        tv_ngayphathanh=findViewById(R.id.tv_ngayphathanh);
        btn_xemphim=findViewById(R.id.btn_xemphim);
        Bundle bd= getIntent().getExtras();

        if(bd!=null)
        {

            p = (phim) bd.getSerializable("data_click");
            getSupportActionBar().setTitle(p.getName());

            Picasso.get().load(p.getBanner()).error(R.drawable.ic_launcher_background).into(img_phim);
            tv_tenphim.setText(p.getName());
            tv_daodien.setText("Đạo diễn: "+p.getDaodien());
            tv_thoiluong.setText("Thời lượng: "+p.getThoiluong()+" Phút");
            tv_ngayphathanh.setText("Ngày phát hành: "+p.getNgayphathanh());
            tv_mota.setText("Mô tả:\n"+p.getMota());



        }
        btn_xemphim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(detail_phim_activity.this,xemphim.class);
                    intent.putExtra("linkphim",p.getLinkphim());
                    startActivity(intent);

            }
        });

    }

}
