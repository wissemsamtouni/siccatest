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
    private Maps maps;
    private double frais;
    private String horaire;
 
   

    public Bonplans() {
    }

    public Bonplans(int id_plan, String nom_bonplan, String type_categorie, String adresse, String description, String images,  double frais, String horaire) {
        this.id_plan = id_plan;
        this.nom_bonplan = nom_bonplan;
        this.type_categorie = type_categorie;
        this.adresse = adresse;
        this.description = description;
        this.images = images;
       
        this.frais = frais;
        this.horaire = horaire;
    }

    public Bonplans(String nom_bonplan, String type_categorie, String adresse, String description, String images, double frais, String horaire) {
        this.nom_bonplan = nom_bonplan;
        this.type_categorie = type_categorie;
        this.adresse = adresse;
        this.description = description;
        this.images = images;
       
        this.frais = frais;
        this.horaire = horaire;
    }

    

    public int getId_plan() {
        return id_plan;
    }

    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
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

    public Maps getMaps() {
        return maps;
    }

    public void setMaps(Maps maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return  type_categorie ;
    }
    



   

  
}
