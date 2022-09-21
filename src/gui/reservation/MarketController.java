package gui.reservation;

import Categorie_event.CategorieController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import interfaces.MyListener;
import model.Evenement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import model.Bonplans;
import siccatest.siccatestFXMain;
import util.Myconnexion;
import util.Statics;

public class MarketController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    private Label fruitPriceLabel;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    private int id;
    private List<Evenement> event = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    Connection cnx = Myconnexion.getInstance().getCnx();
    private Label aa;
    private Label bonplansNameLable;
    private Label categorieLabel;
    private ImageView imgbonolans;
    private Label Descriptionlabel;
    private Label eventNameLable;
    private Label prixLabel;
    private ImageView imgevent;
    private Label labeldd;
    private Label labeldf;
    private Button Reserver;
    @FXML
    private TextField rechevent;
  ObservableList<Evenement> eventlist = FXCollections.observableArrayList();
    private Label idevent;
    @FXML
    private Button enstock;
    @FXML
    private TextField quantite;
    @FXML
    private Button horsstock;
    private Label showstock;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Button addcart;
    @FXML
    private TextField idproduit;
    @FXML
    private TextField txtimage;
    @FXML
    private TextField txtcat;
    @FXML
    private TextField txtcanomproduit;
    @FXML
    private TextField txtdes;
    @FXML
    private TextField prix;
    @FXML
    private Button register;
    @FXML
    private Button totalpanier;
    @FXML
    private Button idretour;
    private List<Evenement> getData() throws SQLException {
        List<Evenement> events = new ArrayList<>();
        Evenement event;
        String tt = "SELECT * FROM `evennement`";

        Statement statement;

        statement = cnx.createStatement();
        ResultSet xx = statement.executeQuery(tt);
        while (xx.next()) {
            event = new Evenement();
            event.setNom_evenement(xx.getString("nom_evenement"));
            event.setImages(xx.getString("image_event"));
            event.setDate_debut(xx.getDate("date_debut"));
            event.setDate_fin(xx.getDate("date_fin"));
            event.setId(xx.getInt("id_evennement"));
            
            event.setPrix_ticket(xx.getFloat("prix_ticket"));

            events.add(event);

        }

        return events;
    }

    private void setChosenBonplans(Evenement event) {
       // id = event.getId();
        //Preferences userpreferences = Preferences.userRoot();
        //userpreferences.put("idEve", String.valueOf(id));
       txtcanomproduit.setText(event.getNom_evenement());
        //quantite.setText(String.valueOf(event.getPrix_ticket()));
          quantite.setText(String.valueOf(event.getPrix_ticket()));
        idproduit.setText(String.valueOf(event.getId()));
      
       txtimage.setText(String.valueOf(event.getDate_debut()));
       txtdes.setText(String.valueOf(event.getDate_fin()));
       String path = event.getImages();
       Image imagebp = new Image("file:" + path);
       fruitImg.setImage(imagebp);
       
        
      chosenFruitCard.setStyle("-fx-background-color: #" + event.getImages() + ";\n"
                + "    -fx-background-radius: 30;");
      
    }
     public Integer refreshpanier() throws SQLException {

        int x = 0;
        Statement stmt = cnx.createStatement();
        String query = "select SUM(nbr_ticket) from reservation where id_utilisateur='" + Statics.current_user.getId_utilisateur()+ "'";

        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        x = rs.getInt(1);
        return x;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            event.addAll(getData());
      
        if (event.size() > 0) {
            setChosenBonplans(event.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Evenement event) {
                    setChosenBonplans(event);
                }

                @Override
                public void onClickListener(Bonplans bpl) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../gui/reservation/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(event.get(i), myListener);
                
                if (column == 3) {
                    column = 0;
                    row++;
                }
                
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     private void setChosenFruit(Evenement fruit){
          
        //  System.out.println(quantite.getText());
        if (quantite.getText().equals("0")) {
            horsstock.setVisible(true);

            enstock.setVisible(false);
            Reserver.setVisible(false);
            showstock.setVisible(false);
            //    ajouterpanierr.setVisible(false);
        } else {
            enstock.setVisible(true);

            Reserver.setVisible(true);
            showstock.setVisible(true);
            showstock.setText("il reste " + quantite.getText() + " produits");
            horsstock.setVisible(false);

     }
  
        
    
  }



    
   
    
    private void gotopanier(MouseEvent event) throws IOException {
        
            Stage stage = (Stage) register.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../compte/market.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(1000);
                stage.setMaxWidth(1500);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();
            }



    @FXML
    private void ajouterPanier(ActionEvent event) throws SQLException, IOException {
                    System.out.println("hiii");
       if (Statics.current_user.getNom()== null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("login");
            alert.setHeaderText("login required!");
            alert.setContentText("you must login  ");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Stage stage = (Stage) addcart.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../login/FXMLlogin.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(500);

                stage.setMaxWidth(600);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();

            }
        } else {
            PreparedStatement ps, psx;
            ResultSet rs, rsx;
            String id_user = String.valueOf(Statics.current_user.getId_utilisateur());
            
            System.out.println(Statics.current_user.getId_utilisateur());
            //String produit = txtcanomproduit.getText();
            
           String nom_evennement = txtcanomproduit.getText();
           System.out.println(quantite.getText());
            float prix_ticket = Float.parseFloat( quantite.getText());
            //String image = txtimage.getText();
         
            String id_evennement = idproduit.getText();
            int nbr_ticket = 1;
            //String nbr_ticket = txtcat.getText();
            //  String xx = id.getText();
            // String yy = "delete   from  categories where name = '" + nom + "' ";
            //String yy = "INSERT INTO evennement(date_debut,date_fin,prix_ticket,nom_evenement,image_event,nbrticket) VALUES (?,?,?,?,?,?)";
            String yy = "INSERT INTO reservation(`id_utilisateur`,`id_evennement`,`nbr_ticket`,`nom_evennement`,`prix_ticket`) VALUES (?,?,?,?,?)";
            // String req = "INSERT INTO `categories`(`name`) VALUES ( ?)";
            String yyy = "SELECT * FROM reservation WHERE id_utilisateur ='" + id_user + "'  and  nom_evennement='" + id_evennement + "'  ";
            psx = cnx.prepareStatement(yyy);

            rsx = psx.executeQuery();
            if (rsx.next()) {

                showMessageDialog(null, "produit deja ajouter au paneir");
            } else {

                ps = cnx.prepareStatement(yy);
                System.out.println(ps);
                ps.setString(1, id_user);
                ps.setString(2,id_evennement);
                ps.setInt(3, nbr_ticket);
                ps.setString(4, nom_evennement);
               ps.setFloat(5, prix_ticket);
                System.out.println(id_user);
                System.out.println(id_evennement);
                System.out.println(nbr_ticket);
                System.out.println(nom_evennement);
               
                System.out.println(prix_ticket);
                ps.executeUpdate();
               
                PreparedStatement ps4, ps5;
                ResultSet rs5;

                String xxx = "update evennement set nbrticket =nbrticket-? where id_evennement =? ";

                ps5 = cnx.prepareStatement(xxx);
                ps5.setInt(1, 1);

                ps5.setString(2, id_evennement);
                System.out.println(idproduit.getText());
                ps5.execute();

                showMessageDialog(null, "produit  ajouter avec succes au panier");
                try {
                    totalpanier.setText(String.valueOf(refreshpanier()));
                    System.out.println(refreshpanier());
                } catch (SQLException ex) {
                    Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                }
//                Stage stage = (Stage) register.getScene().getWindow();
//                stage.close();
//
//                Parent root = FXMLLoader.load(getClass().getResource("../../gui/Categorie_Client/Categorie_Client.fxml"));
//
//                Scene scene = new Scene(root);
//                stage.setMaxHeight(1000);
//                stage.setMaxWidth(1500);
//                stage.setScene(scene);
//                stage.setResizable(true);
//
//                stage.setTitle("Login");
//                //
//                stage.show();
            }
        }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        
        
         Stage stage = (Stage) idretour.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/acceuil/FXMLacceuil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("liste des utilisateurs");
        stage.getIcons().add(new Image("gui/dashboardadmin/Untitled design (2).png"));
        stage.show();
    }




    


}