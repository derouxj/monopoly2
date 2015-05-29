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
        Boolean rep == super.getMonopoly().interface_9.achatProprieteInter(nomC,nomG,prix);
        if (rep == true) {
                j.setCash(j.getCash()-prix);
                j.addPropriete(this);
                System.out.println("confirmation de l'achat de " + this.getNomCarreau()+ " par "+ j.getNomJoueur());
        } else if (rep == false) {
                System.out.println("Le joueur n'a pas voulu acheter la propriété.");
            }
        } else {
            System.out.println("Ce joueur n'a pas assez d'argent pour acheter cette propriété !");
        }    
    }
}
