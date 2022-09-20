/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reservation;

import model.Evenement;

/**
 *
 * @author ASUS
 */
public class CartEntry {
    private Evenement evennement;
    private int quantite;

    public CartEntry(Evenement evennement, int quantite) {
        this.evennement = evennement;
        this.quantite = quantite;
    }

    public Evenement getEvennement() {
        return evennement;
    }

    public void setEvennement(Evenement evennement) {
        this.evennement = evennement;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public void incrimentQuantite(){
        this.quantite++;
    }
     public void disncrimentQuantite(){
        if (this.quantite>0){
         this.quantite--;
        }
    }
    
}
