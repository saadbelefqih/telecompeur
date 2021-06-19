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
public class CompteurDetails {
    
    private Long idCD;
    private Double indexEauNew;
    private Double indexElectNew;
    private Date  DateDernierMAJ;
    private Compteur compteur;
    private Paiement paiement;
    
   public CompteurDetails( Double indexEauNew ,Double indexElectNew ,Date  preleverment ,Compteur compteur,Paiement paiement){
       this.indexEauNew=indexEauNew;
       this.indexElectNew=indexElectNew;
       this.DateDernierMAJ=preleverment;
       this.compteur=compteur;
       this.paiement=paiement;}
   
   public CompteurDetails(){
   }

    /**
     * @return the idCD
     */
    public Long getIdCD() {
        return idCD;
    }

    /**
     * @return the indexEauNew
     */
    public Double getIndexEauNew() {
        return indexEauNew;
    }

    /**
     * @param indexEauNew the indexEauNew to set
     */
    public void setIndexEauNew(Double indexEauNew) {
        this.indexEauNew = indexEauNew;
    }

    /**
     * @return the indexElectNew
     */
    public Double getIndexElectNew() {
        return indexElectNew;
    }

    /**
     * @param indexElectNew the indexElectNew to set
     */
    public void setIndexElectNew(Double indexElectNew) {
        this.indexElectNew = indexElectNew;
    }


    /**
     * @return the DateDernierMAJ
     */
    public Date getDateDernierMAJ() {
        return DateDernierMAJ;
    }

    /**
     * @param DateDernierMAJ the DateDernierMAJ to set
     */
    public void setDateDernierMAJ(Date DateDernierMAJ) {
        this.DateDernierMAJ = DateDernierMAJ;
    }

    /**
     * @return the compteur
     */
    public Compteur getCompteur() {
        return compteur;
    }

    /**
     * @param compteur the compteur to set
     */
    public void setCompteur(Compteur compteur) {
        this.compteur = compteur;
    }
    
    
    /**
     * @return the paiement
     */
    public Paiement getPaiement() {
        return paiement;
    }

    /**
     * @param paiement the paiement to set
     */
    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }
    

    @Override
    public String toString() {
        return " ID :"+ idCD
                +" INDEX EAU PRELEVE : "+indexEauNew
                +" INDEX ELETRICITE PRELEVE: "+indexElectNew
                +" DATE : "+DateDernierMAJ.toString()
                +" COMPTEUR : "+this.compteur.getCode() + " "+this.compteur.getMarque()
                +" CLIENT : "+this.compteur.getClient().getNom() + " " + this.getCompteur().getClient().getPrenom();
    }


    
    
   
   
}
