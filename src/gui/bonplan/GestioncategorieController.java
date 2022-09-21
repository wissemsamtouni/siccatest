/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.bonplan;

import interfaces.Bpinerface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.Catcategories;
import interfaces.Icategories;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import model.Bonplans;
import model.Categories;
import services.bpservice;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author ahmed jb
 */
public class GestioncategorieController implements Initializable {
int index = -1;
ObservableList<Categories> tbc = FXCollections.observableArrayList();
    @FXML
    private TextField idcategorie;
    @FXML
    private TextField tfcategorie;
    @FXML
    private TableView<Categories> tvcategorie;
    @FXML
    private TableColumn<Categories, Integer> colidcategorie;
    @FXML
    private TableColumn<Categories, String> coltypecat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        affichercategorie();
        tbc.clear();
    }    

    @FXML
    private void ajoutercategorie(ActionEvent event) {
         if(tfcategorie.getText().equals("")){
//            showMessageDialog(null, "le chomp categorie est vide");
                  Notification.notificationError("le chomp categorie est vide", "");
            
        tfcategorie.requestFocus();
        }
                else if ((!tfcategorie.getText().matches("[A-Za-z]") )){
//                showMessageDialog(null, "ajouter des caractères");
                   Notification.notificationError("ajouter des caractères", "");
            
        tfcategorie.requestFocus(); 
         }
       
          else if(tfcategorie.getText().length()<=2){
//                  showMessageDialog(null, "le chomp  et ferieur a 2");
                    Notification.notificationError("le chomp  et ferieur a 2", "");
            
        tfcategorie.requestFocus();
          } 
           else{
        Icategories ct = new Catcategories();
        Categories c = new Categories(tfcategorie.getText());
        ct.ajoutercategorie(c);
        affichercategorie();
//        showMessageDialog(null, "categorie ajouter avec succes");
          Notification.notificationSuccess("categorie ajouter avec succes", "");
        
    }
    }

    @FXML
    private void modifiercategorie(ActionEvent event) {
         Icategories ct = new Catcategories();
        Categories c = new Categories(Integer.parseInt(idcategorie.getText()),tfcategorie.getText());
        ct.modifiercategories(c);
//        showMessageDialog(null, "categorie modifier avec succes");
          Notification.notificationSuccess("categorie modifier avec succes", "");
        affichercategorie();
    }

    @FXML
    private void supprimercategorie(ActionEvent event) {
       
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous supprimer");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){
               Icategories ct = new Catcategories();
        Categories c = new Categories(Integer.parseInt(idcategorie.getText()),tfcategorie.getText());
        ct.supprimercategorie(c);
//        showMessageDialog(null, "categorie supprimer avec succes");
          Notification.notificationSuccess("categorie supprimer avec succes", "");
        affichercategorie();
    }}
    

    @FXML
    private void anuuler(ActionEvent event) {
        idcategorie.clear();
        tfcategorie.clear();
    }

    @FXML
    private void aller_a_bonplans(ActionEvent event) throws IOException {
            Stage stage = (Stage) tfcategorie.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../../gui/bonplan/Gestionbonplans.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
    
    

    @FXML
    private void selectionercat(MouseEvent event) {
         index = tvcategorie.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
           idcategorie.setText(colidcategorie.getCellData(index).toString());
        tfcategorie.setText(coltypecat.getCellData(index));
    }
    
      public void affichercategorie() {

        Icategories bt = new Catcategories();
        ObservableList<Categories> tbc = FXCollections.observableArrayList(bt.affichercategorie());
        colidcategorie.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        coltypecat.setCellValueFactory(new PropertyValueFactory<>("type_categories"));
        
        tvcategorie.setItems(tbc);

    }
    
    
}
