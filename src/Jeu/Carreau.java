package Jeu;

public abstract class Carreau implements java.io.Serializable{

    private int numero;
    private String nomCarreau;
    private Monopoly monopoly;

    public Carreau(int numero, String nomCarreau,Monopoly monopoly) {
        setNumero(numero);
        setCarreau(nomCarreau);
        setMonopoly(monopoly);
    }

    public abstract void action(Joueur j);

    private void setNumero(int numero) {
        this.numero = numero;
    }
    
    private void setMonopoly(Monopoly mp) {
        monopoly=mp;
    }

    private void setCarreau(String nomCarreau) {
        this.nomCarreau = nomCarreau;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getNomCarreau() {
        return this.nomCarreau;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }
}
