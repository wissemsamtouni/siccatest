/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javafx.collections.ObservableList;
import model.Maps;

/**
 *
 * @author Elife-Kef-110
 */
public interface Mapsinterface {
     public Maps ajoutermaps(Maps mp);
    public Maps  supprimermaps(Maps mp);
    public ObservableList<Maps> affichermaps();
    public Maps  modifiermaps(Maps mp);
}
