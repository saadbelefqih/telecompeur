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
import com.telecompteursrv.model.Paiement;
import com.telecompteursrv.model.Zone;
import java.sql.CallableStatement;
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
public class ResponsableService {
    
    Connection con;
     public  ResponsableService() throws SQLException{
         con = DriverManager.getConnection(Cnx_DB.db_URL,
            Cnx_DB.username,
            Cnx_DB.passwrd);
     }
 // ******************************************************* AUTHENTIFICATION *******************************************************************************    
     
     public Long authenficiation (String nomUser,String psw) throws SQLException{
        Long idUser= null;
        if (!Objects.isNull(nomUser) && !Objects.isNull(psw)){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT userID FROM responsable WHERE UPPER(username)= UPPER('"+nomUser+"') and password='"+psw+"'");
            while (rs.next()) {idUser=rs.getLong("userID");}
            }
        return idUser;
     }
     
// ******************************************************* Zone *******************************************************************************
          // Ajouter Zone
     
     public boolean zoneaSave(Zone z) throws SQLException,Exception{
         if (!Objects.isNull(z)){
        Statement stmt = con.createStatement();
        String query=" insert into zone (lib_zone,latitude,longitude) values ('"
             +z.getLib_zone()+
             "',"+z.getLatitude()+
             ","+z.getLongitude()+")";
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
        }
         else {return false;}
     }
               // Rechercher Par Id
     
     public Zone zoneFindByID(Long idzone) throws SQLException,Exception{
         Zone z =new Zone();
         if (!Objects.isNull(idzone)){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM zone WHERE id_zone="+idzone+" LIMIT 1");
            while (rs.next()) {
                z.setId_zone(rs.getLong("id_zone"));
                z.setLib_zone(rs.getString("lib_zone"));
                z.setLatitude(rs.getDouble("latitude"));
                z.setLongitude(rs.getDouble("longitude"));}}
        return z;
     }
     
     // Rechercher Par Lib
     public Zone zoneFindByLib(String libzone) throws SQLException,Exception{
         Zone z= new Zone();
         if (!Objects.isNull(libzone)){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM zone WHERE  UPPER(lib_zone) like UPPER('%"+libzone+"%')  LIMIT 1");
            while (rs.next()) {
                z.setId_zone(rs.getLong("id_zone"));
                z.setLib_zone(rs.getString("lib_zone"));
                z.setLatitude(rs.getDouble("latitude"));
                z.setLongitude(rs.getDouble("longitude"));
            }}
        return z;
     }
     // Rechercher all
     public LinkedList<Zone> zoneGetAll() throws SQLException,Exception{
        LinkedList<Zone> zones= new  LinkedList<> ();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM zone");
            while (rs.next()) {
                Zone z =new Zone();
                z.setId_zone(rs.getLong("id_zone"));
                z.setLib_zone(rs.getString("lib_zone"));
                z.setLatitude(rs.getDouble("latitude"));
                z.setLongitude(rs.getDouble("longitude"));
                zones.add(z);}
        return zones;
     }
     
     // delete Zone
     public void zoneDelete(String libele) throws SQLException{
        Statement stmt = con.createStatement();
        Long idZone=null;
        String query = "select zone.id_zone from compteur , zone where compteur.id_zone=zone.id_zone and zone.lib_zone='"+libele+"' LIMIT 1";
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            idZone=rs.getLong("zone.id_zone");
        }
        if(Objects.isNull(idZone)){
         query=" DELETE FROM zone WHERE lib_zone ='"+libele+"' ";
          int nbUpdated = stmt.executeUpdate(query);
          System.out.println("LA SUPPRESSION BIEN EFFECTUEE \n");
        }
        else{
            System.out.println("ATTENTION ! VOUS NE POUVEZ PAS SUPPRIMER UNE ZONE SE REFERENCE DANS AUTRES LIENS \n");
        }
       
     }
