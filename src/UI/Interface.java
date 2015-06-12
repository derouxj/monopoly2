package UI;

import Jeu.Monopoly;
import Jeu.*;
import java.util.Scanner;
import java.util.*;

public class Interface implements java.io.Serializable {

    public Monopoly monopoly;
    transient Scanner sc = new Scanner(System.in);

    public Interface(Monopoly mono) {
        monopoly = mono;
    }

    /**
     * Affiche l'etat du joueur en paramètre: son nom, sa position, son solde et
     * ses propriété ainsi que leurs constructions
     *
     * @param leJoueur joueur à afficher
     */
    public void messageEtatJoueur(Joueur leJoueur) {

        System.out.println("\tNom du joueur : " + leJoueur.getNomJoueur());
        System.out.println("\t  - position : " + leJoueur.getPositionCourante().getNomCarreau());
        System.out.println("\t  - solde : " + leJoueur.getCash() + "$");
        System.out.println("\t  - propriétés : ");
        ArrayList<Gare> gares = leJoueur.getGares();
        if (gares.isEmpty()) {
            System.out.println("\t\tCe joueur n'a aucune gares.");
        } else {
            System.out.println("\t\tGares de ce joueur : ");
            for (Gare g : gares) {
                System.out.println("\t\t\t- " + g.getNomCarreau());
            }
        }
        ArrayList<Compagnie> compagnies = leJoueur.getCompagnies();
        if (compagnies.isEmpty()) {
            System.out.println("\t\tCe joueur n'a pas de compagnies.");
        } else {
            System.out.println("\t\tCompagnies de ce joueur : ");
            for (Compagnie c : compagnies) {
                System.out.println("\t\t\t- " + c.getNomCarreau());
            }
        }
        ArrayList<ProprieteAConstruire> proprietes = leJoueur.getProprietesAConstruire();
        if (proprietes.isEmpty()) {
            System.out.println("\t\tCe joueur n'a aucune propriétés à construire.");
        } else {
            System.out.println("\t\tPropriétés à construire de ce joueur : ");
            String grpTmp = "";
            String grpActuel;
            for (ProprieteAConstruire p : proprietes) {
                grpActuel = p.getGroupePropriete().getCouleur().toString();
                if (!grpTmp.equals(grpActuel)) {
                    grpTmp = grpActuel;
                    System.out.print("\t\t\t" + grpTmp);
                    int i = 0;
                    int nbmax = p.getGroupePropriete().getProprietes().size();
                    for (ProprieteAConstruire pa : p.getGroupePropriete().getProprietes()) {
                        if (leJoueur == pa.getProprietaire()) {
                            i++;
                        }
                    }
                    System.out.println("il vous manque " + (nbmax - i) + " propriété(s) de ce groupe pour pouvoir construire");
                }

                System.out.print("\t\t\t\t- " + p.getNomCarreau());
                int nbhotels = p.getNbHotels();
                int nbmaisons = p.getNbMaisons();
                if (nbmaisons == 0 && nbhotels == 0) {
                    System.out.println(" (cette propriété n'a pas d'hotel ni de maisons)");
                } else if (nbhotels == 1) {
                    System.out.println(" (cette propriété a un hôtel)");
                } else {
                    System.out.println(" (cette propriétés a " + nbmaisons + " maisons)");
                }
            }
        }
        System.out.println();
    }

    /*public String messageTransaction(Joueur jPaye,Joueur jGagne,int somme) {
     return (jPaye.getNomJoueur()+"a payé "+somme+" à "+jGagne.getNomJoueur());
     }*/
    /**
     * Message affichant le joueur ainsi que la somme qu'il a recu
     *
     * @param j joueur gagnant
     * @param somme somme gagné
     */
    public void messageReceptionCash(Joueur j, int somme) {
        System.out.println(j.getNomJoueur() + " a recu " + somme + " €");
    }

    /**
     * Message affichant le joueur ainsi que la somme qu'il a perdu
     *
     * @param j joueur perdant
     * @param somme somme perdu
     */
    public void messagePerteCash(Joueur j, int somme) {
        System.out.println(j.getNomJoueur() + " a perdu " + somme + " €");
    }

    /**
     * Demande le nom au joueur.
     *
     * @return le nom du joueur a inscrire
     */
    public String nouveauJoueur(boolean reel) {
        if (reel) {
            System.out.println("\nNom du joueur : ");
        } else {
            System.out.println("\nNom du robot : ");
        }
        String nom = sc.next();
        return nom;
    }

