/*
 To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IPromo;
import interfaces.IUtilisateur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Promo;
import util.Myconnexion;

/**
 *
 * @author wissem
 */
public class PromoService implements IPromo{
       Connection cnx = Myconnexion.getInstance().getCnx();

    @Override
    public void ajouterPromo(Promo st) {
    String requete = "INSERT INTO `promo`(`date_expiration`,`pourcentage_reduction`,`id_utilisateur`) VALUES ( ?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setDate(1, Date.valueOf(st.getDate_expiration()));
            ps.setInt(2, st.getPoucentage_reduction());
            ps.setInt(3, st.getId_utlisateur().getId_utilisateur());
            
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }



    }

    @Override
    public ObservableList<Promo> affichagepromo() {
   ObservableList<Promo> listepromo = FXCollections.observableArrayList();
        String requete = "select * from promo";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            
            IUtilisateur x = UtilisateurService.getInstance();
            while (resultat.next()) {
                Promo pr = new Promo();
                pr.setId_promo(resultat.getInt(1));
                pr.setDate_expiration(LocalDate.parse(resultat.getString(2)));
                pr.setPoucentage_reduction(resultat.getInt(3));
               
                pr.setId_utlisateur(x.findUserById(resultat.getInt(4)));
                
                listepromo.add(pr);
            }
            return listepromo;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }
    
    
      private static IPromo IPromo;
    
    public static IPromo getInstance() {
        if (IPromo == null) {
            IPromo = new PromoService();
        }
        return IPromo;
    }
    
}
