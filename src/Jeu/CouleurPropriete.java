package Jeu;

public enum CouleurPropriete implements java.io.Serializable{

    bleuFonce("Bleu foncé"),
    orange("Orange"),
    mauve("Mauve"),
    violet("Violet"),
    bleuCiel("Bleu ciel"),
    jaune("Jaune"),
    vert("Vert"),
    rouge("Rouge");

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
