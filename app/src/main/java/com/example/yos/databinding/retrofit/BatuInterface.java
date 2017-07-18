package com.example.yos.databinding.retrofit;

import com.example.yos.databinding.model.WisataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by iboy on 06/07/17.
 */

public interface BatuInterface {
    //untuk mendapatkan file json dari link file
    @GET("List_place_kota_batu.json")
    Call<List<WisataModel>> getWisataModels();
}
