/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author derouxj
 */
public class jeu {

    public static void main(String[] args) {
        boolean fini = false;
        int choix;
        Monopoly mon = new Monopoly("src/data/data.txt","src/data/data_Carte.txt");

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Inscrire les joueurs\n2. Commencer le jeu\n3. Quitter");
            choix = sc.nextInt();
            switch (choix) {
                case 1: {
                    boolean ok = false;
                    while (!ok) {
                        int nbj = 0;
                        boolean bonjour = true;
                        while (bonjour) {
                            try {
                                System.out.println("Combien de joueurs ? (2 à 6)");
                                if (sc.hasNextInt()) {
                                    nbj = sc.nextInt();
                                } else {
                                    sc.next();
                                    continue;
                                }
                                bonjour = false;
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("Ce n'est pas un entier !");
                                sc.next();
                            }
                        }
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
                }
                break;
                case 2: {
                    if (!mon.getJoueurs().isEmpty()) {
                        while (!mon.estFini()) {
                            int compteDouble = 0;
                            mon.jouerUnCoup(mon.getJoueurCourant());
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
                case 4: {
                    HashMap<Integer, Carreau> plateau = mon.getCarreaux();
                    
                    Joueur propBleuC = new Joueur("ProprioBleuCiel",mon);
                    mon.getJoueurs().add(propBleuC);//ajout du joueur ProprioBleuCiel
                    
                    Joueur propGare = new Joueur("ProprioGare",mon);
                    mon.getJoueurs().add(propGare);//ajout du joueur ProprioGare (toutes les gares)
                    
                    Joueur propCompagnie = new Joueur("ProprioCompagnie",mon);
                    mon.getJoueurs().add(propCompagnie);  //ajout du joueur ProprioCompagnie (test avec une seule compagnie)
                    
                    mon.setD1(5);
                    mon.setD2(4);
                    
                   
                            
                    propBleuC.addPropriete((CarreauPropriete)plateau.get(7));
                    propBleuC.addPropriete((CarreauPropriete)plateau.get(9));
                    propBleuC.addPropriete((CarreauPropriete)plateau.get(10));
                    for (int i=0;i<2;i++) {
                        propBleuC.getProprietesAConstruire().get(0).addConstruction();
                    }
                    for (int i=0;i<2;i++) {
                        propBleuC.getProprietesAConstruire().get(1).addConstruction();
                    }
                    propBleuC.getProprietesAConstruire().get(2).addConstruction();
                    
                    //propBleuC.deplacer(plateau.get(7));
                    System.out.println(propBleuC.getPositionCourante().getNomCarreau());
                    System.out.println(propBleuC.getProprietesAConstruire().get(0).getGroupePropriete().getCouleur());
                    //propBleuC.getPositionCourante().action(propBleuC);
                    mon.interface_9.messageEtatJoueur(propBleuC);
                    
                    
                    propGare.addPropriete((Gare)plateau.get(6));
                    propGare.addPropriete((Gare)plateau.get(16));
                    propGare.addPropriete((Gare)plateau.get(26));
                    propGare.addPropriete((Gare)plateau.get(36));
                    
                 
                        
                    propBleuC.setPositionCourante(plateau.get(6));
                    System.out.println(propBleuC.getPositionCourante().getNomCarreau());
                    propBleuC.getPositionCourante().action(propBleuC);
                    mon.interface_9.messageEtatJoueur(propGare);
                    
                    
                    
                    propCompagnie.addPropriete((Compagnie)plateau.get(13));
                    //propCompagnie.addPropriete((Compagnie)plateau.get(29));
                    
                    
                    propGare.setPositionCourante(plateau.get(13));
                    System.out.println(propGare.getPositionCourante().getNomCarreau());
                    propGare.getPositionCourante().action(propGare);
                    mon.interface_9.messageEtatJoueur(propCompagnie);
                    
                }
                case 5: { //envoyer quelqu'un croupir en taule
                    mon.getJoueurCourant().envoyerPrison();
                    mon.getJoueurCourant().ajouterCartePrison();
                    mon.jouerUnCoup(mon.getJoueurCourant());
                }
                default:
                    break;
                                 

            }
        } while (choix != 0);
    }
}
