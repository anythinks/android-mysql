package com.android.tugas9mysqlcr.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hasil {

    private String kode, nama, alamat, telp, tgl, kota, kabupaten, kecamatan, kelurahan;

    public Hasil(String kode, String nama, String alamat, String telp, String tgl, String kota, String kabupaten, String kecamatan, String kelurahan) {
        this.kode = kode;
        this.nama = nama;
        this.alamat = alamat;
        this.telp = telp;
        this.tgl = tgl;
        this.kota = kota;
        this.kabupaten = kabupaten;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }
}
