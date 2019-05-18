package com.bitsandbolts.checkmate;

import java.util.List;

class Place {
    private double latitude;
    private double longitude;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    private Place(double latitude, double longitude, int day, int month, int year, int hour, int minute) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public static Place createPoint(List<String> data) {
        double latitude = Double.parseDouble(data.get(0));
        double longitude = Double.parseDouble(data.get(1));
        int day = Integer.parseInt(data.get(2));
        int month = Integer.parseInt(data.get(3));
        int year = Integer.parseInt(data.get(4));
        int hour = Integer.parseInt(data.get(5));
        int minute = Integer.parseInt(data.get(6));
        return new Place(latitude, longitude, day, month, year, hour, minute);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}

