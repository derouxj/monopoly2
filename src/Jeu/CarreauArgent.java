package Jeu;

public class CarreauArgent extends CarreauAction implements java.io.Serializable{

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
        } else if(somme<0) {
            j.payer(somme*-1);
        } else {
            
        }
    }

    private void setMontant(int montant) {
        this.montant = montant;
    }
    
    public int getMontant() {
        return montant;
    }

}
