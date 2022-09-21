/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.promo;

import interfaces.IPromo;
import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import model.Promo;
import model.Utilisateur;
import services.PromoService;
import services.UtilisateurService;
import util.Myconnexion;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author wissem
 */
public class FXMLPromoController implements Initializable {

    int index = -1;
    @FXML
    private TextField txtPourcentage_reduction;
    @FXML
    private ComboBox<Utilisateur> combouser;
    @FXML
    private DatePicker txtDate_expiration;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TableView<Promo> table;
    @FXML
    private TableColumn<Promo, Integer> IdColmn;
    @FXML
    private TableColumn<Promo, LocalDate> Date_expirationColmn;
    @FXML
    private TableColumn<Promo, Integer> Pourcentage_reductionColmn;
    @FXML
    private TableColumn<Promo, Utilisateur> Id_utilisateurColmn;
    private final ObservableList<Utilisateur> listuser = FXCollections.observableArrayList();
    Connection cnx = Myconnexion.getInstance().getCnx();
    ObservableList<Promo> promolist = FXCollections.observableArrayList();
    @FXML
    private TextField id;
    @FXML
    private Button IDretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        showuser();
        refresh();
    }

    public void refresh() {
        IPromo x = PromoService.getInstance();
        promolist = x.affichagepromo();

        IdColmn.setCellValueFactory(new PropertyValueFactory<>("id_promo"));
        Date_expirationColmn.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        Pourcentage_reductionColmn.setCellValueFactory(new PropertyValueFactory<>("poucentage_reduction"));
        //   idimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        Id_utilisateurColmn.setCellValueFactory(new PropertyValueFactory<>("id_utlisateur"));

//
        table.setItems(promolist);

    }

    public void showuser() {

        listuser.clear();
        String tt = "SELECT * FROM `utilisateur`";

        Statement statement;

        try {
            statement = cnx.createStatement();
            ResultSet x = statement.executeQuery(tt);
            while (x.next()) {
                String nom = x.getString("nom");

                listuser.add(new Utilisateur(nom));
                combouser.setItems(listuser);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPromoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Add(ActionEvent event) {
        if (this.isValidated()) {

            String s = combouser.getSelectionModel().getSelectedItem().toString();
            PreparedStatement ps, cat;
            ResultSet rs, rs2;

            Promo promo = new Promo();
            promo.setDate_expiration(txtDate_expiration.getValue());
            promo.setPoucentage_reduction(Integer.parseInt(txtPourcentage_reduction.getText()));
            IUtilisateur tt = UtilisateurService.getInstance();

            promo.setId_utlisateur(tt.findUserBynom(s));

            IPromo pp = PromoService.getInstance();
            pp.ajouterPromo(promo);
            refresh();
            System.out.println("PS : promo ajoutée avec succés!");

            txtDate_expiration.setValue(null);

            txtPourcentage_reduction.clear();
            showuser();
            Notification.notificationSuccess("promo ajouter avec succes ", "");
            //showMessageDialog(null, "promo ajouter avec succes");

        }
    }

    private boolean xx() {
//        String s = categorie.getSelectionModel().getSelectedItem().toString();

        LocalDate myDate = txtDate_expiration.getValue();
        System.out.println(myDate);
        String number = "[0-9]+";

        Pattern x = Pattern.compile(number);
        if (txtPourcentage_reduction.getText().equals("")) {

            Notification.notificationError("le champ de pourcentage ne peut pas etre vide ");
            //showMessageDialog(null, "pourcentage text field cannot be blank.");
            txtPourcentage_reduction.requestFocus();

        } else if (myDate == null) {
            Notification.notificationError("le champ date ne peut pas etre vide ");
            //showMessageDialog(null, "date  field cannot be blank.");
            txtDate_expiration.requestFocus();
        } else if (!x.matcher(txtPourcentage_reduction.getText()).matches()) {
            Notification.notificationError("le champ pourcentage contient seulement des chiffres ");
            //showMessageDialog(null, "pourcentage contains only number.");
            txtPourcentage_reduction.requestFocus();

        } else {
            return true;
        }
        return false;
    }

    private boolean isValidated() {
//        String s = categorie.getSelectionModel().getSelectedItem().toString();

        LocalDate myDate = txtDate_expiration.getValue();
        System.out.println(myDate);
        String number = "[0-9]+";

        Pattern x = Pattern.compile(number);
        if (txtPourcentage_reduction.getText().equals("")) {
            Notification.notificationError("le champ de pourcentage ne peut pas etre vide ");

            //showMessageDialog(null, "pourcentage text field cannot be blank.");
            txtPourcentage_reduction.requestFocus();

        } else if (myDate == null) {
            Notification.notificationError("le champ date ne peut pas etre vide ");
            //showMessageDialog(null, "date  field cannot be blank.");
            txtDate_expiration.requestFocus();
        } else if (!x.matcher(txtPourcentage_reduction.getText()).matches()) {
            Notification.notificationError("le champ pourcentage contient seulement des chiffres ");
            //showMessageDialog(null, "pourcentage contains only number.");
            txtPourcentage_reduction.requestFocus();

        } else if (combouser.getSelectionModel().isSelected(-1)) {
            Notification.notificationError("utilisateur  must be selected");
            //showMessageDialog(null, "utilisateur  must be selected");
            combouser.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {

        if (txtPourcentage_reduction.getText().equals("") && combouser.getSelectionModel().isSelected(-1)) {
            Notification.notificationError("vous devez selectionner le promo a modifier ");
            //showMessageDialog(null, "you must select utilisateur");
        } else {

            PreparedStatement ps;
            ResultSet rs2;
            String s = combouser.getSelectionModel().getSelectedItem().toString();
            //InterfaceUser x = ServiceUser.getInstance();
            String query = "select * from utilisateur WHERE nom = ?";
            ps = cnx.prepareStatement(query);
            ps.setString(1, s);
            rs2 = ps.executeQuery();
            if (rs2.next()) {

                String s1 = rs2.getString("id_utilisateur");
                PreparedStatement pss;
                ResultSet rs;

                String xx = id.getText();
                LocalDate myDate = txtDate_expiration.getValue();

                String yy = "  update promo set  date_expiration ='" + myDate + "' , pourcentage_reduction ='" + txtPourcentage_reduction.getText() + "',  id_utilisateur ='" + s1 + "'  where id_promo = '" + xx + "' ";
                pss = cnx.prepareStatement(yy);

                pss.execute();
                Notification.notificationSuccess("promo  modifier avec succes", "");

                //showMessageDialog(null, "promo  modifier avec succes");
                refresh();
            }
        }

    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        if (this.xx()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Supprimer promo");
            alert.setHeaderText("You're about to delete promo!");
            alert.setContentText("Do you want to delete ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;
                ResultSet rs;
                Integer xx = Integer.parseInt(id.getText());

                String yy = "delete   from  promo where id_promo = '" + xx + "' ";
                ps = cnx.prepareStatement(yy);
                ps.execute();
                Notification.notificationSuccess("promo  supprimer avec succes", "");

                //showMessageDialog(null, "promo  supprimer avec succes");
                txtDate_expiration.setValue(null);

                txtPourcentage_reduction.clear();
                showuser();
                refresh();

            }
        }
    }

    @FXML
    void getSelected(MouseEvent event) {

        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtDate_expiration.setValue(Date_expirationColmn.getCellData(index));
        txtPourcentage_reduction.setText(String.valueOf(Pourcentage_reductionColmn.getCellData(index)));
        Utilisateur xx = Id_utilisateurColmn.getCellData(index);
        id.setText(String.valueOf(IdColmn.getCellData(index)));
        combouser.getSelectionModel().select(xx);

    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDretour.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/dashboardadmin/FXMLdashboardadmin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Admin");
        stage.getIcons().add(new Image("gui/dashboardadmin/Untitled design (2).png"));
        stage.show();
    }

}
