package com.example.yos.databinding.retrofit;

import com.example.yos.databinding.model.WisataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by iboy on 06/07/17.
 */

public interface KabupatenInterface  {
    //untuk mendapatkan file json dari link file
    @GET("List_place_kab_malang.json")
    Call<List<WisataModel>> getWisataModels();
}
