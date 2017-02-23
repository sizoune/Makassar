package com.example.wildan.makassar.Model;

/**
 * Created by wildan on 09/02/17.
 */

public class Schedule {
    String stage, date, status;
    Band band;

    public Schedule(String stage, String date, String status, Band band) {
        this.stage = stage;
        this.date = date;
        this.status = status;
        this.band = band;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }
}
