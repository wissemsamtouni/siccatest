/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.bonplan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.*;
/**
 * FXML Controller class
 *
 * @author ahmed jb
 */
public class ItemController implements Initializable {

    @FXML
    private Label lnom;
    @FXML
    private Label lcategorie;
    @FXML
    private ImageView imagebp;

    private Bonplans bp;
//    public void  setdat(Bonplans bp){
//        this.bp=bp;
//        lnom.setText(bp.getNom_bonplan());
//        lcategorie.setText(bp.getId());
//        image img=new image(getClass().getResourceAsStream(bp.getImagesrc()));
//        imagebp.setImage(img);
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
