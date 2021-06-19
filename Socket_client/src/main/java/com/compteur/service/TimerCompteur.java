/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compteur.service;

import java.util.Calendar;
import java.util.Timer;

/**
 *
 * @author USER
 */
public class TimerCompteur {
     Timer timer=new Timer();
 
    public void scheduleConsommationSend(Calendar date, int period) {
        System.out.println("COMMUNICATION ACTIVEE ****************");
             timer.scheduleAtFixedRate( new ShareData(), date.getTime(), 1000 * 60 *60 * period); 
       }
}
