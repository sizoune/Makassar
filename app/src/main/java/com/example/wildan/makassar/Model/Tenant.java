package com.example.wildan.makassar.Model;

/**
 * Created by wildan on 07/04/17.
 */

public class Tenant {
    private String nama,deskripsi,status,urlgambar;

    public Tenant(String nama, String deskripsi, String status, String urlgambar) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.status = status;
        this.urlgambar = urlgambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlgambar() {
        return urlgambar;
    }

    public void setUrlgambar(String urlgambar) {
        this.urlgambar = urlgambar;
    }
}
