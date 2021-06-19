/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telecompteursrv.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author USER
 */
public class Utils {
    
    private String yyyyMmDd;
    
     public java.sql.Date formatterDate(String yyyyMmDd) throws ParseException{
        // formater Date  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        java.util.Date dateStr = formatter.parse(yyyyMmDd);
        java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
        return dateDB;
    }
     
    
}
