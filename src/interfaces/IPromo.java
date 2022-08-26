/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javafx.collections.ObservableList;
import model.Promo;

/**
 *
 * @author wissem
 */
public interface IPromo {
    void ajouterPromo(Promo st);

    ObservableList<Promo> affichagepromo();
}
