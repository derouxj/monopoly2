package Jeu;

import UI.Interface;
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
            super.getMonopoly().interface_9.affichageDescriptionCarte(ct.getDescription(), false);
            j.ajouterCartePrison();
        } else if (monType.equals("T")) {
            super.getMonopoly().interface_9.affichageDescriptionCarte(ct.getDescription(), false);
            j.envoyerCase(ct.getNombreAction());
            j.getPositionCourante().action(j);
        } else if (monType.equals("N")) {
            super.getMonopoly().interface_9.affichageDescriptionCarte(ct.getDescription(), false);
            this.anniversaire(j);
        } else if (monType.equals("A")) {
            super.getMonopoly().interface_9.affichageDescriptionCarte(ct.getDescription(), false);
            if (ct.getNombreAction()>0) {
                j.recevoir(ct.getNombreAction());
            } else {
                j.payer(ct.getNombreAction()*-1);
            }
        } else if (monType.equals("B")) {
            super.getMonopoly().interface_9.affichageDescriptionCarte(ct.getDescription(), false);
            j.deplacer(ct.getNombreAction());
            j.getPositionCourante().action(j);
        } else if (monType.equals("P")) {
            super.getMonopoly().interface_9.affichageDescriptionCarte(ct.getDescription(), false);
            j.envoyerPrison();
        } else if (monType.equals("M")) {
            super.getMonopoly().interface_9.affichageDescriptionCarte(ct.getDescription(), false);
            this.reparation(j,ct);
        } else {
            super.getMonopoly().interface_9.affichageDescriptionCarte(null, true);
        }
    } 
    
    public void anniversaire(Joueur j) {
        LinkedList<Joueur> collectJoueurs = new LinkedList<Joueur>(super.getMonopoly().getJoueurs());
        //collectJoueurs=super.getMonopoly().getJoueurs();
        collectJoueurs.removeFirst();
        for (Joueur js : collectJoueurs) {
            js.payer(10);
        }
        j.recevoir(10*collectJoueurs.size());
    }
    public void reparation(Joueur j,Carte ct) {
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
        
        super.getMonopoly().interface_9.affichageReparation(nbM,nbH,prixR);
    }

}
