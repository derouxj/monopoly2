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
public class CarteCaisseCommunaute extends Carte {
    public CarteCaisseCommunaute(String type,String pDesc,Monopoly monopoly) {
        super(type,pDesc,monopoly);
    }
    
    public CarteCaisseCommunaute(String type,String description,int nombreAction,Monopoly monopoly) {
        super(type,description,nombreAction,monopoly);
    }
    
    public CarteCaisseCommunaute(String type,String description,int reparationMaison,int reparationHotel,Monopoly monopoly) {
        super(type,description,reparationMaison,reparationHotel,monopoly);
    }
}
