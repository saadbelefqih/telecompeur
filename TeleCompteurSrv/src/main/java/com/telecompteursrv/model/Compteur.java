/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telecompteursrv.model;

import java.util.Date;

/**
 *
 * @author USER
 */
public class Compteur {
    
    
    private Long idCompteur;
    private String code;
    private String marque;
    private Date dateMiseEnOeuvre;
    private Zone zone;
    private Client client;
    private Boolean isActive;
    
    public Compteur(Long idCompteur ,
      String code ,
      String marque ,
      Date dateMiseEnOeuvre,
      Zone zone,
      Client client,
      Boolean isActive){
        this.idCompteur=idCompteur;
        this.code=code;
        this.marque=marque;
        this.dateMiseEnOeuvre=dateMiseEnOeuvre;
        this.zone=zone;
        this.client=client;
        this.isActive=isActive;
    }

    public Compteur() {
        
    }

    /**
     * @return the idCompteur
     */
    public Long getIdCompteur() {
        return idCompteur;
    }
    
        /**
     * @param idCompteur the idCompteur to set
     */
    public void setIdCompteur(Long idCompteur) {
        this.idCompteur = idCompteur;
    }

    /**
     * @return the isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @return the zone
     */
    public Zone getZone() {
        return zone;
    }

    /**
     * @param zone the zone to set
     */
    public void setZone(Zone zone) {
        this.zone = zone;
    }
    
    
    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }
    
     /**
     * @return the dateMiseEnOeuvre
     */
    public Date getDateMiseEnOeuvre() {
        return dateMiseEnOeuvre;
    }

    /**
     * @param dateMiseEnOeuvre the dateMiseEnOeuvre to set
     */
    public void setDateMiseEnOeuvre(Date dateMiseEnOeuvre) {
        this.dateMiseEnOeuvre = dateMiseEnOeuvre;
    }
    
      @Override
    public String toString() {
        return " COMPTEUR ID : "+this.idCompteur+
                " Code : "+this.code+
                " Marque : "+this.marque +
                " Est Activé "+this.getIsActive()+
                " Date Mise en Oeuvre : "+this.dateMiseEnOeuvre.toString() +
                " Zone : "+this.zone.getLib_zone()+
                " Client : "+this.getClient().getNom() + " "+ this.getClient().getPrenom();
    }


   

    
    

    
}
