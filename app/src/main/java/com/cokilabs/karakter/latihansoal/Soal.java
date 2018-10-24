package com.cokilabs.karakter.latihansoal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Soal {

    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("soal_atas")
    @Expose
    private String soalAtas;
    @SerializedName("soal_bawah")
    @Expose
    private String soalBawah;
    @SerializedName("a")
    @Expose
    private String a;
    @SerializedName("b")
    @Expose
    private String b;
    @SerializedName("c")
    @Expose
    private String c;
    @SerializedName("d")
    @Expose
    private String d;
    @SerializedName("kunci")
    @Expose
    private String kunci;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getSoalAtas() {
        return soalAtas;
    }

    public void setSoalAtas(String soalAtas) {
        this.soalAtas = soalAtas;
    }

    public String getSoalBawah() {
        return soalBawah;
    }

    public void setSoalBawah(String soalBawah) {
        this.soalBawah = soalBawah;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

}
