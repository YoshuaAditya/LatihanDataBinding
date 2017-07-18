package com.example.yos.databinding.viewmodel;

import android.view.View;

import com.example.yos.databinding.view.FragmentContainer;

/**
 * Created by iboy on 06/07/17.
 */

public class HomeViewModel {
    //method untuk button pada home_view
    public void onClickButton(View view){
        FragmentContainer.result.openDrawer();
    }
}
