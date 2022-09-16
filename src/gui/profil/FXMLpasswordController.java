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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import util.Myconnexion;
import util.Notification;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-007
 */
public class FXMLpasswordController implements Initializable {

    Connection cnx = Myconnexion.getInstance().getCnx();

    @FXML
    private Button IDretour;
    @FXML
    private PasswordField IDpasswordone;
    @FXML
    private PasswordField IDpasswordnew;
    @FXML
    private PasswordField IDpasswordconfirm;
    @FXML
    private Button IDchanger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDretour.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/profil/FXMLprofil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Chnager(ActionEvent event) throws SQLException, IOException {
        String y = Statics.current_user.getMdp();
        String x = IDpasswordone.getText();
        String z = IDpasswordnew.getText();
        if (getHash(x.getBytes(), "SHA-1").equals(y)  ) {
            if(!IDpasswordnew.getText().equals("")){
            

            if (IDpasswordnew.getText().equals(IDpasswordconfirm.getText()) && (!IDpasswordnew.getText().equals(IDpasswordone)) ) {
                PreparedStatement pss;
                ResultSet rs;

                String yy = "  update utilisateur set  mdp ='" + getHash(z.getBytes(), "SHA-1") + "'  where id_utilisateur = '" + Statics.current_user.getId_utilisateur() + "' ";
                pss = cnx.prepareStatement(yy);

                pss.execute();
                
                //showMessageDialog(null, "mot de passe modifier avec succes");
                Notification.notificationSuccess("mot de passe modifier avec succes","");
                Stage stage = (Stage) IDchanger.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/profil/FXMLprofil.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Notification.notificationInformation("saisir le meme nouveau mot de passe dans le champ de confirm","");
                //showMessageDialog(null, "saisir le meme nouveau mot de passe dans le champ de confirm");
            }
            }
            else{
                Notification.notificationError("le nouveau mot de passe doit étre rempli");
                //showMessageDialog(null, "le nouveau mot de passe doit étre rempli");
                        
                    
                    }}
         else {
            Notification.notificationError("saisir le correct ancien mot de passe");
            //showMessageDialog(null, "saisir le correct ancien mot de passe");
        }

    }

}
