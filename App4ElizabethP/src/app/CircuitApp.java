package app;

import java.io.File;
import java.util.Scanner;

public class CircuitApp {

    private static final char fSep = File.separatorChar;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bonjour, quelle fichier voulez vous calculer? Taper le chiffre correspondant au fichier souhaiter.");
        System.out.println("[1] : complexe_indistriel_zone_nord.json" + "[2] : eclairage_public_quartier.json" + "[3] : reseau_secours_hopital.json" );
        int choix1 = sc.nextInt();
        CircuitBuilder.construireCircuit(cheminFichier(choix1));




    }

    public static String cheminFichier(int i) {
        String s;
        if (i == 1) {
            s = "complexe_indistriel_zone_nord.json";
        } else if (i == 2) {
            s = "eclairage_public_quartier.json";
        } else if (i == 3) {
            s = "reseau_secours_hopital.json";
        } else {
            throw new IllegalArgumentException("Chiffre invalide");
        }
        String c = System.getProperty("user.dir") + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json" + fSep + s;
        return c;
    }
}
