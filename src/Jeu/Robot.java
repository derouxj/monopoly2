/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import java.util.LinkedList;

/**
 *
 * @author derouxj
 */
public class Robot extends Joueur {

    public Robot(String n, Monopoly m) {
        super(false, n, m);
    }

    public boolean decisionAchatPropriete(int prix) {
        return (super.getCash() - prix > 1200);
    }

    public ProprieteAConstruire decisionConstruction(LinkedList<ProprieteAConstruire> lesTerrains) {
        int choix = 1;

        return lesTerrains.get(choix - 1);

    }

}
