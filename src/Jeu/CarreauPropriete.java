package Jeu;

import java.util.Scanner;

public abstract class CarreauPropriete extends Carreau implements java.io.Serializable{

    private int prixAchat;
    private Joueur proprietaire;

    public CarreauPropriete(int numero, String nomCarreau, int prixAchat,Monopoly monopoly) {
        super(numero, nomCarreau,monopoly);
        setPrixAchat(prixAchat);
        setProprietaire(null);
    }

    /**
     *On sait que la propriété n'a pas de propriétaire.
     * on vérifie si le joueur a assez d'argent, puis on lui propose de l'acheter ou non via l'interface.
     * si il répond oui, on lui ajoute cette propriété.
     */
    public void achatPropriete(Joueur j) {
        int prix = this.getPrixAchat();
        int cash = j.getCash();
        if (prix <= cash) {
            String nomC = this.getNomCarreau();
            Boolean rep = super.getMonopoly().interface_9.messageAchatPropriete(this, prix);
            if (rep == true) {
                j.payer(prix);
                j.addPropriete(this);
            }
        } else {
            System.out.println("Ce joueur n'a pas assez d'argent pour acheter cette propriété !");
        }
    }
    
    /**
     *Défini le joueur j comme etant le propriétaire de cette propriété
     * @param j futur propriétaire
     */
    public void definirProprietaire(Joueur j) {
        setProprietaire(j);
    }

    public Monopoly getMonopoly() {
        return super.getMonopoly();
    }

    public abstract int calculLoyer();
    
    private void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getPrixAchat() {
        return this.prixAchat;
    }

    private void setProprietaire(Joueur j) {
        this.proprietaire = j;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }
}
