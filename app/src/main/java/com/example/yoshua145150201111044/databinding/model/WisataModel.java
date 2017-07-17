package com.example.yoshua145150201111044.databinding.model;

/**
 * Created by iboy on 06/07/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WisataModel {
    @SerializedName("nama")
    @Expose
    public String nama;
    @SerializedName("lokasi")
    @Expose
    public String lokasi;
    @SerializedName("deskripsi")
    @Expose
    public String deskripsi;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("gambar")
    @Expose
    public String gambar;
}

