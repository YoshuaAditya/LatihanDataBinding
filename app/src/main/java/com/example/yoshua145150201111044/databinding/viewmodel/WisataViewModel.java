package com.example.yoshua145150201111044.databinding.viewmodel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.yoshua145150201111044.databinding.R;
import com.example.yoshua145150201111044.databinding.adapter.WisataAdapter;
import com.example.yoshua145150201111044.databinding.databinding.WisataViewBinding;
import com.example.yoshua145150201111044.databinding.model.WisataModel;
import com.example.yoshua145150201111044.databinding.retrofit.BatuInterface;
import com.example.yoshua145150201111044.databinding.retrofit.KabupatenInterface;
import com.example.yoshua145150201111044.databinding.retrofit.MalangInterface;
import com.example.yoshua145150201111044.databinding.retrofit.TempatInterface;
import com.example.yoshua145150201111044.databinding.view.FragmentContainer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iboy on 06/07/17.
 */

public class WisataViewModel {

    public ArrayList<WisataModel> wisataModels=new ArrayList<>();
    private MalangInterface malangInterface;
    private TempatInterface tempatInterface;
    private BatuInterface batuInterface;
    private KabupatenInterface kabupatenInterface;

    Call<List<WisataModel>> callData;

    public WisataViewModel(final Activity activity, final WisataViewBinding wisataViewBinding, int file_string ){

        //Progress dialog digunakan untuk pemberian informasi aplikasi sedang proses
        //hanya bisa digunakan proses untuk thread, retrofit menggunakan thread
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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FragmentContainer.activityContainerBinding.getRoot().getResources().getString(file_string))
                .addConverterFactory(GsonConverterFactory.create()).build();
        //mengambil interface yang mempunyai @GET Call<List<WisataModel>>


        if(file_string==R.string.link_file_json) {
            tempatInterface= retrofit.create(TempatInterface.class);
            callData = tempatInterface.getWisataModels();
        }
        else if(file_string==R.string.link_file_json_malang)
        {
            malangInterface= retrofit.create(MalangInterface.class);
            callData = malangInterface.getWisataModels();
        }
        else if(file_string==R.string.link_file_json_batu)
        {
            batuInterface= retrofit.create(BatuInterface.class);
            callData = batuInterface.getWisataModels();
        }
        else
        {
            kabupatenInterface= retrofit.create(KabupatenInterface.class);
            callData = kabupatenInterface.getWisataModels();
        }
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

                    WisataAdapter wisataAdapter=new WisataAdapter(activity,wisataModels);
                    wisataViewBinding.daftarWisata.setAdapter(wisataAdapter);

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
