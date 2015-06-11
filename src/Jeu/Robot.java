/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author derouxj
 */
public class Robot extends Joueur {

    private int seuil = 200;
    private int seuil2 = 1000;
    public Robot(String n, Monopoly m) {
        super(false, n, m);
    }

    /**
     *
     * @param nomC
     * @param prix
     * @return la décision quant à l'achat d'une propriete
     */
    public boolean decisionAchatPropriete(CarreauPropriete carreauP, int prix) {
        int carreauMemeGroupe = 0;
        Random rand = new Random();

        if (carreauP.getClass().getSimpleName() == "ProprieteAConstruire") {
            ProprieteAConstruire p = (ProprieteAConstruire) carreauP;
            p.getGroupePropriete();
            for (ProprieteAConstruire pAC : getProprietesAConstruire()) {
                if (pAC.getGroupePropriete() == p.getGroupePropriete()) {
                    carreauMemeGroupe++;
                }

            }
        }
       return ((getCash() - prix > seuil) && (rand.nextInt((100 - 0 + 1) + 0) + 20 * carreauMemeGroupe < 60)||(getCash() - prix > seuil2 && (rand.nextInt((100 - 0 + 1) + 0)<70)));
/*if ((getCash() - prix) > seuil) {
            if (rand.nextInt((100 - 0 + 1) + 0) + 20 * carreauMemeGroupe < 60) {
                return true;
            }else {return false;}
        } else if (getCash() - prix > seuil2) {
            if (rand.nextInt((100 - 0 + 1) + 0) < 70) {
                return true;
            }else {return false;}

        } else {
            return false;
        }*/

    }

    /**
     *
     * @param lesTerrains
     * @return la décision quant à la construction de maisons sur les terrains
     */
    public ProprieteAConstruire decisionConstruction(LinkedList<ProprieteAConstruire> lesTerrains,int nbterrain) {
               Random rand = new Random(); 
       int rnd = rand.nextInt((nbterrain - 1 + 1) + 1);
        

        return lesTerrains.get(rnd);

    }

    /**
     *
     * @return la décision quant à l'utilisation d'une carte "libere de prison"
     */
    public boolean decisionCarteLiberePrison() {

        Random rand = new Random();
        rand.nextInt((100 - 0 + 1) + 0);
        return ((getNbTourPrison() > 0 && rand.nextInt((100 - 0 + 1) + 0) < 40) || (getCartePrison() > 1 && rand.nextInt((100 - 0 + 1) + 0) < 80));

    }

}
