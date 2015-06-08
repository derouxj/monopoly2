/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jeu;

/**
 *
 * @author carrejer
 */
public class CarteChance extends Carte {

    
    public CarteChance(String type,String pDesc,Monopoly monopoly) {
        super(type,pDesc,monopoly);
    }
    
    public CarteChance(String type,String description,int nombreAction,Monopoly monopoly) {
        super(type,description,nombreAction,monopoly);
    }
    
    public CarteChance(String type,String description,int reparationMaison,int reparationHotel,Monopoly monopoly) {
        super(type,description,reparationMaison,reparationHotel,monopoly);
    }
    
    

    
}
