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
        int choix;
        Monopoly mon = new Monopoly("/users/info/etu-s2/baiyantm/java/monopoly2/src/data/data.txt");

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Inscrire les joueurs\n2. Commencer le jeu\n3. Quitter");
            choix = sc.nextInt();
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
                            System.out.println();
                        }
                    }
                    break;
                }
                case 2: {
                    if (!mon.getJoueurs().isEmpty()) {
                        while (!mon.estFini()) {
                            int compteDouble = 0;
                            mon.jouerUnCoup(mon.getJoueurs().getFirst());
                            while (mon.getD1() == mon.getD2() && compteDouble < 3) {
                                mon.jouerUnCoup(mon.getJoueurs().getFirst());
                                compteDouble++;
                            }
                            if (compteDouble == 3) {
                                mon.getJoueurs().getFirst().envoyerPrison();
                            }
                            if (!mon.estFini()) {
                                mon.joueurSuivant();
                            }
                        }
                        System.out.println("Le joueur " + mon.getJoueurs().getFirst().getNomJoueur() + " a gagné, gg");
                    } else {
                        System.out.println("Vous n'avez pas inscrit de joueurs !");
                    }
                    break;
                }
                case 3: {
                    break;
                }
                default:
                    break;
            }
        } while (choix != 0);
    }
}