/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elife-Kef-112
 */
public class Materiel {
    private int id_materiel;
    private String categorie;
    private int quantité;
    private String description;
    private float prix_location;
    private String image;

    public Materiel() {
        
    }

    public Materiel(int id_materiel, String categorie, int quantité, String description, float prix_location, String image) {
        this.id_materiel = id_materiel;
        this.categorie = categorie;
        this.quantité = quantité;
        this.description = description;
        this.prix_location = prix_location;
        this.image = image;
    }

   
    

   
    

    public int getId_materiel() {
        return id_materiel;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getQuantité() {
        return quantité;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix_location() {
        return prix_location;
    }

    public String getImage() {
        return image;
    }
    

    public void setId_materiel(int id_materiel) {
        this.id_materiel = id_materiel;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix_location(float prix_location) {
        this.prix_location = prix_location;
    }

    public void setImage(String image) {
        this.image = image;
    }
   
    @Override
    public String toString() {
        return "Materiel{" + "id_materiel=" + id_materiel + ", categorie=" + categorie + ", quantit\u00e9=" + quantité + ", description=" + description + ", prix_location=" + prix_location + '}';
    }
    
    
    
}
