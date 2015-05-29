package Jeu;
//https://github.com/derouxj/monopoly2
import UI.Interface;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Monopoly {

    private int nbMaisons = 32;
    private int nbHotels = 12;
    private HashMap<Integer, Carreau> carreaux = new HashMap<Integer, Carreau>();
    private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
    public Interface interface_9 = new Interface();

    public Monopoly(String dataFilename) {
        buildGamePlateau(dataFilename);
    }

    private void buildGamePlateau(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");

            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];
                
                if (caseType.compareTo("P") == 0) {
                    System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    HashMap<String,Groupe> lesGroupes= new HashMap<String,Groupe>();
                    Groupe grp;
                    if (!lesGroupes.containsKey(data.get(i)[3])) {
                        lesGroupes.put(data.get(i)[3], new Groupe(Integer.parseInt(data.get(i)[11]), Integer.parseInt(data.get(i)[12]),CouleurPropriete.valueOf(data.get(i)[3])));

                    }
                    grp=lesGroupes.get(data.get(i)[3]);
                    int loyer[] = new int[7];
                    for (int j = 0; j <= 6; j++) {
                        loyer[j] = Integer.parseInt(data.get(i)[j + 5]);
                    }
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new ProprieteAConstruire(Integer.parseInt(data.get(i)[1]), data.get(i)[2], loyer, Integer.parseInt(data.get(i)[4]), grp));
                 } else if (caseType.compareTo("G") == 0) {
                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new Gare(Integer.parseInt(data.get(i)[1]), data.get(i)[2]));
                } else if (caseType.compareTo("C") == 0) {
                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new Compagnie(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[3])));
                } else if (caseType.compareTo("CT") == 0) {
                    System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new CarreauTirage(Integer.parseInt(data.get(i)[1]), data.get(i)[2]));
                } else if (caseType.compareTo("CA") == 0) {
                    System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new CarreauArgent(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[3])));
                } else if (caseType.compareTo("CM") == 0) {
                    System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new CarreauTirage(Integer.parseInt(data.get(i)[1]), data.get(i)[2]));
                } else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }

    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        while ((line = reader.readLine()) != null) {
            data.add(line.split(token));
        }
        reader.close();

        return data;
    }

public void lancerDesAvancer(Joueur j) {
		int d1 = genDes();
                int d2 = genDes();
                Carreau pos = j.getPositionCourante();
                int num = pos.getNumero();
                HashMap<Integer,Carreau> collectCarreau = getCarreaux();
                int numFuture = d1+d2+num;
                Carreau posFuture = collectCarreau.get(numFuture);
                    
                    j.deplacer(posFuture);
                
                String nom = j.getNomJoueur();
                int total = d1+d2;
                String nomCarreau = posFuture.getNomCarreau();
                
                    System.out.println("le joueur "+nom+" a lancé les dés faisant un score de "+total+" sa nouvelle position est la case "+nomCarreau);
                
                LinkedList<Joueur> collectJoueurs = getJoueurs();
                
                    for (Joueur lejoueur : collectJoueurs){
                      interface_9.messageEtatJoueur(lejoueur);
                    }
                
	}

    public int genDes() {
        Random rand = new Random();
        return rand.nextInt((6 - 1) + 1) + 1;
    }

    public HashMap<Integer, Carreau> getCarreaux() {
        return carreaux;
    }

    public LinkedList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void jouerUnCoup(Joueur j) {
        lancerDesAvancer(j);
        j.getPositionCourante().action(j);
    }
    
    public void inscrireJoueurs(int nbj) {
        ArrayList<Integer> lesLances = new ArrayList<Integer>();
        LinkedList<Joueur> js = new LinkedList<Joueur>();
        joueurs.clear();
        for (int i = 0; i < nbj; i++) {
            String nom = interface_9.nouveauJoueur();
            js.add(new Joueur(nom, this));
            int nb = genDes() + genDes();
            lesLances.add(nb);
            System.out.println("Le joueur " + js.get(i).getNomJoueur() + " a obtenu " + nb);
        }
        int max = 0;
        for (int i = 0;i < lesLances.size(); i++) {
            if (max < lesLances.get(i)) {
                max = lesLances.get(i);
            }
        }
        int laPos = lesLances.indexOf(max);
        joueurs.add(js.get(laPos));
        js.remove(js.get(laPos));
        while(!js.isEmpty()) {
            joueurs.add(js.getFirst());
            js.remove(js.getFirst());
        }
        System.out.println(lesLances.size());
        System.out.println(js.size());
    }
    
    public boolean estFini() {
        return this.getJoueurs().size() == 1;
    }
}
