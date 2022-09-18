/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboardadmin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Myconnexion;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-007
 */
public class FXMLdashboardadminController implements Initializable {

    
    Connection cnx = Myconnexion.getInstance().getCnx();
    @FXML
    private Button IDpromo;
    @FXML
    private Button IDhome;
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    
    private ImageView c3;
    @FXML
    private ImageView m1;
    @FXML
    private Button idusers;
    @FXML
    private Label stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        setRotate(c2, true, 360, 10);
        setRotate(c1, true, 180, 18);
        setRotatedeux(m1, true, 360, 10);
        
        try {
            stat.setText(String.valueOf(showusers()));
        } catch (SQLException ex) {
            //logger.getLogger(FXMLdashboardadminController.class.getName())
            System.out.println(ex);
        }
    }

    @FXML
    private void Promo(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDpromo.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/promo/FXMLpromo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Promo");
        stage.getIcons().add(new Image("gui/dashboardadmin/Untitled design (2).png"));
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
        stage.setTitle("Acceuil");
        stage.getIcons().add(new Image("gui/dashboardadmin/Untitled design (2).png"));

        stage.show();
    }

    private void setRotate(Circle c, boolean reverse, int angle, int duration) {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration), c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(3);
        rt.setCycleCount(18);
        rt.setAxis(Rotate.Y_AXIS);
        rt.play();
    }

    private void setRotatedeux(ImageView m, boolean reverse, int angle, int duration) {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration), m);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(3);
        rt.setCycleCount(18);
        rt.setAxis(Rotate.Y_AXIS);
        rt.play();
    }

    @FXML
    private void listeutilisateurs(ActionEvent event) throws IOException {
        Stage stage = (Stage) idusers.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/dashboardadmin/FXMLbanir.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("liste des utilisateurs");
        stage.getIcons().add(new Image("gui/dashboardadmin/Untitled design (2).png"));
        stage.show();
    }
     public Integer showusers() throws SQLException {
        int count = 0;
        Statement stmt = cnx.createStatement();
        String query = "select count(*) from utilisateur where role='Client'";
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        count = rs.getInt(1);
        return count;

    }
}
