package com.example.yos.databinding.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yos.databinding.R;
import com.example.yos.databinding.databinding.AboutViewBinding;
import com.example.yos.databinding.viewmodel.AboutViewModel;

/**
 * Created by iboy on 07/07/17.
 */

public class AboutView extends Fragment {
    AboutViewBinding aboutViewBinding;

    @Override //pada class extends Activity
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //untuk memberikan opsi pada actionbar dari fragment
        setHasOptionsMenu(true);
        //menampilkan layout
        aboutViewBinding = DataBindingUtil.inflate(inflater, R.layout.about_view, container, false);
        //membuat VM mainPeserta
        AboutViewModel aboutViewModel = new AboutViewModel(this.getActivity().getSupportFragmentManager(),aboutViewBinding);
        //mengeset data binding pada layout card_peserta
        aboutViewBinding.setAboutViewModel(aboutViewModel);

        //set title toolbar
        FragmentContainer.activityContainerBinding.myAwesomeToolbar.setTitle("About");

        //mendapatkan view dari binding
        View view = aboutViewBinding.getRoot();
        return view;
    }
}
