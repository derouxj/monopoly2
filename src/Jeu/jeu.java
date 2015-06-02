/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import java.util.Scanner;

/**
 *
 * @author derouxj
 */
public class jeu {

    public static void main(String[] args) {
        boolean fini = false;
        Monopoly mon = new Monopoly("/users/info/etu-s2/derouxj/m2105/monopoly2/Monopoly/src/data/data.txt");

        Scanner sc = new Scanner(System.in);
        while (!fini) {
            System.out.println("1. Inscrire les joueurs\n2. Commencer le jeu\n3. Quitter");
            int choix = sc.nextInt();
            switch (choix) {
                case 1: {
                    boolean ok = false;
                    while (!ok) {
                        System.out.println("Combien de joueurs ? (2 à 6)");
                        int nbj = sc.nextInt();
                        if (nbj < 2 || nbj > 6) {
                            System.out.println(nbj + " pas entre 2 et 6");
                        } else {
                            mon.inscrireJoueurs(nbj);
                            ok = true;
                            System.out.println("Ordre : ");
                            for (int i = 0; i < mon.getJoueurs().size(); i++) {
                                System.out.println((i + 1) + "e " + mon.getJoueurs().get(i).getNomJoueur());
                            }
                        }
                    }
                }
                case 2: {
                    if(!mon.getJoueurs().isEmpty()) {
                    }
                    else {
                        System.out.println("Vous n'avez pas inscrit de joueurs !");
                    }
                    
                }
            }
        }

    }
}
