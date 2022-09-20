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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import model.Bonplans;
import model.Categories;
import interfaces.Icategories;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import services.Catcategories;
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
    private TableColumn<Bonplans, Categories> colcategoriebp;
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
    @FXML
    private ComboBox<Categories> cbcategorie;
    @FXML
    private Button btnsupprimer1;
    @FXML
    private TableColumn<Bonplans, Maps> colidmapbp;
    @FXML
    private TableColumn<Bonplans, Categories> colcategoriebonplans;

//    public GestionbonplansController() {
//        this.tbp = FXCollections.observableArrayList();
//    }
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        affichetabmap();
        affichetablebp();
        tbp.clear();
        mpb.clear();
        init();
    }

    @FXML
    private void reset(ActionEvent event) {
        annuler();
    }

    public void annuler() {
        tfadresse.clear();
        tfbonplan.clear();
        tadescription.clear();
        taimage.clear();
        tfhoraire.clear();
        tffrais.clear();
        tfville.clear();
        tflatitude.clear();
        tfllogitude.clear();
        taimage.clear();
    }

    @FXML
    private void addbonplans(ActionEvent event) {

        if (tfbonplan.getText().equals("")) {
            showMessageDialog(null, "le chomp bonplan est vide");
            tfbonplan.requestFocus();

        } else if (tfbonplan.getText().length() <= 2) {
            showMessageDialog(null, "le chomp  et ferieur a 2");

            tfbonplan.requestFocus();
//                    } else if ((!tfbonplan.getText().matches("[A-Za-z]"))) {
//            showMessageDialog(null, "ajouter des caractères");
//            tfbonplan.requestFocus();
            ///////////

        } else if (cbcategorie.getValue().equals("")) {
            showMessageDialog(null, "le chomp type categorie est vide");
            cbcategorie.requestFocus();
        } ////////////
        else if (tfadresse.getText().equals("")) {
            showMessageDialog(null, "le chomp adresse est vide");
            tfadresse.requestFocus();
        } ////
        else if (taimage.getText().equals("")) {
            showMessageDialog(null, "le chomp image est vide");
            taimage.requestFocus();

            /////////////
        } else if (tffrais.getText().equals("")) {
            showMessageDialog(null, "le chomp Frais est vide");
            tffrais.requestFocus();
        } else if ((tffrais.getText().matches("[A-Za-z]"))) {
            showMessageDialog(null, "ajouter des caractères");

            tffrais.requestFocus();

        } else if (tadescription.getText().equals("")) {
            showMessageDialog(null, "le chomp description est vide");
            tadescription.requestFocus();
        } else if (tfhoraire.getText().equals("")) {
            showMessageDialog(null, "le chomp Horaire est vide");
            tfhoraire.requestFocus();
        } else {
            Bpinerface bt = new bpservice();
            Mapsinterface mps = new Mapsservice();
            Maps mp = new Maps(tfville.getText(), Double.parseDouble(tfllogitude.getText()), Double.parseDouble(tflatitude.getText()));
            Maps generatedMaps = mps.ajoutermaps(mp);
            //..
            Bonplans bp = new Bonplans(tfbonplan.getText(), cbcategorie.getValue(), tfadresse.getText(), tadescription.getText(), String.valueOf(xxx), Double.parseDouble(tffrais.getText()), tfhoraire.getText());
            bp.setMaps(generatedMaps);
            bt.ajouterBP(bp);

            showMessageDialog(null, "Bonplans ajouter avec succes");
            annuler();
            affichetablebp();
              affichetabmap() ;
          
        }
    }

    @FXML
    private void modifybonplans(ActionEvent event
    ) {
        Bpinerface bt = new bpservice();
        Mapsinterface mps = new Mapsservice();
        Maps mp = new Maps(Integer.parseInt(idmap.getText()), tfville.getText(), Double.parseDouble(tfllogitude.getText()), Double.parseDouble(tflatitude.getText()));
        Maps generatedMaps = mps.modifiermaps(mp);
        //.... 
        Bonplans bp = new Bonplans(Integer.parseInt(tfidplan.getText()), tfbonplan.getText(), cbcategorie.getValue(), tfadresse.getText(), tadescription.getText(), String.valueOf(xxx), Double.parseDouble(tffrais.getText()), tfhoraire.getText());
        bp.setMaps(generatedMaps);
        bt.modifierBP(bp);
        showMessageDialog(null, "Bonplan Modifier avec succes");
        annuler();
        affichetablebp();
          affichetabmap() ;
    }

    @FXML
    private void deletebonplan(ActionEvent event
    ) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous supprimer");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Bpinerface bt = new bpservice();
            Mapsinterface mps = new Mapsservice();
            Maps mp = new Maps(Integer.parseInt(idmap.getText()), tfville.getText(), Double.parseDouble(tfllogitude.getText()), Double.parseDouble(tflatitude.getText()));
            Maps generatedMaps = mps.supprimermaps(mp);
           ////
            Bonplans bp = new Bonplans(Integer.parseInt(tfidplan.getText()), tfbonplan.getText(), cbcategorie.getValue(), tfadresse.getText(), tadescription.getText(), String.valueOf(xxx), Double.parseDouble(tffrais.getText()), tfhoraire.getText());
            bp.setMaps(generatedMaps);
            bt.supprimerBP(bp);
            showMessageDialog(null, "Bonplan Supprimer avec succes");
            annuler();
            affichetablebp();
            affichetabmap() ;
        }
    }

    public void affichetablebp() {

        Bpinerface bt = new bpservice();
//        tbp=bt.afficherBP();
    
        ObservableList<Bonplans> tbp = FXCollections.observableArrayList(bt.afficherBP());
        colidplon.setCellValueFactory(new PropertyValueFactory<>("id_plan"));
        colnombonplan.setCellValueFactory(new PropertyValueFactory<>("nom_bonplan"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        coldescriptionbp.setCellValueFactory(new PropertyValueFactory<>("description"));  
        colcategoriebonplans.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        colsrcimage.setCellValueFactory(new PropertyValueFactory<>("images"));
        colfrais.setCellValueFactory(new PropertyValueFactory<>("frais"));
        colhorair.setCellValueFactory(new PropertyValueFactory<>("horaire"));
        colidmapbp.setCellValueFactory(new PropertyValueFactory<>("maps"));
        tvbonplan.setItems(tbp);
      
    }

    public void affichetabmap() {
        Mapsinterface mps = new Mapsservice();

        ObservableList<Maps> mpb = FXCollections.observableArrayList(mps.affichermaps());
        colidmap.setCellValueFactory(new PropertyValueFactory<>("id_map"));
        colvile.setCellValueFactory(new PropertyValueFactory<>("ville"));
        collogitude.setCellValueFactory(new PropertyValueFactory<>("logitude"));
        collatidude.setCellValueFactory(new PropertyValueFactory<>("latitude"));

        tvbonplan1.setItems(mpb);
    }

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
//  
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
    @FXML
    void getSelected1(MouseEvent event) {

        index = tvbonplan1.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idmap.setText(colidmap.getCellData(index).toString());
        tfville.setText(colvile.getCellData(index));
        tflatitude.setText(collatidude.getCellData(index).toString());
        tfllogitude.setText(collogitude.getCellData(index).toString());
 
    }
@FXML
    private void getSelected(MouseEvent event) {
        index = tvbonplan.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
//        idmap.setText(colidmapbp.getCellData(index).toString());
        tfidplan.setText(colidplon.getCellData(index).toString());

        tfbonplan.setText(colnombonplan.getCellData(index));
        tfadresse.setText(coladresse.getCellData(index));
       Categories xx = colcategoriebonplans.getCellData(index);
       cbcategorie.getSelectionModel().select(xx);
        tadescription.setText(coldescriptionbp.getCellData(index));
        tffrais.setText(colfrais.getCellData(index).toString());
          tfhoraire.setText(colhorair.getCellData(index));

        taimage.setText(colsrcimage.getCellData(index));
        String path = colsrcimage.getCellData(index);
        Image image = new Image("file:" + path);
        this.img.setImage(image);
    }
    public void init() {
        Icategories ct = new Catcategories();
        ObservableList<Categories> mList = FXCollections.observableArrayList(ct.affichercategorie());
        cbcategorie.setItems(mList);
    }

    @FXML
    private void uploadimage(ActionEvent event) throws IOException {
        fc.setTitle("Uplode Image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        ;
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

    private void showlist(ActionEvent event) throws IOException {
        Stage stage = (Stage) tfadresse.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../../gui/bonplan/market.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    private void allergestioncategorie(ActionEvent event) throws IOException {

        Stage stage = (Stage) tfadresse.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../../gui/bonplan/gestioncategorie.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    
}