// ******************************************************* Client *******************************************************************************
     
    // Ajouter Client
     
     public boolean clientSave(Client c) throws SQLException,Exception{
         if (!Objects.isNull(c)){
        Statement stmt = con.createStatement();
        String query=" insert into client (nom,prenom,genre,cin,nom_utilisateurs,motPasse,dateNaissance,tel,adresse,isActive,num_abonnement) values ('"
                +c.getNom()+
             "','"+c.getPrenom()+
             "','"+c.getGenre()+
             "','"+c.getcIN()+
             "','"+c.getNom_utilisateurs()+
             "','"+c.getMotPasse()+
             "','"+c.getDateNaissance()+
             "','"+c.getTel()+
             "','"+c.getAdresse()+
             "',"+c.getIsActive()+
             ",'"+c.getNum_abonnement()+"')";
        
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;}
         else {return false;}
     }
         // Rechercher ALL Clients  
     
     public List<Client> clientGetAll() throws SQLException{
         List<Client> clients = new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM client");
            while (rs.next()) {
                Client client = new Client();
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
                client.setNum_abonnement(rs.getString("num_abonnement"));
                clients.add(client);}
        return clients;
     }
     
     // GET CLIENTS BY ZONE
     public List<Client> clientByZone(String zoneNom) throws SQLException{
         List<Client> clients = new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT client.* FROM client,compteur,zone where client.userID=compteur.userid AND compteur.id_zone=zone.id_zone and upper(zone.lib_zone) = upper('"+zoneNom+"')");
            while (rs.next()) {
                Client client = new Client();
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
                client.setNum_abonnement(rs.getString("num_abonnement"));
                clients.add(client);}
        return clients;
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
     
     // Rechercher Client PAR Numéro ABONNEMENT
     
     public Client clientFindByNumAbonemment(String numAbonnement) throws SQLException,Exception{
         Client client = new Client();
        if (!Objects.isNull(numAbonnement)){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE num_abonnement='"+numAbonnement+"' LIMIT 1");
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
      // Rechercher Client PAR CINE
     
     public Client clientFindByCINE(String cine) throws SQLException,Exception{
         Client client = new Client();
        if (!Objects.isNull(cine)){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE cIN='"+cine+"' LIMIT 1");
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
     
   //UPDATE `client` 
     public Boolean clientUpdate(Client clientActual,Client clientNouveau) throws SQLException{
         if(clientActual.getcIN()==null){
             return false;
         }
          
         if(clientNouveau.getNom().length()>0){
             clientActual.setNom(clientNouveau.getNom());}
         
         if(clientNouveau.getPrenom().length()>0){
             clientActual.setPrenom(clientNouveau.getPrenom());}
         
         if(clientNouveau.getGenre().toString().length()>0){
             clientActual.setGenre(clientNouveau.getGenre());}       
         
         if(clientNouveau.getDateNaissance()!=null){
             clientActual.setDateNaissance(clientNouveau.getDateNaissance());}        
         
         if(clientNouveau.getNom_utilisateurs().length()>0){
             clientActual.setNom_utilisateurs(clientNouveau.getNom_utilisateurs());}
         
         if(clientNouveau.getMotPasse().length()>0){
             clientActual.setMotPasse(clientNouveau.getMotPasse());}         
         
         if(clientNouveau.getTel().length()>0){
             clientActual.setTel(clientNouveau.getTel());}
         
         if(clientNouveau.getAdresse().length()>0){
             clientActual.setAdresse(clientNouveau.getAdresse());}
       
         if(clientNouveau.getIsActive().toString().length()>0){
             clientActual.setIsActive(clientNouveau.getIsActive());}
         
         
        String  query=" UPDATE client "+
             " SET nom='"+clientActual.getNom()+
             "' , prenom='"+clientActual.getPrenom()+
             "' , genre='"+clientActual.getGenre()+
             "' , nom_utilisateurs='"+clientActual.getNom_utilisateurs()+
             "' , motPasse='"+clientActual.getMotPasse()+
             "' , dateNaissance='"+clientActual.getDateNaissance()+
             "' , tel='"+clientActual.getTel()+
             "' , adresse='"+clientActual.getAdresse()+
             "' , isActive="+clientActual.getIsActive()+
             " WHERE cIN like '"+clientActual.getcIN()+"' ";
        
        
        Statement stmt = con.createStatement();
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
     }
     
     // supprimer client
     public void clientDelete(String cine) throws SQLException{
         Statement stmt = con.createStatement();
        Long userID=null;
        String query = "select client.userID from compteur , client WHERE client.userID=compteur.userid and client.cIN = '"+cine+"' LIMIT 1";
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            userID=rs.getLong("client.userID");
        }
        if(Objects.isNull(userID)){
         query=" DELETE FROM client WHERE client.cIN = '"+cine+"' ";
         int a =stmt.executeUpdate(query);
         if(a>0){
         System.out.println("LA SUPPRESSION BIEN EFFECTUEE \n");}
         else{System.out.println("AUCUNE SUPPRESSION N'EST EFEECTUEE \n");}
        }
        else{
         System.out.println("ATTENTION ! VOUS NE POUVEZ PAS SUPPRIMER UN CLIENT QUI SE REFERENCE DANS AUTRES LIENS \n");}
         
     }
 // ******************************************************* Compteur *******************************************************************************    
     // Ajouter Compteur
     
     public boolean compteurSave(Compteur c) throws SQLException{
         if (!Objects.isNull(c)){
        Statement stmt = con.createStatement();
        String query=" insert into compteur ( code, marque, id_zone, userid,isActive,dateMiseEnOeuvre) values ('"
                +c.getCode()+
             "','"+c.getMarque()+
             "',"+c.getZone().getId_zone()+
             ","+c.getClient().getId_User()+
             ","+c.getIsActive()+
             ",'"+c.getDateMiseEnOeuvre()+"')";
        
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
         }
         else {return false;}
     }
     // Compteurs get All
     public List<Compteur> compteurGetAll() throws SQLException{
        List<Compteur> compteurs = new ArrayList<>();
        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT compteur.idcompteur,compteur.code,compteur.marque,compteur.dateMiseEnOeuvre,compteur.isActive,zone.lib_zone,zone.latitude,zone.longitude,zone.id_zone,client.userID,client.nom,client.prenom,client.genre,client.cIN,client.nom_utilisateurs,client.motPasse,client.dateNaissance,client.tel,client.adresse,client.isActive,client.num_abonnement FROM compteur,zone,client WHERE compteur.id_zone=zone.id_zone and client.userID=compteur.userid");
            while (rs.next()) {
                Compteur compteur = new Compteur();
                Zone zone = new Zone();
                Client client = new Client ();
                //  Compteur
                compteur.setIdCompteur(rs.getLong("compteur.idcompteur"));
                compteur.setCode(rs.getString("compteur.code"));
                compteur.setMarque(rs.getString("compteur.marque"));
                compteur.setDateMiseEnOeuvre(rs.getDate("dateMiseEnOeuvre"));
                compteur.setIsActive(rs.getBoolean("compteur.isActive"));
                // ZONE
                zone.setId_zone(rs.getLong("zone.id_zone"));
                zone.setLib_zone(rs.getString("zone.lib_zone"));
                //zone.setLatitude(rs.getDouble("zone.latitude"));
                //zone.setLongitude(rs.getDouble("zone.longitude"));
                // Client 
                client.setId_User(rs.getLong("client.userID"));
                client.setNom(rs.getString("client.nom"));
                client.setPrenom(rs.getString("client.prenom"));
                client.setcIN(rs.getString("client.cIN"));
                client.setNum_abonnement(rs.getString("client.num_abonnement"));
                client.setIsActive(rs.getBoolean("client.isActive"));
                
                compteur.setClient(client);
                compteur.setZone(zone);
                compteurs.add(compteur);}
        return compteurs;
         
     }
     
     // Compteurs get By Client
     public List<Compteur> compteurGetByClient(String cine) throws SQLException{
        List<Compteur> compteurs = new ArrayList<>();
        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT compteur.idcompteur,compteur.code,compteur.marque,compteur.dateMiseEnOeuvre,compteur.isActive,zone.lib_zone,zone.latitude,zone.longitude,zone.id_zone,client.userID,client.nom,client.prenom,client.genre,client.cIN,client.nom_utilisateurs,client.motPasse,client.dateNaissance,client.tel,client.adresse,client.isActive,client.num_abonnement FROM compteur,zone,client WHERE compteur.id_zone=zone.id_zone and client.userID=compteur.userid and client.cIN='"+cine+"'");
            while (rs.next()) {
                Compteur compteur = new Compteur();
                Zone zone = new Zone();
                Client client = new Client ();
                //  Compteur
                compteur.setIdCompteur(rs.getLong("compteur.idcompteur"));
                compteur.setCode(rs.getString("compteur.code"));
                compteur.setMarque(rs.getString("compteur.marque"));
                compteur.setDateMiseEnOeuvre(rs.getDate("dateMiseEnOeuvre"));
                compteur.setIsActive(rs.getBoolean("compteur.isActive"));
                // ZONE
                zone.setId_zone(rs.getLong("zone.id_zone"));
                zone.setLib_zone(rs.getString("zone.lib_zone"));
                //zone.setLatitude(rs.getDouble("zone.latitude"));
                //zone.setLongitude(rs.getDouble("zone.longitude"));
                // Client 
                client.setId_User(rs.getLong("client.userID"));
                client.setNom(rs.getString("client.nom"));
                client.setPrenom(rs.getString("client.prenom"));
                client.setcIN(rs.getString("client.cIN"));
                client.setNum_abonnement(rs.getString("client.num_abonnement"));
                client.setIsActive(rs.getBoolean("client.isActive"));
                
                compteur.setClient(client);
                compteur.setZone(zone);
                compteurs.add(compteur);}
        return compteurs;
         
     }
            // Compteur get By Code
     public Compteur compteurGetByCode(String codeCompteur) throws SQLException{
        Statement stmt = con.createStatement();
        Compteur compteur = new Compteur();
            ResultSet rs = stmt.executeQuery("SELECT compteur.idcompteur,compteur.code,compteur.marque,compteur.dateMiseEnOeuvre,compteur.isActive,zone.lib_zone,zone.latitude,zone.longitude,zone.id_zone,client.userID,client.nom,client.prenom,client.genre,client.cIN,client.nom_utilisateurs,client.motPasse,client.dateNaissance,client.tel,client.adresse,client.isActive,client.num_abonnement FROM compteur,zone,client WHERE compteur.id_zone=zone.id_zone and client.userID=compteur.userid and compteur.code='"+codeCompteur+"'");
            while (rs.next()) {
                Zone zone = new Zone();
                Client client = new Client ();
                //  Compteur
                compteur.setIdCompteur(rs.getLong("compteur.idcompteur"));
                compteur.setCode(rs.getString("compteur.code"));
                compteur.setMarque(rs.getString("compteur.marque"));
                compteur.setDateMiseEnOeuvre(rs.getDate("dateMiseEnOeuvre"));
                compteur.setIsActive(rs.getBoolean("compteur.isActive"));
                // ZONE
                zone.setId_zone(rs.getLong("zone.id_zone"));
                zone.setLib_zone(rs.getString("zone.lib_zone"));
                //zone.setLatitude(rs.getDouble("zone.latitude"));
                //zone.setLongitude(rs.getDouble("zone.longitude"));
                // Client 
                client.setId_User(rs.getLong("client.userID"));
                client.setNom(rs.getString("client.nom"));
                client.setPrenom(rs.getString("client.prenom"));
                client.setcIN(rs.getString("client.cIN"));
                client.setNum_abonnement(rs.getString("client.num_abonnement"));
                client.setIsActive(rs.getBoolean("client.isActive"));
                
                compteur.setClient(client);
                compteur.setZone(zone);}
        return compteur;
         
     }
     
     
       // Modifier Compteur
     
     public boolean compteurUpdate(Compteur compteurActuel, Compteur compteurNouveeau) throws SQLException{
         if (Objects.isNull(compteurActuel.getCode())){
             System.out.println(" ATTENTION !!! CODE DU COMPTEUR NE PEUT PAS ETRE NULL ");
             return false;}
         
         if(compteurNouveeau.getMarque().length()>0){
             compteurActuel.setMarque(compteurNouveeau.getMarque());}
         
         if(compteurNouveeau.getDateMiseEnOeuvre()!=null){
             compteurActuel.setDateMiseEnOeuvre(compteurNouveeau.getDateMiseEnOeuvre());}
         
         if(compteurNouveeau.getIsActive()!=null){
             compteurActuel.setIsActive(compteurNouveeau.getIsActive());}
         
        Statement stmt = con.createStatement();
        String query=" UPDATE compteur SET marque='"
                +compteurActuel.getMarque()+
             "',dateMiseEnOeuvre ='"+
                compteurActuel.getDateMiseEnOeuvre()+
             "',isActive="+compteurActuel.getIsActive()+
             " WHERE code='"+compteurActuel.getCode()+"'";
        
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
     }
     
     
     // SET Compteur
     
     public boolean compteurActivation(Compteur compteur) throws SQLException{
         if (Objects.isNull(compteur.getCode())){
             System.out.println(" ATTENTION !!! CODE DU COMPTEUR NE PEUT PAS ETRE NULL ");
             return false;}
         
        Statement stmt = con.createStatement();
        String query=" UPDATE compteur SET isActive="+(!compteur.getIsActive())+" WHERE code='"+compteur.getCode()+"'";
         System.out.println(query);
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
     }
     
     
// ******************************************************* PAIEMENT *******************************************************************************
     
     public List<Paiement> getFacturesUnpaidByClient(String identifiantClient) throws SQLException{
         List<Paiement> paiements = new ArrayList<>();
         if(identifiantClient==null){
             System.out.println(" ATTENTION !!! IDENTIFIANT DU CLIENT EST NULL ");
             return paiements;
         }
        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vue_consom_nonpaye WHERE UPPER(IdentifiantClient) like UPPER('%"+identifiantClient+"%')");
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
     
     public List<Paiement> getAllFacturesUnpaid() throws SQLException{
         List<Paiement> paiements = new ArrayList<>();
        
        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vue_consom_nonpaye");
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
     
          public List<Paiement> getAllFacturesPaid() throws SQLException{
         List<Paiement> paiements = new ArrayList<>();
        
        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vue_consom_paye");
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
     
    public Boolean paiementFacture(Paiement paiement) throws SQLException{       
        // tarif,idcompteur,moins,annee
        
            String query = "{call new_paiement(?,?,?,?)}";
            CallableStatement statement = con.prepareCall(query);
            statement.setDouble(1, paiement.getTarif());
            statement.setLong(2,paiement.getCompteur().getIdCompteur());
            statement.setInt(3, paiement.getMoisPaye());
            statement.setInt(4, paiement.getAnneePaye());
           statement.execute();
           return true;
       
    }
     
    
}
