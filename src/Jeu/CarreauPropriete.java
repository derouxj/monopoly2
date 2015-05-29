package Jeu;

import java.util.Scanner;

public abstract class CarreauPropriete extends Carreau {

    private int prixAchat;
    private Joueur proprietaire;
    Scanner s = new Scanner (System.in);

    public CarreauPropriete(int numero, String nomCarreau, int prixAchat) {
        super(numero, nomCarreau);
        setPrixAchat(prixAchat);
        setProprietaire(null);
    }

    private void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getPrixAchat() {
        return this.prixAchat;
    }
    
    public void setProprietaire(Joueur j) {
        this.proprietaire=j;
    }
    
    public Joueur getProprietaire() {
        return proprietaire;
    }

    public CouleurPropriete getNomGroupe() { // a supprimer a mon avis
        throw new UnsupportedOperationException();
    }
    
    public void achatPropriete(Joueur j) {
        int prix = this.getPrixAchat();
        int cash = j.getCash();
        if (prix <= cash) {
        String nomC = this.getNomCarreau();
            CouleurPropriete nomG = this.getNomGroupe();
            System.out.println("Acheter "+nomC+" de "+nomG+" pour "+prix+" ? (y/n)");
            String rep = s.nextLine();
            if (rep == "y") {
                j.setCash(j.getCash()-prix);
                j.addPropriete(this);
            } else if (rep == "n") {
                System.out.println("Le joueur n'a pas voulu acheter la propriété.");
            }
        } else {
            System.out.println("Ce joueur n'a pas assez d'argent pour acheter cette propriété !");
        }    
    }
}
