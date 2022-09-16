/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IMateriel;
import interfaces.IPromo;
import java.sql.Connection;
import java.sql.Date;
import util.Myconnexion;
import model.Materiel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import model.Materiel;
/**
 *
 * @author Elife-Kef-112
 */
public class MaterielService implements IMateriel{
    Connection cnx = Myconnexion.getInstance().getCnx();
    
    
    
    
    
    
    

    @Override
    public void ajouterMateriel(Materiel mt) {
         
         String requete = "INSERT INTO `materiel`(`categorie`, `quantité`, `description`, `prix_location`, `image`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, mt.getCategorie());
            ps.setInt(2, mt.getQuantité());
            ps.setString(3, mt.getDescription());
            ps.setFloat(4, mt.getPrix_location());
            ps.setString(5, mt.getImage());
           
            
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
        
        
    }

    @Override
    public ObservableList<Materiel> affichageMateriel() {
        return null;
       
    }
     private static IMateriel IMateriel;
    
    public static IMateriel getInstance() {
        if (IMateriel == null) {
            IMateriel = new MaterielService();
        }
        return IMateriel;
    }
   
    

   
}
