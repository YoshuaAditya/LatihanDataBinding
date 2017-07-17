package com.example.yoshua145150201111044.databinding.retrofit;

import com.example.yoshua145150201111044.databinding.model.WisataMapsModel;
import com.example.yoshua145150201111044.databinding.model.WisataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by iboy on 11/07/17.
 */

public interface MapsInterface {
    //untuk mendapatkan file json dari link file
    @GET("Maps_Malang_Batu.json")
    Call<List<WisataMapsModel>> getWisataModels();
}
