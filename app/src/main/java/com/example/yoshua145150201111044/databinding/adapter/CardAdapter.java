package com.example.yoshua145150201111044.databinding.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.yoshua145150201111044.databinding.R;
import com.example.yoshua145150201111044.databinding.databinding.CardLayoutBinding;
import com.example.yoshua145150201111044.databinding.model.PesertaModel;
import com.example.yoshua145150201111044.databinding.model.WisataModel;
import com.example.yoshua145150201111044.databinding.view.FragmentContainer;
import com.example.yoshua145150201111044.databinding.view.InfoGalleryActivity;
import com.example.yoshua145150201111044.databinding.viewmodel.CardLayoutViewModel;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by iboy on 04/07/17.
 */
//supaya class dianggap adapter extends BaseAdapter
public class CardAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<WisataModel> wisataModels;

    public CardAdapter(Context context, ArrayList<WisataModel> wisataModels) {
        mContext = context;
        this.wisataModels = wisataModels;
    }

    @Override//method yang perlu diimplementasikan saat extend BaseAdapter
    public int getCount() {
        return wisataModels.size();
    }

    @Override//method yang perlu diimplementasikan saat extend BaseAdapter
    public Object getItem(int position) {
        return null;
    }

    @Override//method yang perlu diimplementasikan saat extend BaseAdapter
    public long getItemId(int position) {
        return 0;
    }

    //getView dipanggil untuk setiap card
    @Override//method yang perlu diimplementasikan saat extend BaseAdapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        //membuat inflater untuk memanggil layout
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        //membuat VM cardLayout
        CardLayoutViewModel cardLayoutViewModel=new CardLayoutViewModel(wisataModels.get(position));
        //inflate layout dari card ke CardLayoutBinding
        CardLayoutBinding cardLayoutBinding= DataBindingUtil.inflate(inflater,R.layout.card_layout,parent,false);
        //mengeset VM pada layout
        cardLayoutBinding.setCardLayoutVM(cardLayoutViewModel);
        //hasil akhir cardView diambil dari binding lalu direturn
        final CardView cardView=cardLayoutBinding.cardView;


        //mengeset gambar pada imageview
        final ImageView imgView=cardLayoutBinding.idGambar;
        //with parent activity, load http string, placeholder sblm gambar berhasil diambil, error saat load gagal, ditaruh ke imageview imgview
        Picasso.with(mContext).load(wisataModels.get(position).thumbnail)
                .placeholder(R.drawable.icon_image).error(R.drawable.no_inet).into(imgView);

        //untuk mengambil gambar dari imgview
        imgView.setDrawingCacheEnabled(true);

        // layout imgview ukuran untuk build cache
        imgView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        imgView.layout(0, 0, imgView.getMeasuredWidth(), imgView.getMeasuredHeight());
        //dijadikan bitmap

        //memberi listener click pada setiap card
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //build cache
                imgView.buildDrawingCache();
                //bitmap untuk menyimpan dari cache
                final Bitmap bitmap=imgView.getDrawingCache();
                //mengubah bitmap jadi bytearray
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                final byte[] byteArray = stream.toByteArray();

                //buat intent untuk buka activity baru
                Intent intent=new Intent(FragmentContainer.activityContainerBinding.fragmentContainer.getContext(),InfoGalleryActivity.class);
                //buat bundle
                Bundle extras=new Bundle();
                extras.putString("link",wisataModels.get(position).gambar);
                extras.putString("nama",wisataModels.get(position).nama);
                extras.putByteArray("gambar",byteArray);
                //bundle ditaruh ke intent
                intent.putExtras(extras);
                //start intent
                FragmentContainer.activityContainerBinding.fragmentContainer.getContext().startActivity(intent);

            }
        });
        return cardView;
    }

    //diperlukan untuk mengeset image dengan data binding
    @BindingAdapter("imageResource") //nama method binding adapter yang akan dipanggil di xml
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}
