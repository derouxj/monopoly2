package Jeu;

public abstract class CarreauPropriete extends Carreau {

    private int prixAchat;
    private Joueur proprietaire;

    public CarreauPropriete(int numero, String nomCarreau, int prixAchat) {
        super(numero, nomCarreau);
        setPrixAchat(prixAchat);
        setProprietaire(null);
    }

    private void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getPrixAchat() {
        return this.prixAchat;
    }
    
    public void setProprietaire(Joueur j) {
        this.proprietaire=j;
    }
    
    public Joueur getProprietaire() {
        return proprietaire;
    }

    public CouleurPropriete getNomGroupe() { // a supprimer a mon avis
        throw new UnsupportedOperationException();
    }

    public void achatPropriete() {
        throw new UnsupportedOperationException();
    }
    
    public Monopoly getMonopoly() {
        return super.getMonopoly();
    }
}
