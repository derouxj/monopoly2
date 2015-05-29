package Jeu;
//https://github.com/derouxj/monopoly2
import UI.Interface;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Monopoly {

    private int nbMaisons = 32;
    private int nbHotels = 12;
    private HashMap<Integer, Carreau> carreaux = new HashMap<Integer, Carreau>();
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    public Interface interface_9;

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
	}

	public int genDes() {
            Random desMono = new Random();
            int rouler = 0;
            for (int i = 0; i < 10; i++) {
                rouler = desMono.nextInt(6) + 1;
                
            }
          return rouler ;  
	}

    public HashMap<Integer, Carreau> getCarreaux() {
        return carreaux;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void jouerUnCoup(Joueur j) {
        lancerDesAvancer(j);
        j.getPositionCourante().action(j);
    }
}
