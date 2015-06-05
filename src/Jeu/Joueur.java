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
    private int nbTourPrison;
    private boolean prison;
    private int cartePrison;

    public Joueur(String n, Monopoly m) {
        this.setNomJoueur(n);
        this.setMonopoly(m);
        compagnies = new ArrayList<Compagnie>();
        gares = new ArrayList<Gare>();
        proprietesAConstruire = new ArrayList<ProprieteAConstruire>();
        this.setPositionCourante(monopoly.getCarreaux().get(1));
        this.setPrison(false);
        this.setCartePrison(0);
        this.setNbTourPrison(0);
    }

    public Carreau getPositionCourante() {
        return this.positionCourante;
    }

    public void deplacer(Carreau posFuture) {
        this.setPositionCourante(posFuture);
    }

    public void recevoirLoyer(int l) {
        setCash(getCash() + l);
        System.out.println(getMonopoly().interface_9.messageReceptionCash(this, l));
    }

    public void payerLoyer(int l) {
        if (getCash() - l < 0) {
            System.out.println("PERDU"); //a finir
        } else {
            setCash(getCash() - l);
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

    public void addPropriete(CarreauPropriete c) {
        if (c.getClass().getSimpleName().equals("Gare")) {
            this.gares.add((Gare) c);
        } else if (c.getClass().getSimpleName().equals("Compagnie")) {
            this.compagnies.add((Compagnie)c);
        } else if (c.getClass().getSimpleName().equals("ProprieteAConstruire")) {
            this.proprietesAConstruire.add((ProprieteAConstruire) c);
        }
        c.setProprietaire(this);
    }

    public void setCash(int solde) {
        cash=solde;
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

    public void envoyerPrison() {
        this.setPositionCourante(monopoly.getCarreaux().get(11));
        this.setPrison(true);
        this.getMonopoly().interface_9.messagePrison(this);
    }

    /**
     * @return the nbTourPrison
     */
    public int getNbTourPrison() {
        return nbTourPrison;
    }

    /**
     * @param nbTourPrison the nbTourPrison to set
     */
    public void setNbTourPrison(int nbTourPrison) {
        this.nbTourPrison = nbTourPrison;
    }

    /**
     * @return the prison
     */
    public boolean isPrison() {
        return prison;
    }

    /**
     * @param prison the prison to set
     */
    public void setPrison(boolean prison) {
        this.prison = prison;
    }

    /**
     * @return the cartePrison
     */
    public int getCartePrison() {
        return cartePrison;
    }

    /**
     * @param cartePrison the cartePrison to set
     */
    public void setCartePrison(int cartePrison) {
        this.cartePrison = cartePrison;
    }
    
    public void ajouterCartePrison() {
        this.setCartePrison(cartePrison+1);
        this.getMonopoly().interface_9.messageCartePrison(true, this);
    }

    public void retirerCartePrison() {
        this.setCartePrison(cartePrison-1);
        this.getMonopoly().interface_9.messageCartePrison(false, this);
    }
    
    public void ajouterCash(int cash){
        setCash(getCash()+cash);
    }
    
    
}
