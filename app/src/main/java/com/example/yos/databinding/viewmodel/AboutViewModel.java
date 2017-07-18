package com.example.yos.databinding.viewmodel;

import android.animation.ValueAnimator;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;

import com.example.yos.databinding.R;
import com.example.yos.databinding.adapter.AboutAdapter;
import com.example.yos.databinding.adapter.ReaderViewPagerTransformer;
import com.example.yos.databinding.databinding.AboutViewBinding;

/**
 * Created by iboy on 07/07/17.
 */

public class AboutViewModel {
    CharSequence Titles[]={"Tentang","Versi","Credits","Lainnya"};
    int Numboftabs =4;
    private int colors[]={R.color.accent, R.color.material_drawer_accent, R.color.material_drawer_dark_background};
    private AboutViewBinding aboutViewBinding;
    private ValueAnimator mColorAnimation;

    public  AboutViewModel(FragmentManager fragmentManager,AboutViewBinding aboutViewBindin){

        aboutViewBinding=aboutViewBindin;

        AboutAdapter aboutAdapter=new AboutAdapter(fragmentManager,Titles,Numboftabs);
        aboutViewBinding.viewPager2.setAdapter(aboutAdapter);
        //mengeset title pada tab layout
        for (int i=0;i<Numboftabs;i++) {
            TabLayout.Tab tab = aboutViewBinding.tabLayout.newTab();
            tab.setText(Titles[i]);
            aboutViewBinding.tabLayout.addTab(tab);
        }
        //menghubungakan tablayout dengan viewpager supaya saat geset ikut geser
        aboutViewBinding.tabLayout.setupWithViewPager(aboutViewBinding.viewPager2);
        //scrollable buat tabnya kescroll
        aboutViewBinding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        aboutViewBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //setting cara animasi swipe
        aboutViewBinding.viewPager2
                .setPageTransformer(true, new ReaderViewPagerTransformer(ReaderViewPagerTransformer.TransformType.ZOOM));

    }

}
