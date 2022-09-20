/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.evenement;

import interfaces.Ievenement;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.Myconnexion;
import java.sql.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import model.Evenement;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLevenementController implements Initializable {

    int index = -1;
    @FXML
    private TextField prix;
    @FXML
    private TableView<Evenement> table;
    @FXML
    private Button IDretour;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    
    @FXML
    private DatePicker Datedebut;
    @FXML
    private DatePicker Datefin;

    
        final FileChooser fc = new FileChooser();
    private File file;
    File xxx = null;
    
    
    @FXML
    private TableColumn<Evenement, Integer> ID;
    @FXML
    private TableColumn<Evenement, String> Date_de_début;
    @FXML
    private TableColumn<Evenement, String> Date_fin;
    @FXML
    private TableColumn<Evenement, String> Prix_ticket;
    ObservableList<Evenement> eventlist = FXCollections.observableArrayList();
    //String query = null;
    //Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Evenement evenement = null;
    ObservableList<Evenement>dataList;
    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TableColumn<Evenement, String> nom_event;
 
    @FXML
    private TextField rech;
    @FXML
    private Button recherch;
    @FXML
    private TextField taimage;
    @FXML
    private TextField nbrticket;
   
    @FXML
    private TableColumn<Evenement, String> nbrdeticket;
    /**
     * Initializes the controller class.
     */
    Connection cnx = Myconnexion.getInstance().getCnx();
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
        Search();
    }
    
      
      

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDretour.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../reservation/market.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("../../gui/reservation/Panier.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("../../gui/event/Categorie.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    private void getDateD(ActionEvent event) {
        //   LocalDate myDate = Datedebut.getValue();
        // String myFormatteDate = myDate.format(DateTimeFormatter.ofPattern("mm-dd-yyyy"));
        // System.out.println(myDate.toString());
        // LocalDate myDatef = Datefin.getValue();
        //  String myFormatteDatef;
        // myFormatteDatef = myDatef.format(DateTimeFormatter.ofPattern("mm-dd-yyyy"));
        // System.out.println(myDatef.toString());

    }
    private boolean isValidated() {
//        String s = categorie.getSelectionModel().getSelectedItem().toString();

        LocalDate myDate = Datedebut.getValue();
        LocalDate myDatef = Datefin.getValue();
        System.out.println(myDate);
        System.out.println(myDatef);
        String number = "[0-9]+";

        Pattern x = Pattern.compile(number);
        if (nom.getText().equals("")) {

            showMessageDialog(null, "Nom text field cannot be blank.");
            nom.requestFocus();

        } else if (myDate == null) {
            showMessageDialog(null, "date  field cannot be blank.");
            Datedebut.requestFocus();
        } else if (myDatef == null) {
            showMessageDialog(null, "date  field cannot be blank.");
            Datefin.requestFocus();
        } else if (myDate.compareTo(myDatef) > 0) {
            showMessageDialog(null, "date");
            Datedebut.requestFocus();
        } else if (!x.matcher(nom.getText()).matches()) {
            showMessageDialog(null, "nom contains only number.");
            nom.requestFocus();

        } else if (!x.matcher(prix.getText()).matches()) {
            showMessageDialog(null, "utilisateur  must be selected");
            prix.requestFocus();
        } else if(!x.matcher(taimage.getText()).matches()) { 
             showMessageDialog(null, "utilisateur  must be selected");
                taimage.requestFocus();
         } else if(!x.matcher(nbrticket.getText()).matches()) { 
             showMessageDialog(null, "utilisateur  must be selected");
                nbrticket.requestFocus();     
        } else {
            return true;
        }
        return false;
    }


    public void getAllinfo() {
       
        try {
            eventlist.clear();
           
            PreparedStatement ps = (PreparedStatement) cnx.prepareStatement("SELECT * FROM `evennement`");
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                Evenement m = new Evenement();
                m.setId(Integer.parseInt(rs.getString("id_evennement")));
                m.setNbrticket(rs.getString("nbrticket"));
                m.setNom_evenement(rs.getString("nom_evenement"));
                m.setDate_debut( rs.getDate("date_debut"));
                m.setDate_fin(rs.getDate("date_fin"));
                m.setPrix_ticket( rs.getFloat("prix_ticket"));
                 eventlist.add(m);
                
                
                //eventlist.add(new Evenement(Integer.parseInt(rs.getString("id_evennement")),
                        //String.valueOf(rs.getInt("nbrticket")),
                        //rs.getString("nom_evenement"),
                        //rs.getDate("date_debut"),
                        //rs.getDate("date_fin"),
                       // rs.getFloat("prix_ticket")));
                       
               // System.out.println(rs.getString("nom_evenement"));
                table.setItems(eventlist);

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLevenementController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @FXML
    private void getDatef(ActionEvent event) {
    }

    @FXML
    private void Add(ActionEvent event) throws SQLException {
          //Connection cnx = MaConnexion.getInstance().getCnx();
        //String sql="INSERT INTO `evennement`(`id_evennement`, `date_debut`, `date_fin`, `prix_ticket`, `nom_evenement`) VALUES ?,?,?,?,?";
       //PreparedStatement ps = (PreparedStatement) cnx.prepareStatement();
       // ps.executeQuery();
        //System.out.println(ps);
         
        //PreparedStatement ps = cnx.prepareStatement(sql);
        //if (this.isValidated()) {
        Evenement even = new Evenement();
        even.setNom_evenement(nom.getText());
        System.out.println(nom.getText());
        even.setDate_debut(java.sql.Date.valueOf(Datedebut.getValue()));
        even.setDate_fin(java.sql.Date.valueOf(Datefin.getValue()));
        even.setPrix_ticket(Integer.parseInt(prix.getText()));
        even.setImages(taimage.getText());
        even.setNbrticket(nbrticket.getText());    
        Ievenement pp = EvenementService.getInstance();
        pp.ajouterEvenement(even);
      
            //ps= cnx.prepareStatement(sql);
            //ps.setString(1,nom.getText());
            //ps.setString(2, String.valueOf(Datedebut.getValue()));
            //ps.setString(3, String.valueOf(Datefin.getValue()));
            //ps.setString(4, String.(prix.getValue()));
           //ps.execute();
            //JOptionPane.showMessageDialog(null, "Transporteur ajouter");
        //}catch(Exception e){
           // JOptionPane.showMessageDialog(null, e);
        //}
        
            
       //
       System.out.println("PS : Evenement ajoutée avec succés!");
        nom.clear();
        Datedebut.setValue(null);
        Datefin.setValue(null);
        nom.clear();
        prix.clear();
        taimage.clear();
        nbrticket.clear();
        showMessageDialog(null, "Evenement  ajouter avec succes");
        getAllinfo();
        
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
           
       
          try {
          //  Connection cnx = MaConnexion.getInstance().getCnx();
            String value1 = id.getText();
            String value2 = nom.getText();
            String value3 = prix.getText();
            String value4 = taimage.getText();
            String value5 = nbrticket.getText();
            
            
            String sql = "update evennement set  nom_evenement= '"+value2+ "',prix_ticket='"+value3+"',image_event='"+value4+"' ,nbrticket= '"+value5+ "' where id_evennement='"+value1+"' ";
            ps= cnx.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Update");
             getAllinfo();
           nom.clear();
           Datedebut.setValue(null);
           Datefin.setValue(null);
           nom.clear();
           prix.clear();
           taimage.clear();
           nbrticket.clear();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
       
    }
   

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
                  
                 //  Connection cnx = MaConnexion.getInstance().getCnx();
            String sql = "DELETE FROM `evennement` WHERE id_evennement= ? ";
    try{
         PreparedStatement pst;
        pst = cnx.prepareStatement(sql);
       pst.setString(1, id.getText());
        pst.execute();
        JOptionPane.showMessageDialog(null,"deleted blog with success ✅✅✅");
  
         getAllinfo();
    } catch (HeadlessException | SQLException e){
         JOptionPane.showMessageDialog(null,e);
    }   
        

    }
   

    private void loadDate() {
        Ievenement x = EvenementService.getInstance();
        eventlist = x.affichageevenement();
        getAllinfo();
       // Connection cnx = MaConnexion.getInstance().getCnx();

        ID.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));
        nom_event.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_evenement"));
        Date_de_début.setCellValueFactory(new PropertyValueFactory<Evenement, String>("date_debut"));
        Date_fin.setCellValueFactory(new PropertyValueFactory<Evenement, String>("date_fin"));
        Prix_ticket.setCellValueFactory(new PropertyValueFactory<Evenement, String>("prix_ticket"));
        nbrdeticket.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nbrticket"));

    }

    @FXML
    private void showEvent(MouseEvent event) {

        Evenement eve = table.getSelectionModel().getSelectedItem();
        //index = table.getSelectionModel().getSelectedIndex();
        id.setText(" " + eve.getId());
        nom.setText(" " + eve.getNom_evenement());
       
        //Date date = eve.getDate_debut("Datedebut");
        //Datedebut.setValue(Date_de_début.getCellData(index));
        System.out.println(eve.getDate_fin());
         //Datedebut.setValue(LocalDate.parse(string, dateFormatter));
        Datedebut.setValue(LocalDate.parse(eve.getDate_debut().toString()));
        Datefin.setValue(LocalDate.parse(eve.getDate_fin().toString()) );
        prix.setText(" " + eve.getPrix_ticket());
        nbrticket.setText(" "+ eve.getNbrticket());
        

    }

    @FXML
    private void Search() {
        ID.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));
        nom_event.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_evenement"));
        Date_de_début.setCellValueFactory(new PropertyValueFactory<Evenement, String>("date_debut"));
        Date_fin.setCellValueFactory(new PropertyValueFactory<Evenement, String>("date_fin"));
        Prix_ticket.setCellValueFactory(new PropertyValueFactory<Evenement, String>("prix_ticket"));
        nbrdeticket .setCellValueFactory(new PropertyValueFactory<Evenement, String>("nbrticket"));
        
        dataList = this.eventlist;
        table.setItems(dataList);
        FilteredList<Evenement> filteredData = new FilteredList<>(dataList, b -> true);  
        rech.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getNom_evenement().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Evenement> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(table.comparatorProperty());  
  table.setItems(sortedData);      
    }

    @FXML
    private void uploadimage(ActionEvent event) throws IOException {
        
        fc.setTitle("Uplode Image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {

            String x = file.getAbsolutePath();
            String newpath = "uploads/bonplans/";
            File dir = new File(newpath);
            if (!dir.exists()) {
                // folder wa7dd ken barchaa mkdirs
                dir.mkdirs();
            }
            File sourceFile = null;
            File destinationFile = null;
            String extension = x.substring(x.lastIndexOf('.') + 1);
            sourceFile = new File(x);
            xxx = new File(newpath + randomStringforimage() + "." + extension);
            Files.copy(sourceFile.toPath(), xxx.toPath());
            //   System.out.println(destinationFile);
            System.out.println(xxx);
            taimage.appendText(file.getAbsolutePath() + "\n");
            //img.setImage(new Image(file.toURI().toString()));
        } else {
            System.out.println("file is invalide");
        }
    }

    public static String randomStringforimage() {
        //   String  randomString  =null;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 12;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }
        String randomString = sb.toString();

        return randomString;
    }

}
