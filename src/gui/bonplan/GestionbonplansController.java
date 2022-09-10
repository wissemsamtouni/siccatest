/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.bonplan;

import interfaces.Bpinerface;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.Random;
import interfaces.Mapsinterface;
import model.Maps;
import services.Mapsservice;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import model.Bonplans;

import services.bpservice;

import util.Myconnexion;

/**
 * FXML Controller class
 *
 * @author ahmed jb
 */
public class GestionbonplansController implements Initializable {

    Connection cnx = Myconnexion.getInstance().getCnx();
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfbonplan;

    @FXML
    private TextField tadescription;
    @FXML
    private TableView<Bonplans> tvbonplan;
    @FXML
    private TableColumn<Bonplans, Integer> colidplon;
    @FXML
    private TableColumn<Bonplans, String> colcategoriebp;
    @FXML
    private TableColumn<Bonplans, String> coldescriptionbp;
    @FXML
    private TableColumn<Bonplans, String> colnombonplan;
    @FXML
    private TableColumn<Bonplans, String> coladresse;

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;

    @FXML
    private TextField tfcategorie;
    @FXML
    private Button btmannuler;
    final FileChooser fc = new FileChooser();
    private File file;
    File xxx = null;
    /**
     * Initializes the controller class.
     */
    ObservableList<Bonplans> tbp = FXCollections.observableArrayList();
    ObservableList<Maps> mpb = FXCollections.observableArrayList();
    int index = -1;

    @FXML
    private TextField tfidbp;
    @FXML
    private TextField taimage;
    @FXML
    private ImageView img;
    @FXML
    private TableColumn<Bonplans, String> colsrcimage;
    @FXML
    private TextField tfidplan;

    @FXML
    private VBox ta;
    @FXML
    private Button btnshow;
    @FXML
    private VBox ta1;
    @FXML
    private TextField tfville;
    @FXML
    private TextField tflatitude;
    @FXML
    private TextField tfllogitude;
    @FXML
    private TextField idmap;
    @FXML
    private TextField tffrais;
    @FXML
    private TextField tfhoraire;
    @FXML
    private TableView<Maps> tvbonplan1;
    @FXML
    private TableColumn<Maps, Integer> colidmap;
    @FXML
    private TableColumn<Maps, String> colvile;
    @FXML
    private TableColumn<Maps, Double> collogitude;
    @FXML
    private TableColumn<Maps, Double> collatidude;
    @FXML
    private TableColumn<Bonplans, Double> colfrais;
    @FXML
    private TableColumn<Bonplans, String> colhorair;

//    public GestionbonplansController() {
//        this.tbp = FXCollections.observableArrayList();
//    }
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        showtablebp1();
        showtablebp();

