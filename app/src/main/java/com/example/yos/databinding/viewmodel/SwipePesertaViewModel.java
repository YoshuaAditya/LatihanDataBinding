package com.example.yos.databinding.viewmodel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.example.yos.databinding.R;
import com.example.yos.databinding.adapter.ViewPagerAdapter;
import com.example.yos.databinding.databinding.SwipePesertaBinding;
import com.example.yos.databinding.model.WisataModel;
import com.example.yos.databinding.retrofit.TempatInterface;
import com.example.yos.databinding.view.FragmentContainer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iboy on 05/07/17.
 */

public class SwipePesertaViewModel {
    public ArrayList<WisataModel> wisataModels =new ArrayList<>();

    public SwipePesertaViewModel(final Activity activity,final SwipePesertaBinding swipePesertaBinding){

        final ProgressDialog pDialog= new ProgressDialog(activity);
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(true);
//        pDialog.setCanceledOnTouchOutside(false);
        //listener saat dialog dicancel
//        pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                Toast.makeText(activity,R.string.loading_error,Toast.LENGTH_SHORT).show();
//            }
//        });
        pDialog.show();

        //inisialisasi retrofit dengan builder, dengan mengambil url lalu diconvert ke gson
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FragmentContainer.activityContainerBinding.getRoot().getResources().getString(R.string.link_file_json))
                .addConverterFactory(GsonConverterFactory.create()).build();
        //mengambil interface yang mempunyai @GET Call<List<WisataModel>>


        TempatInterface tempatInterface= retrofit.create(TempatInterface.class);
        Call<List<WisataModel>> callData = tempatInterface.getWisataModels();

        //mendapatkan call list dari method TempatInterface.get
        //memasukkan data ke callData dengan enqueue dari Callback
        callData.enqueue(new Callback<List<WisataModel>>() {
            @Override
            public void onResponse(Call<List<WisataModel>> call, Response<List<WisataModel>> response) {
                if (response.isSuccessful()) {
                    int i = 0;
                    for (WisataModel wisataModel : response.body()) {
                        wisataModels.add(wisataModel);
                        i++;
                    }

                    //membuat adapter untuk gridView daftarPeserta
                    ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(activity,wisataModels);
                    swipePesertaBinding.viewPager.setAdapter(viewPagerAdapter);

                    //setelah thread retrofit selesai progress dialog dimatikan
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<WisataModel>> call, Throwable t) {
                //setelah thread retrofit selesai progress dialog dimatikan
                pDialog.dismiss();
                Toast.makeText(activity,R.string.loading_error,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
