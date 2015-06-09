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
public abstract class Carte implements java.io.Serializable{
    private Monopoly monopoly;
    private String description,type;
    private int reparationMaison,reparationHotel,nombreAction;
    
    public Carte(String type,String pDesc,Monopoly monopoly) {
        setType(type);
        setDescription(pDesc);
        setMonopoly(monopoly);
    }
    public Carte(String type,String description,int nombreAction,Monopoly monopoly) {
        setType(type);
        setDescription(description);
        setNombreAction(nombreAction);
        setMonopoly(monopoly);
    }
    
    public Carte(String type,String description,int reparationMaison,int reparationHotel,Monopoly monopoly) {
        setType(type);
        setDescription(description);
        setReparationMaison(reparationMaison);
        setReparationHotel(reparationHotel);
        setMonopoly(monopoly);
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

    /**
     * @return the monopoly
     */
    public Monopoly getMonopoly() {
        return monopoly;
    }

    /**
     * @param monopoly the monopoly to set
     */
    private void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }
}
