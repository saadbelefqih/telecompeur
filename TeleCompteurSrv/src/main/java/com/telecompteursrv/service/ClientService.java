/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telecompteursrv.service;


import com.telecompteursrv.config.Cnx_DB;
import com.telecompteursrv.config.Genre;
import com.telecompteursrv.model.Client;
import com.telecompteursrv.model.Compteur;
import com.telecompteursrv.model.CompteurDetails;
import com.telecompteursrv.model.Paiement;
import com.telecompteursrv.model.Zone;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class ClientService {
     Connection con;
     public  ClientService() throws SQLException{
         con = DriverManager.getConnection(Cnx_DB.db_URL,
            Cnx_DB.username,
            Cnx_DB.passwrd);
     }
     
     public Long authenficiation (String nomUser,String psw) throws SQLException{
        Long idUser= null;
        if (!Objects.isNull(nomUser) && !Objects.isNull(psw)){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT userID FROM client WHERE UPPER(nom_utilisateurs)= UPPER('"+nomUser+"') and motPasse='"+psw+"' and  isActive is true ");
            while (rs.next()) {idUser=rs.getLong("userID");}
            }
        return idUser;
     }
     
         
    public Boolean resetPassword(Long userID,String passwordOld,String passwordNew) throws SQLException{
        String  query="UPDATE client  SET motPasse='"+passwordNew+"' WHERE motPasse='"+passwordOld+"' AND userID="+userID;
        Statement stmt = con.createStatement();
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
    }
     
              // Rechercher Client By ID
     
     public Client clientFindByID(Long idClient) throws SQLException,Exception{
         Client client = new Client();
        if (!Objects.isNull(idClient)){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE userID="+idClient+" LIMIT 1");
            while (rs.next()) {
                client.setId_User(rs.getLong("userID"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setGenre(Genre.valueOf(rs.getString("genre")));
                client.setcIN(rs.getString("cIN"));
                client.setNom_utilisateurs(rs.getString("nom_utilisateurs"));
                client.setMotPasse(rs.getString("motPasse"));
                client.setDateNaissance(rs.getDate("dateNaissance"));
                client.setTel(rs.getString("tel"));
                client.setAdresse(rs.getString("adresse"));
                client.setIsActive(rs.getBoolean("isActive"));
                client.setNum_abonnement(rs.getString("num_abonnement"));}
            }
        return client;
     }
     
     
      public LinkedList<Compteur>  afficherCompteursByClient (Long idUser) throws SQLException{
        LinkedList<Compteur> compteurs= new  LinkedList<> ();
        if (!Objects.isNull(idUser)){
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM compteur,zone,client where compteur.userid=client.userID and zone.id_zone=compteur.id_zone and compteur.userid="+idUser);
        while (rs.next()) {
            Zone zone = new Zone (rs.getLong("id_zone"),rs.getString("lib_zone"),rs.getDouble("latitude"),rs.getDouble("longitude"));
            Client client=new Client(rs.getString("nom"),rs.getString("prenom"),Genre.valueOf(rs.getString("genre")),rs.getString("cIN"),rs.getString("cIN"),rs.getString("motPasse"),rs.getDate("motPasse"),rs.getString("tel"),rs.getString("adresse"),rs.getBoolean("isActive"),rs.getString("num_abonnement"));
            client.setId_User(idUser);      
            Compteur cmpt = new Compteur(rs.getLong("idcompteur"), rs.getString("code"),rs.getString("marque"), rs.getDate("dateMiseEnOeuvre"), zone, client,rs.getBoolean("isActive"));
            compteurs.add(cmpt);}
        }
     return compteurs;
     }
      
     public LinkedList<CompteurDetails>  afficherDetailsCompteur (Long CompteurId) throws SQLException{
         
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM compteurdetails,compteur WHERE compteurdetails.idcompteur=compteur.idcompteur and idcompteur="+CompteurId);
        LinkedList<CompteurDetails> compteurDetails= new  LinkedList<> ();
        while (rs.next()) {    
            Compteur cmpt = new Compteur(rs.getLong("idcompteur"), rs.getString("code"),rs.getString("marque"), rs.getDate("dateMiseEnOeuvre"), null, null,rs.getBoolean("isActive"));
            CompteurDetails details = new CompteurDetails(rs.getDouble("indexEauNew"),rs.getDouble("indexElectNew"),rs.getDate("DateDernierMAJ"),cmpt,null);
            compteurDetails.add(details);}
     return compteurDetails;
     }
     
     
    public List<Paiement> getAllFacturesUnpaid(Long idUser) throws SQLException{
         List<Paiement> paiements = new ArrayList<>();
        
        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vue_consom_nonpaye WHERE userID="+idUser);
            while (rs.next()) {
                
                Compteur compteur = new Compteur();
                Client client = new Client ();
                Paiement paiement = new Paiement();
                
                //  Compteur
                compteur.setIdCompteur(rs.getLong("idcompteur"));
                compteur.setCode(rs.getString("code"));
                compteur.setMarque(rs.getString("marque"));
                
                // CLIENT
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setcIN(rs.getString("cIN"));
                client.setNum_abonnement(rs.getString("num_abonnement"));
                
                // paiement 
                paiement.setAnneePaye(rs.getInt("annee"));
                paiement.setMoisPaye(rs.getInt("mois"));
                paiement.setTotalEau(rs.getDouble("sum_eau"));
                paiement.setTotalElec(rs.getDouble("sum_elect"));
                paiement.setLibelPaiement(paiement.getMoisPaye()+"/"+paiement.getAnneePaye());
                Double tarif=(paiement.getTotalEau()*1)+(paiement.getTotalElec()*2);
                paiement.setTarif((int)(Math.round((tarif) * 100))/100.0);
                
                paiement.setIsPaid(false);
                paiement.setClient(client);
                paiement.setCompteur(compteur);
                // add to liste
                paiements.add(paiement);}
        return paiements;
         
     }
    
    public List<Paiement> getAllFacturesPaid(Long idUser) throws SQLException{
         List<Paiement> paiements = new ArrayList<>();
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM vue_consom_paye WHERE userID="+idUser);
        while (rs.next()) {
                
                Compteur compteur = new Compteur();
                Client client = new Client ();
                Paiement paiement = new Paiement();
                
                //  Compteur
                compteur.setIdCompteur(rs.getLong("idcompteur"));
                compteur.setCode(rs.getString("code"));
                compteur.setMarque(rs.getString("marque"));
                
                // CLIENT
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setcIN(rs.getString("cIN"));
                client.setNum_abonnement(rs.getString("num_abonnement"));
                
                // paiement 
                paiement.setAnneePaye(rs.getInt("anneePaye"));
                paiement.setMoisPaye(rs.getInt("moisPaye"));
                paiement.setTotalEau(rs.getDouble("sum_eau"));
                paiement.setTotalElec(rs.getDouble("sum_elect"));
                paiement.setLibelPaiement(rs.getString("libelPaiement"));
                paiement.setDatePaiement(rs.getDate("datePaiement"));
                paiement.setTarif(rs.getDouble("tarif"));
                paiement.setIsPaid(rs.getBoolean("isPaid"));
                paiement.setClient(client);
                paiement.setCompteur(compteur);
                // add to liste
                paiements.add(paiement);}
        return paiements;
         
     }
    
    public List<CompteurDetails> getDetailsConsommation(Long idUser,int mois,int annee) throws SQLException{
        //SELECT * FROM vue_consom_details WHERE userID = 5 AND mois=5 and annee=2021
        List<CompteurDetails>compteurDetailses = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM vue_consom_details WHERE userID="+idUser+" and mois="+mois+" and annee="+annee);
        while (rs.next()) {
                
                Compteur compteur = new Compteur();
                Client client = new Client ();
                CompteurDetails compteurDetailse = new CompteurDetails();
                
                //  Compteur
                compteur.setIdCompteur(rs.getLong("idcompteur"));
                compteur.setCode(rs.getString("code"));
                compteur.setMarque(rs.getString("marque"));
                compteur.setClient(client);
                
                // CLIENT
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setcIN(rs.getString("cIN"));
                client.setNum_abonnement(rs.getString("num_abonnement"));
                
                // CompteurDetails 
                compteurDetailse.setDateDernierMAJ(rs.getDate("DateDernierMAJ"));
                compteurDetailse.setIndexEauNew(rs.getDouble("indexEauNew"));
                compteurDetailse.setIndexElectNew(rs.getDouble("indexElectNew"));
                compteurDetailse.setCompteur(compteur);
                
                // add to liste
                compteurDetailses.add(compteurDetailse);}
        return compteurDetailses;
        
    }

     
     
     
    
    
}
