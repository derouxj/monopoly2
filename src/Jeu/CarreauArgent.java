package Jeu;

public class CarreauArgent extends CarreauAction {

    private int montant;

    public CarreauArgent(int numero, String nomCarreau, int montant) {
        super(numero, nomCarreau);
        setMontant(montant);

    }
    
    @Override
        public void action(Joueur j) {
            
        }

    private void setMontant(int prixAchat) {
        this.montant = montant;
    }
    

}
