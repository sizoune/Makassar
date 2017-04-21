package com.example.wildan.makassar.Model;

/**
 * Created by wildan on 21/04/17.
 */

public class Sponsor {
    private String name,desc,gambar;

    public Sponsor(String name, String desc, String gambar) {
        this.name = name;
        this.desc = desc;
        this.gambar = gambar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
