/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.registr;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.xml.bind.DatatypeConverter;

import util.Myconnexion;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-007
 */
public class FXMLregistrController implements Initializable {

    @FXML
    private TextField IDnom;
    @FXML
    private TextField IDprenom;
    @FXML
    private TextField IDemail;
    @FXML
    private TextField IDlogin;
    @FXML
    private TextField IDmdp;
    @FXML
    private TextField IDtel;
    @FXML
    private TextField IDadresse;

    @FXML
    private Button IDinscrire;
    @FXML
    private Button IDretour;
    Connection cnx = Myconnexion.getInstance().getCnx();
    @FXML
    private Label alertnom;
    @FXML
    private Label alertprenom;
    @FXML
    private Label alertemail;
    @FXML
    private Label alertlogin;
    @FXML
    private Label alertmdp;
    @FXML
    private Label alerttel;
    @FXML
    private Label alertadresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String getHash(byte[] inputBytes, String algorithme) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithme);
            messageDigest.update(inputBytes);
            byte[] digesteBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digesteBytes).toLowerCase();

        } catch (Exception e) {

        }
        return hashValue;
    }

    @FXML
    private void Registr(ActionEvent event) throws SQLException, IOException {
        FileInputStream fis = null;
        if (this.Validated()) {
            PreparedStatement ps;
            ResultSet rs;
            String yy = "SELECT * FROM utilisateur WHERE login = '" + IDlogin.getText() + "' ";

            String req = " INSERT INTO utilisateur (nom,prenom,mail,login,mdp,tel,role,adresse) VALUES (?,?,?,?,?,?,?,?)";
            ps = cnx.prepareStatement(yy);
            rs = ps.executeQuery();
            if (rs.next()) {
                showMessageDialog(null, "login (username) deja existe");
            } else {
                String nom = IDnom.getText();
                String prenom = IDprenom.getText();
                String mail = IDemail.getText();
                String login = IDlogin.getText();
                String mdp = IDmdp.getText();
                String tel = IDtel.getText();
                String adresse = IDadresse.getText();
                PreparedStatement x = cnx.prepareStatement(req);
                x.setString(1, nom);
                x.setString(2, prenom);
                x.setString(3, mail);
                x.setString(4, login);
                x.setString(5, getHash(mdp.getBytes(), "SHA-1"));
                x.setString(6, tel);
                x.setString(7, "Client");
                x.setString(8, adresse);
                x.executeUpdate();
                System.out.println("PS : compte creer avec succes !");
                showMessageDialog(null, "compte creer avec succes ");
                IDnom.clear();
                IDprenom.clear();
                IDemail.clear();
                IDlogin.clear();
                IDmdp.clear();
                IDtel.clear();
                IDadresse.clear();
                Stage stage = (Stage) IDinscrire.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/login/FXMLlogin.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }

        }

    }

    private boolean isValidate() {
        if (IDnom.getText().trim().isEmpty() && IDprenom.getText().trim().isEmpty() && IDemail.getText().trim().isEmpty() && IDlogin.getText().trim().isEmpty()
                && IDmdp.getText().trim().isEmpty() && IDtel.getText().trim().isEmpty() && IDadresse.getText().trim().isEmpty()) {
            alertnom.setText("Nom is Empty");
            alertprenom.setText("Prenom is Empty");
            alertemail.setText("Email is Empty");
            alertlogin.setText("Login is Empty");
            alertmdp.setText("Password is Empty");
            alerttel.setText("Telephone is Empty");
            alertadresse.setText("Adresse is Empty");
        }
        if (IDnom.getText().trim().isEmpty()) {
            alertnom.setText("Username is Empty");
        }

        if (IDprenom.getText().trim().isEmpty()) {
            alertprenom.setText("Password is Empty");

        }
        if (IDemail.getText().trim().isEmpty()) {
            alertemail.setText("Password is Empty");

        }
        if (IDlogin.getText().trim().isEmpty()) {
            alertlogin.setText("Password is Empty");

        }
        if (IDmdp.getText().trim().isEmpty()) {
            alertmdp.setText("Password is Empty");

        }
        if (IDtel.getText().trim().isEmpty()) {
            alerttel.setText("Password is Empty");

        }
        if (IDadresse.getText().trim().isEmpty()) {
            alertadresse.setText("Password is Empty");
        }

        if (!IDnom.getText().trim().isEmpty()) {
            alertnom.setText("");
        }
        if (!IDprenom.getText().trim().isEmpty()) {
            alertprenom.setText("");
        }
        if (!IDemail.getText().trim().isEmpty()) {
            alertemail.setText("");
        }
        if (!IDlogin.getText().trim().isEmpty()) {
            alertlogin.setText("");

        }
        if (!IDmdp.getText().trim().isEmpty()) {
            alertmdp.setText("");

        }
        if (!IDtel.getText().trim().isEmpty()) {
            alerttel.setText("");

        }
        if (!IDadresse.getText().trim().isEmpty()) {
            alertadresse.setText("");

        } else {
            return true;
        }
        return false;

    }

    private boolean Validated() throws SQLException {
        String regex = "^(.+)@(.+)$";
        String number = "[0-9]+";
        Pattern x = Pattern.compile(number);
        Pattern pattern = Pattern.compile(regex);

        PreparedStatement ps;
        ResultSet rs;
        String yy = "SELECT * FROM utilisateur WHERE  mail ='" + IDemail.getText() + "' ";

        ps = cnx.prepareStatement(yy);
        rs = ps.executeQuery();
        if (rs.next()) {
            showMessageDialog(null, "e-mail deja existe");
        }

        if (IDnom.getText().equals("")) {

            showMessageDialog(null, "firstName text field cannot be blank.");
            IDnom.requestFocus();
        } else if (x.matcher(IDnom.getText()).matches()) {
            showMessageDialog(null, "name contains only caracter.");
            IDnom.requestFocus();

        } else if (IDnom.getText().length() < 3 || IDnom.getText().length() > 25) {

            showMessageDialog(null, "Error , name text field cannot be less than 3 and greator than 25 characters.");
            IDnom.requestFocus();

        } else if (IDmdp.getText().equals("")) {

            showMessageDialog(null, "Password is Emty  ");
            IDmdp.requestFocus();

        } else if (IDprenom.getText().equals("")) {

            showMessageDialog(null, "Prenom  is Emty");
            IDprenom.requestFocus();

        } else if (x.matcher(IDprenom.getText()).matches()) {
            showMessageDialog(null, "prenom contains only caracter.");
            IDprenom.requestFocus();
        } else if (IDemail.getText().equals("")) {

            showMessageDialog(null, "email is Emty");
            IDemail.requestFocus();
        } else if (IDlogin.getText().equals("")) {

            showMessageDialog(null, "login is Emty");
            IDlogin.requestFocus();

        } else if (!pattern.matcher(IDemail.getText()).matches()) {

            showMessageDialog(null, "email invalid");
            IDemail.requestFocus();

        } else if (IDprenom.getText().length() < 3 || IDprenom.getText().length() > 25) {

            showMessageDialog(null, "Error , lastName text field cannot be less than 3 and greator than 25 characters.");
            IDprenom.requestFocus();

        } else if (IDtel.getText().length() < 8 || IDtel.getText().length() > 8) {

            showMessageDialog(null, "Error , tel text field cannot be less than 8 and greator than 8 characters.");
            IDtel.requestFocus();
        } else if (!x.matcher(IDtel.getText()).matches()) {
            showMessageDialog(null, "telephone contains only number.");
            IDtel.requestFocus();

        } else if (IDmdp.getText().length() < 8 || IDmdp.getText().length() > 25) {

            showMessageDialog(null, "Password text field cannot be less than 8 and greator than 25 characters.");
            IDmdp.requestFocus();

        } else if (IDadresse.getText().equals("")) {

            showMessageDialog(null, "Adresse  is Emty");
            IDadresse.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDretour.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/login/FXMLlogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
