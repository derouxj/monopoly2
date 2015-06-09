package Jeu;

import java.util.ArrayList;
import java.util.LinkedList;

public class CarreauTirage extends CarreauAction implements java.io.Serializable{
    private ArrayList<ProprieteAConstruire> collectPAC;
    private int nbM = 0;
    private int nbH = 0;
    private int prixR;

    public CarreauTirage(int numero, String nomCarreau,Monopoly monopoly) {
        super(numero, nomCarreau,monopoly);
    }

    @Override
    public void action(Joueur j) {
        Carte ct;
        if(super.getNomCarreau().equals("Chance")) {
            ct = super.getMonopoly().tirerCarteChance();
        } else {
            ct = super.getMonopoly().tirerCarteCaisseCommunaute();
        }
        
        String monType=ct.getType();
        if (monType=="L") {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().ajouterCartePrison();
        } else if (monType=="T") {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().envoyerCase(ct.getNombreAction());
        } else if (monType=="N") {
            System.out.println(ct.getDescription());
            this.anniversaire();
        } else if (monType=="A") {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().recevoir(ct.getNombreAction());
        } else if (monType=="B") {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().deplacer(ct.getNombreAction());
        } else if (monType=="P") {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().envoyerPrison();
        } else if (monType=="M") {
            System.out.println(ct.getDescription());
            collectPAC = j.getProprietesAConstruire();
            for (ProprieteAConstruire pAC : collectPAC){
                nbH = nbH + pAC.getNbHotels();
                nbM = nbM + pAC.getNbMaisons();
            }
            prixR = nbH * ct.getReparationHotel() + nbM*ct.getReparationMaison();
            int cash = j.getCash();
            j.setCash(cash-prixR);
            System.out.println("Les réparations ont été faites sur " + nbM +" maisons et " + nbH + " hotels pour le prix de "+ prixR + "$.");
        } else {
            System.out.println("ERREUR, type non reconnu");
        }
    } 
    
    public void anniversaire() {
        LinkedList<Joueur> collectJoueurs = super.getMonopoly().getJoueurs();
        collectJoueurs.removeFirst();
        for (Joueur j : collectJoueurs) {
            j.setCash(j.getCash()-10);
        }
        super.getMonopoly().getJoueurCourant().recevoir(10*collectJoueurs.size());
    }
    
}
