package app;

import java.io.File;
import java.util.Scanner;

public class CircuitApp {

    private static final char fSep = File.separatorChar;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircuitApp app = new CircuitApp();
        System.out.println("Bonjour, quelle fichier voulez vous calculer? Taper le chiffre correspondant au fichier souhaiter.");
        app.choixFichier();
        app.recommencer();
    }

    public void choixFichier() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[1] : complexe_industriel_zone_nord.json" + "\n" + "[2] : eclairage_public_quartier.json" + "\n" + "[3] : reseau_secours_hopital.json");
        int choix1 = sc.nextInt();
        CircuitBuilder c = new CircuitBuilder();
        System.out.println(c.construireCircuit(cheminFichier(choix1)).calculerResistance() + "Ω");

        //System.out.println("Taper [R] si vous souhaitez tester un autre fichier");
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

    public void recommencer(){
        Scanner sc = new Scanner(System.in);
        boolean c;
        System.out.println("Taper la lettre correspondant à l'action souhaitée." + "\n" + "[R] Tester un autre fichier" + "\n" + "[Q] Quitter");
        String choix2 = sc.nextLine();
        if (choix2.equals("R")){
            c = true;
        } else if (choix2.equals("Q")) {
            c = false;
        }else{
            throw new IllegalArgumentException();
        }
        while (c){
            choixFichier();
            System.out.println("Taper la lettre corespondant a l'action souhaité." + "\n" + "[R] Tester un autre fichier" + "\n" + "[Q] Quitter");
            String choi2 = sc.nextLine();
            if (choi2.equals("R")){
                c = true;
            } else if (choi2.equals("Q")) {
                c = false;
            }else{
                System.out.println("Erreur: entrez seulement R ou Q");
            }
        }
    }
}
