package Jeu;
//https://github.com/derouxj/monopoly2

import UI.Interface;
import java.io.*;
import java.util.*;

public class Monopoly implements java.io.Serializable{

    private int nbMaisons = 32;
    private int nbHotels = 12;
<<<<<<< HEAD
    private HashMap<Integer, Carreau> carreaux = new HashMap<Integer, Carreau>();
    private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
    private LinkedList<CarteChance> pileCC = new LinkedList<CarteChance>();
    private LinkedList<CarteCaisseCommunaute> pileCDC = new LinkedList<CarteCaisseCommunaute>();
    public Interface interface_9 = new Interface(this);
=======
    private HashMap<Integer, Carreau> carreaux;
    private LinkedList<Joueur> joueurs;
    private LinkedList<CarteChance> pileCC;
    private LinkedList<CarteCaisseCommunaute> pileCDC;
>>>>>>> e49b702304a09937901a89d2fa10bd730ee0742d
    private int d1, d2;
    public Interface interface_9 = new Interface(this);
    
    private static String SAVE = "save.db";
    private ArrayList<CarteChance> chanceTmp = new ArrayList<CarteChance>();
    private ArrayList<CarteCaisseCommunaute> cdcTmp = new ArrayList<CarteCaisseCommunaute>();

    public Monopoly(String dataFilename, String dataFilename2) {
        buildGamePlateau(dataFilename);
        buildGameCarte(dataFilename2);
    }
    public Monopoly() {
        
    }
             // Fichier de sérialisation
	

    /**
     * @return the DB_FILE
     */
    public static String getSAVE() {
        return SAVE;
    }

