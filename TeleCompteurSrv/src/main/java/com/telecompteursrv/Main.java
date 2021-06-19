/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telecompteursrv;

import com.telecompteursrv.config.Genre;
import com.telecompteursrv.config.MainThread;
import com.telecompteursrv.model.Client;
import com.telecompteursrv.model.Compteur;
import com.telecompteursrv.model.Paiement;
import com.telecompteursrv.model.Zone;
import com.telecompteursrv.service.ClientService;
import com.telecompteursrv.service.ResponsableService;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Main {
  
     
    static java.sql.Date formatterDate(String yyyymmdd) throws ParseException{
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateStr1 = formatter.parse(yyyymmdd);
        return new java.sql.Date(dateStr1.getTime());
    }
     
    /*
    MENU PRINCIPALE CLIENT
    
    */
          static void menuClient(ClientService cs) throws Exception{
              Scanner inputClient=new Scanner(System.in);
              
              System.out.println("VOUS ETES SUR MENU CLIENT \n");
              System.out.println("*** AUTHENTIFICATION ***\n");
              System.out.println("--> ETAPE 1 : ENTREZ LOGIN : ");
              String login=inputClient.nextLine();
              System.out.println("--> ETAPE 2 : ENTREZ MOT DE PASSE : ");
              String password=inputClient.nextLine();
              Long userId=cs.authenficiation(login, password);
              if(userId==null){
                  System.out.println("LOGIN OU MOT DE PASSE INCORRECT");
              }
              
              else{
                    boolean exitClient=false;
                    System.out.println("AUTHENTIFICATION REUSSIE");
                    System.out.println("BIENVENUE .....");
                    do
                    {
                     System.out.println("\n \n \n \n \n \n \n \n \n \n");
                      System.out.println("1.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MES CONSOMMATIONS %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                      System.out.println("2.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MES FACTURES NON-PAYÉES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                      System.out.println("3.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MES FACTURES PAYÉES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                      System.out.println("4.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MES COMPTEURS %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                      System.out.println("5.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% PARAMETRAGES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                      System.out.println("6.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% QUITER %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                      System.out.println("CHOISIR UN!");
                      Scanner sd=new Scanner(System.in);
                      System.out.println("ENTREZ VOTRE CHOIX");
                      int num=sd.nextInt();

                        switch(num){
                            case 1:
                                System.out.println("1.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MES CONSOMMATIONS %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                                System.out.println("--> ETAPE 1 : ENTREZ LE MOIS EN CHIFFRE [1-12] ''EXP ENTREZ CHIIFRE [5] POUR DIRE LE MOIS MAI:'' ");
                                int mois=Integer.parseInt(inputClient.nextLine());
                                System.out.println("--> ETAPE 2 : ENTREZ MOT DE PASSE : ");
                                int annee=Integer.parseInt(inputClient.nextLine());
                                cs.getDetailsConsommation(userId,mois,annee).forEach(d->{System.out.println(d.toString());});
                                break;
                            case 2:
                                System.out.println("2.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MES FACTURES NON-PAYÉES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                                cs.getAllFacturesUnpaid(userId).forEach(f->{System.out.println(f.toString());});
                                break;
                            case 3:
                                System.out.println("3.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MES FACTURES PAYÉES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                                cs.getAllFacturesPaid(userId).forEach(f->{System.out.println(f.toString());});
                                break;
                            case 4:
                                System.out.println("4.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MES COMPTEURS  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                                cs.afficherCompteursByClient(userId).forEach(c->{System.out.println(c.toString());});
                                break;
                            case 5:
                                 System.out.println("5.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% PARAMETRAGES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                                 System.out.println("1.*** %%%% MODIFIER MOT DE PASSE");
                                 System.out.println("2.*** %%%% CONSULTER MON COMPTE");
                                 int choixParamClient=Integer.parseInt(inputClient.nextLine());
                                 switch(choixParamClient){
                                     case 1:
                                         System.out.println("1.*** %%%% MODIFIER MOT DE PASSE");
                                         System.out.println("1.*** %%%% ETAPE 1 :---> ENTREZ ANCIAN MOT DE PASSE ");
                                         String passwordOld=inputClient.nextLine();
                                         System.out.println("2.*** %%%% ETAPE 2 :---> ENTREZ NOUVEAU MOT DE PASSE ");
                                         String passwordNew=inputClient.nextLine();
                                         System.out.println("3.*** %%%% ETAPE 3 :---> RETAPEZ NOUVEAU MOT DE PASSE ");
                                         String passwordConfirmation=inputClient.nextLine();
                                         if(!passwordNew.equals(passwordConfirmation)){
                                             System.out.println("4.*** %%%% ERROR :---> MOT DE PASSE DE CONFIRMATION EST NON-IDENTIQUE ");
                                             break;}
                                         if(cs.resetPassword(userId,passwordOld,passwordNew)){
                                             System.out.println("*** %%%% MOT DE PASSE EST BIEN MODIFIE ---> ");
                                             exitClient=true;
                                         }
                                         else{
                                             System.out.println("*** %%%% ANCIAN MOT DE PASSE EST INCORRECT ---> ");
                                         }
                                         break;
                                     case 2:
                                         System.out.println("2.*** %%%% CONSULTER MON COMPTE");
                                         System.out.println(cs.clientFindByID(userId).toString());
                                         break;
                                 }
                                 break;
                            case 6:
                                 exitClient=true;
                                 break;

                        }
                    }while(!exitClient);
              }
     }
    
//                                                              MENUS RESPONSABLE + SOUS MENS
    //DEBUT
          
          /*
          
          
          MENU RESPONSABLE PRINCIPALE
          
          */
          
       static void menuResponsable(ResponsableService rs) throws Exception{
           Scanner inputRespo=new Scanner(System.in);
                         System.out.println("VOUS ETES SUR MENU CLIENT \n");
              System.out.println("*** AUTHENTIFICATION ***\n");
              System.out.println("--> ETAPE 1 : ENTREZ LOGIN : ");
              String login=inputRespo.nextLine();
              System.out.println("--> ETAPE 2 : ENTREZ MOT DE PASSE : ");
              String password=inputRespo.nextLine();
              Long userId=rs.authenficiation(login, password);
              if(userId==null){
                  System.out.println("LOGIN OU MOT DE PASSE INCORRECT");
              }
              else{
                boolean exitResponsable=false;

               do
               {
                System.out.println("\n \n \n \n \n \n \n \n \n \n");
                 System.out.println("1.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TABLEAU DE BORD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                 System.out.println("2.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% SERVICE CLIENT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                 System.out.println("3.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% PARAMETRAGES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                 System.out.println("4.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% QUITER %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
                 System.out.println("CHOISIR UN!");
                 Scanner sd=new Scanner(System.in);
                 System.out.println("ENTREZ VOTRE CHOIX");
                 int num=sd.nextInt();

                   switch(num){
                       case 1:
                           dashBoard(rs);
                           break;
                       case 2:
                           serviceClient(rs);
                           break;
                       case 3:
                           parametrages(rs);
                           break;
                       case 4:
                            exitResponsable=true;
                           break;

                   }
               }while(!exitResponsable);
       }
     }
           /*
          
          
          SOUS MENU RESPONSABLE PARAMETRAGES
          
          */
     static void parametrages(ResponsableService rs) throws Exception{
              boolean exitZone=false;
              Scanner inputZone=new Scanner(System.in);
              do{
                System.out.println("********************************************************************** GESTION DES ZONES **********************************************************************\n");
                System.out.println("1.*** AJOUTER UNE ZONE ***");
                System.out.println("2.*** AFFICHER LA LISTE DES ZONES ***");
                System.out.println("3.*** RECHERCHER PAR LIBELLE ***");
                System.out.println("4.*** SUPPRIMER ZONE ***");
                System.out.println("5.*** QUITTER LA GESTION DES ZONES ***");
                System.out.println("CHOISIR UN!");
                Scanner sd=new Scanner(System.in);
                System.out.println("ENTREZ VOTRE CHOIX");
                int numZone=sd.nextInt();
                switch(numZone)
                {
                    case 1:
                        System.out.println("1.*** AJOUTER UNE ZONE ***");
                        System.out.println("--> ETAPE 1 : ENTREZ INTITULE DE LA ZONE");
                        
                        String intitule=inputZone.nextLine();
                        System.out.println("--> ETAPE 2 : ENTREZ LATITUDE DE LA ZONE");
                        Double lat=Double.parseDouble(inputZone.nextLine());
                        System.out.println("--> ETAPE 3 : ENTREZ LONGITUDE DE LA ZONE");
                        Double lon=Double.parseDouble(inputZone.nextLine());
                        if(rs.zoneaSave(new Zone (null,intitule,lat,lon))){
                            System.out.println("L'AJOUT BIEN EFFECTUEE \n");}
                        else{System.out.println("ÉCHEC OPERATION  \n");}
                        break;
                    case 2:
                        System.out.println("2.*** AFFICHER LA LISTE DES ZONES ***");
                        rs.zoneGetAll().forEach(z->{System.out.println(z.toString());});
                        System.out.println("\n");
                        break;
                    case 3:
                        System.out.println("3.*** RECHERCHER PAR LIBELLE\n ***");
                        System.out.println("--> ENTREZ LIBELLE DE LA ZONE");
                        String libel=inputZone.nextLine();
                        System.out.println(rs.zoneFindByLib(libel));
                        System.out.println("\n");
                        break;
                    case 4:
                        System.out.println("4.*** SUPPRIMER ZONE ***");
                        System.out.println("--> ENTREZ LIBELLE DE LA ZONE");
                        String libelDelete=inputZone.nextLine();
                        rs.zoneDelete(libelDelete);
                        break;
                    case 5:
                        exitZone=true;
                        break;
                }

              }while(!exitZone);
      }
     
           /*
          
          
          SOUS MENU RESPONSABLE  SERVICE CLIENT
          
          */
     
    static void serviceClient (ResponsableService rs) throws Exception{
        boolean exitCompteur=false;
        Scanner inputClient=new Scanner(System.in);           
        do{
            System.out.println("********************************************************************** SERVICE CLIENT **********************************************************************\n");
            System.out.println("|=================================================> I-GESTIONS CLIENTS <=================================================|");
            System.out.println("1.|**** AJOUTER UN NOUVEAU CLIENT ****");
            System.out.println("2.|**** AFFICHER LA LISTE DES CLIENT ****");
            System.out.println("3.|**** AFFICHER LA LISTE DES CLIENTS PAR ZONE ****");
            System.out.println("4.|**** RECHERCHER PAR NUMERO ABONNEMENT ****");
            System.out.println("5.|**** RECHERCHER PAR CINE ****");
            System.out.println("6.|**** MODIFIER CLIENT ****");
            System.out.println("7.|**** SUPPRIMER UN CLIENT ****");     
            System.out.println("|=================================================> II-GESTIONS COMPTEURS <=================================================|");
            System.out.println("8.|**** AJOUTER UN NOUVEAU COMPTEUR ****");
            System.out.println("9.|**** AFFICHER LA LISTE DES COMPTEURS ****");
            System.out.println("10|**** AFFICHER LA LISTE DES COMPTEURS PAR CLIENT ****");
            System.out.println("11|**** RECHERCHER UN COMPTEUR PAR CODE ****");
            System.out.println("12|**** MODIFIER COMPTEUR ****");
            System.out.println("13|**** DESACTIVER/ACTIVER UN COMPTEUR ****");
            System.out.println("|=================================================> III-AUTRES <=================================================|");
            System.out.println("14|**** QUITTER SERVICE CLIENT ****");
            System.out.println("CHOISIR UN!");
            Scanner sd=new Scanner(System.in);
            System.out.println("ENTREZ VOTRE CHOIX");
            int numZone=sd.nextInt();
                switch(numZone)
                {
                    case 1:
                        System.out.println("1.*** AJOUTER UN NOUVEAU CLIENT  ***");
                        
                        System.out.println("--> ETAPE 1 : ENTREZ NOM DU NOUVEAU CLIENT");
                        String nomNVClient=inputClient.nextLine();
                        
                        System.out.println("--> ETAPE 2 : ENTREZ PRENOM DU NOUVEAU CLIENT");
                        String prenomNVClient=inputClient.nextLine();
                        
                        System.out.println("--> ETAPE 3 : ENTREZ GENRE DU NOUVEAU CLIENT : 'Homme/Femme'");
                        String gClientNv=inputClient.nextLine();
                        Genre genreNVClient=Genre.valueOf("Homme");
                        if(gClientNv.matches("Homme") || gClientNv.matches("Femme")){
                           genreNVClient=Genre.valueOf(gClientNv);}
                        
                        System.out.println("--> ETAPE 4 : ENTREZ CINE DU NOUVEAU CLIENT");
                        String cinNVClient=inputClient.nextLine();
                        
                        System.out.println("--> ETAPE 5 : ENTREZ DATE DE NAISSANCE DU NOUVEAU CLIENT EN RESPECTANT LE FORMAT : 'YYYY-MM-DD' ");
                        java.sql.Date dateNaisNVClient=formatterDate(LocalDate.now().getYear()+"-"+LocalDate.now().getMonthValue()+"-"+LocalDate.now().getDayOfMonth());
                         String dateCompteur=inputClient.nextLine();
                        if (dateCompteur.matches("\\d{4}-\\d{2}-\\d{2}")) {
                         dateNaisNVClient=formatterDate(dateCompteur);
                        }
                        
                        System.out.println("--> ETAPE 6 : ENTREZ NUMERO DE TELEPHONE DU NOUVEAU CLIENT");
                        String telNVClient=inputClient.nextLine();
                        
                        System.out.println("--> ETAPE 7 : ENTREZ ADRESSE DU NOUVEAU CLIENT");
                        String adresseNVClient=inputClient.nextLine();
                        
                        String nomUtilisateur=prenomNVClient.substring(0, 1).toUpperCase()+"."+nomNVClient.toUpperCase();
                        
                        String numAbonnement=cinNVClient.substring(0, 1).toUpperCase()+System.currentTimeMillis();
                        
                        Client client = new Client(nomNVClient, prenomNVClient, genreNVClient, cinNVClient,nomUtilisateur , cinNVClient, dateNaisNVClient, telNVClient, adresseNVClient, true, numAbonnement);
                        
                        if(rs.clientSave(client)){
                            System.out.println("L'AJOUT BIEN EFFECTUEE \n");
                            System.out.println(client.toString());}
                        else{System.out.println("ÉCHEC OPERATION  \n");}
                        break;
                    case 2:
                        System.out.println("2.*** AFFICHER LA LISTE DES CLIENT ***");
                        rs.clientGetAll().forEach(c->{System.out.println(c.toString());});
                        System.out.println("\n");
                        break;
                    case 3:
                        System.out.println("3.**** AFFICHER LA LISTE DES CLIENTS PAR ZONE ****");
                        System.out.println("--> ENTREZ LIBELLE DE LA ZONE");
                        String libelZone=inputClient.nextLine();
                        rs.clientByZone(libelZone).forEach(c->{System.out.println(c.toString());});
                        System.out.println("\n");
                        break;
                    case 4:
                        System.out.println("4.**** RECHERCHER PAR NUMERO ABONNEMENT ****");
                        System.out.println("--> ENTREZ NUMERO ABONNEMENT DU CLIENT ");
                        String numAbo=inputClient.nextLine();
                        if(Objects.nonNull(rs.clientFindByNumAbonemment(numAbo).getNum_abonnement())){
                        System.out.println(rs.clientFindByNumAbonemment(numAbo));}
                        else{System.out.println("CLIENT INTROUVABLE \n");}
                        break;
                    case 5:
                        System.out.println("5.**** RECHERCHER PAR CINE ****");
                        System.out.println("--> ENTREZ CINE DU CLIENT ");
                        String cin=inputClient.nextLine();
                        if(Objects.nonNull(rs.clientFindByCINE(cin).getcIN())){
                        System.out.println(rs.clientFindByCINE(cin));}
                        else{System.out.println("CLIENT INTROUVABLE \n");}
                        break;
                    case 6:
                        System.out.println("6.**** MODIFIER CLIENT ****");
                        System.out.println("--> ENTREZ CINE DU CLIENT QUE VOUS SOUHAITEZ MODIFIER");
                        String cinSearch=inputClient.nextLine();
                        Client clientActualle= rs.clientFindByCINE(cinSearch);
                        if(clientActualle.getcIN()!=null){
                            System.out.println(" LE CLIENT SELECTIONNE EST :");
                            System.out.println(clientActualle.toString());
        
                            System.out.println("SI VOUS SOUHAITEZ GARDER LES VALEURS ACTUELLES IL SUFFIT DE TAPER ENTREZ");
                            
                            System.out.println("--> ETAPE 1 : LA VALEUR ACTUELLE DU NOM EST :"+clientActualle.getNom()+" ENTREZ LE NOUVEAU NOM");
                            String nomUPDATE=inputClient.nextLine();
                            
                            System.out.println("--> ETAPE 2 : LA VALEUR ACTUELLE DU PRENOM EST :"+clientActualle.getPrenom()+" ENTREZ LE NOUVEAU PRENOM");
                            String prenomUpdatet=inputClient.nextLine();
                            
                            System.out.println("--> ETAPE 3 : LA VALEUR ACTUELLE DU GENRE EST :"+clientActualle.getGenre()+" ENTREZ NOUVELLE VALEUR DU GENRE 'Homme/Femme' ");
                            String gClientUpdate =inputClient.nextLine();
                            
                            Genre genreUpdate=Genre.valueOf("Homme");
                            if(gClientUpdate.matches("Homme") || gClientUpdate.matches("Femme")){
                                genreUpdate=Genre.valueOf(gClientUpdate);
                            }
                            
                            System.out.println("--> ETAPE 4 : LA VALEUR ACTUELLE DU DATE NAISSANCE EST : "+clientActualle.getDateNaissance()+" LA NOUVELLE VALEUR RESPECTE LE FORMAT SUIVANT : 'YYYY-MM-DD' ");
                            java.sql.Date dateNaisUpdate=formatterDate(inputClient.nextLine());
                            
                            System.out.println("--> ETAPE 5 : LA VALEUR ACTUELLE DU TELEPHONE EST :"+clientActualle.getTel()+" ENTREZ LE NOUVEAU NUMERO");
                            String telUpdate=inputClient.nextLine();
                            
                            System.out.println("--> ETAPE 6 : LA VALEUR ACTUELLE DU ADRESSE EST :"+clientActualle.getAdresse()+" ENTREZ LA NOUVELLE VALEUR");
                            String adresseUpdate=inputClient.nextLine();
                            
                            System.out.println("--> ETAPE 7 : LA VALEUR ACTUELLE DU NOM D'UTILISATEUR EST :"+clientActualle.getNom_utilisateurs()+" ENTREZ LA NOUVELLE VALEUR");
                            String nomUtilisateurUpdate=inputClient.nextLine();
                            
                            System.out.println("--> ETAPE 8: REINITIALISER MOT DE PASSE  ");
                            String motPasseUpdate=inputClient.nextLine();
                            
                            System.out.println("--> ETAPE 9: ACTIVER/DESACTIVER COMPLTE UTILISATEUR : 0:DESACTIVER/1:ACTIVER");
                            Boolean isActiveUpdate=Boolean.parseBoolean(inputClient.nextLine());
                            
                            
                            Client clientNouveau = new Client(nomUPDATE, prenomUpdatet, genreUpdate, null, nomUtilisateurUpdate, motPasseUpdate, dateNaisUpdate, telUpdate, adresseUpdate, isActiveUpdate, null);
                            
                            if(rs.clientUpdate(clientActualle, clientNouveau)){
                            System.out.println("LA MODIFICATION EST BIEN EFFECTUEE \n");}
                            else{System.out.println("ÉCHEC OPERATION \n");}
                            break;
                        }
                        else{System.out.println("CLIENT INTROUVABLE \n");}
                        break;
                    case 7:
                        System.out.println("7.|**** SUPPRIMER UN CLIENT ****");
                        System.out.println("--> ENTREZ CINE DU CLIENT QUE VOUS SOUHAITEZ SUPPRIMER ");
                        String cinDelete=inputClient.nextLine();
                        if(Objects.nonNull(rs.clientFindByCINE(cinDelete).getcIN())){
                            rs.clientDelete(cinDelete);
                            break;}
                        else{System.out.println("CLIENT INTROUVABLE \n");}
                        break;
                    case 8:
                        System.out.println("8.|**** AJOUTER UN NOUVEAU COMPTEUR ****");
                        
                         System.out.println("--> ETAPE 1 : ENTREZ LE NUMERO DE LA SERIE DU COMPTUR");
                         String codeCompteur=inputClient.nextLine();
                         
                         System.out.println("--> ETAPE 2 : ENTREZ LA MARQUE DU COMPTUR");
                         String marqueCompteur=inputClient.nextLine();
                         
                         System.out.println("--> ETAPE 3 : DATE MISE EN OEUVRE EN RESPECTANT LE FORMAT SUIVANT : 'YYYY-MM-DD' ");
                         java.sql.Date dateMES=formatterDate(LocalDate.now().getYear()+"-"+LocalDate.now().getMonthValue()+"-"+LocalDate.now().getDayOfMonth());
                         String dateMisSave=inputClient.nextLine();
                        if (dateMisSave.matches("\\d{4}-\\d{2}-\\d{2}")) {
                         dateMES=formatterDate(dateMisSave);
                        }
                            
                         System.out.println("--> ETAPE 4: ACTIVER/DESACTIVER COMPTEUR EN RESPECTANT LE FORMAT SUIVANT 0:DESACTIVER/1:ACTIVER");
                         Boolean isActiveCmpt=true;
                         String activationCompt=inputClient.nextLine();
                         if(activationCompt.matches("0|1")){
                            int isActiveCompteur=Integer.parseInt(activationCompt);                            
                            if(isActiveCompteur==0){
                                isActiveCmpt=false;
                            }
                         }
                         
                         System.out.println("--> ETAPE 5 : CHOISIR UNE ZONE");
                         List<Zone> zones= new ArrayList<>();
                         zones.addAll(rs.zoneGetAll());
                         zones.forEach(z->{System.out.println(z.toString());});
                         String libZone=inputClient.nextLine();
                         Optional<Zone> optionalZone = zones.stream().filter(z -> z.getLib_zone().equals(libZone)).findFirst();
                         Zone zone = optionalZone.orElse(null);
                         if(Objects.isNull(zone.getId_zone())){
                             System.out.println("ZONE CHOISIE EST INCORRECTE");
                             break;}
                         
                         System.out.println("--> ETAPE 6 : AU PROFIT DU CLIENT ENTREZ CINE");
                         List<Client> clients = new ArrayList<>();
                         clients.addAll(rs.clientGetAll());
                         clients.forEach(c->{System.out.println(c.toString());});
                         String cineClientCmt=inputClient.nextLine();
                         Optional<Client> ptionalClient= clients.stream().filter(c->c.getcIN().equals(cineClientCmt)).findFirst();
                         Client client1=ptionalClient.orElse(null);
                          if(Objects.isNull(client1.getId_User())){
                             System.out.println("CLIENT CHOISI EST INCORRECT");
                             break;}
                          Compteur compteur = new Compteur(null, codeCompteur, marqueCompteur, dateMES, zone, client1, isActiveCmpt);
                            if(rs.compteurSave(compteur)){
                            System.out.println("L'AJOUT BIEN EFFECTUEE \n");
                            System.out.println(compteur.toString());}
                        else{System.out.println("ÉCHEC OPERATION  \n");}
                        break;
                    case 9:
                        System.out.println("9.|**** AFFICHER LA LISTE DES COMPTEURS ****");
                        rs.compteurGetAll().forEach(cmpt->{System.out.println(cmpt.toString());});
                        System.out.println("\n");
                        break;
                    case 10:
                        System.out.println("10|**** AFFICHER LA LISTE DES COMPTEURS PAR CLIENT ****");
                        System.out.println("--> ENTREZ CINE DU CLIENT ");
                        String cinCmpt=inputClient.nextLine();
                        if(Objects.nonNull(rs.clientFindByCINE(cinCmpt).getcIN())){
                        System.out.println(rs.compteurGetByClient(cinCmpt));}
                        else{System.out.println("COMPTEUR INTROUVABLE \n");}
                        break;
                    case 11:
                        System.out.println("11|**** RECHERCHER UN COMPTEUR PAR CODE ****");
                        System.out.println("--> ENTREZ CODE DU COMPTEUR ");
                        String codeCmpt=inputClient.nextLine();
                        if(Objects.nonNull(rs.compteurGetByCode(codeCmpt).getCode())){
                        System.out.println(rs.compteurGetByCode(codeCmpt));}
                        else{System.out.println("COMPTEUR INTROUVABLE \n");}
                        break;
                    case 12:
                        System.out.println("12|**** MODIFIER COMPTEUR ****");
                        System.out.println("--> ENTREZ CODE DU COMPTEUR ");
                        String codeCmptUpdate=inputClient.nextLine();
                        Compteur compteur1 = rs.compteurGetByCode(codeCmptUpdate);
                        
                        if(!Objects.nonNull(compteur1.getCode())){
                            System.out.println("COMPTEUR INTROUVABLE");
                            break;}
                        Compteur compteurNv= new Compteur();
                        System.out.println("--> ETAPE 1 : ENTREZ LA MARQUE DU COMPTUR , LA VALEUR ACTUELLE EST : "+compteur1.getMarque());
                        String marqueCompteurUpdate=inputClient.nextLine();
                        
                        System.out.println("--> ETAPE 2 : DATE MISE EN OEUVRE EN RESPECTANT LE FORMAT SUIVANT : 'YYYY-MM-DD' "+compteur1.getDateMiseEnOeuvre());
                        String dateUpdate=inputClient.nextLine();
                        System.out.println(dateUpdate);
                        if (dateUpdate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                         java.sql.Date dateMesUpdate=formatterDate(dateUpdate);
                         compteurNv.setDateMiseEnOeuvre(dateMesUpdate);
                        }
                        
                        System.out.println("--> ETAPE 3 : ACTIVER/DESACTIVER COMPTEUR EN RESPECTANT LE FORMAT SUIVANT 0:DESACTIVER/1:ACTIVER");
                        
                         String activationComptUpdate=inputClient.nextLine();
                         if(activationComptUpdate.matches("0|1")){
                            int isActiveCompteur=Integer.parseInt(activationComptUpdate);                            
                            if(isActiveCompteur==0){
                                compteurNv.setIsActive(false);
                            }
                            else{
                                compteurNv.setIsActive(true);
                            }
                         }
                         
                       
                       compteurNv.setMarque(marqueCompteurUpdate);
                       
                       
                       if(rs.compteurUpdate(compteur1, compteurNv)){
                            System.out.println("LA MODIFICATION EST BIEN EFFECTUEE \n");}
                            else{System.out.println("ÉCHEC OPERATION \n");}
                            break; 
                    case 13:
                        System.out.println("13|**** DESACTIVER/ACTIVER UN COMPTEUR ****");
                        System.out.println("--> ENTREZ CODE DU COMPTEUR ");
                        String codeCmptToActive=inputClient.nextLine();
                        if(Objects.nonNull(rs.compteurGetByCode(codeCmptToActive).getCode())){
                            Compteur compteur2 =  rs.compteurGetByCode(codeCmptToActive);
                            String etatActul="DESACTIVE";
                            String etatProcaine="ACTIVE";
                            if(compteur2.getIsActive()){
                                etatActul="ACTIVE";
                                etatProcaine="DESACTIVE";
                            }
                            
                        System.out.println("--> CE COMPTEUR EST "+etatActul+" VOULEZ-VOUS VRAIMENT "+etatProcaine+" 0:NON/1:OUI");
                        int choix=Integer.parseInt(inputClient.nextLine());
                            if(choix==0){
                                break;
                            }
                            else{
                                
                                if(rs.compteurActivation(compteur2)){
                                    System.out.println("LE COMPTEUR PORTE LE CODE "+compteur2.getCode()+" EST BIEN "+etatProcaine+" \n");}
                                else{System.out.println("ÉCHEC OPERATION \n");}
                            }
                        
                        }
                        else{System.out.println("COMPTEUR INTROUVABLE \n");}
                        break;
                        
                    case 14:
                        exitCompteur=true;
                        break;    
                }

              }while(!exitCompteur);
        
    }
    
        /*
          
          
          SOUS MENU RESPONSABLE DASHBOARD
          
          */
         static void dashBoard(ResponsableService rs) throws Exception{
              boolean exitDashBoard=false;
              Scanner inputDashBoard=new Scanner(System.in);
              do{
                System.out.println("********************************************************************** TABLEAU DE BORD **********************************************************************\n");
                System.out.println("1.*** PAYER DES FACTURES ***");
                System.out.println("2.*** LISTE DES FACTURES NON-PAYÉES ***");
                System.out.println("3.*** LISTE DES FACTURES PAYÉES ***");
                System.out.println("4.*** RECHERCHER LES FACTURES PAR CLIENT ***");
                System.out.println("5.*** QUITTER TABLEAU DE BORD ***");
                System.out.println("CHOISIR UN!");
                Scanner sd=new Scanner(System.in);
                System.out.println("ENTREZ VOTRE CHOIX");
                int numDashBoard=sd.nextInt();
                switch(numDashBoard)
                {
                    case 1:
                        System.out.println("1.*** PAIEMENT DES FACTURES ***");
                        System.out.println("--> ETAPE 1 : ENTREZ [ NUMERO D'ABONNEMENT | CIN | CODE COMPTEUR ] DU CLIENT");
                        String identifiantClient=inputDashBoard.nextLine();
                        
                        // charger listes et afficher
                        ArrayList<Paiement> paiements = new ArrayList<>();
                        paiements.addAll(rs.getFacturesUnpaidByClient(identifiantClient));
                        paiements.forEach(fact->{System.out.println(fact.toString());});
                        
                        System.out.println("--> ETAPE 2 : ENTREZ SAISIR LIBELLE DU PAIEMENT");
                        String libele=inputDashBoard.nextLine();
                        
                        
                        Optional<Paiement> optionalPaiement = paiements.stream().filter(p -> p.getLibelPaiement().equals(libele)).findFirst();
                        
                        optionalPaiement.ifPresent(p->{p.toString();});
                        
                        
                        if(!optionalPaiement.isPresent()){
                            System.out.println(" CHOIX DU FACTURE EST INCOORECT ");
                            break;}
                        
                        System.out.println("--> ETAPE 3 : ENTREZ SAISIR MONTANT DU PAIEMENT ["+optionalPaiement.get().getTarif()+"] DH");
                        Double tarifClient=inputDashBoard.nextDouble();
                        
                        if(optionalPaiement.get().getTarif()>tarifClient){
                            System.out.println(" MONTANT EST INSUFAISANT");
                            break;}
                        
                        if(rs.paiementFacture(optionalPaiement.get())){
                            System.out.println(" OPERATION BIEN EFFECTUEE ");
                            System.out.println(" RESTE EST [" + (tarifClient - optionalPaiement.get().getTarif())+"] DH");}
                        
                        else{System.out.println("ÉCHEC OPERATION \n");}
                        break;
                    case 2:
                        System.out.println("2.*** LISTE DES FACTURES NON-PAYÉES ***");
                        rs.getAllFacturesUnpaid().forEach(f->{System.out.println(f.toString());});
                        System.out.println("\n");
                        break;
                    case 3:
                        System.out.println("3.*** LISTE DES FACTURES PAYÉES ***");
                        rs.getAllFacturesPaid().forEach(f->{System.out.println(f.toString());});
                        System.out.println("\n");
                        break;
                    case 4:
                        System.out.println("4.*** RECHERCHER LES FACTURES NON-PAYÉES CLIENT ***");
                        System.out.println("--> ETAPE 1 : ENTREZ [ NUMERO D'ABONNEMENT | CIN | CODE COMPTEUR ] DU CLIENT");
                        String ideClientSearch=inputDashBoard.nextLine();
                        rs.getFacturesUnpaidByClient(ideClientSearch).forEach(fact->{System.out.println(fact.toString());});
                        break;
                    case 5:
                        exitDashBoard=true;
                        break;
                }

              }while(!exitDashBoard);
      }
           //FIN
//                                                            FIN SOUS MENUS RESPONSABLE

    
    
     
    public static void main(String[] args) throws  SQLException, ParseException, Exception{

        // Create Thread for receive Data From Telecompteur
         MainThread mainThread = new MainThread();
            mainThread.start();      
        // instance Service LAYER
        ResponsableService rs= new ResponsableService();
        ClientService cs = new ClientService();
        
        boolean exit=false;
        
        do
        {
          System.out.println("..*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MENU PRINCIPALE %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
          System.out.println("1.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% CLIENT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
          System.out.println("2.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% RESPONSABLE %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
          System.out.println("3.*** %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% QUITER %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ***");
          System.out.println("CHOISIR UN!");
          Scanner sd=new Scanner(System.in);
          System.out.println("ENTREZ VOTRE CHOIX");
          int num=sd.nextInt();

            switch(num){
                case 1:
                    menuClient(cs);
                    break;
                case 2:
                    menuResponsable(rs);
                    break;
                case 3:
                     exit=true;
                     break;

            }
        }while(!exit);
        
 
    }
    
}
