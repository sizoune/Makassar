package com.example.wildan.makassar.Model;

/**
 * Created by wildan on 09/02/17.
 */

public class Schedule {
    String stage, band, status;


    public Schedule(String stage, String band, String status) {
        this.stage = stage;
        this.band = band;
        this.status = status;
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


}
