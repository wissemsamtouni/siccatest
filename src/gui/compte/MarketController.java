package gui.compte;

import Categorie_event.CategorieController;
import gui.reservation.ItemController;
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
import javafx.scene.control.SpinnerValueFactory;
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
    private Button enstock;
    @FXML
    private TextField quantite;
    private Button horsstock;
    private Label showstock;
    @FXML
    private ImageView fruitImg;
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
    private Spinner<Integer> number;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    private List<Evenement> getData() throws SQLException {
        List<Evenement> events = new ArrayList<>();
        Evenement event;
        String tt = "SELECT * FROM `reservation` where id_utilisateur ='" + Statics.current_user.getId_utilisateur()+ "'";

        Statement statement;

        statement = cnx.createStatement();
        ResultSet xx = statement.executeQuery(tt);
        while (xx.next()) {
            event = new Evenement();
            event.setNom_evenement(xx.getString("nom_evennement"));
            //event.setImages(xx.getString("image_event"));
            //event.setDate_debut(xx.getDate("date_debut"));
          //event.setDate_fin(xx.getDate("date_fin"));
            event.setId(xx.getInt("id_evennement"));
            event.setId(xx.getInt("id_reservation"));
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
          quantite.setText(event.getPrix_ticket()+ siccatestFXMain.CURRENCY);
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
        valueFactory.setValue(1);
        number.setValueFactory(valueFactory);
        
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

    @FXML
    private void updateproduit(ActionEvent event) throws IOException, SQLException {
         
          PreparedStatement ps, ps2, ps3, ps4;
        ResultSet rs, rs2 = null;

        String y = "select * from reservation WHERE id_reservation = ?";
        System.out.println(y);
        ps4 = cnx.prepareStatement(y);
        ps4.setString(1, txtimage.getText());
        rs2 = ps4.executeQuery();
        while (rs2.next()) {
            Integer s1 = rs2.getInt("nbr_ticket");
            Integer x = number.getValue() + s1;
            Integer yy = number.getValue();
            String query = "select * from evennement WHERE id_evennement = ?";

            ps = cnx.prepareStatement(query);
            ps.setString(1, idproduit.getText());

            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("stock_tiket") >= yy) {
                    System.out.println("update succes");
                    String xx = "update evennement set nbrticket = nbrticket - ? where id_evennement =? ";
                    ps2 = cnx.prepareStatement(xx);
                    ps2.setInt(1, yy);
                    ps2.setString(2, idproduit.getText());
                    ps2.execute();
                    String xxx = "update reservation set nbr_ticket =?  , prix_ticket=? * ?  where id_reservation =? and id_utilisateur =?";
                    ps3 = cnx.prepareStatement(xxx);
                    ps3.setInt(1, x);
                    ps3.setInt(2, x);
                    ps3.setString(3, quantite.getText());
                    ps3.setString(4, txtimage.getText());
                    ps3.setInt(5, Statics.current_user.getId_utilisateur());
                    ps3.execute();

                    totalpanier.setText(String.valueOf(refreshpanier()));
                     Stage stage = (Stage) register.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../gui/compte/market.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(1000);
                stage.setMaxWidth(1500);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();

//        
//                    try {
//                        totalprix.setText(String.valueOf(totalprix()));
//                    } catch (SQLException ex) {
//                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    refreshtableproduit();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Produit");

                    alert.setContentText("produit hors stock ");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        System.out.println("produit hors stock");
                    }

                }
            }
        }

        
    }
    
    @FXML
    private void delet(ActionEvent event) throws SQLException, IOException {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" produit");

        alert.setContentText("delete ");
        if (alert.showAndWait().get() == ButtonType.OK) {

            PreparedStatement ps2, ps3, ps4;
            ResultSet rs, rs2 = null;
            String y = "select * from panier WHERE id = ?";

            ps4 = cnx.prepareStatement(y);
            ps4.setString(1, txtimage.getText());
            rs2 = ps4.executeQuery();
            while (rs2.next()) {
                Integer s1 = rs2.getInt("stock_ticket");
                System.out.println(s1);

                String xxx = "update evenement set stock_tiket =stock_tiket+? where id_event =? ";

                ps2 = cnx.prepareStatement(xxx);
                ps2.setInt(1, s1);

                ps2.setString(2, idproduit.getText());
                ps2.execute();

            }

            PreparedStatement ps;
            //   ResultSet rs;
            String id = txtimage.getText();

            String yy = "delete   from  panier where id = '" + id + "' ";
            ps = cnx.prepareStatement(yy);
            ps.execute();

              Stage stage = (Stage) register.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../gui/compte/market.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(1000);
                stage.setMaxWidth(1500);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();

//        
            totalpanier.setText(String.valueOf(refreshpanier()));
//            try {
//                if (refreshpanier() == 0) {
//                    btnpaiment.setVisible(false);
//                    labeltotalprix.setVisible(false);
//                    totalprix.setVisible(false);
//                    labelcoupon.setVisible(false);
//                    combocoupoin.setVisible(false);
//                    btnpaiment.setVisible(false);
//                    btnajoutercoupon.setVisible(false);
//                    labelpaniervide.setVisible(true);
//
//                    acheterproduit.setVisible(true);
//
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
//            }

        }
    }
    
    private void gotopanier(MouseEvent event) throws IOException {
        
            Stage stage = (Stage) register.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../gui/compte/market.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(1000);
                stage.setMaxWidth(1500);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();
            }



    


}