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
    
    @Override
    public String toString() {
        String couleur = "";
        if (this == bleuFonce) {
            couleur = "34";
        } else if(this== orange) {
            couleur = "33";
        } else if (this== mauve) {
            couleur ="35";
        } else if (this== violet) {
            couleur ="35";
        } else if (this==bleuCiel) {
            couleur ="36";
        } else if (this==jaune) {
            couleur ="33";
        } else if (this==vert) {
            couleur ="32";
        } else if (this==rouge) {
            couleur ="31";
        } else {
            System.err.println("NON RECONNU");
        }
        return "\033[" + couleur + "m" + super.toString() + "\033[0m";
    }
}
