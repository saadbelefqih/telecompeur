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
public final class Client extends Utilisateur{
    private String num_abonnement;
    
    //  constructeur Client init
    public Client(String nom,String prenom,Genre genre,String cin,String nom_utilisateurs,String motPasse,Date dateNaissance,String tel,String adresse,Boolean isActive,String num_abonnement){
        super(nom,prenom,genre,cin,nom_utilisateurs, motPasse, dateNaissance, tel, adresse,isActive);
        this.num_abonnement=num_abonnement;
    }
    
    //  constructeur Client default
    public Client(){};

    /**
     * @return the num_abonnement
     */
    public String getNum_abonnement() {
        return num_abonnement;
    }

    /**
     * @param num_abonnement the num_abonnement to set
     */
    public void setNum_abonnement(String num_abonnement) {
        this.num_abonnement = num_abonnement;
    }
    
    
    //toString()
    @Override
    public String toString() {
        return super.toString()+" num_abonnement : "+this.num_abonnement;
    }
    
    
    
}
