/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import model.Evenement;

/**
 *
 * @author ASUS
 */
public interface Ievenement {
     //insert

   public void ajouterEvenement(Evenement e);
   public ObservableList<Evenement> affichageevenement();
    
    //select
   // public List<Personne> afficherPersonne();
    
    //delete
   public void supprimerEvenement(Evenement e);
   public void modifierEvenement (Evenement e);
}
