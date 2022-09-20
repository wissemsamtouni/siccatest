/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Bpinerface;
import interfaces.Icategories;
import interfaces.Mapsinterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Bonplans;
import model.Maps;
import util.Myconnexion;

/**
 *
 * @author ahmed jb
 */
public class bpservice implements Bpinerface {

    //var
    Connection cnx = Myconnexion.getInstance().getCnx();

    @Override
    public void ajouterBP(Bonplans bp) {
        String req;
        req = " INSERT INTO bonplan(  nom_bonplan, adresse, description_bonplan, id_cat, images, id_map,frais, horaire) VALUES (?,?,?,?,?,?,?, ?)";

        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, bp.getNom_bonplan());
            ps.setString(2, bp.getAdresse());
            ps.setString(3, bp.getDescription());
            ps.setInt(4, bp.getcategories().getId_cat());
            ps.setString(5, bp.getImages());
            ps.setInt(6, bp.getMaps().getId_map());
            ps.setDouble(7, bp.getFrais());
            ps.setString(8, bp.getHoraire());
            ps.executeUpdate();
            System.out.println("bon plans ajouter avec succé");
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerBP(Bonplans bp) {

        String req;
        req = "DELETE FROM `bonplan` WHERE id_plan='" + bp.getId_plan() + "'";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);

            ps.execute();
            System.out.println("le bon plan supprimer avec succé");
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifierBP(Bonplans bp) {

        String req;
        req = "UPDATE bonplan SET nom_bonplan=?,adresse=?,description_bonplan=?,id_cat=?,images=?,id_map=?,frais=?,horaire=? WHERE id_plan='" + bp.getId_plan() + "'";

        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, bp.getNom_bonplan());
            ps.setString(2, bp.getAdresse());
            ps.setString(3, bp.getDescription());
            ps.setInt(4, bp.getcategories().getId_cat());
            ps.setString(5, bp.getImages());
            ps.setInt(6, bp.getMaps().getId_map());
            ps.setDouble(7, bp.getFrais());
            ps.setString(8, bp.getHoraire());

            ps.executeUpdate();
            System.out.println("bonjour");
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      private static Icategories icategories;
        
          public static Icategories getInstance() {
        if (icategories == null) {
            icategories = new Catcategories();
        }
        return icategories;    
    }
            private static Mapsinterface imaps;
        
          public static Mapsinterface getInstance1() {
        if (imaps == null) {
            imaps = new Mapsservice();
        }
        return imaps;    
    }

    @Override
    public ObservableList<Bonplans> afficherBP() {

        ObservableList<Bonplans> bpl = FXCollections.observableArrayList();
        String req;
        req = "SELECT * FROM `bonplan`";
        Statement ps;
        ResultSet rs;
        
        try {
            ps = cnx.createStatement();
            rs = ps.executeQuery(req);
Icategories ctss = Catcategories.getInstance();
Mapsinterface mpss=Mapsservice.getInstance1();
            while (rs.next()) {
                Bonplans bt = new Bonplans() ;
                bt.setId_plan(rs.getInt("id_plan"));
                bt.setNom_bonplan(rs.getString("nom_bonplan"));
                bt.setAdresse(rs.getString("adresse"));
                bt.setDescription(rs.getString("description_bonplan"));
      
                bt.setcategories(ctss.findcatById(rs.getInt("id_cat")));
                System.out.println(ctss.findcatById(rs.getInt("id_cat")));
                bt.setImages(rs.getString("images"));
                bt.setHoraire(rs.getString("horaire"));
                bt.setFrais(rs.getDouble("frais"));
                bt.setMaps(mpss.findcatById(rs.getInt(7)));
                bpl.add(bt);
            }

        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bpl;

    }

}
