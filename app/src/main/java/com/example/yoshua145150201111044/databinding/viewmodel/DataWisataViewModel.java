package com.example.yoshua145150201111044.databinding.viewmodel;

import android.databinding.ObservableField;

import com.example.yoshua145150201111044.databinding.model.PesertaModel;
import com.example.yoshua145150201111044.databinding.model.WisataModel;

/**
 * Created by iboy on 06/07/17.
 */

public class DataWisataViewModel {
    //variabel untuk data binding
    public ObservableField<String> idGambar=new ObservableField<>();
    public ObservableField<String> nama=new ObservableField<>();
    public ObservableField<String> lokasi=new ObservableField<>();
    public ObservableField<String> deskripsi=new ObservableField<>();
    public ObservableField<String> thumbnail=new ObservableField<>();

    public DataWisataViewModel(WisataModel wisataModel){
        this.idGambar.set(wisataModel.gambar);
        this.nama.set(wisataModel.nama);
        this.lokasi.set(wisataModel.lokasi);
        this.deskripsi.set(wisataModel.deskripsi);
        this.thumbnail.set(wisataModel.thumbnail);
    }
}
