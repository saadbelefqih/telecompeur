/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telecompteursrv.service;

import com.telecompteursrv.config.Cnx_DB;
import com.telecompteursrv.model.CompteurDetails;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class CompteurService extends Thread{
    
    Socket soc;
    Connection con;
    public  CompteurService(Socket socketCompteurDetails) throws SQLException{
        this.soc=socketCompteurDetails;
        con = DriverManager.getConnection(Cnx_DB.db_URL,
            Cnx_DB.username,
            Cnx_DB.passwrd);
    }
    
    
    // chercher Compteur par Code Si existe Return ID 
    public Long findCompteurByCode(String code) throws SQLException{
        Long idCompteur= null;
       if (!Objects.isNull(code)){
       Statement stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery("SELECT idcompteur FROM compteur WHERE code= '"+code+"' and isActive is true ");
       while (rs.next()) {idCompteur=rs.getLong("idcompteur");}}
       return idCompteur;
       
    }
    
     // Save Compteur Details 
    //INSERT INT `indexEauNew`, `indexElectNew`, `indexEauOld`, `indexElectOld`, `DateDernierMAJ`, `idcompteur`) VALUES (NULL, '', '', '', '', '', '') 
    public void saveCompteurDetails(CompteurDetails compteurDetails,Long idCompteur) throws SQLException{
        
       if (!Objects.isNull(compteurDetails)){
           
       String query=" insert into compteurdetails (indexEauNew,indexElectNew,DateDernierMAJ,idcompteur) values (?,?,?,?)";
       PreparedStatement preparedStatement =  con.prepareStatement(query);
       preparedStatement.setDouble(1, compteurDetails.getIndexEauNew());
       preparedStatement.setDouble(2, compteurDetails.getIndexElectNew());
       preparedStatement.setDate(3, (Date) compteurDetails.getDateDernierMAJ());
       preparedStatement.setDouble(4, idCompteur);
       preparedStatement.executeUpdate();
    }
    }

    @Override
    public void run() {
            try {
             
            // Declare Flux 
            
            InputStream flux1 = soc.getInputStream();
            BufferedReader entree = new BufferedReader (new InputStreamReader (flux1)) ;
            OutputStream flux2 = soc.getOutputStream() ;
            OutputStreamWriter sortie = new OutputStreamWriter (flux2) ;
            
            // identification
            
            //System.out.println("Identification en cours ..... ");
            String codeSended = entree.readLine();
            
            // If IDentification isn't done destroy socket and InputStream
            Long idCompt =findCompteurByCode(codeSended);
            //System.out.println("*** codeSended *** "+codeSended);
            //System.out.println("*** findCompteurByCode(codeSended) *** "+findCompteurByCode(codeSended));
            //System.out.println("*** idCompt *** "+idCompt);
            if(Objects.isNull(idCompt)){
                //System.out.println("*** Echec Identification ***");
                soc.close();
                 flux1.close();
                flux2.close();
            }
            else{
            
            //System.out.println("--- Succes Identification ---");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        
        java.util.Date dateStr = formatter.parse(LocalDate.now().toString());
        java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());

                // format is 14,254;8272;
                String indexEauAndElec = entree.readLine();
                Double indexEau = Double.parseDouble(indexEauAndElec.substring(0, indexEauAndElec.indexOf('-')));
                Double indexElec = Double.parseDouble(indexEauAndElec.substring(indexEauAndElec.indexOf('-')+1, indexEauAndElec.length()));
                //System.out.println("--- Double indexEau ---> "+indexEau);
                //System.out.println("--- Double indexElec ---> "+indexElec);
                saveCompteurDetails(new CompteurDetails(indexEau,indexElec,dateDB,null,null),idCompt);
            
            soc.close();
             flux1.close();
                flux2.close();
            }
        } catch (IOException | SQLException | ParseException ex) {
            Logger.getLogger(CompteurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
             
        }
    
    
}
