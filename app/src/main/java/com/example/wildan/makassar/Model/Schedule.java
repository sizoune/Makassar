package com.example.wildan.makassar.Model;

/**
 * Created by wildan on 09/02/17.
 */

public class Schedule {
    String stage, band, status, img_url;


    public Schedule(String stage, String band, String status, String img_url) {
        this.stage = stage;
        this.band = band;
        this.status = status;
        this.img_url = img_url;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
