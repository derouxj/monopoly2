package Jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ProprieteAConstruire extends CarreauPropriete {

    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int[] loyers;
    private Groupe groupePropriete;

    public ProprieteAConstruire(int numero, String nomCarreau, int[] loyer, int prixAchat, Groupe groupePropriete) {
        super(numero, nomCarreau, prixAchat);
        setLoyers(loyer);
        setGroupePropriete(groupePropriete);
        groupePropriete.ajouterPropriete(this);
    }

    @Override
    public void action(Joueur j) {
        Joueur jProprio = this.getProprietaire();
        if (jProprio == null) {
            super.achatPropriete(j);
        } else {
            if (jProprio != j) {
                int l = this.calculLoyer();
                jProprio.recevoirLoyer(l);
                j.payerLoyer(l);

            } else {
                construire();
            }
        }
    }

    private void setGroupePropriete(Groupe groupePropriete) {
        this.groupePropriete = groupePropriete;
    }

    public Groupe getGroupePropriete() {
        return groupePropriete;
    }

    public void addMaison() {
        if (getNbMaisons() < 4) {
            setNbMaisons(getNbMaisons() + 1);
        } else {
            setNbMaisons(0);
            setNbHotel(1);
        }

    }

    private void setNbMaisons(int numero) {
        this.nbMaisons = nbMaisons;
    }

    private void setNbHotel(int nb) {
        nbHotels = nb;
    }

    private void setLoyers(int[] loyer) {
        this.loyers = loyer;
    }

    public int[] getLoyers() {
        return loyers;
    }

    public int calculLoyer() {
        if (getNbHotels() == 1) {
            return getLoyers()[5];//5 est le loyer d'un hotel (0 terrain nu, 4=4maisons, 5=1hotel)
        } else {
            return getLoyers()[getNbMaisons()];
        }
    }

    public CouleurPropriete getCouleur() {
        return super.getNomGroupe();
    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }
    
    public int getConstruction() {
        return getNbMaisons()+getNbHotels();
    }

    private void construire() {
        int i=0;
        boolean estProprio = true;
        Joueur proprio=super.getProprietaire();
        ArrayList<ProprieteAConstruire> lesProp = getGroupePropriete().getProprietes();
        LinkedList<ProprieteAConstruire> proprieteConstructible = new LinkedList<ProprieteAConstruire>();
        int mini=5; //initialise mini à 5, le maximum de construction qu'une propriété peut avoir
        
        while (estProprio && i<lesProp.size()) { // a la sortie, si estProprio=true, le joueur est proprietaire de toutes les prop du groupe
            if (lesProp.get(i).getProprietaire()!=proprio) {
                estProprio = false;
            }
            
            proprieteConstructible.add(lesProp.get(i)); // on commence a créer une liste au cas ou il est bien propriete de tous les terrains
            if (mini>lesProp.get(i).getConstruction()) { //  on vas prendre le nombre de construction qu'a le terrain le moins construit
                mini=lesProp.get(i).getConstruction();
            }
        }
        //On va regarder la répartition
        if (estProprio) {
            for (ProprieteAConstruire ct : proprieteConstructible) {
                if (ct.getConstruction()>mini || ct.getNbHotels()>0) {
                    proprieteConstructible.remove(ct);
                }
            }
            ProprieteAConstruire pAConstruire = super.getMonopoly().interface_9.messageChoixConstruction(proprieteConstructible);
            if (pAConstruire!=null) {
                pAConstruire.addMaison();
            }
            
        } else {
            System.out.println("Vous ne possedez pas toutes les propriétés de ce groupe");
        }
    }
}
