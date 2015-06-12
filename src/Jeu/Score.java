/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jeu;

import java.util.ArrayList;

/**
 *
 * @author carrejer
 */
public class Score implements java.io.Serializable{
    private ArrayList<Joueur> lesMeilleursJ;

    public Score() {
    }
    
    public void ajouterScore(Joueur j) {
        int taille = getLesMeilleursJ().size();
        int i = 0;
        boolean trouve = false;
        if (getLesMeilleursJ().size() == 0) {
            getLesMeilleursJ().add(j);
        } else {
            while (i < taille && !trouve) {
                if (getLesMeilleursJ().get(i).getCash() < j.getCash()) {
                    getLesMeilleursJ().add(i, j);
                    trouve = true;
                } else if (getLesMeilleursJ().get(i).getCash() == j.getCash()) {
                    String nomCourant= getLesMeilleursJ().get(i).getNomJoueur();
                    String nomNouveau = j.getNomJoueur();
                    if (nomCourant.compareTo(nomNouveau) > 0) {
                        getLesMeilleursJ().add(i, j);
                        trouve = true;
                    }
                }
                i++;
            }
            if (i == taille && !trouve) {
                getLesMeilleursJ().add(j);
            }
        }

    }
    /**
     * @return the lesMeilleursJ
     */
    public ArrayList<Joueur> getLesMeilleursJ() {
        return lesMeilleursJ;
    }

    /**
     * @param aLesMeilleursJ the lesMeilleursJ to set
     */
    public void setLesMeilleursJ(ArrayList<Joueur> aLesMeilleursJ) {
        lesMeilleursJ = aLesMeilleursJ;
    }
}

