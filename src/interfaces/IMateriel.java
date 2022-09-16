/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javafx.collections.ObservableList;
import model.Materiel;
import model.Promo;

/**
 *
 * @author Elife-Kef-112
 */
public interface IMateriel {
    void ajouterMateriel(Materiel mt);
  
    ObservableList<Materiel> affichageMateriel();
}