    public boolean affecterTypeJoueur() {
        boolean boucle = true;
        int choix = 0;
        boolean reel = true;
        while (boucle) {
            try {
                System.out.println("Choisir un type de joueur : ");
                System.out.println("1. Joueur réel\n2. Robot");
                System.out.println();
                if (sc.hasNextInt()) {
                    choix = sc.nextInt();
                } else {
                    sc.nextInt();
                    continue;
                }
                switch (choix) {
                    case 1: {
                        reel = true;
                        boucle = false;
                        break;
                    }
                    case 2: {
                        reel = false;
                        boucle = false;
                        break;
                    }
                    default:
                        System.out.println("1 ou 2, pas autre chose !\n");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ce n'est même pas un entier !\n");
                sc.next();
                boucle = true;
            }
        }
        return reel;
    }

    /**
     * Message affichant la propriété ansi que ses infos(nom,prix) concerné par
     * l'achat potentiel. Il demande une confirmation au joueur.
     *
     * @param nomC nom de la propriété
     * @param prix prix d'achat de la propriété
     * @return
     */
    public Boolean messageAchatPropriete(CarreauPropriete prop, int prix) {
        Joueur j = monopoly.getJoueurCourant();
        Scanner sc = new Scanner(System.in);
        boolean aRepondu = false;
        String rep;
        if (!monopoly.getJoueurCourant().estReel()) {
            Robot rb = (Robot) j;
            boolean decision = rb.decisionAchatPropriete(prop, prix);
            System.out.println(prop.getClass().getSimpleName());
            if (decision) {
                System.out.println(j.getNomJoueur() + " a acheté la propriété " + prop.getNomCarreau());
            } else {
                System.out.println(j.getNomJoueur() + " n'a pas acheté la propriété " + prop.getNomCarreau());
            }
            return decision;
        } else {

            do {
                System.out.println("Acheter " + prop.getNomCarreau() + " pour " + prix + " ? (y/n)");
                rep = sc.nextLine();
                if (rep.equals("y") || rep.equals("n")) {
                    aRepondu = true;
                } else {
                    System.out.println("Répondre par y/n\n");
                }
            } while (!aRepondu);

            if (rep.equals("y")) {
                System.out.println("confirmation de l'achat de " + prop.getNomCarreau() + " par " + j.getNomJoueur());
                return true;
            } else {
                System.out.println(j.getNomJoueur() + " n'a pas acheté la propriété " + prop.getNomCarreau());
                return false;
            }
        }
    }

    /**
     * Affiche toutes les propriété contenu dans la liste donnée en paramètre.
     * Demande au joueur sur quel terrain il veut construire.
     *
     * @param lesTerrains liste des terrains constructible
     * @return null si il ne veut pas construire, sinon il renvoi la propriété
     * ou le joueur veut construire
     */
    public ProprieteAConstruire messageChoixConstruction(LinkedList<ProprieteAConstruire> lesTerrains) {
        ProprieteAConstruire reponse = null;
        if (lesTerrains.isEmpty()) {
            return null;
        }

        int nbterrain = 0;

        for (ProprieteAConstruire pc : lesTerrains) {
            nbterrain++;
            System.out.println("\t" + nbterrain + " - " + pc.getNomCarreau());
        }

        if (!monopoly.getJoueurCourant().estReel()) {
            Robot rb = (Robot) monopoly.getJoueurCourant();
            int reponseRobot = rb.decisionConstruction(lesTerrains, nbterrain);

            if (reponseRobot == 0) {
                System.out.println("Je choisir de ne pas construire");
                return null;
            } else {
                System.out.println("Je construit sur le terrain " + lesTerrains.get(reponseRobot - 1).getNomCarreau());
                return lesTerrains.get(reponseRobot - 1);
            }
        } else {

            int choix = 0;

            Scanner sc = new Scanner(System.in);
            boolean boucle = true;

            while (boucle) {

                boucle = false;
                try {
                    System.out.println("Sur quel terrain voulez vous construire ?" + "\n\t0 - Aucun");

                    if (sc.hasNextInt()) {
                        choix = sc.nextInt();
                    } else {
                        sc.nextInt();
                        continue;
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Ce n'est pas un entier !\n");
                    nbterrain = 0;
                    sc.next();
                }
                if (choix > nbterrain || choix < 0) {
                    System.out.println("Le choix n'est pas correct\n");
                    boucle = true;
                } else if (choix == 0) {
                    reponse = null;
                } else {
                    reponse = lesTerrains.get(choix - 1);
                }

            }
            return reponse;
        }
    }

    /**
     * indique que le joueur est envoyé en prison
     *
     * @param j joueur a bannir
     */
    public void messagePrison(Joueur j) {
        System.out.println(j.getNomJoueur() + " a été envoyé en prison.");
    }

    /**
     * Message indiquant si le joueur a gagné ou perdu une carte sortie de
     * prison
     *
     * @param yn vrai si il gagne une carte
     * @param j joueur concerné par cette modification
     */
    public void messageCartePrison(boolean yn) {
        Joueur j = monopoly.getJoueurCourant();
        if (yn) {
            System.out.println(j.getNomJoueur() + " s'est vu ajouter une carte <<Vous êtes liberé de prison>>");
        } else {
            System.out.println(j.getNomJoueur() + " s'est vu retirer une carte <<Vous êtes liberé de prison>>");
        }
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
    public void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

}
