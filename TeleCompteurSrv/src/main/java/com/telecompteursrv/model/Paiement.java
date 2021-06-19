/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telecompteursrv.model;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author USER
 */
public class Paiement {

    private Long codePaiement;
    private String libelPaiement;
    private Client client;
    private Compteur compteur;
    private Date datePaiement;
    private Double tarif;
    private Double totalEau;
    private Double totalElec;
    private int moisPaye;
    private int anneePaye;
    private Boolean isPaid;
    private Set<CompteurDetails> compteurDetailses;

    public Paiement() {
    }

    public Paiement(Long codePaiement, String libelPaiement,Client client, Date datePaiement, Double tarif,Double totalEau,Double totalElec, int moisPaye,int anneePaye, Boolean isPaid,Set<CompteurDetails> compteurDetailses,Compteur compteur) {
        this.codePaiement = codePaiement;
        this.libelPaiement = libelPaiement;
        this.client=client;
        this.compteur=compteur;
        this.datePaiement = datePaiement;
        this.tarif = tarif;
        this.totalEau=totalEau;
        this.totalElec=totalElec;
        this.moisPaye = moisPaye;
        this.anneePaye=anneePaye;
        this.isPaid = isPaid;
        this.compteurDetailses=compteurDetailses;
    }

    /**
     * @return the codePaiement
     */
    public Long getCodePaiement() {
        return codePaiement;
    }

    /**
     * @param codePaiement the codePaiement to set
     */
    public void setCodePaiement(Long codePaiement) {
        this.codePaiement = codePaiement;
    }

    /**
     * @return the libelPaiement
     */
    public String getLibelPaiement() {
        return libelPaiement;
    }

    /**
     * @param libelPaiement the libelPaiement to set
     */
    public void setLibelPaiement(String libelPaiement) {
        this.libelPaiement = libelPaiement;
    }

    /**
     * @return the datePaiement
     */
    public Date getDatePaiement() {
        return datePaiement;
    }

    /**
     * @param datePaiement the datePaiement to set
     */
    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    /**
     * @return the tarif
     */
    public Double getTarif() {
        return tarif;
    }

    /**
     * @param tarif the tarif to set
     */
    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    /**
     * @return the moisPaye
     */
    public int getMoisPaye() {
        return moisPaye;
    }

    /**
     * @param moisPaye the moisPaye to set
     */
    public void setMoisPaye(int moisPaye) {
        this.moisPaye = moisPaye;
    }

    /**
     * @return the anneePaye
     */
    public int getAnneePaye() {
        return anneePaye;
    }

    /**
     * @param anneePaye the anneePaye to set
     */
    public void setAnneePaye(int anneePaye) {
        this.anneePaye = anneePaye;
    }

    /**
     * @return the isPaid
     */
    public Boolean getIsPaid() {
        return isPaid;
    }

    /**
     * @param isPaid the isPaid to set
     */
    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
    
        /**
     * @return the totalEau
     */
    public Double getTotalEau() {
        return totalEau;
    }

    /**
     * @param totalEau the totalEau to set
     */
    public void setTotalEau(Double totalEau) {
        this.totalEau = totalEau;
    }

    /**
     * @return the totalElec
     */
    public Double getTotalElec() {
        return totalElec;
    }

    /**
     * @param totalElec the totalElec to set
     */
    public void setTotalElec(Double totalElec) {
        this.totalElec = totalElec;
    }
    
    /**'
     * @return the compteurDetailses
     */
    public Set<CompteurDetails> getCompteurDetailses() {
        return compteurDetailses;
    }

    /**
     * @param compteurDetailses the compteurDetailses to set
     */
    public void setCompteurDetailses(Set<CompteurDetails> compteurDetailses) {
        this.compteurDetailses = compteurDetailses;
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




    @Override
    public String toString() {
        return " code : "+ this.codePaiement+
               " Client Nom : "+this.client.getNom()+" PRENOM : "+this.client.getPrenom()+" ABONNEMENT "+this.client.getNum_abonnement()+"\n"+
               " Lib :  ["+ this.libelPaiement+"] \n"+
               " Date Paiement : "+this.datePaiement+"\n"+
               " Tarif : ["+this.tarif+"] DH \n"+
               " Somme Eau : "+this.totalEau+"\n"+
               " Somme Electricité : "+this.totalElec+"\n"+
               " date à payé : **/"+this.moisPaye+"/"+this.anneePaye+"\n"+
               " Est payé : "+this.isPaid;
    }








    
    
}
