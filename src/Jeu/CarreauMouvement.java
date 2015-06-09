package Jeu;

public class CarreauMouvement extends CarreauAction implements java.io.Serializable{

    public CarreauMouvement(int numero, String nomCarreau,Monopoly monopoly) {
        super(numero, nomCarreau,monopoly);

    }

    @Override
    public void action(Joueur j) {
        j.envoyerPrison();
    }

}
