package com.example.yoshua145150201111044.databinding.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoshua145150201111044.databinding.R;
import com.example.yoshua145150201111044.databinding.databinding.HomeViewBinding;
import com.example.yoshua145150201111044.databinding.viewmodel.HomeViewModel;

/**
 * Created by iboy on 06/07/17.
 */

public class HomeView extends Fragment {
    HomeViewBinding homeViewBinding;

    @Override //pada class extends Activity
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //untuk memberikan opsi pada actionbar dari fragment
        setHasOptionsMenu(true);
        //menampilkan layout
        homeViewBinding = DataBindingUtil.inflate(inflater, R.layout.home_view, container, false);
        //membuat VM mainPeserta
        HomeViewModel homeViewModel = new HomeViewModel();
        //mengeset data binding pada layout card_peserta
        homeViewBinding.setHomeViewModel(homeViewModel);

        //set title toolbar
        FragmentContainer.activityContainerBinding.myAwesomeToolbar.setTitle("Home");


        //mendapatkan view dari binding
        View view = homeViewBinding.getRoot();
        return view;
    }
}