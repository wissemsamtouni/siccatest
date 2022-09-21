/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reservation;


import java.util.Map;

import java.util.HapMap;
import java.util.HashMap;
import model.Evenement;
/**
 *
 * @author ASUS
 */
public class ShoopingCart {
    private static ShoopingCart INSTANCE;
    public static ShoopingCart getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ShoopingCart();
            
        }
        return INSTANCE;
    }
    private  Map<String, CartEntry> list;
    
    public ShoopingCart(){
        this.list =  new HashMap<>();
    }
    public void addevent(String nomevent){
        
        CartEntry evennement = list.get(nomevent.toUpperCase());
        if(evennement!=null){
            evennement.incrimentQuantite();
        }else{
            //Evenement event = Evenement.valueOf(nomevent);
           // CartEntry entry = new CartEntry(event,1);
            //list.put(nomevent.toUpperCase(), entry);
        }
        
    }
    public void removeEvent(String nomevent){
        CartEntry evennement = list.get(nomevent.toUpperCase());
        if(evennement!=null){
            evennement.disncrimentQuantite();
        } 
    }
    
    public int getQuantite(String nomevent){
        CartEntry entry =  list.get(nomevent.toUpperCase());
        if(entry!=null){
            return entry.getQuantite();
            
        }
        return 0;
    }
    public float calculeTotal(){
        float total = 0;
        for(CartEntry entry:list.values()){
            float entruCost = entry.getEvennement().getPrix_ticket()*entry.getQuantite();
            total = total +  entruCost;
            
        }
        return total;
        
    }
}