    /**
     * @param fASave the DB_FILE to set
     */
    public static void setSAVE(String fASave) {
        SAVE = fASave;
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
                    ProprieteAConstruire prop = new ProprieteAConstruire(Integer.parseInt(data.get(i)[1]), data.get(i)[2], loyer, Integer.parseInt(data.get(i)[4]), grp, this);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), prop);

                } else if (caseType.compareTo("G") == 0) {
                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new Gare(Integer.parseInt(data.get(i)[1]), data.get(i)[2], this));
                } else if (caseType.compareTo("C") == 0) {
                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new Compagnie(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[3]), this));
                } else if (caseType.compareTo("CT") == 0) {
                    System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new CarreauTirage(Integer.parseInt(data.get(i)[1]), data.get(i)[2], this));
                } else if (caseType.compareTo("CA") == 0) {
                    System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new CarreauArgent(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[3]), this));
                } else if (caseType.compareTo("CM") == 0) {
                    System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.put(Integer.parseInt(data.get(i)[1]), new CarreauMouvement(Integer.parseInt(data.get(i)[1]), data.get(i)[2], this));
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

    private void buildGameCarte(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");
            Random rand = new Random();

            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];

                if (caseType.compareTo("CC") == 0) {
                    String caseType2 = data.get(i)[1];

                    if (caseType2.compareTo("L") == 0) {
                        CarteChance liberer = new CarteChance(data.get(i)[1], data.get(i)[2], this);
                        chanceTmp.add(liberer);
                    } else if (caseType2.compareTo("B") == 0) {
                        CarteChance reculer = new CarteChance(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3]), this);
                        chanceTmp.add(reculer);

                    } else if (caseType2.compareTo("M") == 0) {
                        CarteChance reparation = new CarteChance(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3]), Integer.parseInt(data.get(i)[4]), this);
                        chanceTmp.add(reparation);

                    } else if (caseType2.compareTo("A") == 0) {
                        CarteChance amende = new CarteChance(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3]), this);
                        chanceTmp.add(amende);
                    } else if (caseType2.compareTo("T") == 0) {
                        CarteChance deplacer = new CarteChance(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3]), this);
                        chanceTmp.add(deplacer);

                    } else if (caseType2.compareTo("P") == 0) {
                        CarteChance prison = new CarteChance(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3]), this);
                        chanceTmp.add(prison);

                    }

                } else if (caseType.compareTo("CDC") == 0) {
                    String caseType2 = data.get(i)[1];

                    if (caseType2.compareTo("L") == 0) {

                        CarteCaisseCommunaute liberer = new CarteCaisseCommunaute(data.get(i)[1], data.get(i)[2], this);
                        cdcTmp.add(liberer);

                    } else if (caseType2.compareTo("A") == 0) {
                        CarteCaisseCommunaute amende = new CarteCaisseCommunaute(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3]), this);
                        cdcTmp.add(amende);

                    } else if (caseType2.compareTo("N") == 0) {
                        CarteCaisseCommunaute liberer = new CarteCaisseCommunaute(data.get(i)[1], data.get(i)[2], this);
                        cdcTmp.add(liberer);

                    } else if (caseType2.compareTo("P") == 0) {
                        CarteCaisseCommunaute prison = new CarteCaisseCommunaute(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3]), this);
                        cdcTmp.add(prison);

                    } else if (caseType2.compareTo("T") == 0) {
                        CarteCaisseCommunaute deplacer = new CarteCaisseCommunaute(data.get(i)[1], data.get(i)[2], Integer.parseInt(data.get(i)[3]), this);
                        cdcTmp.add(deplacer);

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

    public void lancerDesAvancer() {
        //Carreau pos = getJoueurCourant().getPositionCourante();
        //int num = pos.getNumero();
        Joueur j = getJoueurCourant();
        System.out.println(j.getPositionCourante().getNomCarreau());
        HashMap<Integer, Carreau> collectCarreau = getCarreaux();
        j.deplacer(d1 + d2);

        int total = d1 + d2;

        System.out.println("le joueur " + j.getNomJoueur() + " a lancé les dés faisant un score de " + total + " sa nouvelle position est la case " + j.getPositionCourante().getNomCarreau());

        LinkedList<Joueur> collectJoueurs = getJoueurs();

        for (Joueur lejoueur : collectJoueurs) {
            interface_9.messageEtatJoueur(lejoueur);
        }

    }

    public void jouerUnCoup(Joueur j) {
        d1 = genDes();
        d2 = genDes();
        System.out.println(j.getNomJoueur() + " a lancé les dés et a obtenu " + d1 + " et " + d2);
        if (!j.isPrison()) {
            faireUnTour();
        } else {
            lancerDesPrison();
        }
    }

    public void joueurSuivant() {
        Joueur j = joueurs.removeFirst();
        joueurs.add(j);
    }

    public Joueur getJoueurCourant() {
        return this.getJoueurs().getFirst();
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

    public void lancerDesPrison() { //lancé si le joueur est emprisonné
        if (d1 == d2) { //le joueur fait un double
            System.out.println(getJoueurCourant().getNomJoueur() + " a fait un double(" + d1 + "," + d2 + ") et a été libéré de prison.");
            getJoueurCourant().setPrison(true);
            getJoueurCourant().setNbTourPrison(0);
            faireUnTour();
        } else { //pas de chance
            if (getJoueurCourant().getCartePrison() == 0) {
                getJoueurCourant().tourPrison();
            } else {
                System.out.println(getJoueurCourant().getNomJoueur() + " possède " + getJoueurCourant().getCartePrison() + " carte(s) pour se libérer de prison, en utiliser une ?(oui/non)");
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
                    if (rep.equals("oui")) {
                        ok = true;
                        getJoueurCourant().retirerCartePrison();
                        getJoueurCourant().setPrison(false);
                        getJoueurCourant().setNbTourPrison(0);
                        faireUnTour();
                    } else if (rep.equals("non")) {
                        ok = true;
                        getJoueurCourant().tourPrison();
                    } else {
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

    public boolean estFini() {
        return this.getJoueurs().size() == 1;
    }

    public void triche() {

        LinkedList<Joueur> joueurs1 = this.getJoueurs();

        for (Joueur leJoueurtr : joueurs1) {
            Scanner sc = new Scanner(System.in);
            System.out.println("sur quelle case souhaitez-vous aller avec ce joueur ?");
            int numvoulu = sc.nextInt();
            Carreau carreauvoulu = carreaux.get(numvoulu);
            leJoueurtr.setPositionCourante(carreauvoulu);
            leJoueurtr.getPositionCourante().action(leJoueurtr);
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
<<<<<<< HEAD
    }   
    
=======
    }
    
        
>>>>>>> e49b702304a09937901a89d2fa10bd730ee0742d
    /**
=======
    
    
        /**
>>>>>>> c9782dd7d0b7fdc11777da9f1d536ffbfc6e486d
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
    public boolean updateDB() {
                System.out.println("Update data");

        return saveDB();
    }

    /**
     * Création d'une nouvelle sérialisation
     */
    public void newDB() {
        System.out.println("Creation data");
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
	
    private boolean saveDB() {
        File file;
        boolean success = true;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;            
        
        file = new File(SAVE);
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            
            oos.writeInt(nbMaisons);
            oos.writeInt(nbHotels);
            oos.writeObject(carreaux);
            oos.writeObject(joueurs);
            oos.writeObject(pileCC);
            oos.writeObject(pileCDC);
            oos.writeInt(d1);
            oos.writeInt(d2);

            /*oos.writeInt(numDerMonit);
            oos.writeObject(lesClubs);
            oos.writeObject(lesActivites);
            oos.writeObject(lesMoniteurs);*/
            
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
    /*private int nbMaisons = 32;
    private int nbHotels = 12;
    private HashMap<Integer, Carreau> carreaux;
    private LinkedList<Joueur> joueurs;
    private LinkedList<CarteChance> pileCC;
    private LinkedList<CarteCaisseCommunaute> pileCDC;
    private int d1, d2;*/
    /**
     * Chargement des données à partir d'un fichier de sérialisation
     */
    public boolean loadDB() {
        System.out.println("Chargement");
        boolean success = true;
        File file = new File(SAVE);
        
        if (file.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;            

            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                
                nbMaisons= ois.readInt();
                nbHotels= ois.readInt();
                carreaux = (HashMap<Integer, Carreau>) ois.readObject();
                joueurs = (LinkedList<Joueur>) ois.readObject();
                pileCC = (LinkedList<CarteChance>) ois.readObject();
                pileCDC = (LinkedList<CarteCaisseCommunaute>) ois.readObject();
                d1 = ois.readInt();
                d2 = ois.readInt();
                /*numDerMonit = ois.readInt();
                lesClubs = (HashMap<String, CAF>) ois.readObject();
                lesActivites = (HashMap<String, Activite>) ois.readObject();
                lesMoniteurs = (HashMap<Integer, Moniteur>) ois.readObject();*/
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
    
}
