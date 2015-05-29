package UI;

import Jeu.Monopoly;
import Jeu.*;
import java.util.Scanner;

public class Interface {
	public Monopoly monopoly;

        public Interface() {
            
        }
        
	public void messageEtatJoueur(Joueur leJoueur) {
		throw new UnsupportedOperationException();
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
            Scanner sc = new Scanner(System.in);
            System.out.println("\nNom du joueur : ");
            String nom = sc.nextLine();
            return nom;
        }
}