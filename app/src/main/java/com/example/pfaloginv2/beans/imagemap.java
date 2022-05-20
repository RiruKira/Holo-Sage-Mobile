package com.example.pfaloginv2.beans;

import java.util.List;

public class imagemap {
    String file_url;
    String date_captured;
    gps_location gps_location;
    String width, height;
    List<hotspots> hotspots;

    public imagemap() {

    }

    public imagemap(String file_url, String date_captured, com.example.pfaloginv2.beans.gps_location gps_location, String width, String height, List<hotspots> hotspots) {
        this.file_url = file_url;
        this.date_captured = date_captured;
        this.gps_location = gps_location;
        this.width = width;
        this.height = height;
        this.hotspots = hotspots;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getDate_captured() {
        return date_captured;
    }

    public void setDate_captured(String date_captured) {
        this.date_captured = date_captured;
    }

    public com.example.pfaloginv2.beans.gps_location getGps_location() {
        return gps_location;
    }

    public void setGps_location(com.example.pfaloginv2.beans.gps_location gps_location) {
        this.gps_location = gps_location;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public List<hotspots> getHotspots() {
        return hotspots;
    }

    public void setHotspots(List<hotspots> hotspots) {
        this.hotspots = hotspots;
    }
}
