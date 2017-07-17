package com.example.yoshua145150201111044.databinding.viewmodel;

import android.databinding.BindingMethod;
import android.view.View;

import com.example.yoshua145150201111044.databinding.view.FragmentContainer;
import com.mikepenz.materialdrawer.Drawer;

/**
 * Created by iboy on 06/07/17.
 */

public class HomeViewModel {
    //method untuk button pada home_view
    public void onClickButton(View view){
        FragmentContainer.result.openDrawer();
    }
}
