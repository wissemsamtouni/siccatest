/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panier;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PanierController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private TextField recherche;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private TextFlow txtarea;
    @FXML
    private Button btnupdate11;
    @FXML
    private Spinner<?> number;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnupdate1;
    @FXML
    private Button btndelete;
    @FXML
    private Label showstock;
    @FXML
    private Button addcart;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtidproduit;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prix;
    @FXML
    private TextField txtcat;
    @FXML
    private TextField nomproduit;
    @FXML
    private TextField txtdes;
    @FXML
    private ImageView img;
    @FXML
    private Button totalpanier;
    @FXML
    private Button register;
    @FXML
    private Button login;
    @FXML
    private Label labelnom;
    @FXML
    private Button btnSignout;
    @FXML
    private ImageView image;
    @FXML
    private Button btnAboutus;
    @FXML
    private Button btnContact;
    @FXML
    private Button idcat;
    @FXML
    private HBox avishbox;
    @FXML
    private Button avis;
    @FXML
    private HBox profilehbox;
    @FXML
    private Button btnprofile;
    @FXML
    private HBox histbox;
    @FXML
    private Button historique;
    @FXML
    private HBox quizhbox;
    @FXML
    private Button quiz;
    @FXML
    private HBox adresshbox;
    @FXML
    private Button btnadresse;
    @FXML
    private Button btnAccueil;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rechercherproduit(ActionEvent event) {
    }

    @FXML
    private void updateproduit(ActionEvent event) {
    }

    @FXML
    private void deleteproduit(ActionEvent event) {
    }

    @FXML
    private void ajouterpanier(ActionEvent event) {
    }

    @FXML
    private void showpanier(MouseEvent event) {
    }

    @FXML
    private void totalpanier(ActionEvent event) {
    }

    @FXML
    private void register(ActionEvent event) {
    }

    @FXML
    private void login(ActionEvent event) {
    }

    @FXML
    private void btnSignout(ActionEvent event) {
    }

    @FXML
    private void about(ActionEvent event) {
    }

    @FXML
    private void contact(ActionEvent event) {
    }

    @FXML
    private void categorie(ActionEvent event) {
    }

    @FXML
    private void avis(ActionEvent event) {
    }

    @FXML
    private void Profile(ActionEvent event) {
    }

    @FXML
    private void Historique(ActionEvent event) {
    }

    @FXML
    private void quiz(ActionEvent event) {
    }

    @FXML
    private void adresse(ActionEvent event) {
    }

    @FXML
    private void Accueil(ActionEvent event) {
    }
    
}
