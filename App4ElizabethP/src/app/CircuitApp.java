package app;

import java.io.File;
import java.util.Scanner;

public class CircuitApp {

    private static final char fSep = File.separatorChar;

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircuitApp app = new CircuitApp();
        System.out.println("Bonjour, quel fichier voulez vous-calculer? Tapez le chiffre correspondant au fichier souhaité.");
        app.choixFichier();
        app.recommencer();
    }

    public void choixFichier() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[1] : complexe_industriel_zone_nord.json" + "\n" + "[2] : eclairage_public_quartier.json" + "\n" + "[3] : reseau_secours_hopital.json");
        int choix1 = sc.nextInt();
        CircuitBuilder c = new CircuitBuilder();
        System.out.println(c.construireCircuit(cheminFichier(choix1)).calculerResistance() + "Ω");
    }

    public String cheminFichier(int i) {
        String s;
        if (i == 1) {
            s = "complexe_industriel_zone_nord.json";
        } else if (i == 2) {
            s = "eclairage_public_quartier.json";
        } else if (i == 3) {
            s = "reseau_secours_hopital.json";
        } else {
            throw new IllegalArgumentException("Chiffre invalide");
        }
        String c = System.getProperty("user.dir") + fSep + "App4ElizabethP" + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json" + fSep + s;
        return c;
    }

    public void recommencer() {
        Scanner sc = new Scanner(System.in);
        boolean c;
        System.out.println("Tapez la lettre correspondante à l'action souhaitée." + "\n" + "[R] Tester un autre fichier" + "\n" + "[Q] Quitter");
        String choix2 = sc.nextLine();
        if (choix2.equals("R")) {
            c = true;
        } else if (choix2.equals("Q")) {
            c = false;
        } else {
            System.out.println("Erreur : entrez seulement R ou Q");
        }
        while (c) {
            choixFichier();
            System.out.println("Taper la lettre corespondante à l'action souhaitée." + "\n" + "[R] Tester un autre fichier" + "\n" + "[Q] Quitter");
            String choi2 = sc.nextLine();
            if (choi2.equals("R")) {
                c = true;
            } else if (choi2.equals("Q")) {
                c = false;
            } else {
                System.out.println("Erreur : entrez seulement R ou Q");
            }
        }
    }
}
