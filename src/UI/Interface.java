package UI;

import Jeu.Monopoly;
import Jeu.*;
import java.util.*;


public class Interface {
	public Monopoly monopoly;

	public void messageEtatJoueur(Joueur leJoueur) {

                System.out.println("Nom du joueur : " + leJoueur.getNomJoueur());
                System.out.println("  - position : " + leJoueur.getPositionCourante());
                System.out.println("  - solde : " + leJoueur.getCash());
                System.out.print("  - propriétés : ");
                ArrayList<Gare> gares = leJoueur.getGares();
                if (gares.isEmpty()) {
                    System.out.println("Ce joueur n'a aucune gares.");
                } else {
                    System.out.println("gares de ce joueur : ");
                    for (Gare g : gares) {
                        System.out.println("- " + g.getNomCarreau());
                    }
                }
                ArrayList<Compagnie> compagnies = leJoueur.getCompagnies();
                if (compagnies.isEmpty()) {
                    System.out.println("Ce joueur n'a pas de compagnies.");
                } else {
                    System.out.println("compagnies de ce joueur : ");
                    for (Compagnie c : compagnies) {
                        System.out.print("- " + c.getNomCarreau());
                    }
                }
                ArrayList<ProprieteAConstruire> proprietes = leJoueur.getProprietesAConstruire();
                if (proprietes.isEmpty()) {
                    System.out.println("Ce joueur n'a aucune propriétés à construire.");
                } else {
                    System.out.println("propriétés à construire de ce joueur : ");
                    for (ProprieteAConstruire p : proprietes) {
                        System.out.print("- " + p.getNomCarreau() + " du groupe " + p.getNomGroupe());
                        int nbhotels = p.getNbHotels();
                        int nbmaisons = p.getNbMaisons()
                        if (nbmaisons == 0 && nbhotels == 0) {
                            System.out.println("(cette propriété n'a pas d'hotel ni de maisons)");
                        } else if (nbhotels == 1 ) {
                            System.out.println("(cette propriété a un hôtel)");
                        } else {
                            System.out.println("(cette propriétés a "+ nbmaisons + " maisons)");                        
                        }
                    }
                }
                        
        }
        
	
        
        /*public String messageTransaction(Joueur jPaye,Joueur jGagne,int somme) {
            return (jPaye.getNomJoueur()+"a payé "+somme+" à "+jGagne.getNomJoueur());
        }*/
        
        public String messageReceptionCash(Joueur j,int somme) {
            return (j.getNomJoueur()+" a recu "+somme+" $");
        }
        
        public String messagePerteCash(Joueur j,int somme) {
            return (j.getNomJoueur()+" a perdu "+somme+" $");
        }
}