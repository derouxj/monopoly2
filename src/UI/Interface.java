package UI;

import Jeu.Monopoly;
import Jeu.*;
import java.util.Scanner;
import java.util.*;

public class Interface implements java.io.Serializable{

    public Monopoly monopoly;
    transient Scanner sc = new Scanner(System.in);

    public Interface(Monopoly mono) {
        monopoly=mono;
    }

    /**
     * Affiche l'etat du joueur en paramètre: son nom, sa position, son solde et
     * ses propriété ainsi que leurs constructions
     *
     * @param leJoueur joueur à afficher
     */
    public void messageEtatJoueur(Joueur leJoueur) {

        System.out.println("Nom du joueur : " + leJoueur.getNomJoueur());
        System.out.println("  - position : " + leJoueur.getPositionCourante().getNomCarreau());
        System.out.println("  - solde : " + leJoueur.getCash() + "$");
        System.out.println("  - propriétés : ");
        ArrayList<Gare> gares = leJoueur.getGares();
        if (gares.isEmpty()) {
            System.out.println("\tCe joueur n'a aucune gares.");
        } else {
            System.out.println("\tGares de ce joueur : ");
            for (Gare g : gares) {
                System.out.println("\t- " + g.getNomCarreau());
            }
        }
        ArrayList<Compagnie> compagnies = leJoueur.getCompagnies();
        if (compagnies.isEmpty()) {
            System.out.println("\tCe joueur n'a pas de compagnies.");
        } else {
            System.out.println("\tCompagnies de ce joueur : ");
            for (Compagnie c : compagnies) {
                System.out.print("      - " + c.getNomCarreau());
            }
        }
        ArrayList<ProprieteAConstruire> proprietes = leJoueur.getProprietesAConstruire();
        if (proprietes.isEmpty()) {
            System.out.println("\tCe joueur n'a aucune propriétés à construire.");
        } else {
            System.out.println("\tPropriétés à construire de ce joueur : ");
            for (ProprieteAConstruire p : proprietes) {
                System.out.print("\t- " + p.getNomCarreau() + " du groupe " + p.getCouleur());
                int nbhotels = p.getNbHotels();
                int nbmaisons = p.getNbMaisons();
                if (nbmaisons == 0 && nbhotels == 0) {
                    System.out.println("(cette propriété n'a pas d'hotel ni de maisons)");
                } else if (nbhotels == 1) {
                    System.out.println("(cette propriété a un hôtel)");
                } else {
                    System.out.println("(cette propriétés a " + nbmaisons + " maisons)");
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
     *Message affichant le joueur ainsi que la somme qu'il a perdu
     * @param j joueur perdant
     * @param somme somme perdu
     */
    public void messagePerteCash(Joueur j, int somme) {
        System.out.println(j.getNomJoueur() + " a perdu " + somme + " €");
    }

    /**
     *Demande le nom au joueur.
     * @return le nom du joueur a inscrire
     */
    public String nouveauJoueur() {
        System.out.println("\nNom du joueur : ");
        String nom = sc.nextLine();
        return nom;
    }

    /**
     *Message affichant la propriété ansi que ses infos(nom,prix) concerné par l'achat potentiel.
     * Il demande une confirmation au joueur.
     * @param nomC nom de la propriété
     * @param prix prix d'achat de la propriété
     * @return
     */
    public Boolean messageAchatPropriete(String nomC, int prix) {
        Joueur j=monopoly.getJoueurCourant();
        Scanner sc = new Scanner(System.in);
        boolean aRepondu = false;
        String rep;
        do {
            System.out.println("Acheter " + nomC + " pour " + prix + " ? (y/n)");
            rep = sc.nextLine();
            if (rep.equals("y") || rep.equals("n")) {
                aRepondu = true;
            }
        } while (!aRepondu);

        if (rep.equals("y")) {
            System.out.println("confirmation de l'achat de " + nomC + " par " + j.getNomJoueur());
            return true;
        } else {
            System.out.println("Le joueur n'a pas voulu acheter la propriété.");
            return false;
        }
    }

    /**
     *Affiche toutes les propriété contenu dans la liste donnée en paramètre.
     * Demande au joueur sur quel terrain il veut construire.
     * @param lesTerrains liste des terrains constructible
     * @return null si il ne veut pas construire, sinon il renvoi la propriété ou le joueur veut construire
     */
    public ProprieteAConstruire messageChoixConstruction(LinkedList<ProprieteAConstruire> lesTerrains) {
        if (lesTerrains.isEmpty()) {
            return null;
        }

        int nbterrain = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Sur quelle terrain voulez vous construire ?" + "\n\t0 - Aucun");
        for (ProprieteAConstruire pc : lesTerrains) {
            nbterrain = nbterrain + 1;
            System.out.println("\t" + nbterrain + " - " + pc.getNomCarreau());
        }
        int choix = sc.nextInt();

        if (choix == 0 || choix > nbterrain) {
            return null;
        } else {
            return lesTerrains.get(choix - 1);
        }
    }

    /**
     *indique que le joueur est envoyé en prison
     * @param j joueur a bannir
     */
    public void messagePrison(Joueur j) {
        System.out.println(j.getNomJoueur() + " a été envoyé en prison.");
    }

    /**
     *Message indiquant si le joueur a gagné ou perdu une carte sortie de prison
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
