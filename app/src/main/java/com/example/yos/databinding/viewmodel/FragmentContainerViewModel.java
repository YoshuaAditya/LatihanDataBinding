package com.example.yos.databinding.viewmodel;

import com.example.yos.databinding.databinding.ActivityContainerBinding;
import com.example.yos.databinding.view.AboutView;
import com.example.yos.databinding.view.CardPeserta;
import com.example.yos.databinding.view.FragmentContainer;
import com.example.yos.databinding.view.HomeView;
import com.example.yos.databinding.view.MapsActivity;
import com.example.yos.databinding.view.SwipePeserta;
import com.example.yos.databinding.view.WisataView;

/**
 * Created by iboy on 05/07/17.
 */

public class FragmentContainerViewModel {

    public FragmentContainerViewModel(FragmentContainer fragmentContainer, ActivityContainerBinding activityContainerBinding,int codeView) {
        if (codeView == FragmentContainer.CARD_VIEW) {
            //Fragment cardPeserta
            CardPeserta cardPeserta = new CardPeserta();
            //Mendapatkan objek Fragment Transaction
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    fragmentContainer.getSupportFragmentManager().beginTransaction();
            //mengganti layout id fragmentContainer dengan fragment
            fragmentTransaction.replace(activityContainerBinding.fragmentContainer.getId(), cardPeserta);
            //simpan perubahan
            fragmentTransaction.commit();
        }
        else if (codeView == FragmentContainer.SWIPE_VIEW) {
            //Fragment swipePeserta
            SwipePeserta swipePeserta = new SwipePeserta();
            //Mendapatkan objek Fragment Transaction
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    fragmentContainer.getSupportFragmentManager().beginTransaction();
            //mengganti layout id fragmentContainer dengan fragment
            fragmentTransaction.replace(activityContainerBinding.fragmentContainer.getId(), swipePeserta);
            //simpan perubahan
            fragmentTransaction.commit();
        }
        else if (codeView == FragmentContainer.HOME_VIEW) {
            //Fragment swipePeserta
            HomeView homeView= new HomeView();
            //Mendapatkan objek Fragment Transaction
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    fragmentContainer.getSupportFragmentManager().beginTransaction();
            //mengganti layout id fragmentContainer dengan fragment
            fragmentTransaction.replace(activityContainerBinding.fragmentContainer.getId(), homeView);
            //simpan perubahan
            fragmentTransaction.commit();
        }
        else if (codeView == FragmentContainer.WISATA_VIEW) {
            //Fragment swipePeserta
            WisataView wisataView= new WisataView();
            //Mendapatkan objek Fragment Transaction
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    fragmentContainer.getSupportFragmentManager().beginTransaction();
            //mengganti layout id fragmentContainer dengan fragment
            fragmentTransaction.replace(activityContainerBinding.fragmentContainer.getId(), wisataView);
            //simpan perubahan
            fragmentTransaction.commit();
        }
        else if (codeView == FragmentContainer.MAP_VIEW) {
            //Fragment swipePeserta
            MapsActivity mapsActivity= new MapsActivity();
            //Mendapatkan objek Fragment Transaction
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    fragmentContainer.getSupportFragmentManager().beginTransaction();
            //mengganti layout id fragmentContainer dengan fragment
            fragmentTransaction.replace(activityContainerBinding.fragmentContainer.getId(), mapsActivity);
            //simpan perubahan
            fragmentTransaction.commit();
        }
        else if (codeView == FragmentContainer.ABOUT_VIEW) {
            //Fragment swipePeserta
            AboutView aboutView= new AboutView();
            //Mendapatkan objek Fragment Transaction
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    fragmentContainer.getSupportFragmentManager().beginTransaction();
            //mengganti layout id fragmentContainer dengan fragment
            fragmentTransaction.replace(activityContainerBinding.fragmentContainer.getId(), aboutView);
            //simpan perubahan
            fragmentTransaction.commit();
        }
    }
}
