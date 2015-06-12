/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import UI.Interface;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author derouxj
 */
public class jeu {

    public static void main(String[] args) throws InterruptedException {
        int choix;
        boolean boucle = true;

        //Monopoly mon = new Monopoly("src/data/data.txt","src/data/data_Carte.txt");
        Monopoly mon = new Monopoly();

        if (!mon.loadDBScore()) {
            mon.newDBScore();
        }
        Scanner sc = new Scanner(System.in);
        while (boucle) {
            choix = mon.interface_9.choisirAvecContexte("0. Quitter\n1. Inscrire les joueurs\n2. Commencer le jeu\n3. Test loyer, construction\n4. Test prison\n5. Charger partie\n6. Consulter les scores");

            switch (choix) {
                case 0: {
                    boucle = false;
                    break;
                }
                case 1: {
                    mon.newDBSave();
                    boolean ok = false;
                    while (!ok) {
                        int nbj = 0;
                        nbj = mon.interface_9.choisirAvecContexte("Combien de joueurs ? (2 à 6)");
                        if (nbj < 2 || nbj > 6) {
                            mon.interface_9.messageErreurScan(false);
                        } else {
                            mon.inscrireJoueurs(nbj);
                            ok = true;
                            mon.interface_9.affichageOrdre(mon.getJoueurs());
                        }
                    }
                    mon.updateDBSave();
                    break;
                }
                case 2: {
                    if (!mon.loadDBScore()) {
                        mon.newDBScore();
                    }
                    mon.loadDBSave();
                    boolean quitter = false;


                    if (!mon.getJoueurs().isEmpty()) {
                        while (!mon.estFini() && !quitter) {
                            int choisir = mon.interface_9.choisirAvecContexte("\n1. Jouer!\n2. Quitter(partie sauvegardée)");
                            boolean boucler = true;
                            while (boucler) {
                                switch (choisir) {
                                    case 1: {
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
                                        mon.updateDBSave();
                                        boucler = false;
                                        break;
                                    }
                                    case 2: {
                                        quitter = true;
                                        boucler = false;
                                        break;
                                    }
                                    default: {
                                        mon.interface_9.messageErreurScan(false);
                                        break;
                                    }
                                }
                            }

                        }
                        if (!quitter) {
                            mon.interface_9.affichageJoueurSt(mon.getJoueurCourant().getNomJoueur(), false);
                            mon.getScore().ajouterScore(mon.getJoueurCourant());
                            mon.updateDBScore();
                        }
                    } else {
                        mon.interface_9.affichageJoueurSt(null, true);
                    }
                    break;
                }
                case 3: { // test des construction ect ...
                    if (!mon.loadDBScore()) {
                        mon.newDBScore();
                    }
                    HashMap<Integer, Carreau> plateau = mon.getCarreaux();

                    Joueur propBleuC = new JoueurReel("ProprioBleuCiel", mon);
                    mon.getJoueurs().add(propBleuC);//ajout du joueur ProprioBleuCiel

                    Joueur propGare = new JoueurReel("ProprioGare", mon);
                    mon.getJoueurs().add(propGare);//ajout du joueur ProprioGare (toutes les gares)

                    Joueur propCompagnie = new JoueurReel("ProprioCompagnie", mon);
                    mon.getJoueurs().add(propCompagnie);  //ajout du joueur ProprioCompagnie (test avec une seule compagnie)

                    mon.setD1(5);
                    mon.setD2(4);

                    propBleuC.addPropriete((CarreauPropriete) plateau.get(7));
                    propBleuC.addPropriete((CarreauPropriete) plateau.get(9));
                    propBleuC.addPropriete((CarreauPropriete) plateau.get(10));
                    for (int i = 0; i < 2; i++) {
                        propBleuC.getProprietesAConstruire().get(0).addConstruction();
                    }
                    for (int i = 0; i < 2; i++) {
                        propBleuC.getProprietesAConstruire().get(1).addConstruction();
                    }
                    propBleuC.getProprietesAConstruire().get(2).addConstruction();

                    propBleuC.envoyerCase(7);
                    System.out.println(propBleuC.getPositionCourante().getNomCarreau());
                    System.out.println(propBleuC.getProprietesAConstruire().get(0).getGroupePropriete().getCouleur());
                    propBleuC.getPositionCourante().action(propBleuC);
                    mon.interface_9.messageEtatJoueur(propBleuC);

                    propGare.addPropriete((Gare) plateau.get(6));
                    propGare.addPropriete((Gare) plateau.get(16));
                    propGare.addPropriete((Gare) plateau.get(26));
                    propGare.addPropriete((Gare) plateau.get(36));

                    propBleuC.setPositionCourante(plateau.get(6));
                    System.out.println(propBleuC.getPositionCourante().getNomCarreau());
                    propBleuC.getPositionCourante().action(propBleuC);
                    mon.interface_9.messageEtatJoueur(propGare);

                    propCompagnie.addPropriete((Compagnie) plateau.get(13));
                    //propCompagnie.addPropriete((Compagnie)plateau.get(29));

                    propGare.setPositionCourante(plateau.get(13));
                    System.out.println(propGare.getPositionCourante().getNomCarreau());
                    propGare.getPositionCourante().action(propGare);
                    mon.interface_9.messageEtatJoueur(propCompagnie);

                    propGare.envoyerCase(10);
                    propGare.getPositionCourante().action(propGare);

                    break;
                }

                case 5: {
                    if (!mon.loadDBSave()) {
                        mon.newDBSave();
                    }
                    mon.updateDBSave();
                    break;
                }

                case 6: {       //OK
                    if (!mon.loadDBSave()) {
                        mon.newDBSave();
                    }
                    if (!mon.loadDBScore()) {
                        mon.newDBScore();
                    }
                    mon.getScore().ajouterScore(new JoueurReel("TestScore", mon));
                    Joueur deux = new JoueurReel("Deuxieme", mon);
                    deux.payer(1000);
                    mon.getScore().ajouterScore(deux);
                    Joueur deuxeq = new JoueurReel("Aeuxeq", mon);
                    deuxeq.payer(1000);
                    mon.getScore().ajouterScore(deuxeq);
                    mon.updateDBScore();
                    int i = 1;
                    mon.interface_9.affichageScore(mon.getScore().getLesMeilleursJ());
                    break;
                }
                case 11: {//il doit payer double car le prop a tous le groupe    OK
                    mon.newDBSave();
                    HashMap<Integer, Carreau> plateau = mon.getCarreaux();
                    Joueur leProp = new JoueurReel("leProp", mon);
                    Joueur lePayeur = new JoueurReel("LePayeur", mon);
                    mon.getJoueurs().add(leProp);
                    mon.getJoueurs().add(lePayeur);

                    leProp.addPropriete((CarreauPropriete) plateau.get(7));
                    leProp.addPropriete((CarreauPropriete) plateau.get(9));
                    leProp.addPropriete((CarreauPropriete) plateau.get(10));

                    lePayeur.setPositionCourante((CarreauPropriete) plateau.get(7));
                    lePayeur.getPositionCourante().action(lePayeur);
                    break;
                }
                case 12: {//carte chance, CDC              OK
                    mon.newDBSave();
                    HashMap<Integer, Carreau> plateau = mon.getCarreaux();
                    Joueur laniv = new JoueurReel("Laniv", mon);
                    Joueur lePayeur1 = new JoueurReel("LePayeur1", mon);
                    Joueur lePayeur2 = new JoueurReel("LePayeur2", mon);
                    mon.getJoueurs().add(laniv);
                    mon.getJoueurs().add(lePayeur1);
                    mon.getJoueurs().add(lePayeur2);

                    laniv.addPropriete((CarreauPropriete) plateau.get(7));
                    laniv.addPropriete((CarreauPropriete) plateau.get(9));
                    laniv.addPropriete((CarreauPropriete) plateau.get(10));
                    laniv.addPropriete((CarreauPropriete) plateau.get(40));
                    for (int i = 0; i < 2; i++) {
                        laniv.getProprietesAConstruire().get(0).addConstruction();
                    }
                    for (int i = 0; i < 2; i++) {
                        laniv.getProprietesAConstruire().get(1).addConstruction();
                    }
                    laniv.getProprietesAConstruire().get(2).addConstruction();
                    for (int i = 0; i < 5; i++) {
                        laniv.getProprietesAConstruire().get(3).addConstruction();
                    }

                    mon.getPileCDC().addFirst(new CarteCaisseCommunaute("N", "annivesaire"));
                    laniv.envoyerCase(3);
                    for (int i = 0; i < 17; i++) {
                        laniv.envoyerCase(3);
                        laniv.getPositionCourante().action(laniv);
                        System.out.println(laniv.getPositionCourante().getNomCarreau());
                    }
                    System.out.println("\n\n\n");
                    for (int i = 0; i < 17; i++) {
                        laniv.envoyerCase(8);
                        laniv.getPositionCourante().action(laniv);
                        System.out.println(laniv.getPositionCourante().getNomCarreau());
                    }
                    break;
                }
                default:
                    mon.interface_9.messageErreurScan(false);
                    break;
            }

        }
    }
}