        tbp.clear();
    }

    @FXML
    private void reset(ActionEvent event) {
        tfadresse.clear();
        tfbonplan.clear();
        tfcategorie.clear();
        tfidbp.clear();
        tadescription.clear();
        taimage.clear();

    }

    @FXML
    private void addbonplans(ActionEvent event) {
        if (tfbonplan.getText().equals("")) {
            showMessageDialog(null, "le chomp bonplan est vide");
            tfbonplan.requestFocus();
        } else if (tfadresse.getText().equals("")) {
            showMessageDialog(null, "le chomp adresse est vide");
            tfadresse.requestFocus();
        } else if (tfcategorie.getText().equals("")) {
            showMessageDialog(null, "le chomp categorie est vide");
            tfcategorie.requestFocus();
        } else if (tadescription.getText().equals("")) {
            showMessageDialog(null, "le chomp description est vide");
            tadescription.requestFocus();
        } else {
            Bpinerface bt = new bpservice();
            Mapsinterface mps = new Mapsservice();
            Maps mp = new Maps(tfville.getText(), Double.parseDouble(tfllogitude.getText()), Double.parseDouble(tflatitude.getText()));
            Maps generatedMaps = mps.ajoutermaps(mp);
            //..
            Bonplans bp = new Bonplans(tfbonplan.getText(), tfcategorie.getText(), tfadresse.getText(), tadescription.getText(), String.valueOf(xxx), Double.parseDouble(tffrais.getText()), tfhoraire.getText());
            bp.setMaps(generatedMaps);
            bt.ajouterBP(bp);
            showtablebp();

            showMessageDialog(null, "Bonplans ajouter avec succes");

        }
    }

    @FXML
    private void modifybonplans(ActionEvent event) {
        Bpinerface bt = new bpservice();
        Mapsinterface mps = new Mapsservice();
        Maps mp = new Maps(Integer.parseInt(idmap.getText()), tfville.getText(), Double.parseDouble(tfllogitude.getText()), Double.parseDouble(tflatitude.getText()));
        mps.modifiermaps(mp);
        //.... 
        Bonplans bp = new Bonplans(Integer.parseInt(tfidplan.getText()), tfbonplan.getText(), tfcategorie.getText(), tfadresse.getText(), tadescription.getText(), String.valueOf(xxx), Double.parseDouble(tffrais.getText()), tfhoraire.getText());
        bt.modifierBP(bp);
        showtablebp();
        showMessageDialog(null, "Bonplan Modifier avec succes");
    }

    @FXML
    private void deletebonplan(ActionEvent event) {
        Bpinerface bt = new bpservice();
        Mapsinterface mps = new Mapsservice();
        Maps mp = new Maps(Integer.parseInt(idmap.getText()), tfville.getText(), Double.parseDouble(tfllogitude.getText()), Double.parseDouble(tflatitude.getText()));
        mps.supprimermaps(mp);
        Bonplans bp = new Bonplans(Integer.parseInt(tfidplan.getText()), tfbonplan.getText(), tfcategorie.getText(), tfadresse.getText(), tadescription.getText(), String.valueOf(xxx), Double.parseDouble(tffrais.getText()), tfhoraire.getText());

        bt.supprimerBP(bp);
        showtablebp();
        showMessageDialog(null, "Bonplan Supprimer avec succes");
    }

    public void showtablebp() {

        Bpinerface bt = new bpservice();
        ObservableList<Bonplans> tbp = FXCollections.observableArrayList(bt.afficherBP());
        colidplon.setCellValueFactory(new PropertyValueFactory<>("id_plan"));
        colnombonplan.setCellValueFactory(new PropertyValueFactory<>("nom_bonplan"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        coldescriptionbp.setCellValueFactory(new PropertyValueFactory<>("description"));
        colcategoriebp.setCellValueFactory(new PropertyValueFactory<>("type_categorie"));
        colsrcimage.setCellValueFactory(new PropertyValueFactory<>("images"));
        colfrais.setCellValueFactory(new PropertyValueFactory<>("frais"));
        colhorair.setCellValueFactory(new PropertyValueFactory<>("horaire"));
        tvbonplan.setItems(tbp);

    }

    public void showtablebp1() {
        Mapsinterface mps = new Mapsservice();

        ObservableList<Maps> mpb = FXCollections.observableArrayList(mps.affichermaps());
        colidmap.setCellValueFactory(new PropertyValueFactory<>("id_map"));
        colvile.setCellValueFactory(new PropertyValueFactory<>("ville"));
        collogitude.setCellValueFactory(new PropertyValueFactory<>("logitude"));
        collatidude.setCellValueFactory(new PropertyValueFactory<>("latitude"));

        tvbonplan1.setItems(mpb);
    }
//
//    private void addcat(ActionEvent event) {
//        if(tfcategorie.getText().equals("")){
//            showMessageDialog(null, "le chomp categorie est vide");
//        tfcategorie.requestFocus();
//        }else{
//        Catineterface ct = new catservice();
//        Categorie c = new Categorie(tfcategorie.getText());
//        ct.ajoutercat(c);
//        showtabcat();
//        showMessageDialog(null, "categorie ajouter avec succes");
//        init();
//    }}

//    private void modifycat(ActionEvent event) {
//        Integer id = Integer.parseInt(txtid.getText());
//        Catineterface ct = new catservice();
//        Categorie c = new Categorie(tfcategorie.getText());
//        ct.modifiercat(c, id);
//        showMessageDialog(null, "categorie modifier avec succes");
//        showtabcat();
//    }
    ////////***** deuxieme méthoder******///////
//    private void modifycat(ActionEvent event) throws SQLException {
//        PreparedStatement ps;
//        ResultSet rs;
//        Integer id = Integer.parseInt(txtid.getText());
//        String x = tfcategorie.getText();
//        String yy = "update categorie set type_categorie ='" + x + "'  where id = '" + id + "' ";
//        ps = cnx.prepareStatement(yy);
//        ps.executeUpdate();
//
//        showMessageDialog(null, "categorie  modifier  avec succes");
//        tfcategorie.clear();
//        showtabcat() ;
//    }
//    private void deletecat(ActionEvent event) {
//        Catineterface ct = new catservice();
//        Categorie c = new Categorie(tfcategorie.getText());
//        ct.supprimercat(c);
//        showMessageDialog(null, "categorie supprimer avec succes");
//        showtabcat();
//    }
    ////////***** deuxieme méthoder******///////
//    private void deletecat(ActionEvent event) throws SQLException {
//        PreparedStatement ps;
//        ResultSet rs;
//        Integer id = Integer.parseInt(txtid.getText());
//
//        String yy = "delete   from  categorie where id = '" + id + "' ";
//        ps = cnx.prepareStatement(yy);
//        ps.execute();
//
//        showMessageDialog(null, "categorie  supprimer avec succes");
//        tfcategorie.clear();
//        showtabcat(); 
//    }
//    public void showtabcat() {
//
//        Catineterface ct = new catservice();
//        ObservableList<Categorie> tbc = FXCollections.observableArrayList(ct.afficherCT());
//
//        coloid.setCellValueFactory(new PropertyValueFactory<>("id"));
//        colcategorie.setCellValueFactory(new PropertyValueFactory<>("type_categorie"));
//        tvcategorie.setItems(tbc);
//
//    }
    @FXML
    void getSelected1(MouseEvent event) {

        index = tvbonplan1.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
         idmap.setText(colidmap.getCellData(index).toString());
        tfville.setText(colvile.getCellData(index));
        tflatitude.setText(collatidude.getCellData(index).toString());
        tfllogitude.setText(collogitude.getCellData(index).toString()                                                                                   );
        
        index = tvbonplan.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tfidplan.setText(colidplon.getCellData(index).toString());
        tfidbp.setText(colnombonplan.getCellData(index));
        tfbonplan.setText(colnombonplan.getCellData(index));
        tfadresse.setText(coladresse.getCellData(index));
        tfcategorie.setText(colcategoriebp.getCellData(index));
        tadescription.setText(coldescriptionbp.getCellData(index));
        tffrais.setText(colfrais.getCellData(index).toString());
        tfhoraire.setText(colhorair.getCellData(index));
        
        taimage.setText(colsrcimage.getCellData(index));
        String path = colsrcimage.getCellData(index);
        Image image = new Image("file:" + path);
        this.img.setImage(image);
    }

//    public void init() {
//        Catineterface ct = new catservice();
//        ObservableList<Categorie> mList = FXCollections.observableArrayList(ct.afficherCT());
//        cbcategorie.setItems(mList);
//    }
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
            img.setImage(new Image(file.toURI().toString()));
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

    @FXML
    private void showlist(ActionEvent event) throws IOException {
        Stage stage = (Stage) tfadresse.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../../gui/bonplan/market.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

}
