package com.example.yos.databinding.viewmodel;

import android.databinding.ObservableField;

import com.example.yos.databinding.model.WisataModel;

/**
 * Created by iboy on 04/07/17.
 */

public class CardLayoutViewModel {
    //variabel untuk data binding
    public ObservableField<String> thumbnail=new ObservableField<>();
    public ObservableField<String> nama=new ObservableField<>();

    public CardLayoutViewModel(WisataModel wisataModel){
        this.thumbnail.set(wisataModel.thumbnail);
        this.nama.set(wisataModel.nama);
    }
}
