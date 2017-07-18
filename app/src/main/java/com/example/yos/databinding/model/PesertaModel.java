package com.example.yos.databinding.model;


/**
 * Created by iboy on 04/07/17.
 */

public class PesertaModel {
    public int idGambar;
    public String nama;
    public  int umur;
    public int tinggi;
    public PesertaModel(int idGambar,String  nama,int umur, int tinggi){
        this.idGambar=idGambar;
        this.nama=nama;
        this.umur=umur;
        this.tinggi=tinggi;
    }
}
