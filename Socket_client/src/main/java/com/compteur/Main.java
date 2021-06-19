package com.compteur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */

import com.compteur.service.TimerCompteur;
import java.io.* ;
import java.util.Calendar;
public class Main { 
    

    public static void main (String args[]) throws IOException 
    { 
        Calendar date = Calendar.getInstance();
 
        TimerCompteur timerCompteur=new TimerCompteur();
 
        date.set(Calendar.HOUR, 7);
        date.set(Calendar.MINUTE,0 );
            date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        timerCompteur.scheduleConsommationSend(date, 24);
        
    }
    
}