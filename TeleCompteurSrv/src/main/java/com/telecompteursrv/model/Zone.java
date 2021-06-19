/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telecompteursrv.model;

/**
 *
 * @author USER
 */
public class Zone {
    
    private Long id_zone;
    private String lib_zone;
    private Double latitude;
    private Double longitude;
    
    public Zone(Long id, String lib,Double lat,Double lon){
        this.id_zone=id;
        this.lib_zone=lib;
        this.latitude=lat;
        this.longitude=lon;
    }
    
    public Zone(){}

    /**
     * @return the id_zone
     */
    public Long getId_zone() {
        return id_zone;
    }
    
   
    public void setId_zone(Long id_zone) {
        this.id_zone=id_zone;
    }

    /**
     * @return the lib_zone
     */
    public String getLib_zone() {
        return lib_zone;
    }

    /**
     * @param lib_zone the lib_zone to set
     */
    public void setLib_zone(String lib_zone) {
        this.lib_zone = lib_zone;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "ZONE ID : "+this.id_zone+
                " LIB : "+this.lib_zone+
                " Latitude : "+this.latitude + 
                " Longitude : "+this.longitude; 
    }
    
    
    
    
    
}
