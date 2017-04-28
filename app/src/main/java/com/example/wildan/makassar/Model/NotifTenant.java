package com.example.wildan.makassar.Model;

import java.io.Serializable;

/**
 * Created by wildan on 28/04/17.
 */

public class NotifTenant implements Serializable{
    private String nama,deskripsi,status;

    public NotifTenant(String nama, String deskripsi, String status) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.status = status;
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
}
