package Jeu;
//https://github.com/derouxj/monopoly2

import UI.Interface;
import java.io.*;
import java.util.*;

public class Monopoly {

    private int nbMaisons = 32;
    private int nbHotels = 12;
    private HashMap<Integer, Carreau> carreaux = new HashMap<Integer, Carreau>();
    private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
    private LinkedList<CarteChance> pileCC = new LinkedList<CarteChance>();
    private LinkedList<CarteCaisseCommunaute> pileCDC = new LinkedList<CarteCaisseCommunaute>();

    public Interface interface_9 = new Interface();
    private int d1, d2;

    public Monopoly(String dataFilename) {
        buildGamePlateau(dataFilename);
    }

    private void buildGamePlateau(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");

            //TODO: create cases instead of displaying
            HashMap<String, Groupe> lesGroupes = new HashMap<String, Groupe>();
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];

                if (caseType.compareTo("P") == 0) {
                    System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    
                    Groupe grp;
                    if (!lesGroupes.containsKey(data.get(i)[3])) {
                        lesGroupes.put(data.get(i)[3], new Groupe(Integer.parseInt(data.get(i)[11]), Integer.parseInt(data.get(i)[12]), CouleurPropriete.valueOf(data.get(i)[3])));

                    }
                    grp = lesGroupes.get(data.get(i)[3]);
                    int loyer[] = new int[7];
                    for (int j = 0; j <= 6; j++) {
                        loyer[j] = Integer.parseInt(data.get(i)[j + 5]);
                    }
                    ProprieteAConstruire prop = new ProprieteAConstruire(Integer.parseInt(data.get(i)[1]), data.get(i)[2], loyer, Integer.parseInt(data.get(i)[4]), grp,this);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), prop);
                    
                } else if (caseType.compareTo("G") == 0) {
                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new Gare(Integer.parseInt(data.get(i)[1]), data.get(i)[2],this));
                } else if (caseType.compareTo("C") == 0) {
                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new Compagnie(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[3]),this));
                } else if (caseType.compareTo("CT") == 0) {
                    System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new CarreauTirage(Integer.parseInt(data.get(i)[1]), data.get(i)[2],this));
                } else if (caseType.compareTo("CA") == 0) {
                    System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new CarreauArgent(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[3]),this));
                } else if (caseType.compareTo("CM") == 0) {
                    System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new CarreauTirage(Integer.parseInt(data.get(i)[1]), data.get(i)[2],this));
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

    public void lancerDesAvancer() {
        //Carreau pos = getJoueurCourant().getPositionCourante();
        //int num = pos.getNumero();
        System.out.println(getJoueurCourant().getPositionCourante().getNomCarreau());
        int num = getJoueurCourant().getPositionCourante().getNumero();
        HashMap<Integer, Carreau> collectCarreau = getCarreaux();
        int numFuture = (d1 + d2 + num)%40; //modulo
        if (d1 + d2 + num >40){
            getJoueurCourant().ajouterCash(200);
        }
        
        Carreau posFuture = collectCarreau.get(numFuture);
        getJoueurCourant().setPositionCourante(posFuture);

        int total = d1 + d2;

        System.out.println("le joueur " + getJoueurCourant().getNomJoueur() + " a lancé les dés faisant un score de " + total + " sa nouvelle position est la case " +        getJoueurCourant().getPositionCourante().getNomCarreau());


        LinkedList<Joueur> collectJoueurs = getJoueurs();

        for (Joueur lejoueur : collectJoueurs) {
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

    public int getD1() {
        return d1;
    }

    public int getD2() {
        return d2;
    }
    
    public void setD1(int dpr){
        this.d1=dpr;
    }
   
    public void setD2(int dse){
        this.d2=dse;
    }
    public LinkedList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void jouerUnCoup(Joueur j) {
        d1 = genDes();
        d2 = genDes();
        System.out.println(j.getNomJoueur()+" a lancé les dés et a obtenu "+d1+" et "+d2);
        if(!j.isPrison()) {
            faireUnTour();
        }
        else {
            lancerDesPrison();
        }
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
        for (int i = 0; i < lesLances.size(); i++) {
            if (max < lesLances.get(i)) {
                max = lesLances.get(i);
            }
        }
        int laPos = lesLances.indexOf(max);
        joueurs.add(js.get(laPos));
        js.remove(js.get(laPos));
        while (!js.isEmpty()) {
            joueurs.add(js.getFirst());
            js.remove(js.getFirst());
        }
    }

    public boolean estFini() {
        return this.getJoueurs().size() == 1;
    }

    public void joueurSuivant() {
        Joueur j = joueurs.removeFirst();
        joueurs.add(j);
    }
    
    public int getNbMaisons() {
        return nbMaisons;
    }
    public int getNbHotels() {
        return nbHotels;
    }
    
    public Joueur getJoueurCourant() {
        return this.getJoueurs().getFirst();
    }
    
    public void triche(){
        
        LinkedList<Joueur> joueurs1 =  this.getJoueurs();
        
        for(Joueur leJoueurtr : joueurs1){
            Scanner sc = new Scanner(System.in);
                System.out.println("sur quelle case souhaitez-vous aller avec ce joueur ?");
            int numvoulu = sc.nextInt();
            Carreau carreauvoulu = carreaux.get(numvoulu);
                leJoueurtr.setPositionCourante(carreauvoulu);
                leJoueurtr.getPositionCourante().action(leJoueurtr);
                } 
               
        }
    
    public CarteChance tirerCarteChance() {
        CarteChance carte = pileCC.getFirst();
        pileCC.removeFirst();
        pileCC.addLast(carte);
        return carte;
    }
    
    public CarteCaisseCommunaute tirerCarteCaisseCommunaute() {
        CarteCaisseCommunaute carte = pileCDC.getFirst();
        pileCDC.removeFirst();
        pileCDC.addLast(carte);
        return carte;
    }

    /**
     * a lancer si le joueur est emprisonné
     */
    public void lancerDesPrison() {
        if (d1 == d2) { //le joueur fait un double
            System.out.println(getJoueurCourant().getNomJoueur()+" a fait un double("+d1+","+d2+") et a été libéré de prison.");
            getJoueurCourant().setPrison(true);
            getJoueurCourant().setNbTourPrison(0);
            faireUnTour();
        }
        else { //pas de chance
            if(getJoueurCourant().getCartePrison()==0) {
                getJoueurCourant().tourPrison();
            }
            else{
                System.out.println(getJoueurCourant().getNomJoueur()+" possède "+getJoueurCourant().getCartePrison()+" carte(s) pour se libérer de prison, en utiliser une ?(oui/non)");
                Scanner sc = new Scanner(System.in);
                String rep;
                boolean ok = false;
                while (!ok) {
                    if (sc.hasNextLine()) {
                        rep = sc.nextLine();
                    } else {
                        sc.next();
                        continue;
                    }
                    if(rep.equals("oui")) {
                        ok = true;
                        getJoueurCourant().retirerCartePrison();
                        getJoueurCourant().setPrison(false);
                        getJoueurCourant().setNbTourPrison(0);
                        faireUnTour();
                    }
                    else if(rep.equals("non")) {
                        ok = true;
                        getJoueurCourant().tourPrison();
                    }
                    else {
                        System.out.println("Veuillez répondre correctement !");
                        ok = false;
                    }
                }
            }
        }
    }
    
    public void faireUnTour() {
        lancerDesAvancer();
        getJoueurCourant().getPositionCourante().action(getJoueurCourant());
    }
}
