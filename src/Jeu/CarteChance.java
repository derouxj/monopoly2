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
public class CarteChance {
    private String description,type;
    private int mouvement,reparationMaison,ReparationHotel,somme;
    
    private CarteChance(String type,String pDesc) {
        setType(type);
        setDescription(pDesc);
    }
    private CarteChance(String )

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
     * @return the mouvement
     */
    public int getMouvement() {
        return mouvement;
    }

    /**
     * @param mouvement the mouvement to set
     */
    private void setMouvement(int mouvement) {
        this.mouvement = mouvement;
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
        return ReparationHotel;
    }

    /**
     * @param ReparationHotel the ReparationHotel to set
     */
    private void setReparationHotel(int ReparationHotel) {
        this.ReparationHotel = ReparationHotel;
    }

    /**
     * @return the somme
     */
    public int getSomme() {
        return somme;
    }

    /**
     * @param somme the somme to set
     */
    private void setSomme(int somme) {
        this.somme = somme;
    }
}
