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
    private String description,type;
    private int reparationMaison,reparationHotel,nombreAction;
    
    private CarteChance(String type,String pDesc) {
        setType(type);
        setDescription(pDesc);
    }
    private CarteChance(String type,String description,int nombreAction) {
        setType(type);
        setDescription(description);
        setNombreAction(nombreAction);
    }
    
    private CarteChance(String type,String description,int reparationMaison,int reparationHotel) {
        setType(type);
        setDescription(description);
        setReparationMaison(reparationMaison);
        setReparationHotel(reparationHotel);
    }
    
    

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    private void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    private void setType(String type) {
        this.type = type;
    }

    /**
     * @return the reparationMaison
     */
    public int getReparationMaison() {
        return reparationMaison;
    }

    /**
     * @param reparationMaison the reparationMaison to set
     */
    private void setReparationMaison(int reparationMaison) {
        this.reparationMaison = reparationMaison;
    }

    /**
     * @return the ReparationHotel
     */
    public int getReparationHotel() {
        return reparationHotel;
    }

    /**
     * @param ReparationHotel the ReparationHotel to set
     */
    private void setReparationHotel(int reparationHotel) {
        this.reparationHotel = reparationHotel;
    }

    /**
     * @return the nombreAction
     */
    public int getNombreAction() {
        return nombreAction;
    }

    /**
     * @param nombreAction the nombreAction to set
     */
    private void setNombreAction(int nombreAction) {
        this.nombreAction = nombreAction;
    }
}
