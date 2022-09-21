/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reservation;
    import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author ASUS
 */
public class Reserv {



    private Parent view;

    public Reserv() throws IOException {
        URL url = new File("C:\\Users\\ASUS\\Desktop\\siccatest\\siccatest\\src\\gui\\reservation\\FXMLReserv.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(url);
        this.view = root;
    }

    public Parent getView(){
        return this.view;
    }
}
