/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.Reservation;

/**
 *
 * @author ASUS
 */
public interface Ireservation {
    public void ReserverEvent(Reservation r);
    public void Annuler (Reservation r);
    
}
