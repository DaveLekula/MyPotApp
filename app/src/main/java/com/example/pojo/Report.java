package com.example.pojo;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {

    private int id;
    private Date date;
    private File image;
    private String description;
    private double longitude;
    private double latitude;
    private String Status;

    public Report() {

    }

    public Report(int id, Date date, File image, String description, double longitude, double latitude, String status) {
        this.id = id;
        this.date = date;
        this.image = image;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.Status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


}
