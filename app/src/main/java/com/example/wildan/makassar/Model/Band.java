package com.example.wildan.makassar.Model;

/**
 * Created by wildan on 09/02/17.
 */

public class Band {
    String name;
    String desc;
    String img_url;

    public Band(String img_url, String name, String desc) {
        this.img_url = img_url;
        this.name = name;
        this.desc = desc;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
