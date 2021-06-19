/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telecompteursrv.config;

import com.telecompteursrv.service.CompteurService;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class MainThread extends Thread{

    @Override
    public void run() {
         int port=1000;
        ServerSocket sersoc ; 
        try {
            sersoc = new ServerSocket (port);
        
        System.out.println ("serveur active sur port " + port) ; 
        while (true) { 
            Socket soc = sersoc.accept(); 
            CompteurService compteurDetails = new CompteurService(soc);
            compteurDetails.start();
        }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
