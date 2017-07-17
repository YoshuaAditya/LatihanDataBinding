package com.example.yoshua145150201111044.databinding.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoshua145150201111044.databinding.R;
import com.squareup.picasso.Picasso;

import static com.example.yoshua145150201111044.databinding.view.FragmentContainer.activityContainerBinding;

/**
 * Created by iboy on 10/07/17.
 */

public class InfoWisataActivity extends AppCompatActivity {
    @Override //pada class extends Activity
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_wisata);

        //mendapatkan bundle jika ada dari activity sebelumnya
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        byte[] byteArray = extras.getByteArray("gambar");

        //decode dari bytearray ke bitmap
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        ImageView imageView=(ImageView)findViewById(R.id.idInfoGambar);
//        imageView.setImageBitmap(bmp);
        Picasso.with(this).load(extras.getString("link"))
                .placeholder(R.drawable.icon_image).error(R.drawable.no_inet).into(imageView);

        TextView textView1=(TextView)findViewById(R.id.deskripsiGambar);
        textView1.setText(String.valueOf(extras.getString("deskripsi")));

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_second_toolbar);
        toolbar.setTitle(extras.getString("nama"));
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);
        //menunjukkan arah panah kiri di toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //memberi listener untuk arah panah kiri
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
