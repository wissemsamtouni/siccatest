/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javafx.collections.ObservableList;
import model.Bonplans;
import model.Categories;

/**
 *
 * @author Elife-Kef-110
 */
public interface Icategories {
       public void ajoutercategorie(Categories cat);
    public void supprimercategorie(Categories cat);
    public ObservableList<Categories> affichercategorie();
    public void modifiercategories(Categories cat);

}
