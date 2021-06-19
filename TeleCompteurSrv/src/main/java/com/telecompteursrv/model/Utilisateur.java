/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telecompteursrv.model;

import com.telecompteursrv.config.Genre;
import java.util.Date;
/**
 *
 * @author USER
 */
public abstract class Utilisateur {
    private Long id_User;
    private String nom;
    private String prenom;
    private Genre genre;
    private String cIN;
    private String nom_utilisateurs;
    private String motPasse;
    private Date dateNaissance;
    private String tel;
    private String adresse;
    private Boolean isActive;
    
    // constructeur Utilisateur init
    public  Utilisateur (String nom,String prenom,Genre genre,String cin,String nom_utilisateurs,String motPasse,Date dateNaissance,String tel,String adresse,Boolean isActive){
         //this.id_User=id;
         this.nom=nom;
         this.prenom=prenom;
         this.cIN=cin;
         this.genre=genre;
         this.nom_utilisateurs=nom_utilisateurs;
         this.motPasse=motPasse;
         this.dateNaissance=dateNaissance;
         this.tel=tel;
         this.adresse=adresse;
         this.isActive=isActive;
    }
    
    // constructeur  defaut
    public  Utilisateur (){}
   
    

    /**
     * @return the id_User
     */
    public Long getId_User() {
        return id_User;
    }
    
    /**
     * @param id_User the id_User to set
     */
    public void setId_User(Long id_User) {
        this.id_User = id_User;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * @return the nom_utilisateurs
     */
    public String getNom_utilisateurs() {
        return nom_utilisateurs;
    }
    
    /**
     * @param nom_utilisateurs the nom_utilisateurs to set
     */
    public void setNom_utilisateurs(String nom_utilisateurs) {
        this.nom_utilisateurs = nom_utilisateurs;
    }

    /**
     * @return the motPasse
     */
    public String getMotPasse() {
        return motPasse;
    }

    /**
     * @param motPasse the motPasse to set
     */
    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    /**
     * @return the dateNaissance
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
     * @return the cIN
     */
    public String getcIN() {
        return cIN;
    }

    /**
     * @param cIN the cIN to set
     */
    public void setcIN(String cIN) {
        this.cIN = cIN;
    }
    
    
    // To string()
    @Override
    public String toString() {
        return " ID:  "+this.id_User+
         " Nom : "+this.nom+
         " Prenom : "+this.prenom+
         " CINE : "+this.cIN+
         " Genre : "+this.genre+
         " UserName : "+this.nom_utilisateurs+
         " MOT de Passe : "+this.motPasse+
         " Date Naissance : "+this.dateNaissance.toString()+
         " Tel : "+this.tel+
         " Adresse : "+this.adresse+
         " isActive : "+this.isActive;
    }



   
    
    
    
}
