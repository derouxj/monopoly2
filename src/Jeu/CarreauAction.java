package Jeu;

public abstract class CarreauAction extends Carreau implements java.io.Serializable{

    public CarreauAction(int numero, String nomCarreau,Monopoly monopoly) {
        super(numero, nomCarreau,monopoly);
    }
}
