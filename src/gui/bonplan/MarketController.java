package gui.bonplan;

import interfaces.Bpinerface;
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
import model.Bonplans;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import siccapalans.NewFXMain;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import services.bpservice;
import siccapalans.NewFXMain;
import util.Myconnexion;

public class MarketController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    private Label fruitNameLable;

    private Label fruitPriceLabel;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Bonplans> xxx = new ArrayList<>();
    private List<Bonplans> BPLS = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    Connection cnx = Myconnexion.getInstance().getCnx();


    @FXML
    private Label bonplansNameLable;
    @FXML
    private Label categorieLabel;
    @FXML
    private ImageView imgbonolans;
    @FXML
    private Label Descriptionlabel;
    @FXML
    private Button lacalisationbtn;
    @FXML
    private ComboBox<Bonplans> cbcategorie;
    @FXML
    private Label bonplansNameLable1;
    @FXML
    private Label horairlabel;
    @FXML
    private Label fraislabel;

    private List<Bonplans> getData() throws SQLException {
        List<Bonplans> BPLS = new ArrayList<>();
        Bonplans bpl;

        String tt = "SELECT * FROM `bonplan`";
        Statement statement;

        statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(tt);
        while (rs.next()) {
            bpl = new Bonplans();
            bpl.setNom_bonplan(rs.getString("nom_bonplan"));
            bpl.setImages(rs.getString("images"));
            bpl.setDescription(rs.getString("description_bonplan"));
            bpl.setType_categorie(rs.getString("type_categorie"));
            bpl.setHoraire(rs.getString("horaire"));
            bpl.setFrais(rs.getDouble("frais"));
            BPLS.add(bpl);

        }

        return BPLS;

    }

    private List<Bonplans> getDataf() throws SQLException {
        List<Bonplans> xxx = new ArrayList<>();
        Bonplans bpl;

        String a = cbcategorie.getSelectionModel().getSelectedItem().toString();
        String tt = "SELECT * FROM bonplan where type_categorie='" + a + "' ";

        Statement statement;

        statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(tt);
        while (rs.next()) {
            bpl = new Bonplans();
            bpl.setNom_bonplan(rs.getString("nom_bonplan"));
            bpl.setImages(rs.getString("images"));
            bpl.setDescription(rs.getString("description_bonplan"));
            bpl.setType_categorie(rs.getString("type_categorie"));
            bpl.setHoraire(rs.getString("horaire"));
            bpl.setFrais(rs.getDouble("frais"));
            xxx.add(bpl);

        }

        return xxx;
    }

    private void setChosenBonplans(Bonplans bpl) {
        bonplansNameLable.setText(bpl.getNom_bonplan());
        categorieLabel.setText(bpl.getType_categorie());
        Descriptionlabel.setText(bpl.getDescription());
        horairlabel.setText(bpl.getHoraire());
        fraislabel.setText(bpl.getFrais() + NewFXMain.currency);
        String path = bpl.getImages();
        Image imagebp = new Image("file:" + path);
        imgbonolans.setImage(imagebp);
//        chosenFruitCard.setStyle("-fx-background-color: #" + bpl.getImages() + ";\n"
//                + "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//          init();  
        init1();
        intitlist();
BPLS.clear();
//      
    }

    @FXML
    private void filter(ActionEvent event) throws SQLException {
 grid.getChildren().clear();
        xxx.clear();

        try {

            xxx.addAll(getDataf());
            if (xxx.size() > 0) {
                setChosenBonplans(xxx.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Bonplans bpl) {
                        setChosenBonplans(bpl);
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < xxx.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../../gui/bonplan/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(xxx.get(i), myListener);

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
    }

//       public void init() {
//        Bpinerface modelbp= new bpservice();
//        ObservableList<Bonplans> mList = FXCollections.observableArrayList(modelbp.afficherBP());
//        cbbonplan.setItems(mList);
//    }
    public void init1() {
        Bpinerface modelcat = new bpservice();
        ObservableList<Bonplans> mList = FXCollections.observableArrayList(modelcat.afficherBP());
        cbcategorie.setItems(mList);
    }
    private void intitlist(){
            try {
            BPLS.addAll(getData());
            if (BPLS.size() > 0) {
                setChosenBonplans(BPLS.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Bonplans bpl) {
                        setChosenBonplans(bpl);
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < BPLS.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../../gui/bonplan/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(BPLS.get(i), myListener);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row); //(child,column,row)
//                    set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

//                    set grid height
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
    }
}
