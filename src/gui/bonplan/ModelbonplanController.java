/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.bonplan;
import model.Bonplans;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXMLLoader;


/**
 * FXML Controller class
 *
 * @author ahmed jb
 */
public class ModelbonplanController implements Initializable {

    private final List<Bonplans> bpl=new ArrayList<>();
private List<Bonplans> getdata(){
 
    List<Bonplans> bpl=new ArrayList<>();
    Bonplans bp;
    for(int i=0;i<20;i++){
        bp=new Bonplans();
        bp.setNom_bonplan("nom_bonplan");
//        bp.setId(cat.findcatById("id"));
        bp.setAdresse("adresse");
        bp.setDescription("description");
        bpl.add(bp);
    }
        return null;
    
}
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        bpl.addAll(getdata());
//        for (int i=0;i<bpl.size();i++){
//            FXMLLoader x=new FXMLLoader();x.setLocation(getClass().getResource("../gui/bonplan/item.fxml"));
//            
        }
        
        
        
        
        
    }    
    
