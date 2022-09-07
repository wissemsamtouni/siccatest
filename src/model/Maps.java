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
public class Maps {
    private int id_map;
    private String ville;
    private double logitude;
    private double latitude;

    public Maps() {
    }

    public Maps(String ville, double logitude, double latitude) {
        this.ville = ville;
        this.logitude = logitude;
        this.latitude = latitude;
    }

    public Maps(int id_map, String ville, double logitude, double latitude) {
        this.id_map = id_map;
        this.ville = ville;
        this.logitude = logitude;
        this.latitude = latitude;
    }

    public int getId_map() {
        return id_map;
    }

    public void setId_map(int id_map) {
        this.id_map = id_map;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public double getLogitude() {
        return logitude;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Maps{" + "id_map=" + id_map + ", ville=" + ville + ", logitude=" + logitude + ", latitude=" + latitude + '}';
    }
    
    
    
    
}
