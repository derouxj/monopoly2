package UI;

import Jeu.Monopoly;
import Jeu.*;

public class Interface {
	public Monopoly monopoly;

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
}