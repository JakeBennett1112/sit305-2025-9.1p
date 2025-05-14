package com.example.passtask91.model;

public class Advert {
    // Variables for the item
    private String item_name, item_description, item_date, item_location;
    private int item_phone;

    // new variables to hold latitude and longitude of the item
    private double latitude;
    private double longitude;

    // Constructor
    public Advert (String item_name, String item_description, String item_date, String item_location, int item_phone) {
        this.item_name = item_name;
        this.item_description = item_description;
        this.item_date = item_date;
        this.item_location = item_location;
        this.item_phone = item_phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }



    // empty constructor
    public Advert() {
    }

    // Getter and setter so we can access and change these variables later

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_date() {
        return item_date;
    }

    public void setItem_date(String item_date) {
        this.item_date = item_date;
    }

    public String getItem_location() {
        return item_location;
    }

    public void setItem_location(String item_location) {
        this.item_location = item_location;
    }

    public int getItem_phone() {
        return item_phone;
    }

    public void setItem_phone(int item_phone) {
        this.item_phone = item_phone;
    }

    // set and get latitude and longitude
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
