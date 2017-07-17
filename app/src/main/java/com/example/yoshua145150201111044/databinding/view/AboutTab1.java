package com.example.yoshua145150201111044.databinding.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoshua145150201111044.databinding.R;

/**
 * Created by iboy on 07/07/17.
 */

public class AboutTab1 extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.about_tab1,container,false);

        return v;
    }
}
