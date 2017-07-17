package com.example.yoshua145150201111044.databinding.view;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoshua145150201111044.databinding.R;
import com.example.yoshua145150201111044.databinding.databinding.WisataViewBinding;
import com.example.yoshua145150201111044.databinding.viewmodel.FragmentContainerViewModel;
import com.example.yoshua145150201111044.databinding.viewmodel.WisataViewModel;

/**
 * Created by iboy on 06/07/17.
 */

public class WisataView extends Fragment {

    private WisataViewBinding wisataViewBinding;
    Menu optionsMenu;

    @Override //pada class extends Activity
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //untuk memberikan opsi pada actionbar dari fragment
        setHasOptionsMenu(true);

        //menampilkan layout
        wisataViewBinding = DataBindingUtil.inflate(inflater, R.layout.wisata_view, container, false);
        //membuat VM mainPeserta
        WisataViewModel wisataViewModel = new WisataViewModel(this.getActivity(),wisataViewBinding,R.string.link_file_json);
        //mengeset data binding pada layout card_peserta
        wisataViewBinding.setWisataViewModel(wisataViewModel);


        //set title toolbar
        FragmentContainer.activityContainerBinding.myAwesomeToolbar.setTitle("Wisata");


        //mendapatkan view dari binding
        View view = wisataViewBinding.getRoot();
        return view;
    }
    @Override //untuk menambahkan opsi pada action bar jika ada
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.pilihan, menu);
        //memberi tanda checked pada lingkaran
        menu.getItem(0).setChecked(true);
        optionsMenu=menu;
    }

    @Override //memberi event handler saat opsi dipilih
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.semua == item.getItemId()) {
            optionsMenu.getItem(0).setChecked(true);
            //membuat VM mainPeserta
            WisataViewModel wisataViewModel = new WisataViewModel(this.getActivity(),wisataViewBinding,R.string.link_file_json);
            //mengeset data binding pada layout card_peserta
            wisataViewBinding.setWisataViewModel(wisataViewModel);
            return true;
        } else if (R.id.malang == item.getItemId()) {
            optionsMenu.getItem(1).setChecked(true);
            //membuat VM mainPeserta
            WisataViewModel wisataViewModel = new WisataViewModel(this.getActivity(),wisataViewBinding,R.string.link_file_json_malang);
            //mengeset data binding pada layout card_peserta
            wisataViewBinding.setWisataViewModel(wisataViewModel);
            return true;
        }else if (R.id.batu == item.getItemId()) {
            optionsMenu.getItem(2).setChecked(true);
            //membuat VM mainPeserta
            WisataViewModel wisataViewModel = new WisataViewModel(this.getActivity(),wisataViewBinding,R.string.link_file_json_batu);
            //mengeset data binding pada layout card_peserta
            wisataViewBinding.setWisataViewModel(wisataViewModel);
            return true;
        }else if (R.id.kabupaten == item.getItemId()) {
            optionsMenu.getItem(3).setChecked(true);
            //membuat VM mainPeserta
            WisataViewModel wisataViewModel = new WisataViewModel(this.getActivity(),wisataViewBinding,R.string.link_file_json_kabupaten);
            //mengeset data binding pada layout card_peserta
            wisataViewBinding.setWisataViewModel(wisataViewModel);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
