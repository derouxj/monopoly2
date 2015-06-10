package Jeu;

import java.util.ArrayList;
import java.util.LinkedList;

public class Groupe implements java.io.Serializable{

    private int prixAchatMaison;
    private int prixAchatHotel;
    private CouleurPropriete couleur;
    private ArrayList<ProprieteAConstruire> proprietes = new ArrayList<ProprieteAConstruire>();

    public Groupe(int prixAchatMaison, int prixAchatHotel, CouleurPropriete couleur) {

        setPrixAchatMaison(prixAchatMaison);
        setPrixAchatHotel(prixAchatHotel);
        setCouleur(couleur);
    }
    
    public boolean estProprietaireGroupe(Joueur j){
        int i=0;
        boolean estProprio = true;
        ArrayList<ProprieteAConstruire> lesProp = this.getProprietes();
        LinkedList<ProprieteAConstruire> proprieteConstructible = new LinkedList<ProprieteAConstruire>();
        int mini=5; //initialise mini à 5, le maximum de construction qu'une propriété peut avoir
        
        while (estProprio && i<lesProp.size()) { // a la sortie, si estProprio=true, le joueur est proprietaire de toutes les prop du groupe
            if (lesProp.get(i).getProprietaire()!=j) {
                estProprio = false;
            }
            
            proprieteConstructible.add(lesProp.get(i)); // on commence a créer une liste au cas ou il est bien propriete de tous les terrains
            if (mini>lesProp.get(i).getConstruction()) { //  on vas prendre le nombre de construction qu'a le terrain le moins construit
                mini=lesProp.get(i).getConstruction();
            }
            i++;
        }
        i=0; 
        return estProprio;
    }

    private void setPrixAchatMaison(int numero) {
        this.prixAchatMaison = numero;
    }

    private void setPrixAchatHotel(int numero) {
        this.prixAchatHotel = numero;
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
    
    public int getPrixAchatMaison() {
        return prixAchatMaison;
    }
    
    public int getPrixAchatHotel() {
        return prixAchatHotel;
    }
    public void ajouterPropriete(ProprieteAConstruire prop) {
        getProprietes().add(prop);
    }
}
