/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siccatest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author wissem
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/acceuil/FXMLacceuil.fxml"));
    
          //Parent root = FXMLLoader.load(getClass().getResource("../gui/login/FXMLlogin.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("../gui/promo/FXMLPromo.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("../gui/registr/FXMLregistr.fxml"));
           
          
          
          
            Scene scene = new Scene(root);
            primaryStage.setTitle("HOME");
            //primaryStage.setTitle("promo");
            //primaryStage.setTitle("login");
            //primaryStage.setTitle("registr");
            
            
          //  primaryStage.getIcons().add(new Image("/img/foody.png"));
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
           Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
