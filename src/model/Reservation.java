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
public class Reservation {
    private int id_utilisateur;
    private int id_reservation;
    private int id_evennement;
    private int nbr_ticket;
    private String nom_evenement;
    private Date date_debut;
    private Date date_fin;
    private float prix_ticket;
   

    public Reservation() {
    }

    public Reservation(int id_utilisateur, int id_reservation, int id_evennement, int nbr_ticket, String nom_evenement, Date date_debut, Date date_fin, float prix_ticket) {
        this.id_utilisateur = id_utilisateur;
        this.id_reservation = id_reservation;
        this.id_evennement = id_evennement;
        this.nbr_ticket = nbr_ticket;
        this.nom_evenement = nom_evenement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix_ticket = prix_ticket;
        //this.nbrticket = nbrticket;
    }

    

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public int getId_reservation() {
        return id_reservation;
    }

  

    public int getNbr_ticket() {
        return nbr_ticket;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

  

    public void setNbr_ticket(int nbr_ticket) {
        this.nbr_ticket = nbr_ticket;
    }

    public int getId_event() {
        return id_evennement;
    }

    public void setId_event(int id_event) {
        this.id_evennement = id_event;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public float getPrix_ticket() {
        return prix_ticket;
    }

    public void setPrix_ticket(float prix_ticket) {
        this.prix_ticket = prix_ticket;
    }

    

    @Override
    public String toString() {
        return "Reservation{" + "id_utilisateur=" + id_utilisateur + ", id_reservation=" + id_reservation + ", id_evennement=" + id_evennement + ", nbr_ticket=" + nbr_ticket + ", nom_evenement=" + nom_evenement + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prix_ticket=" + prix_ticket +  '}';
    }

  
    
}
