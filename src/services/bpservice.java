/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import interfaces.Bpinerface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
        req = " INSERT INTO bonplan( id_plan, nom_bonplan, adresse, description_bonplan, type_categorie, images) VALUES (?,?,?,?,?)";

        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, bp.getNom_bonplan());
            ps.setString(2, bp.getAdresse());
            ps.setString(3, bp.getDescription());
            ps.setString(4, bp.getType_categorie()); 
            InputStream fis =new FileInputStream( (File) bp.getImages());
           ps.setBlob(5, (InputStream)fis);
            ps.executeUpdate();
            System.out.println("bon plans ajouter avec succé");
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerBP(Bonplans bp) {

        String req;
        req = "DELETE FROM bonplan WHERE id_plan='" + bp.getId_plan() + "'";
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("le bon plan supprimer avec succé");
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifierBP(Bonplans bp) {

        String req;
        req = "UPDATE bonplan SET id_plan=?,nom_bonplan=?,adresse=?,description_bonplan=?,type_categorie=?,images=? WHERE id_plan=?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, bp.getNom_bonplan());
            ps.setString(2, bp.getAdresse());
            ps.setString(3, bp.getDescription());
            ps.setString(4, bp.getType_categorie());
            InputStream  fis=new FileInputStream((File) bp.getImages());
           ps.setBlob(5, (InputStream)fis);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Bonplans> afficherBP() {

        ObservableList<Bonplans> bpl = FXCollections.observableArrayList();
        String req;
        req = "SELECT * FROM `bonplan`";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = cnx.prepareStatement(req);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Bonplans bt = new Bonplans();
                bt.setId_plan(rs.getInt("id_plan"));
                bt.setNom_bonplan(rs.getString("nom_bonplan"));
                bt.setAdresse(rs.getString("adresse"));
                bt.setDescription(rs.getString("description_bonplan"));
                bt.setType_categorie(rs.getString("type_categorie"));
                bpl.add(bt);
            }

        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bpl;

    }
        

}
