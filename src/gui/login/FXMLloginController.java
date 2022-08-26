/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;


import com.sun.glass.ui.Cursor;
import gui.registr.FXMLregistrController;
import static gui.registr.FXMLregistrController.getHash;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import util.Myconnexion;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-007
 */
public class FXMLloginController implements Initializable {
    Connection cnx = Myconnexion.getInstance().getCnx();
   
    @FXML
    private TextField IDusername;
    @FXML
    private PasswordField IDpassword;
    @FXML
    private Button IDlogin;
    @FXML
    private Button IDsignup;
    @FXML
    private Label alertusername;
    @FXML
    private Label alertpassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Recoverpassword(ActionEvent event) {
    }

    @FXML
    private void Login(ActionEvent event) throws SQLException, IOException {
        String pass = IDpassword.getText();
        if(IDusername.getText().trim().isEmpty() && IDpassword.getText().trim().isEmpty()){
            alertusername.setText("Username is Empty");
            alertpassword.setText("Password is Empty");
        }
        else if(IDusername.getText().trim().isEmpty()){
            alertusername.setText("Username is Empty");  
            alertpassword.setText("");
    }       
        else if(IDpassword.getText().trim().isEmpty()){
            alertpassword.setText("Password is Empty");
            alertusername.setText("");
        }
        else{
        try{
        String sql = "Select * from utilisateur where login=? and mdp=?";
        PreparedStatement pst = cnx.prepareStatement(sql);
        pst.setString(1, IDusername.getText());
        pst.setString(2, getHash(pass.getBytes(),"SHA-1"));
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
           
            String s1 = rs.getString("role");
           if(s1.equalsIgnoreCase("Admin")){
               Statics.current_user.setNom(rs.getString("nom"));
               
           
           Stage stage = (Stage) IDlogin.getScene().getWindow();
           stage.close();
           Parent root = FXMLLoader.load(getClass().getResource("../../gui/dashboardadmin/FXMLdashboardadmin.fxml"));
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.setTitle("Admin Panel");
           stage.getIcons().add(new Image("gui/dashboardadmin/Untitled design (2).png"));
           stage.show();
           showMessageDialog(null,"you are connected" );
          
     
        }
        else if (s1.equalsIgnoreCase("Client")){
            
            Statics.current_user.setNom(rs.getString("nom"));
            Statics.current_user.setPrenom(rs.getString("Prenom"));
            
            Statics.current_user.setId_utilisateur(rs.getInt("id_utilisateur"));
            Statics.current_user.setLogin(rs.getString("login"));
            Statics.current_user.setMdp(rs.getString("mdp"));
            Statics.current_user.setMail(rs.getString("mail"));
            Statics.current_user.setTel(rs.getInt("tel"));
            Statics.current_user.setAdresse(rs.getString("adresse"));
            
            Stage stage = (Stage) IDlogin.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("../../gui/acceuil/FXMLacceuil.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            showMessageDialog(null,"you are connected" );
            
            
            
            
           
            
             
           
            
            
        } 
            
        }
        else{
            showMessageDialog(null, "Username or Password incorrect");
        }   IDusername.setText("");
            IDpassword.setText(""); 
          
    }catch(Exception e){
        showMessageDialog(null, e);
    }
    }
    }
    @FXML
    private void Signup(ActionEvent event) throws IOException {
         Stage stage = (Stage) IDsignup.getScene().getWindow();
         stage.close();
         Parent root = FXMLLoader.load(getClass().getResource("../../gui/registr/FXMLregistr.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        
        
        
       
    }
    
