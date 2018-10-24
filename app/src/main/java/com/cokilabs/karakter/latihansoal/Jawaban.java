package com.cokilabs.karakter.latihansoal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jawaban {

    @SerializedName("no")
    @Expose
    private String no;
    @SerializedName("jawaban")
    @Expose
    private String jawaban;
    @SerializedName("kunci")
    @Expose
    private String kunci;
    @SerializedName("koreksi")
    @Expose
    private Boolean koreksi;

    public Jawaban(String no, String jawaban, String kunci) {
        this.no = no;
        this.jawaban = jawaban;
        this.kunci = kunci;
        Boolean korek;
        if(jawaban.equals(kunci)){
            korek = true;
        }else{
            korek = false;
        }
        this.koreksi = korek;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    public Boolean getKoreksi() {
        return koreksi;
    }

    public void setKoreksi(Boolean koreksi) {
        this.koreksi = koreksi;
    }

}