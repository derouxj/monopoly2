package Jeu;

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
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().ajouterCartePrison();
        } else if (monType=="T") {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().envoyerCase(ct.getNombreAction());
        } else if (monType=="M") {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().anniversaire();
        } else if (monType=="A") {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().recevoir(ct.getNombreAction());
        } else if (monType=="B") {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().deplacer(ct.getNombreAction());
        } else if (monType=="P") {
            System.out.println(ct.getDescription());
            super.getMonopoly().getJoueurCourant().envoyerPrison();
        } else {
            System.out.println("ERREUR, type non reconnu");
        }
    } 
}
