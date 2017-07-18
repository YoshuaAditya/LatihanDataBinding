package com.example.yos.databinding.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.yos.databinding.R;
import com.example.yos.databinding.databinding.SwipePesertaBinding;
import com.example.yos.databinding.viewmodel.FragmentContainerViewModel;
import com.example.yos.databinding.viewmodel.SwipePesertaViewModel;

/**
 * Created by iboy on 05/07/17.
 */

public class SwipePeserta extends Fragment {
    //layout pada card_peserta yang diberikan data binding
    private SwipePesertaBinding swipePesertaBinding;

    @Override //pada class extends Activity
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //untuk memberikan opsi pada actionbar dari fragment
        setHasOptionsMenu(true);
        //menampilkan layout
        swipePesertaBinding= DataBindingUtil.inflate(inflater, R.layout.swipe_peserta,container,false);
        //membuat VM mainPeserta
        SwipePesertaViewModel swipePesertaViewModel =new SwipePesertaViewModel(this.getActivity(),swipePesertaBinding);
        //mengeset data binding pada layout card_peserta
        swipePesertaBinding.setSwipeViewModel(swipePesertaViewModel);

        //mendapatkan view dari binding
        View view = swipePesertaBinding.getRoot();
        return view;
    }

    @Override //untuk menambahkan opsi pada action bar jika ada
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main, menu);
        //memberi tanda checked pada lingkaran
        menu.getItem(1).setChecked(true);
    }

    @Override //memberi event handler saat opsi dipilih
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.card_view == item.getItemId()) {
            //membuat VM fragmentContainer
            FragmentContainerViewModel fragmentContainerViewModel=new FragmentContainerViewModel((FragmentContainer)getActivity(), FragmentContainer.activityContainerBinding,FragmentContainer.CARD_VIEW);
            //mengeset data binding pada layout card_peserta
            FragmentContainer.activityContainerBinding.setFragmentContainerViewModel(fragmentContainerViewModel);
            return true;
        } else if (R.id.swipe_view == item.getItemId()) {
            //membuat VM fragmentContainer
            FragmentContainerViewModel fragmentContainerViewModel=new FragmentContainerViewModel((FragmentContainer)getActivity(), FragmentContainer.activityContainerBinding,FragmentContainer.SWIPE_VIEW);
            //mengeset data binding pada layout card_peserta
            FragmentContainer.activityContainerBinding.setFragmentContainerViewModel(fragmentContainerViewModel);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
