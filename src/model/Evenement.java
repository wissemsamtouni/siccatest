/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Evenement {

   // public static Evenement valueOf(String nomevent) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

   

    public Evenement() {
    }

    public int id;
    private String nom_evenement;
    private Date date_debut;
    private Date date_fin;
    private float prix_ticket;
    private String images;
    private String nbrticket;

   

   
   
    

    public String getNbrticket() {
        return nbrticket;
    }

    public void setNbrticket(String nbrticket) {
        this.nbrticket = nbrticket;
    }

    public Evenement(int id) {
        this.id = id;
    }
    

    public Evenement(int id, String nom_evenement, Date date_debut, Date date_fin, float prix_ticket, String images, String nbrticket) {
        this.id = id;
        this.nom_evenement = nom_evenement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix_ticket = prix_ticket;
        this.images = images;
        this.nbrticket = nbrticket;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    // public Evenement(int id,String nom_evenement,  Date date_debut, Date date_fin, float prix_ticket) {
    //   this.id = id;
    // this.nom_evenement = nom_evenement;
    //this.date_debut = date_debut;
    //this.date_fin = date_fin;
    //this.prix_ticket = prix_ticket;
    //}
 

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom_evenement=" + nom_evenement + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prix_ticket=" + prix_ticket + ", images=" + images + ", nbrticket=" + nbrticket + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setPrix_ticket(float prix_ticket) {
        this.prix_ticket = prix_ticket;
    }

    public int getId() {
        return id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public float getPrix_ticket() {
        return prix_ticket;
    }

}
