/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import model.Bonplans;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author ahmed jb
 */
public interface Bpinerface {
    public void ajouterBP(Bonplans bp);
    public void supprimerBP(Bonplans bp);
    public ObservableList<Bonplans> afficherBP();
    public void modifierBP(Bonplans bp);

   
    
    
}
