package Jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ProprieteAConstruire extends CarreauPropriete implements java.io.Serializable{

    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int[] loyers;
    private Groupe groupePropriete;

    public ProprieteAConstruire(int numero, String nomCarreau, int[] loyer, int prixAchat, Groupe groupePropriete,Monopoly monopoly) {
        super(numero, nomCarreau, prixAchat,monopoly);
        setLoyers(loyer);
        setGroupePropriete(groupePropriete);
        groupePropriete.ajouterPropriete(this);
    }

    /**
     *
     * @param j, si le carreau n'a pas de proprietaire alors on appelle achatPropriete sinon si le joueur n'est pas lui même propriétaire il paie la loyer enfin s'il est propriétaire la méthode construire se lance
     */
    @Override
    public void action(Joueur j) {
        Joueur jProprio = this.getProprietaire();
        if (jProprio == null) {
            super.achatPropriete(j);
        } else {
            if (jProprio != j) {
                int l = this.calculLoyer();
                jProprio.recevoir(l);
                j.payer(l);

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

    /**
     *Ajoute une maison si la propriété en possède moins de 4 sinon ajoute un hotel
     */
    public void addConstruction() {
        if (getNbMaisons() < 4) {
            setNbMaisons(getNbMaisons() + 1);
        } else {
            setNbMaisons(0);
            setNbHotel(1);
        }

    }

    private void setNbMaisons(int numero) {
        this.nbMaisons = numero;
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

    /**
     *
     * @return Calcul le loyer à payer
     */
    @Override
    public int calculLoyer() {
        int aPayer;
        int i = 0;
        Boolean superprop = true;
        Joueur proprio=this.getProprietaire();
        ArrayList<ProprieteAConstruire> groupeprop = getGroupePropriete().getProprietes();
        if (getNbHotels() == 1) {
            aPayer = getLoyers()[5];//5 est le loyer d'un hotel (0 terrain nu, 4=4maisons, 5=1hotel)
        } else if (getNbMaisons() == 0) {
            for (ProprieteAConstruire prop : groupeprop) {
                if (prop.getProprietaire()!=proprio) {
                    superprop=false;
                }
            }
            if (superprop) {
                System.out.println("Comme"+getProprietaire().getNomJoueur()+" a tous les terrains de cette couleur, le loyer est doublé");
                aPayer=getLoyers()[0]*2;
            } else {
                aPayer=getLoyers()[0];
            }
        } else {
            aPayer = getLoyers()[getNbMaisons()];
        }

        return aPayer;
    }

    public CouleurPropriete getCouleur() {
        return getGroupePropriete().getCouleur();
    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }
    
    public int getConstruction() {
        if (getNbHotels()==1) {
            return 5;
        } else {
            return getNbMaisons();
        }
    }

    private void construire() {
        ProprieteAConstruire pAConstruire=null;
        do {
            int i = 0;
            boolean estProprio = true;
            Joueur proprio = super.getProprietaire();
            Groupe grp = this.getGroupePropriete();
            ArrayList<ProprieteAConstruire> lesProp = grp.getProprietes();
            LinkedList<ProprieteAConstruire> proprieteConstructible = new LinkedList<ProprieteAConstruire>();
            int mini = 5; //initialise mini à 5, le maximum de construction qu'une propriété peut avoir

            while (estProprio && i < lesProp.size()) { // a la sortie, si estProprio=true, le joueur est proprietaire de toutes les prop du groupe
                if (lesProp.get(i).getProprietaire() != proprio) {
                    estProprio = false;
                }

                proprieteConstructible.add(lesProp.get(i)); // on commence a créer une liste au cas ou il est bien propriete de tous les terrains
                if (mini > lesProp.get(i).getConstruction()) { //  on vas prendre le nombre de construction qu'a le terrain le moins construit
                    mini = lesProp.get(i).getConstruction();
                }
                i++;
            }
            i = 0;

            //On va regarder la répartition
            if (estProprio) {
                int cash = proprio.getCash();
                int prixHotel = grp.getPrixAchatHotel();
                int prixMaison = grp.getPrixAchatMaison();

                while (i < proprieteConstructible.size()) {
                    ProprieteAConstruire ct = proprieteConstructible.get(i);
                    if ((mini == 4 && cash < prixHotel) || (mini < 4 && cash < prixMaison)) {
                        //message interface
                        proprieteConstructible.clear();
                    } else if (ct.getConstruction() > mini || ct.getConstruction() > 4) {
                        proprieteConstructible.remove(ct);
                    } else {
                        i++;
                    }
                }

                pAConstruire = super.getMonopoly().interface_9.messageChoixConstruction(proprieteConstructible);
                if (pAConstruire != null) {
                    int nbMaisonsMonopoly = super.getMonopoly().getNbMaisons();
                    int nbHotelsMonopoly = super.getMonopoly().getNbHotels();
                    if (mini == 4) {//construction d'hotel
                        if (nbHotelsMonopoly > 0) {
                            proprio.payer(prixHotel);
                            super.getMonopoly().enleverHotel();
                        }
                    } else {
                        if (nbMaisonsMonopoly > 0) {
                            proprio.payer(prixMaison);
                            super.getMonopoly().enleverMaison();
                        }
                    }
                    pAConstruire.addConstruction();
                    getMonopoly().interface_9.messageEtatJoueur(proprio);
                }

            } else {
                System.out.println("Vous ne possedez pas toutes les propriétés de ce groupe");
            }
        } while (pAConstruire != null);

    }
}
