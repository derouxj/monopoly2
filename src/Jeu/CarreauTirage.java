package Jeu;

import java.util.ArrayList;
import java.util.LinkedList;

public class CarreauTirage extends CarreauAction implements java.io.Serializable{
    


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
        if (monType.equals("L")) {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().ajouterCartePrison();
        } else if (monType.equals("T")) {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().envoyerCase(ct.getNombreAction());
        } else if (monType.equals("N")) {
            System.out.println(ct.getDescription());
            this.anniversaire();
        } else if (monType.equals("A")) {
            System.out.println(ct.getDescription());
            if (ct.getNombreAction()>0) {
                super.getMonopoly().getJoueurCourant().recevoir(ct.getNombreAction());
            } else {
                super.getMonopoly().getJoueurCourant().payer(ct.getNombreAction()*-1);
            }
        } else if (monType.equals("B")) {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().deplacer(ct.getNombreAction());
        } else if (monType.equals("P")) {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().envoyerPrison();
        } else if (monType.equals("M")) {
            System.out.println(ct.getDescription());
            this.reparation(ct, j);
        } else {
            System.out.println("ERREUR, type non reconnu");
        }
    } 
    
    public void anniversaire() {
        LinkedList<Joueur> collectJoueurs = new LinkedList<Joueur>(super.getMonopoly().getJoueurs());
        //collectJoueurs=super.getMonopoly().getJoueurs();
        collectJoueurs.removeFirst();
        for (Joueur j : collectJoueurs) {
            j.payer(10);
        }
        super.getMonopoly().getJoueurCourant().recevoir(10*collectJoueurs.size());
    }
    
    public void reparation(Carte ct,Joueur j) {
        int nbM = 0;
        int nbH = 0;
        int prixR;
        ArrayList<ProprieteAConstruire> collectPAC;
        collectPAC = j.getProprietesAConstruire();
        for (ProprieteAConstruire pAC : collectPAC) {
            nbH = nbH + pAC.getNbHotels();
            nbM = nbM + pAC.getNbMaisons();
        }
        prixR = nbH * ct.getReparationHotel() + nbM * ct.getReparationMaison();
        j.payer(prixR);
        
            System.out.println("Les réparations ont été faites sur " + nbM +" maisons et " + nbH + " hotels pour le prix de "+ prixR + "$.");
    }

}
