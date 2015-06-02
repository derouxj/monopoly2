package Jeu;

import java.util.ArrayList;

public class Joueur {
	private String nomJoueur;
	private int cash = 1500;
	private Monopoly monopoly;
	private ArrayList<Compagnie> compagnies = new ArrayList<Compagnie>();
	private ArrayList<Gare> gares = new ArrayList<Gare>();
	private Carreau positionCourante;
	private ArrayList<ProprieteAConstruire> proprietesAConstruire = new ArrayList<ProprieteAConstruire>();

        public Joueur(String n, Monopoly m) {
            this.setNomJoueur(n);
            this.setMonopoly(m);
            compagnies = new ArrayList<Compagnie>();
            gares = new ArrayList<Gare>();
            proprietesAConstruire = new ArrayList<ProprieteAConstruire>();
            this.setPositionCourante(monopoly.getCarreaux().get(0));
        }
        
	public Carreau getPositionCourante() {
		return this.positionCourante;
	}

	public void deplacer(Carreau posFuture) {
		throw new UnsupportedOperationException();
	}
        
        public void recevoirLoyer(int l) {
            setCash(getCash()+l);
            System.out.println(getMonopoly().interface_9.messageReceptionCash(this, l));
        }
        
        public void payerLoyer(int l) {
            if (getCash()-l<0) {
                System.out.println("PERDU"); //a finir
            } else {
                setCash(getCash()-l);
                System.out.println(getMonopoly().interface_9.messagePerteCash(this, l));
            }
        }

	public String getNomJoueur() {
		return this.nomJoueur;
	}

	public ArrayList<Gare> getGares() {
		return this.gares;
	}

	public int getCash() {
		return this.cash;
	}

	public void addPropriete(Carreau c) {
		throw new UnsupportedOperationException();
	}

	public void setCash(int solde) {
		throw new UnsupportedOperationException();
	}

    /**
     * @param nomJoueur the nomJoueur to set
     */
    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    /**
     * @return the monopoly
     */
    public Monopoly getMonopoly() {
        return monopoly;
    }

    /**
     * @param monopoly the monopoly to set
     */
    public void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     * @return the compagnies
     */
    public ArrayList<Compagnie> getCompagnies() {
        return compagnies;
    }

    /**
     * @param compagnies the compagnies to set
     */
    public void setCompagnies(ArrayList<Compagnie> compagnies) {
        this.compagnies = compagnies;
    }

    /**
     * @param gares the gares to set
     */
    public void setGares(ArrayList<Gare> gares) {
        this.gares = gares;
    }

    /**
     * @param positionCourante the positionCourante to set
     */
    public void setPositionCourante(Carreau positionCourante) {
        this.positionCourante = positionCourante;
    }

    /**
     * @return the proprietesAConstruire
     */
    public ArrayList<ProprieteAConstruire> getProprietesAConstruire() {
        return proprietesAConstruire;
    }

    /**
     * @param proprietesAConstruire the proprietesAConstruire to set
     */
    public void setProprietesAConstruire(ArrayList<ProprieteAConstruire> proprietesAConstruire) {
        this.proprietesAConstruire = proprietesAConstruire;
    }
    
    public void envoyerPrison (){
            this.setPositionCourante(monopoly.getCarreaux().get(11));
    }


    
}
