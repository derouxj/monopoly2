package Jeu;

public class CarreauTirage extends CarreauAction {

    public CarreauTirage(int numero, String nomCarreau,Monopoly monopoly) {
        super(numero, nomCarreau,monopoly);

    }

    @Override
    public void action(Joueur j) {
        if(super.getNomCarreau()=="Chance") {
            //super.getMonopoly().tirerCarteChance()
        } else {
            
        }
    } 

}
