package com.bitsandbolts.checkmate;

public class Happening {
    private String location;
    private String date;
    private String time;


    public Happening(String location, String date, String time) {
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event happened at " + getLocation() + " on " + getDate() + " around " + getTime();
    }
}