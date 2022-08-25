/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed jb
 */
public class Myconnexion {
      //DB
    final String URL = "jdbc:mysql://localhost:3306/siccaplan";
    final String USR  = "root";
    final String PWD = "";
    
    //Var
    Connection cnx;
    
    //1 : instance
    private static Myconnexion instance = null;
    
    //const
    //2: private constructor
    private Myconnexion(){
    
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
            System.out.println("Connexion établie avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(Myconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
        
    }
    
 //3 : get instance
    public static Myconnexion getInstance() {
        if(instance == null)
            instance = new Myconnexion();
        
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
    
    
}
