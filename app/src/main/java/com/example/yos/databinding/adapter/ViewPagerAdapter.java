package com.example.yos.databinding.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yos.databinding.R;
import com.example.yos.databinding.databinding.CardLayoutBinding;
import com.example.yos.databinding.model.WisataModel;
import com.example.yos.databinding.viewmodel.CardLayoutViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by iboy on 05/07/17.
 */
//Untuk viewPager tipe view saja, kalau fragment menggunakan FragmentStatePagerAdapter
public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<WisataModel> wisataModels;

    public ViewPagerAdapter(Context context, ArrayList<WisataModel> wisataModels) {
        mContext = context;
        this.wisataModels = wisataModels;
    }

    //proses menambahkan view pada viewPager
    @Override
    public Object instantiateItem(ViewGroup collection,final int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //membuat VM cardLayout
        CardLayoutViewModel cardLayoutViewModel=new CardLayoutViewModel(wisataModels.get(position));
        //inflate layout dari card ke CardLayoutBinding
        CardLayoutBinding cardLayoutBinding= DataBindingUtil.inflate(inflater, R.layout.card_layout,collection,false);
        //mengeset VM pada layout
        cardLayoutBinding.setCardLayoutVM(cardLayoutViewModel);
        //untuk menambahkan view pada viewPager satu per satu
        collection.addView(cardLayoutBinding.cardView);

        final ImageView imgView=cardLayoutBinding.idGambar;
        //with parent activity, load http string, placeholder sblm gambar berhasil diambil, error saat load gagal, ditaruh ke imageview imgview
        Picasso.with(mContext).load(wisataModels.get(position).thumbnail)
                .fit().centerCrop() // untuk meresize secara otomatis
                .placeholder(R.drawable.icon_image).error(R.drawable.no_inet).into(imgView);


        //memberi listener click pada setiap card
        cardLayoutBinding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, wisataModels.get(position).nama,Toast.LENGTH_SHORT).show();
            }
        });

        return cardLayoutBinding.cardView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override //wajib dioverride
    public int getCount() {
        return wisataModels.size();
    }

    @Override//wajib dioverride
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return wisataModels.get(position).nama;
    }

    @BindingAdapter("imageResource") //nama method binding adapter yang akan dipanggil di xml
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}
