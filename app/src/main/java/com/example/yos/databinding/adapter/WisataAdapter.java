package com.example.yos.databinding.adapter;

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

import com.example.yos.databinding.R;
import com.example.yos.databinding.databinding.WisataDeskripsiBinding;
import com.example.yos.databinding.model.WisataModel;
import com.example.yos.databinding.view.FragmentContainer;
import com.example.yos.databinding.view.InfoWisataActivity;
import com.example.yos.databinding.viewmodel.DataWisataViewModel;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**Lanjutkan retrofit parsing!
 * Created by iboy on 06/07/17.
 */

public class WisataAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<WisataModel> wisataModels;

    public WisataAdapter(Context context, ArrayList<WisataModel> wisataModels) {
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

    @Override//method yang perlu diimplementasikan saat extend BaseAdapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        //membuat inflater untuk memanggil layout
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        //membuat VM cardLayout
        DataWisataViewModel dataWisataViewModel=new DataWisataViewModel(wisataModels.get(position));
        //inflate layout dari card ke CardLayoutBinding
        WisataDeskripsiBinding wisataDeskripsiBinding= DataBindingUtil.inflate(inflater, R.layout.wisata_deskripsi,parent,false);
        //mengeset VM pada layout
        wisataDeskripsiBinding.setDataWisataVM(dataWisataViewModel);
        //hasil akhir cardView diambil dari binding lalu direturn
        final CardView cardView=wisataDeskripsiBinding.cardView;


        //mengeset gambar pada imageview dengan picasso
        final ImageView imgView=wisataDeskripsiBinding.idGambar;
        //with parent activity, load http string, placeholder sblm gambar berhasil diambil, error saat load gagal, ditaruh ke imageview imgview
        Picasso.with(mContext).load(wisataModels.get(position).gambar)
                .fit().centerCrop() // untuk meresize secara otomatis33
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
                Intent intent=new Intent(FragmentContainer.activityContainerBinding.fragmentContainer.getContext(),InfoWisataActivity.class);
                //buat bundle
                Bundle extras=new Bundle();
                extras.putString("link",wisataModels.get(position).gambar);
                extras.putString("nama",wisataModels.get(position).nama);
                extras.putString("deskripsi",wisataModels.get(position).deskripsi);
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
