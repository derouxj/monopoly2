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

    
    public CarteChance(String type,String pDesc) {
        super(type,pDesc);
    }
    
    public CarteChance(String type,String description,int nombreAction) {
        super(type,description,nombreAction);
    }
    
    public CarteChance(String type,String description,int reparationMaison,int reparationHotel) {
        super(type,description,reparationMaison,reparationHotel);
    }
    
    

    
}
