package Jeu;

import java.util.LinkedList;

public class CarreauTirage extends CarreauAction {

    public CarreauTirage(int numero, String nomCarreau,Monopoly monopoly) {
        super(numero, nomCarreau,monopoly);
    }

    @Override
    public void action(Joueur j) {
        Carte ct;
        if(super.getNomCarreau()=="Chance") {
            ct = super.getMonopoly().tirerCarteChance();
        } else {
            ct = super.getMonopoly().tirerCarteCaisseCommunaute();
        }
        
        String monType=ct.getType();
        if (monType=="L") {
            super.getMonopoly().getJoueurCourant().ajouterCartePrison();
        } else if (monType=="T") {
            super.getMonopoly().getJoueurCourant().envoyerCase(ct.getNombreAction());
        } else if (monType=="M") {
            this.anniversaire();
        } else if (monType=="A") {
            super.getMonopoly().getJoueurCourant().recevoir(ct.getNombreAction());
        } else if (monType=="B") {
            super.getMonopoly().getJoueurCourant().deplacer(ct.getNombreAction());
        } else if (monType=="P") {
            super.getMonopoly().getJoueurCourant().envoyerPrison();
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
