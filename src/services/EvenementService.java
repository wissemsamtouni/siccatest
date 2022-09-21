/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Ievenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Evenement;
import util.Myconnexion;

/**
 *
 * @author ASUS
 */
public class EvenementService implements Ievenement{
    Connection cnx = Myconnexion.getInstance().getCnx();
    @Override
    public void ajouterEvenement(Evenement e) {
        String requete = "INSERT INTO `evennement`(`nom_evenement`,`date_debut`, `date_fin`, `prix_ticket`,`image_event`,`nbrticket`) VALUES ( ?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            //ps.setDate(1, Date.valueOf(e.getDate_debut()));
            //ps.setDate(2, Date.valueOf(e.getDate_fin()));
            //ps.setInt(3, e.getPrix_ticket());
            pst.setString(1, e.getNom_evenement());
            pst.setString(2, String.valueOf(e.getDate_debut()));
            pst.setString(3, String.valueOf(e.getDate_fin()));
            pst.setInt(4, (int) e.getPrix_ticket());
            pst.setString(5, e.getImages());
            pst.setString(6, e.getNbrticket());
            pst.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }

    }
    private static Ievenement Ievenement;
    public static Ievenement getInstance(){
        if (Ievenement == null){
           Ievenement = new EvenementService();
        }
        return Ievenement;
    }

    public ObservableList<Evenement> affichageevenement() {
        ObservableList<Evenement> eventlist = FXCollections.observableArrayList();
        String requete = "select * from evennement";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            
            //IUtilisateur x = UtilisateurService.getInstance();
            while (resultat.next()) {
                Evenement pr = new Evenement();
                
                        
                //table.setItems(eventlist);
               
               
                
               
            }
            return eventlist;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }
    @Override
    public void supprimerEvenement(Evenement e) {
        
    }

    @Override
    public void modifierEvenement(Evenement e) {
        
    }
    
}
