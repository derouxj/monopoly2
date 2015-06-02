package Jeu;

import java.util.ArrayList;

public class Groupe {

    private int prixAchatMaison;
    private int prixAchatHotel;
    private CouleurPropriete couleur;
    private ArrayList<ProprieteAConstruire> proprietes = new ArrayList<ProprieteAConstruire>();

    public Groupe(int prixAchatMaison, int prixAchatHotel, CouleurPropriete couleur) {

        setPrixAchatMaison(prixAchatMaison);
        setPrixAchatHotel(prixAchatHotel);
        setCouleur(couleur);
    }

    private void setPrixAchatMaison(int numero) {
        this.prixAchatMaison = prixAchatMaison;
    }

    private void setPrixAchatHotel(int numero) {
        this.prixAchatHotel = prixAchatHotel;
    }

    private void setCouleur(CouleurPropriete couleur) {
        this.couleur = couleur;
    }

    public CouleurPropriete getCouleur() {
        return this.couleur;
    }
    
    public ArrayList<ProprieteAConstruire> getProprietes() {
        return proprietes;
    }
    
    public void ajouterPropriete(ProprieteAConstruire prop) {
        getProprietes().add(prop);
    }
}
