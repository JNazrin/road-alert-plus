package com.example.groupproject_ict602;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Maklumat {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("date_time")
    @Expose
    private String dateTime;

    public Maklumat() {
    }

    /**
     *
     * @param dateTime
     * @param lng
     * @param name
     * @param description
     * @param location
     * @param id
     * @param type
     * @param lat
     */

    public Maklumat(String id, String name, String type, String location, String description, String lat, String lng, String dateTime) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}