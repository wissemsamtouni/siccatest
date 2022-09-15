/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Icategories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Categories;
import util.Myconnexion;

/**
 *
 * @author Elife-Kef-110
 */
public class Catcategories implements Icategories{
         Connection cnx = Myconnexion.getInstance().getCnx();
   
         
         @Override
    public void ajoutercategorie(Categories cat) {


  
        String req;
        req = "INSERT INTO categories(type_categorie) VALUES (?)";

        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, cat.getType_categories());
           
         
            ps.executeUpdate();
            System.out.println("bon categorie ajouter avec succé");
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimercategorie(Categories cat) {
          String req;
        req = "DELETE FROM categories WHERE id_cat='" + cat.getId_cat() + "'";
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
    public ObservableList<Categories> affichercategorie() {
        ObservableList<Categories> cats = FXCollections.observableArrayList();
        String req;
        req = "SELECT * FROM `categories`";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = cnx.prepareStatement(req);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categories cat = new Categories();
                cat.setId_cat(rs.getInt("id_cat"));
                cat.setType_categories(rs.getString("type_categorie"));
               
              
                cats.add(cat);
            }

        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cats;

    
    }

    @Override
    public void modifiercategories(Categories cat) {
          String req;
        req = "UPDATE categories SET type_categories=?WHERE id_cat='"  + cat.getId_cat() +  "'";

        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, cat.getType_categories());
         

            ps.executeUpdate();
            System.out.println("categories modifier avec succé");
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

   

   

 
   
    
    
    
    
}
