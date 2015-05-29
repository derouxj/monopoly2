package Jeu;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int numero, String nomCarreau, int prixAchat) {
        super(numero, nomCarreau, prixAchat);
    }
    
    @Override
    public void action(Joueur j) {
        Joueur jProprio=this.getProprietaire();
        if (jProprio==null) {
            super.achatPropriete(j);
        } else {
            if (jProprio!=j) {
                int l = this.calculLoyer();
                jProprio.recevoirLoyer(l);
                j.payerLoyer(l);
                
            }
        }
    }
    
    public int calculLoyer() {
        return 0;
    }
}
