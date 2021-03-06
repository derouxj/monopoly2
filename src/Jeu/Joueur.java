package Jeu;

import UI.Interface;
import java.util.ArrayList;

public abstract class Joueur implements java.io.Serializable {
    private boolean estReel;
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

    public Joueur(boolean estJoueurReel,String n, Monopoly m) {
        this.setNomJoueur(n);
        this.setMonopoly(m);
        this.setEstJoueurReel(estJoueurReel);
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

    /**
     * Ajoute la somme en paramètre au cash du joueur.
     *
     * @param l somme a recevoir
     */
    public void recevoir(int l) {
        if (l > 0) {
            setCash(getCash() + l);
            getMonopoly().interface_9.messageReceptionCash(this, l);
        }

    }
    
    
     public boolean estReel (){
        return estReel;
    }
     
    public void setEstJoueurReel(boolean estReel){
        this.estReel = estReel;
    } 
    

    /**
     * Enlève la somme en paramètre au cash du joueur. si il n'a pas assez de
     * cash, il a perdu et est supprimé de la liste des joueurs.
     *
     * @param l somme à enlever
     */
    public void payer(int l) {
        if (getCash() - l < 0) {
            this.virer();
        } else if (l>0) {
            setCash(getCash() - l);
            getMonopoly().interface_9.messagePerteCash(this, l);
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

    /**
     * on ajoute la propriété donné en paramètre. si c'est une gare, on ajoute
     * cette gare à l'arraylist "gares" en vérifiant qu'il n'en possède pas plus
     * de 4 (pour etre conforme au diagramme de classe) si c'est une compagnie,
     * on ajoute cette compagnie à l'arraylist "compagnies" en vérifiant qu'il
     * n'en possède pas plus de 2 (pour etre conforme au diagramme de classe) si
     * c'est une propriété à construire, on ajoute cette propriété à
     * "priprietesAConstruire" en vérifiant qu'il n'en possède pas plus de 28
     * (pour etre conforme au diagramme de classe)
     */
    public void addPropriete(CarreauPropriete c) {
        
        int tailleProp = this.getProprietesAConstruire().size();
        int i = 0;
        boolean plusPetit = false;
        
        switch (c.getClass().getSimpleName()) {
            case "Gare":
                if (getGares().size()<=4) {
                    getGares().add((Gare)c);
                }   break;
            case "Compagnie":
                if (getCompagnies().size()<=2) {
                    getCompagnies().add((Compagnie)c);
                }   break;
            case "ProprieteAConstruire":
                if (getProprietesAConstruire().size()<28) {
                    if(tailleProp == 0){
                        getProprietesAConstruire().add((ProprieteAConstruire) c);
                    }
                    else{
                        while(i<tailleProp && !plusPetit){
                            if(getProprietesAConstruire().get(i).getNumero()>c.getNumero()){
                                getProprietesAConstruire().add(i,(ProprieteAConstruire) c );
                                plusPetit = true;
                            }
                            else{
                                i++;
                            }
                        }
                        if (i == tailleProp) {
                            getProprietesAConstruire().add((ProprieteAConstruire)c);
                        }
                    }
                }   break;
        }
        c.definirProprietaire(this);
    }

    public void setCash(int solde) {
        cash = solde;
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

    /**
     * Change la position du joueur par celle de la prison (case n°11) Change la
     * variable prison du joueur à true. envoie le joueur en prison
     */
    public void envoyerPrison() {
        this.setPositionCourante(getMonopoly().getCarreaux().get(11));
        this.setPrison(true);
        this.getMonopoly().interface_9.messagePrison(this);
    }

    /**
     * Change la position du joueur par celle du numero en paramètre. Si il
     * passe par la case départ il recoit 200€
     *
     * @param numero numero de la case d'arrivé
     */
    public void envoyerCase(int numero) {
        int numPos = getPositionCourante().getNumero();

        if (numPos > numero && numero > 0) {
            recevoir(200);
        } else if (numPos < numero && numero < 0) {
            //recevoir(200);
        }
        setPositionCourante(getMonopoly().getCarreaux().get(numero));
    }


    /**
     * deplace le joueur du nombre de case inscrit en paramètre. Vérifie
     * également si il est passé par la case départ.
     *
     * @param nbCaseADeplacer avance le joueur de ce nombre
     */
    public void deplacer(int nbCaseADeplacer) {
        envoyerCase(verifPos(nbCaseADeplacer));
    }

    /**
     * Vérifie si le joueur est arrivé à la fin du plateau ou pas
     *
     * @param nbAAvancer nombre de case à avancer
     */
    private int verifPos(int nbAAvancer) {
        int posFutur = getPositionCourante().getNumero() + nbAAvancer;
        if (posFutur > 40) {
            return posFutur-40;
        } else {
            return posFutur;
        }
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

        this.setCartePrison(getCartePrison() + 1);
        this.getMonopoly().interface_9.messageCartePrison(true);
    }

    public void retirerCartePrison() {
        this.setCartePrison(getCartePrison() - 1);
        this.getMonopoly().interface_9.messageCartePrison(false);
    }

    public void ajouterCash(int cash) {
        setCash(getCash() + cash);
    }

    /**
     * Vérifie le nombre de tour passé en prison par le joueur et applique la
     * règle: si il a passé moins de deux tours, il rejoue sinon il doit payer
     * 50
     */
    public void tourPrison() {
        if (this.getNbTourPrison() > 2) {
            getMonopoly().interface_9.affichageTourPrison(this.getNomJoueur(), true);
            this.setNbTourPrison(0);
            this.payer(50);
            this.setPrison(false);
            getMonopoly().faireUnTour();
        } else {
            getMonopoly().interface_9.affichageTourPrison(this.getNomJoueur(), false);
            this.setNbTourPrison(this.getNbTourPrison() + 1);
        }
    }

    /**
     * Supprime le joueur du monopoly, rend toutes ses maisons et hotels à la
     * banque et rend tous ses terrains achetable.
     */
    public void virer() {
        for (Gare g : getGares()) {
            g.definirProprietaire(null);
        }
        for (Compagnie c : getCompagnies()) {
            c.definirProprietaire(null);
        }
        for (ProprieteAConstruire pc : getProprietesAConstruire()) {
            getMonopoly().ajouterMaison(pc.getNbMaisons());
            getMonopoly().ajouterHotel(pc.getNbHotels());
            pc.definirProprietaire(null);
        }
        getMonopoly().getJoueurs().remove(this);
        getMonopoly().interface_9.affichageVirer(this);
    }

}
