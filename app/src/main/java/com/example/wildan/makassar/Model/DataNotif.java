package com.example.wildan.makassar.Model;

import java.io.Serializable;

/**
 * Created by wildan on 12/04/17.
 */

public class DataNotif implements Serializable {
    private String nama,status;

    public DataNotif(String nama, String status) {
        this.nama = nama;
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
