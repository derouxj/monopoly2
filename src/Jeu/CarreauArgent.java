package Jeu;

public class CarreauArgent extends CarreauAction {

    private int montant;

    public CarreauArgent(int numero, String nomCarreau, int montant,Monopoly monopoly) {
        super(numero, nomCarreau,monopoly);
        setMontant(montant);

    }

    @Override
    public void action(Joueur j) {
        int somme=getMontant();
        if (somme>0) {
            j.recevoir(somme);
        } else {
            j.payer(somme);
        }
    }

    private void setMontant(int montant) {
        this.montant = montant;
    }
    
    public int getMontant() {
        return montant;
    }

}
