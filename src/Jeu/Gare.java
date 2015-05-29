package Jeu;

public class Gare extends CarreauPropriete {
    
    public Gare (int numero, String nomCarreau){
        super(numero,nomCarreau,200);
        //    numéro de la case, son nom, le prix de l'achat (toujours 200)
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

    public int calculLoyer() { // renommé de calculLoyerGare (pour factoriser)
		throw new UnsupportedOperationException();
    }

    public int getPrixLoyer() {
		throw new UnsupportedOperationException();
    }
}