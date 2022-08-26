/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author wissem
 */
public class Promo {
     private int id_promo;
    private LocalDate date_expiration;
    private int poucentage_reduction;
    private Utilisateur id_utlisateur;

    public Promo() {
    }
    public Promo(int id_promo, LocalDate date_expiration, int poucentage_reduction, Utilisateur id_utlisateur) {
        this.id_promo = id_promo;
        this.date_expiration = date_expiration;
        this.poucentage_reduction = poucentage_reduction;
        this.id_utlisateur = id_utlisateur;
    }

    public int getId_promo() {
        return id_promo;
    }

    public LocalDate getDate_expiration() {
        return date_expiration;
    }

    public int getPoucentage_reduction() {
        return poucentage_reduction;
    }

    public Utilisateur getId_utlisateur() {
        return id_utlisateur;
    }

    public void setId_promo(int id_promo) {
        this.id_promo = id_promo;
    }

    public void setDate_expiration(LocalDate date_expiration) {
        this.date_expiration = date_expiration;
    }

    public void setPoucentage_reduction(int poucentage_reduction) {
        this.poucentage_reduction = poucentage_reduction;
    }

    public void setId_utlisateur(Utilisateur id_utlisateur) {
        this.id_utlisateur = id_utlisateur;
    }
    
    
}
