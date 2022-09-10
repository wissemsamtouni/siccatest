/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.acceuil;

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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-007
 */
public class FXMLacceuilController implements Initializable {

    @FXML
    private Button IDsignin;
    @FXML
    private Label IDlabel;
    @FXML
    private Button IDsignout;
    @FXML
    private Button IDprofil;
    @FXML
    private Button IDacceuil;
    @FXML
    private Button IDbonplans;
    @FXML
    private Button IDevennements;
    @FXML
    private Button IDmateriels;
    @FXML
    private Button IDaboutus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          if (Statics.current_user.getNom() == null) {
            IDlabel.setVisible(false);
            IDsignin.setVisible(true);
            IDprofil.setVisible(false);
            IDsignout.setVisible(false);
            
        } else {
              
            IDsignout.setVisible(true);
            IDaboutus.setVisible(true);
            IDmateriels.setVisible(true);
            IDbonplans.setVisible(true);
            IDprofil.setVisible(true);
            IDsignin.setVisible(false);
            
            
            
            IDlabel.setText("welcome " + Statics.current_user.getNom());
            
            
            
        }
    }    

    @FXML
    private void Signin(ActionEvent event) throws IOException {
         Stage stage = (Stage) IDsignin.getScene().getWindow();
         stage.close();
         Parent root = FXMLLoader.load(getClass().getResource("../../gui/login/FXMLlogin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void Signout(ActionEvent event) throws IOException {
        Statics.current_user.setNom(null);
         Stage stage = (Stage) IDsignout.getScene().getWindow();
         stage.close();
         Parent root = FXMLLoader.load(getClass().getResource("../../gui/acceuil/FXMLacceuil.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            stage.show();
    }

    @FXML
    private void Profil(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDprofil.getScene().getWindow();
         stage.close();
         Parent root = FXMLLoader.load(getClass().getResource("../../gui/profil/FXMLprofil.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}
