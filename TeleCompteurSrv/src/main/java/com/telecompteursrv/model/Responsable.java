/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telecompteursrv.model;

import com.telecompteursrv.config.Genre;
import com.telecompteursrv.config.Poste;
import java.util.Date;

/**
 *
 * @author USER
 */
public final class Responsable extends Utilisateur{
    private Poste poste;
    
    //  constructeur Responsable init
    public Responsable(String nom,String prenom,Genre genre,String cin,String nom_utilisateurs,String motPasse,Date dateNaissance,String tel,String adresse,Boolean isActive,Poste poste){
        super(nom,prenom,genre,cin,nom_utilisateurs, motPasse, dateNaissance, tel, adresse,isActive);
        this.poste=poste;
    }
    //  constructeur Responsable defaut
    public Responsable(){}
    /**
     * @return the poste
     */
    public Poste getPoste() {
        return poste;
    }

    /**
     * @param poste the poste to set
     */
    public void setPoste(Poste poste) {
        this.poste = poste;
    }
    
    
    //toString()
    @Override
    public String toString() {
        return super.toString()+" poste : "+this.poste;
    }
    
}
