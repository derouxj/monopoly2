package Jeu;

public enum CouleurPropriete {
	bleuFonce("bleuFonce"),
        orange("orange"), 
        mauve("mauve"), 
        violet("violet"), 
        bleuCiel("bleuCiel"), 
        jaune("jaune"), 
        vert("vert"), 
        rouge("rouge");
        
        private String couleur;
        
        CouleurPropriete(String couleur) {
            setCouleur(couleur);
        }

    public String getCouleur() {
        return couleur;
    }
    
    private void setCouleur(String couleur) {
        this.couleur = couleur;
    }
}