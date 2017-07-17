package com.example.yoshua145150201111044.databinding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by iboy on 11/07/17.
 */

public class WisataMapsModel {
    @SerializedName("name")
    @Expose
    public String nama;
    @SerializedName("lokasi")
    @Expose
    public int lokasi;
    @SerializedName("latitude")
    @Expose
    public double latitude;
    @SerializedName("longitude")
    @Expose
    public double longitude;
}
