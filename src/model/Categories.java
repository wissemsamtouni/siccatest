/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elife-Kef-110
 */
public class Categories {
     private int id_cat;
    private String type_categories;

    public Categories() {
    }

    public Categories(String type_categories) {
        this.type_categories = type_categories;
    }

    public Categories(int id_cat, String type_categories) {
        this.id_cat = id_cat;
        this.type_categories = type_categories;
    }



    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getType_categories() {
        return type_categories;
    }

    public void setType_categories(String type_categories) {
        this.type_categories = type_categories;
    }

    @Override
    public String toString() {
        return type_categories ;
    }
    
}
