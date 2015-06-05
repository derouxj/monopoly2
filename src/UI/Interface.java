package UI;

import Jeu.Monopoly;
import Jeu.*;
import java.util.Scanner;
import java.util.*;

public class Interface {
	public Monopoly monopoly;
        Scanner sc = new Scanner(System.in);


        public Interface() {
            
        }
        
	public void messageEtatJoueur(Joueur leJoueur) {

                System.out.println("Nom du joueur : " + leJoueur.getNomJoueur());
                System.out.println("  - position : " + leJoueur.getPositionCourante().getNomCarreau());
                System.out.println("  - solde : " + leJoueur.getCash());
                System.out.println("  - propriétés : ");
                ArrayList<Gare> gares = leJoueur.getGares();
                if (gares.isEmpty()) {
                    System.out.println("      Ce joueur n'a aucune gares.");
                } else {
                    System.out.println("      gares de ce joueur : ");
                    for (Gare g : gares) {
                        System.out.println("      - " + g.getNomCarreau());
                    }
                }
                ArrayList<Compagnie> compagnies = leJoueur.getCompagnies();
                if (compagnies.isEmpty()) {
                    System.out.println("      Ce joueur n'a pas de compagnies.");
                } else {
                    System.out.println("      compagnies de ce joueur : ");
                    for (Compagnie c : compagnies) {
                        System.out.print("      - " + c.getNomCarreau());
                    }
                }
                ArrayList<ProprieteAConstruire> proprietes = leJoueur.getProprietesAConstruire();
                if (proprietes.isEmpty()) {
                    System.out.println("      Ce joueur n'a aucune propriétés à construire.");
                } else {
                    System.out.println("      propriétés à construire de ce joueur : ");
                    for (ProprieteAConstruire p : proprietes) {
                        System.out.print("      - " + p.getNomCarreau() + " du groupe " + p.getCouleur());
                        int nbhotels = p.getNbHotels();
                        int nbmaisons = p.getNbMaisons();
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
        
        public String nouveauJoueur() {
            System.out.println("\nNom du joueur : ");
            String nom = sc.nextLine();
            return nom;
        }
        
        public Boolean messageAchatPropriete(String nomC,int prix, Joueur j){
            Scanner sc = new Scanner(System.in);
            boolean aRepondu = false;
            String rep;
            do {
                System.out.println("Acheter "+nomC+" pour "+prix+" ? (y/n)");
                rep = sc.nextLine();
                if(rep.equals("y")|| rep.equals("n")) {
                    aRepondu = true;
                }
            }while(!aRepondu);
            
            if (rep.equals("y")) {
                System.out.println("confirmation de l'achat de " + nomC+ " par "+ j.getNomJoueur());
                return true;
            } else {
                System.out.println("Le joueur n'a pas voulu acheter la propriété.");
                return false;
            }
        }
        
        public ProprieteAConstruire messageChoixConstruction(LinkedList<ProprieteAConstruire> lesTerrains) {
            if (lesTerrains.isEmpty()) {return null;}
            
            int nbterrain=0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Sur quelle terrain voulez vous construire ?"+"\n\t0 - Aucun");
            for (ProprieteAConstruire pc : lesTerrains) {
                nbterrain=nbterrain+1;
                System.out.println("\t"+nbterrain+" - "+pc.getNomCarreau());
            }
            int choix = sc.nextInt();
            
            if (choix==0 || choix>nbterrain) {
                return null;
            } else {
                return lesTerrains.get(choix);
            }
         }
}
