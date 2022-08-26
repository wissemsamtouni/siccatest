/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;

/**
 *
 * @author ahmed jb
 */
public class Bonplans {
    //var
    private int id_plan;
    private  String nom_bonplan;
    private String type_categorie;
    private String adresse;
    private String description;
    private String images;
 
   

    public Bonplans() {
    }

    public Bonplans(String nom_bonplan, String type_categorie, String adresse, String description, String images) {
        this.nom_bonplan = nom_bonplan;
        this.type_categorie = type_categorie;
        this.adresse = adresse;
        this.description = description;
        this.images = images;
    }

    public Bonplans(int id_plan, String nom_bonplan, String type_categorie, String adresse, String description, String images) {
        this.id_plan = id_plan;
        this.nom_bonplan = nom_bonplan;
        this.type_categorie = type_categorie;
        this.adresse = adresse;
        this.description = description;
        this.images = images;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public String getNom_bonplan() {
        return nom_bonplan;
    }

    public void setNom_bonplan(String nom_bonplan) {
        this.nom_bonplan = nom_bonplan;
    }

    public String getType_categorie() {
        return type_categorie;
    }

    public void setType_categorie(String type_categorie) {
        this.type_categorie = type_categorie;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Bonplans{" + "id_plan=" + id_plan + ", nom_bonplan=" + nom_bonplan + ", type_categorie=" + type_categorie + ", adresse=" + adresse + ", description=" + description + ", images=" + images + '}';
    }

   

  
}
