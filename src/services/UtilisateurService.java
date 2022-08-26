/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IPromo;
import interfaces.IUtilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Utilisateur;
import util.Myconnexion;

/**
 *
 * @author wissem
 */
public class UtilisateurService implements IUtilisateur{
    Connection cnx = Myconnexion.getInstance().getCnx();

    @Override
    public Utilisateur findUserById(int id) {
      Utilisateur user = new Utilisateur();
        String requete = "select * from utilisateur where id_utilisateur=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                user.setId_utilisateur(resultat.getInt(1));
                user.setNom(resultat.getString(2));
            }
            return user;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du users " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Utilisateur findUserBynom(String nom) {
        Utilisateur user = new Utilisateur();
        String requete = "select * from utilisateur where nom=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                user.setId_utilisateur(resultat.getInt(1));
                user.setNom(resultat.getString(2));
            }
            return user;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du user " + ex.getMessage());
            return null;
        }
    }

  
    @Override
    public List<Utilisateur> DisplayAllusers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



      private static IUtilisateur IUtilisateur;
    
    public static IUtilisateur getInstance() {
        if (IUtilisateur == null) {
            IUtilisateur = new UtilisateurService();
        }
        return IUtilisateur;
    }    
}
