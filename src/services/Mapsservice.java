/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import interfaces.Mapsinterface;

import model.Maps;
import util.Myconnexion;

/**
 *
 * @author Elife-Kef-110
 */
public class Mapsservice implements Mapsinterface {

    Connection cnx = Myconnexion.getInstance().getCnx();

    @Override
    public Maps ajoutermaps(Maps mp) {
        String req;
        req = " INSERT INTO `map`(`ville`, `logitude`, `latitude`)  VALUES (?,?,?)";

        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, mp.getVille());
            ps.setDouble(2, mp.getLogitude());
            ps.setDouble(3, mp.getLatitude());

            ps.executeUpdate();
            System.out.println(" maps ajouter avec succé");
            
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            
            mp.setId_map(generatedKey);
            
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mp;
    }

    @Override
    public Maps supprimermaps(Maps mp) {
        String req;
        req = "DELETE FROM `map` WHERE id_map='" + mp.getId_map() + "'";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);

            ps.execute();
            System.out.println("maps supprimer avec succé");
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp;
    }

    @Override
    public ObservableList<Maps> affichermaps() {
        ObservableList<Maps> mps = FXCollections.observableArrayList();
        String req;
        req = " SELECT * FROM map";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = cnx.prepareStatement(req);
            rs = ps.executeQuery();

            while (rs.next()) {
                Maps mp = new Maps();
                mp.setId_map(rs.getInt("id_map"));
                mp.setVille(rs.getString("ville"));
                mp.setLogitude(rs.getDouble("logitude"));
                mp.setLatitude(rs.getDouble("latitude"));

                mps.add(mp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mps;

    }

    @Override
    public Maps  modifiermaps(Maps mp) {
        String req;
        req = "UPDATE map SET ville=?,logitude=?,latitude=? WHERE id_plan='" + mp.getId_map() + "'";

        PreparedStatement ps;
         
        try {
           ps = cnx.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
       
           
           ps.setString(1, mp.getVille());
            ps.setDouble(2, mp.getLogitude());
            ps.setDouble(3, mp.getLatitude());
   ps.executeUpdate();
           
            System.out.println(" maps modifier avec succé");
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            
            mp.setId_map(generatedKey);
        } catch (SQLException ex) {
            Logger.getLogger(bpservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp;
    }

    @Override
    public Maps findcatById(int id_map) {
             Maps mp=new Maps();
        String requete = "select * from map where id_map=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id_map);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                mp.setId_map(resultat.getInt(1));
                mp.setVille(resultat.getString(2));
//                mp.setLatitude(resultat.getDouble(3));
//                mp.setLogitude(resultat.getDouble(4));
            }
            return mp;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Maps findcatBynom(String ville) {
                  Maps mp=new Maps();
        String requete = "select * from map where ville=?";
         try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, ville);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                mp.setId_map(resultat.getInt(1));
                mp.setVille(resultat.getString(2));
//                mp.setLatitude(resultat.getDouble(3));
//                mp.setLogitude(resultat.getDouble(4));
            }
            return mp;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }
        private static Mapsinterface imaps;
        
          public static Mapsinterface getInstance1() {
        if (imaps == null) {
            imaps = new Mapsservice();
        }
        return imaps;    
    }
}
