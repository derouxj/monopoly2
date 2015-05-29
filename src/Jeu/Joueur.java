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

	public Carreau getPositionCourante() {
		return this.positionCourante;
	}

	public void deplacer(Carreau posFuture) {
		throw new UnsupportedOperationException();
	}
        
        public void recevoirLoyer(int l) {
            setCash(getCash()+l);
            System.out.println(monopoly.interface_9.messageReceptionCash(this, l));
        }
        
        public void payerLoyer(int l) {
            if (getCash()-l<0) {
                System.out.println("PERDU"); //a finir
            } else {
                setCash(getCash()-l);
                System.out.println(monopoly.interface_9.messagePerteCash(this, l));
            }
        }

	public String getNomJoueur() {
		return this.nomJoueur;
	}

	public ArrayList<Gare> getGares() {
		throw new UnsupportedOperationException();
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


    public ArrayList<Compagnie> getCompagnies() {
        return compagnies;
    }
  
    public ArrayList<ProprieteAConstruire> getProprietesAConstruire() {
        return proprietesAConstruire;
    }
}