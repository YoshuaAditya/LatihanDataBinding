package com.example.yoshua145150201111044.databinding.retrofit;

import com.example.yoshua145150201111044.databinding.model.WisataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by iboy on 06/07/17.
 */

public interface MalangInterface {
    //untuk mendapatkan file json dari link file
    @GET("List_place_kota_malang.json")
    Call<List<WisataModel>> getWisataModels();
}
