package com.example.yoshua145150201111044.databinding.view;

import android.support.v4.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoshua145150201111044.databinding.R;
import com.example.yoshua145150201111044.databinding.databinding.CardLayoutBinding;
import com.example.yoshua145150201111044.databinding.databinding.CardPesertaBinding;
import com.example.yoshua145150201111044.databinding.viewmodel.CardPesertaViewModel;
import com.example.yoshua145150201111044.databinding.viewmodel.FragmentContainerViewModel;

/**
 * Created by iboy on 04/07/17.
 */

public class CardPeserta extends Fragment{
    //layout pada card_peserta yang diberikan data binding
    private CardPesertaBinding cardPesertaBinding;

    public static final int CARD_VIEW=0;
    public static final int SWIPE_VIEW=1;


    @Override //pada class extends Activity
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //untuk memberikan opsi pada actionbar dari fragment
        setHasOptionsMenu(true);
        //menampilkan layout
        cardPesertaBinding= DataBindingUtil.inflate(inflater, R.layout.card_peserta,container,false);
        //membuat VM mainPeserta
        CardPesertaViewModel cardPesertaViewModel =new CardPesertaViewModel(this.getActivity(),cardPesertaBinding);
        //mengeset data binding pada layout card_peserta
        cardPesertaBinding.setPesertaViewModel(cardPesertaViewModel);

        //set title toolbar
        FragmentContainer.activityContainerBinding.myAwesomeToolbar.setTitle("Gallery");

        //mendapatkan view dari binding
        View view = cardPesertaBinding.getRoot();
        return view;
    }

    @Override //untuk menambahkan opsi pada action bar jika ada
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main, menu);
        //memberi tanda checked pada lingkaran
        menu.getItem(0).setChecked(true);
    }

    @Override //memberi event handler saat opsi dipilih
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.card_view == item.getItemId()) {
            //membuat VM fragmentContainer
            FragmentContainerViewModel fragmentContainerViewModel=new FragmentContainerViewModel((FragmentContainer)getActivity(), FragmentContainer.activityContainerBinding,CARD_VIEW);
            //mengeset data binding pada layout card_peserta
            FragmentContainer.activityContainerBinding.setFragmentContainerViewModel(fragmentContainerViewModel);
            return true;
        } else if (R.id.swipe_view == item.getItemId()) {
            //membuat VM fragmentContainer
            FragmentContainerViewModel fragmentContainerViewModel=new FragmentContainerViewModel((FragmentContainer)getActivity(), FragmentContainer.activityContainerBinding,SWIPE_VIEW);
            //mengeset data binding pada layout card_peserta
            FragmentContainer.activityContainerBinding.setFragmentContainerViewModel(fragmentContainerViewModel);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
