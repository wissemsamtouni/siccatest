/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.profil;

import static gui.registr.FXMLregistrController.getHash;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import util.Myconnexion;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-007
 */
public class FXMLprofilController implements Initializable {

    @FXML
    private TextField champnom;
    @FXML
    private TextField champprenom;
    @FXML
    private TextField champlogin;
    @FXML
    private TextField champadresse;
    @FXML
    private TextField champtel;
    @FXML
    private Button IDretour;
    @FXML
    private Button IDmodifier;
    @FXML
    private Button IDsupprimer;
    @FXML
    private Button champpassword;
    Connection cnx = Myconnexion.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        champnom.setText(Statics.current_user.getNom());
        champprenom.setText(Statics.current_user.getPrenom());
        champlogin.setText(Statics.current_user.getLogin());
        champadresse.setText(Statics.current_user.getAdresse());
        champtel.setText(String.valueOf(Statics.current_user.getTel()));

    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDretour.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/acceuil/FXMLacceuil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException, IOException {
        PreparedStatement pss;

        String yy = "  update utilisateur set  nom ='" + champnom.getText() + "' ,prenom ='" + champprenom.getText() + "' ,login ='" + champlogin.getText() + "',tel ='" + champtel.getText() + "',adresse ='" + champadresse.getText() + "' where id_utilisateur = '" + Statics.current_user.getId_utilisateur() + "' ";
        pss = cnx.prepareStatement(yy);

        pss.execute();
        showMessageDialog(null, "vos informations sont mise a jour ,reconnecter vous  ");
        Stage stage = (Stage) IDmodifier.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/login/FXMLlogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Supprimer utilisateur");
        alert.setHeaderText("vous etes en train de supprimer votre compte definitivement !");
        alert.setContentText("Veuillez vous le supprimer !");
        if (alert.showAndWait().get() == ButtonType.OK) {
            PreparedStatement ps;
            ResultSet rs;

            String yy = "delete   from  utilisateur where id_utilisateur = '" + Statics.current_user.getId_utilisateur() + "' ";
            ps = cnx.prepareStatement(yy);
            ps.execute();
            showMessageDialog(null, "votre compte a etait supprimer");
            champnom.clear();
            champprenom.clear();
            champadresse.clear();
            champtel.clear();
            champlogin.clear();
            Stage stage = (Stage) IDsupprimer.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/login/FXMLlogin.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
    }

    @FXML
    private void Changer(ActionEvent event) throws IOException {
        Stage stage = (Stage) champpassword.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/profil/FXMLpassword.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
