/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compteur.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class ShareData extends TimerTask{
    
    public final static String HOTE = "127.0.0.1" ;
    public final static int PORT = 1000;
    public final static String CODE = "Z1000200" ;
    
    public String  getRandomConsumation(){
        double indexEau=0;
        double indexElec=0;
        
        
        Random random = new Random();
        for (int n = 0; n < 1; n++) {
            // EAU
            indexEau = random.nextInt(9999) / 100.0;
              // ELECTRICITE
            indexElec = random.nextInt(9999) / 100.0;
        }

        
        
        return indexEau+"-"+indexElec;
    }

    @Override
    public void run() {
        try {
            System.out.println("*********************************************");
            System.out.println("DEBUT ");
            Socket soc= new Socket(HOTE,PORT);
            OutputStream flux2 = soc.getOutputStream() ; 
            OutputStreamWriter sortie = new OutputStreamWriter (flux2);
            sortie.write(CODE+"\n");
            sortie.flush(); // pour forcer l'envoi de la ligne  sortie.write("458.587-245.687"+"\n"); 
            System.out.println("SEND VALUE ");
            sortie.write(getRandomConsumation()+"\n"); 
            sortie.flush(); // pour forcer l'envoi de la ligne 
            soc.close();
            System.out.println("STOP ");
            System.out.println("*********************************************");
        } catch (IOException ex) {
            Logger.getLogger(ShareData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
