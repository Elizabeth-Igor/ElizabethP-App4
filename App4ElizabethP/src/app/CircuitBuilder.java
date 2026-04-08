package app;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import electronique.CircuitParallele;
import electronique.CircuitSerie;
import electronique.Composant;
import electronique.Resistance;

import java.io.File;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {

    //private static final char fSep = File.separatorChar;
    //private static final String cheminFichier = System.getProperty("user.dir") + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json" + fSep + "reseau_secours_hopital.json";
    private ArrayList<Composant> composants;



    public Composant construireCircuit(String cheminFichier){
        composants = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode donnees = mapper.readTree(new File(cheminFichier));

        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }

    }


    private Composant lireComposant(JsonNode node){
        String type = node.get("type").asText();

        if ("resistance".equals(type)) {
            return new Composant(node.get("valeur").asDouble());

        } else if ("parallele".equals(type)) {
            List<Composant> composant = new ArrayList<>();
            for (JsonNode composantNode : node.get("composants")) {
                composant.add(lireComposant(composantNode));
            }
            return new CircuitParallele(composant);
        } else if ("serie".equals(type)) {
            List<Composant> composant = new ArrayList<>();
            for (JsonNode composantNode : node.get("composants")) {
                composant.add(lireComposant(composantNode));
            }
            return new CircuitSerie(composant);
        }

        throw new IllegalArgumentException("Type de circuit inconnu : " + type);
    }

}
