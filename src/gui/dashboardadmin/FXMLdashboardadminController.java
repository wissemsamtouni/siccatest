/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboardadmin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-007
 */
public class FXMLdashboardadminController implements Initializable {

    @FXML
    private Button IDpromo;
    @FXML
    private Button IDhome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Promo(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDpromo.getScene().getWindow();
         stage.close();
         Parent root = FXMLLoader.load(getClass().getResource("../../gui/promo/FXMLpromo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
        Statics.current_user.setNom(null);
         Stage stage = (Stage) IDhome.getScene().getWindow();
         stage.close();
         Parent root = FXMLLoader.load(getClass().getResource("../../gui/acceuil/FXMLacceuil.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            stage.show();
    }
    
}
