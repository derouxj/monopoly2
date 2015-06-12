package Jeu;
//https://github.com/derouxj/monopoly2

import UI.Interface;
import java.io.*;
import java.util.*;

public class Monopoly implements java.io.Serializable{

    private int nbMaisons = 32;
    private int nbHotels = 12;
    private HashMap<Integer, Carreau> carreaux;
    private LinkedList<Joueur> joueurs;
    private LinkedList<CarteChance> pileCC;
    private LinkedList<CarteCaisseCommunaute> pileCDC;
    private int d1, d2;
    private Score score;
    public Interface interface_9 = new Interface(this);
    
    private static String SAVE = "save.db";
    private static String SCORE = "score.db";

    public Monopoly(String dataFilename, String dataFilename2) {
        buildGamePlateau(dataFilename);
        buildGameCarte(dataFilename2);
    }
    public Monopoly() {
        setScore(new Score());
    }
             // Fichier de sérialisation
	

    /**
     * @return the DB_FILE
     */
    public static String getSAVE() {
        return SAVE;
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

    private void buildGamePlateau(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");

            //TODO: create cases instead of displaying
            HashMap<String, Groupe> lesGroupes = new HashMap<String, Groupe>();
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];

                if (caseType.compareTo("P") == 0) {
                    //System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);

                    Groupe grp;
                    if (!lesGroupes.containsKey(data.get(i)[3])) {
                        lesGroupes.put(data.get(i)[3], new Groupe(Integer.parseInt(data.get(i)[11]), Integer.parseInt(data.get(i)[12]), CouleurPropriete.valueOf(data.get(i)[3])));

                    }
                    grp = lesGroupes.get(data.get(i)[3]);
                    int loyer[] = new int[7];
                    for (int j = 0; j <= 6; j++) {
                        loyer[j] = Integer.parseInt(data.get(i)[j + 5]);
                    }
                    ProprieteAConstruire prop = new ProprieteAConstruire(Integer.parseInt(data.get(i)[1]), data.get(i)[2], loyer, Integer.parseInt(data.get(i)[4]), grp, this);
                    getCarreaux().put(Integer.parseInt(data.get(i)[1]), prop);

                } else if (caseType.compareTo("G") == 0) {
                    //System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    getCarreaux().put(Integer.parseInt(data.get(i)[1]), new Gare(Integer.parseInt(data.get(i)[1]), data.get(i)[2], this));
                } else if (caseType.compareTo("C") == 0) {
                    //System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    getCarreaux().put(Integer.parseInt(data.get(i)[1]), new Compagnie(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[3]), this));
                } else if (caseType.compareTo("CT") == 0) {
                    //System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    getCarreaux().put(Integer.parseInt(data.get(i)[1]), new CarreauTirage(Integer.parseInt(data.get(i)[1]), data.get(i)[2], this));
                } else if (caseType.compareTo("CA") == 0) {
                    //System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    getCarreaux().put(Integer.parseInt(data.get(i)[1]), new CarreauArgent(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[3]), this));
                } else if (caseType.compareTo("CM") == 0) {
                    //System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    getCarreaux().put(Integer.parseInt(data.get(i)[1]), new CarreauMouvement(Integer.parseInt(data.get(i)[1]), data.get(i)[2], this));
                } else {
                    //System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }

     private void buildGameCarte(String dataFilename) {
        LinkedList<CarteChance> chanceTmp = new LinkedList<CarteChance>();
        LinkedList<CarteCaisseCommunaute> cdcTmp = new LinkedList<CarteCaisseCommunaute>();
        Random rand = new Random();
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");

            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];

                if (caseType.compareTo("CC") == 0) {
                    String caseType2 = data.get(i)[1];

                    if (caseType2.compareTo("L") == 0) {
                        chanceTmp.add(new CarteChance(data.get(i)[1], data.get(i)[2]));
                    } else if (caseType2.compareTo("B") == 0) {
                        chanceTmp.add(new CarteChance(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3])));

                    } else if (caseType2.compareTo("M") == 0) {
                        chanceTmp.add(new CarteChance(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3]), Integer.parseInt(data.get(i)[4])));
                    } else if (caseType2.compareTo("A") == 0) {
                        chanceTmp.add(new CarteChance(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3])));
                    } else if (caseType2.compareTo("T") == 0) {
                        chanceTmp.add(new CarteChance(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3])));
                    } else if (caseType2.compareTo("P") == 0) {
                        chanceTmp.add(new CarteChance(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3])));
                    } else {
                        System.err.println("[buildGameCarte()] : Invalid Data type");
                    }

                } else if (caseType.compareTo("CDC") == 0) {
                    String caseType2 = data.get(i)[1];

                    if (caseType2.compareTo("L") == 0) {
                        cdcTmp.add(new CarteCaisseCommunaute(data.get(i)[1], data.get(i)[2]));
                    } else if (caseType2.compareTo("A") == 0) {
                        cdcTmp.add(new CarteCaisseCommunaute(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3])));
                    } else if (caseType2.compareTo("N") == 0) {
                        cdcTmp.add(new CarteCaisseCommunaute(data.get(i)[1], data.get(i)[2]));
                    } else if (caseType2.compareTo("P") == 0) {
                        cdcTmp.add(new CarteCaisseCommunaute(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3])));
                    } else if (caseType2.compareTo("T") == 0) {
                        cdcTmp.add(new CarteCaisseCommunaute(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3])));
                    } else {
                        System.err.println("[buildGameCarte()] : Invalid Data type");
                    }
                }
            }
            int nbCC = chanceTmp.size()-1;
            int nbCDC= cdcTmp.size()-1;
            
            int rnd;
            
            while (!chanceTmp.isEmpty()) {
                rnd=rand.nextInt((nbCC - 0 + 1) + 0);
                getPileCC().add(chanceTmp.get(rnd));
                chanceTmp.remove(rnd);
                nbCC--;
            }
            
            while (!cdcTmp.isEmpty()) {
                rnd=rand.nextInt((nbCDC - 0 + 1) + 0);
                getPileCDC().add(cdcTmp.get(rnd));
                cdcTmp.remove(rnd);
                nbCDC--;
            }
            
            /*for (CarteChance cc : getPileCC()) {
                System.out.println(cc.getDescription());
            }
            System.out.println("\n\n");
            for (CarteCaisseCommunaute cdc : getPileCDC()) {
                System.out.println(cdc.getDescription());
            }*/
            
        } catch (FileNotFoundException e) {
            System.err.println("[buildGameCarte()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGameCarte()] : Error while reading file!");
        }
    }

    /**
     *
     * @param nbj, Ordonne les joueurs en fonction du joueur ayant fait le plus gros lancé de dés (il sera positionné en premier) le joueur à sa gauche est le suivant et ainsi de suite
     */
    public void inscrireJoueurs(int nbj) {
        ArrayList<Integer> lesLances = new ArrayList<Integer>();
        LinkedList<Joueur> js = new LinkedList<Joueur>();
        boolean conflit = false;
        joueurs.clear();
        for (int i = 0; i < nbj; i++) {
            boolean reel = interface_9.affecterTypeJoueur();
            boolean dejaUtilisé=false;
            String nom=null;
            while (!dejaUtilisé) {
                boolean trouve=false;
                int j=0;
                nom = interface_9.nouveauJoueur(reel);
                while (j<js.size() && !trouve && js.size()>0) {
                    if (js.get(j).getNomJoueur().equals(nom)) {
                        trouve=true;
                        interface_9.affichageAutreNom();
                    }
                    j++;
                }
                if (!trouve) {
                    dejaUtilisé=true;
                }
            }
            if(reel) {
                js.add(new JoueurReel(nom, this));
            }
            else {
                js.add(new Robot(nom, this));
            }
            int nb = genDes() + genDes();
            lesLances.add(nb);
            interface_9.affichageLancerInscrire(js.get(i),nb,false);
        }
        int max = 0;
        for (int i = 0; i < lesLances.size(); i++) {
            if (lesLances.get(i) == max) {
                conflit = true;
            }
            if (max < lesLances.get(i)) {
                max = lesLances.get(i);
            }
        }
        while (conflit) { //conflit si deux joueurs ont affichageLancerInscrire le même affichageScore
            interface_9.affichageConflit();
            for (int i = 0; i < nbj; i++) {
                if (lesLances.get(i) == max) {
                    int nb = genDes() + genDes();
                    lesLances.set(i, lesLances.get(i) + nb);
                    interface_9.affichageLancerInscrire(js.get(i),nb,true);
                }
            }
            for (int i = 0; i < lesLances.size(); i++) {
                if (lesLances.get(i) == max) {
                    conflit = true;
                }
                if (max < lesLances.get(i)) {
                    max = lesLances.get(i);
                } else {
                    conflit = false;
                }
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

    /**
     *Récupère les dés lancés et déplace le joueur
     */
    public void avancer() {
        Joueur j = getJoueurCourant();
        int total = getD1() + getD2();
        j.deplacer(total);
        interface_9.affichageAvancer(j, total);

        LinkedList<Joueur> collectJoueurs = getJoueurs();

        for (Joueur lejoueur : collectJoueurs) {
            interface_9.messageEtatJoueur(lejoueur);
        }

    }

    /**
     *
     * @param j, Lance les dés, si le joueur est en prison : appelle faire un tour, sinon lance : lancerDesPrison
     */
    public void jouerUnCoup(Joueur j) {
        setD1(genDes());
        setD2(genDes());
        interface_9.affichageJouerUnCoup(j.getNomJoueur(),getD1(),getD2());
        if (!j.isPrison()) {
            faireUnTour();
        } else {
            lancerDesPrison();
        }
    }

    /**
     * Passe la main au joueur suivant
     */
    public void joueurSuivant() {
        Joueur j = getJoueurs().removeFirst();
        getJoueurs().add(j);
    }

    /**
     *
     * @return le joueur courant
     */
    public Joueur getJoueurCourant() {
        return this.getJoueurs().getFirst();
    }

    /**
     *
     * @return la carte chance au sommet de la pile
     */
    public CarteChance tirerCarteChance() {
        CarteChance carte = getPileCC().getFirst();
        getPileCC().removeFirst();
        getPileCC().addLast(carte);
        return carte;
    }

    /**
     *
     * @return la carte communaute au sommet de la pile
     */
    public CarteCaisseCommunaute tirerCarteCaisseCommunaute() {
        CarteCaisseCommunaute carte = getPileCDC().getFirst();
        getPileCDC().removeFirst();
        getPileCDC().addLast(carte);
        return carte;
    }

    /**
     *Si le joueur emprisonné fait un double alors il est libéré, s'il possède une carte "libérer de prison" il a le choix de l'utiliser ou non
     */
    public void lancerDesPrison() { //lancé si le joueur est emprisonné
        if (getD1() == getD2()) { //le joueur fait un double
            interface_9.affichageLancerDesPrison(getJoueurCourant().getNomJoueur(),getD1(),getD2());
            getJoueurCourant().setPrison(false);
            getJoueurCourant().setNbTourPrison(0);
            faireUnTour();
        } else { //pas de chance
            if (getJoueurCourant().getCartePrison() == 0) {
                getJoueurCourant().tourPrison();
            } else {
                boolean boucle = true;
                while (boucle) {
                    int choix = interface_9.choisirAvecContexte(getJoueurCourant().getNomJoueur() + " possède " + getJoueurCourant().getCartePrison() + " carte(s) pour se libérer de prison, en utiliser une ?\n1. Oui\n2. Non");
                    switch (choix) {
                        case 1: {
                            getJoueurCourant().retirerCartePrison();
                            getJoueurCourant().setPrison(false);
                            getJoueurCourant().setNbTourPrison(0);
                            faireUnTour();
                            boucle = false;
                            break;
                        }
                        case 2: {
                            getJoueurCourant().tourPrison();
                            boucle = false;
                            break;
                        }
                        default:
                            interface_9.messageErreurScan(false);
                            break;
                    }
                }
            }
        }
    }

    /**
     *Lance les dés et execute l'action correspondante au carreau
     */
    public void faireUnTour() {
        avancer();
        getJoueurCourant().getPositionCourante().action(getJoueurCourant());
    }
    
    /**
     *
     * @return si c'est la fin du tour
     */
    public boolean estFini() {
        return this.getJoueurs().size() == 1;
    }

    /**
     *Demande la saisit du numéro de la case et déplace le joueur à cette case
     */
    public void triche() {

        LinkedList<Joueur> joueurs1 = this.getJoueurs();

        for (Joueur leJoueurtr : joueurs1) {
            Scanner sc = new Scanner(System.in);
            int numvoulu = interface_9.choisirAvecContexte("Sur quelle case souhaitez-vous aller avec ce joueur ?");
            Carreau carreauvoulu = getCarreaux().get(numvoulu);
            leJoueurtr.setPositionCourante(carreauvoulu);
            leJoueurtr.getPositionCourante().action(leJoueurtr);
        }

    }
    
        /**
     * @return the pileCC
     */
    public LinkedList<CarteChance> getPileCC() {
        return pileCC;
    }

    /**
     * @return the pileCDC
     */
    public LinkedList<CarteCaisseCommunaute> getPileCDC() {
        return pileCDC;
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

    public void setD1(int dpr) {
        this.d1 = dpr;
    }

    public void setD2(int dse) {
        this.d2 = dse;
    }

    public LinkedList<Joueur> getJoueurs() {
        return joueurs;
    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }


    
    
    /**
     *Ajoute au monopoly des maisons
     * @param nbMaison nombre de maison a ajouter au monopoly
     */
    public void ajouterMaison(int nbMaison) {
        setNbMaisons(getNbMaisons()+nbMaison);
    }
    
    /**
     *Enlève au monopoly UNE maison au monopoly
     */
    public void enleverMaison() {
        setNbMaisons(getNbMaisons()-1);
    }
    
    /**
     *Ajoute au monopoly des hotel
     * @param nbHotel nombre d'hotel à ajouter au monopoly
     */
    public void ajouterHotel(int nbHotel) {
        setNbHotels(getNbHotels()+nbHotel);
    }
    
    /**
     *Enlève au monopoly UN hotel au monopoly
     */
    public void enleverHotel() {
        setNbHotels(getNbHotels()-1);
    }

    /**
     * @param nbMaisons the nbMaisons to set
     */
    public void setNbMaisons(int nbMaisons) {
        this.nbMaisons = nbMaisons;
    }

    /**
     * @param nbHotels the nbHotels to set
     */
    public void setNbHotels(int nbHotels) {
        this.nbHotels = nbHotels;
    }
    
        /**
     * Met à jour du fichier de sérialisation
     */
    public boolean updateDBSave() {
        return saveDBSave();
    }

    /**
     * Création d'une nouvelle sérialisation
     */
    public void newDBSave() {
        interface_9.messageData(true,false);
	this.setCarreaux(new HashMap<Integer, Carreau>());
	this.setJoueurs(new LinkedList<Joueur>());
        this.setPileCC(new LinkedList<CarteChance>());
        this.setPileCDC(new LinkedList<CarteCaisseCommunaute>());
        this.buildGamePlateau("src/data/data.txt");
        this.buildGameCarte("src/data/data_Carte.txt");
    }
    
   /**
    * Sauvegarde du fichier de sérialisation
    */
	
    private boolean saveDBSave() {
        File file;
        boolean success = true;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;            
        
        file = new File(SAVE);
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            
            oos.writeInt(getNbMaisons());
            oos.writeInt(getNbHotels());
            oos.writeObject(getCarreaux());
            oos.writeObject(getJoueurs());
            oos.writeObject(getPileCC());
            oos.writeObject(getPileCDC());
            oos.writeInt(getD1());
            oos.writeInt(getD2());
            
        }
        catch (Exception e) {
            System.out.println("SAVE" + e);
            success = false;
        }
        finally {
                if (oos != null) { 
                    try { oos.close(); }
                    catch(IOException e) {}
                }
                
                if (fos != null) { 
                    try { fos.close(); }
                    catch(IOException e) {}
                }
            
        }
        return success;
    }
    /**
     * Chargement des données à partir d'un fichier de sérialisation
     */
    public boolean loadDBSave() {
        interface_9.messageData(false,true);
        boolean success = true;
        File file = new File(SAVE);
        
        if (file.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;            

            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                
                setNbMaisons(ois.readInt());
                setNbHotels(ois.readInt());
                setCarreaux((HashMap<Integer, Carreau>) ois.readObject());
                setJoueurs((LinkedList<Joueur>) ois.readObject());
                setPileCC((LinkedList<CarteChance>) ois.readObject());
                setPileCDC((LinkedList<CarteCaisseCommunaute>) ois.readObject());
                setD1(ois.readInt());
                setD2(ois.readInt());
            }             
            catch(Exception e) {
                System.out.println("LOAD" + e);
                success = false;
            }
            finally {
                if (ois != null) { 
                    try { ois.close(); }
                    catch(IOException e) {}
                }
                
                if (fis != null) { 
                    try { fis.close(); }
                    catch(IOException e) {}
                }
            }
        } else { success = false; }
        return success;
    }

    /**
     * @param carreaux the carreaux to set
     */
    public void setCarreaux(HashMap<Integer, Carreau> carreaux) {
        this.carreaux = carreaux;
    }

    /**
     * @param joueurs the joueurs to set
     */
    public void setJoueurs(LinkedList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * @param pileCC the pileCC to set
     */
    public void setPileCC(LinkedList<CarteChance> pileCC) {
        this.pileCC = pileCC;
    }

    /**
     * @param pileCDC the pileCDC to set
     */
    public void setPileCDC(LinkedList<CarteCaisseCommunaute> pileCDC) {
        this.pileCDC = pileCDC;
    }
    
    /**
     * Met à jour du fichier de sérialisation
     */
    public boolean updateDBScore() {
        return saveDBScore();
    }

    /**
     * Création d'une nouvelle sérialisation
     */
    public void newDBScore() {
        interface_9.messageData(true, true);
	setScore(new Score());
        getScore().setLesMeilleursJ(new ArrayList<Joueur>());
    }
    
   /**
    * Sauvegarde du fichier de sérialisation
    */
	
    private boolean saveDBScore() {
        File file;
        boolean success = true;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;            
        
        file = new File(getSCORE());
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(getScore().getLesMeilleursJ());
            
        }
        catch (Exception e) {
            System.out.println("SAVE" + e);
            success = false;
        }
        finally {
                if (oos != null) { 
                    try { oos.close(); }
                    catch(IOException e) {}
                }
                
                if (fos != null) { 
                    try { fos.close(); }
                    catch(IOException e) {}
                }
            
        }
        return success;
    }
    /**
     * Chargement des données à partir d'un fichier de sérialisation
     */
    public boolean loadDBScore() {
        interface_9.messageData(false, true);
        boolean success = true;
        File file = new File(getSCORE());
        
        if (file.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;            

            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);

                getScore().setLesMeilleursJ((ArrayList<Joueur>) ois.readObject());
                
            }             
            catch(Exception e) {
                System.out.println("LOAD" + e);
                success = false;
            }
            finally {
                if (ois != null) { 
                    try { ois.close(); }
                    catch(IOException e) {}
                }
                
                if (fis != null) { 
                    try { fis.close(); }
                    catch(IOException e) {}
                }
            }
        } else { success = false; }
        return success;
    }

    /**
     * @return the affichageScore
     */
    public Score getScore() {
        return score;
    }

    /**
     * @param score the affichageScore to set
     */
    private void setScore(Score score) {
        this.score = score;
    }
        /**
     * @return the SCORE
     */
    public static String getSCORE() {
        return SCORE;
    }

    /**
     * @param aSCORE the SCORE to set
     */
    private static void setSCORE(String aSCORE) {
        SCORE = aSCORE;
    }
}