package Jeu;

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
                //construire();
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
        return groupePropriete.getCouleur();
    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }
}
