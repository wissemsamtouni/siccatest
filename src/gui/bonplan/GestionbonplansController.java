/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.bonplan;

import interfaces.Bpinerface;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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
    private TextArea tadescription;
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
final FileChooser fc=new FileChooser();
private File file;
    /**
     * Initializes the controller class.
     */
    ObservableList<Bonplans> tbp = FXCollections.observableArrayList();
   
    int index = -1;

    @FXML

    private TextField txtid;

    @FXML
    private TextField tfidbp;
    @FXML
    private TextArea taimage;
    @FXML
    private ImageView img;

//    public GestionbonplansController() {
//        this.tbp = FXCollections.observableArrayList();
//    }
    @Override

    public void initialize(URL url, ResourceBundle rb) {

        
        showtablebp();
      

        
        tbp.clear();
    }
    ////////***** deuxieme méthoder******///////
    /// crud bon plans
    /// afficher table view bonplans
//       public void refreshbp() {
//           
//        tbp.clear();
//        String tt = "SELECT * FROM `bonplan`";
//
//        Statement statement;
//
//        try {
//            statement = cnx.createStatement();
//            ResultSet rs = statement.executeQuery(tt);
//            while (rs.next()) {
//                Integer x = rs.getInt("id_plan");
//                String y = rs.getString("nom_bonplan");
//                String z = rs.getString("adresse");
//                String e = rs.getString("description");
//                String f = rs.getString("type_categorie");
//
//                tbp.add(new Bonplans(x, y, z, e, f));
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(GestionbonplansController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        colidplon.setCellValueFactory(new PropertyValueFactory<>("id_plan"));
//        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
//        colnombonplan.setCellValueFactory(new PropertyValueFactory<>("nom_bonplan"));
//        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        colcategorie.setCellValueFactory(new PropertyValueFactory<>("type_categorie"));
//        tvbonplan.setItems(tbp);
//        
//    }

    ////////***** deuxieme méthoder******///////
//       /// crud categorie
//       //afficher categorie dans le tableau tableview categorie
//
//    public void refresh() {
//        dataList.clear();
//        String tt = "SELECT * FROM `categorie`";
//
//        Statement statement;
//
//        try {
//            statement = cnx.createStatement();
//            ResultSet rst = statement.executeQuery(tt);
//            while (rst.next()) {
//                String x = rst.getString("type_categorie");
//                Integer y = rst.getInt("id");
//
//                dataList.add(new Categorie(y, x));
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(GestionbonplansController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        coloid.setCellValueFactory(new PropertyValueFactory<>("id"));
//        colcategorie.setCellValueFactory(new PropertyValueFactory<>("type_categorie"));
//        tvcategorie.setItems(dataList);
//    }
    @FXML
    private void reset(ActionEvent event){
        tfadresse.clear();
        tfbonplan.clear();
        tfcategorie.clear();
        tfidbp.clear();
        tadescription.clear();
        taimage.clear();
        
    }
    @FXML
    private void addbonplans(ActionEvent event) {
        if(tfbonplan.getText().equals("")){
            showMessageDialog(null,"le chomp bonplan est vide");
            tfbonplan.requestFocus();
        }
        else if(tfadresse.getText().equals("")){
            showMessageDialog(null,"le chomp adresse est vide");
            tfadresse.requestFocus();
        }
         else if(tfcategorie.getText().equals("")){
            showMessageDialog(null,"le chomp categorie est vide");
            tfcategorie.requestFocus();
        }
          else if(tadescription.getText().equals("")){
            showMessageDialog(null,"le chomp description est vide");
            tadescription.requestFocus();
        }
        
        else{
        Bpinerface bt = new bpservice();
        Bonplans bp = new Bonplans(tfbonplan.getText(), tfcategorie.getText(), tfadresse.getText(), tadescription.getText(), (Blob) img.getImage());
        bt.ajouterBP(bp);
        showtablebp();


        showMessageDialog(null, "Bonplans ajouter avec succes");
 
    }}

    @FXML
    private void modifybonplans(ActionEvent event) {
        Bpinerface bt = new bpservice();
        Bonplans bp = new Bonplans(tfbonplan.getText(), tfcategorie.getText(), tfadresse.getText(), tadescription.getText(), (Blob) img.getImage());
        bt.modifierBP(bp);
        showtablebp();
        showMessageDialog(null, "Bonplan Modifier avec succes");
    }

    @FXML
    private void deletebonplan(ActionEvent event) {
        Bpinerface bt = new bpservice();
        Bonplans bp = new Bonplans(tfbonplan.getText(), tfcategorie.getText(), tfadresse.getText(), tadescription.getText(), (Blob) img.getImage());
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
        coldescriptionbp.setCellValueFactory(new PropertyValueFactory<>("description_bonplan"));
        colcategoriebp.setCellValueFactory(new PropertyValueFactory<>("type_categorie"));
      
        tvbonplan.setItems(tbp);
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

        index = tvbonplan.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tfidbp.setText(colnombonplan.getCellData(index));
        tfbonplan.setText(colnombonplan.getCellData(index));
        tfadresse.setText(coladresse.getCellData(index));
        tfcategorie.setText(colcategoriebp.getCellData(index));
        tadescription.setText(coldescriptionbp.getCellData(index));

    }

//    void getSelected(MouseEvent event) {
//
//        index = tvcategorie.getSelectionModel().getSelectedIndex();
//        if (index <= -1) {
//            return;
//        }
//
//        tfcategorie.setText(colcategorie.getCellData(index));
//        txtid.setText(String.valueOf(coloid.getCellData(index)));
//
//    }

//    public void init() {
//        Catineterface ct = new catservice();
//        ObservableList<Categorie> mList = FXCollections.observableArrayList(ct.afficherCT());
//        cbcategorie.setItems(mList);
//    }

    @FXML
    private void uploadimage(ActionEvent event) {
        fc.setTitle("Uplode Image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file","*.*"),
        new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
        File file=fc.showOpenDialog(null);
        if(file !=null){
        taimage.appendText(file.getAbsolutePath() +"\n");
        img.setImage(new Image(file.toURI().toString()));
        }else {
            System.out.println("file is invalide");
        }
    }

  
}
