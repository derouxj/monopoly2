package Jeu;

public class Compagnie extends CarreauPropriete implements java.io.Serializable{

    public Compagnie(int numero, String nomCarreau, int prixAchat,Monopoly monopoly) {
        super(numero, nomCarreau, prixAchat,monopoly);
    }

    /**
     *Calcul le loyer en fonction des dés
     * @return int correspondant au loyer
     */
    @Override
    public int calculLoyer() {
        if (super.getProprietaire().getCompagnies().size() == 2) {
            return 10 * (super.getMonopoly().getD1() + super.getMonopoly().getD2());
        } else {
            return 4 * (super.getMonopoly().getD1() + super.getMonopoly().getD2());
        }
    }
}
