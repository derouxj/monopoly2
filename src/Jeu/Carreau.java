package Jeu;

public abstract class Carreau {

    private int numero;
    private String nomCarreau;
    private Monopoly monopoly;

    public Carreau(int numero, String nomCarreau) {
        setNumero(numero);
        setCarreau(nomCarreau);
    }
    
    public abstract void action(Joueur j) ;

    private void setNumero(int numero) {
        this.numero = numero;
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
}
