package app;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import electronique.*;

import java.io.File;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;

public class CircuitBuilder {

    //private static final char fSep = File.separatorChar;
    //private static final String cheminFichier = System.getProperty("user.dir") + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json" + fSep + "reseau_secours_hopital.json";
    private ArrayList<Composant> composants;


    public CircuitBuilder (){
    }

    public void construireCircuit(String cheminFichier){
        try {
            //composants = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode donnees = mapper.readTree(new File(cheminFichier));
            String type = mapper.get("circuit");
            return lireComposant(donnees);
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }
    }


    private Composant lireComposant(JsonNode node){
        String type = node.get("type").asText();

        if ("resistance".equals(type)) {
            return new Resistance(node.get("valeur").asDouble());

        } else if ("parallele".equals(type)) {
            ArrayList<Composant> composant = new ArrayList<>();
            for (JsonNode composantNode : node.get("composants")) {
                composant.add(lireComposant(composantNode));
            }
            return new CircuitParallele(composant);
        } else if ("serie".equals(type)) {
            ArrayList<Composant> composant = new ArrayList<>();
            for (JsonNode composantNode : node.get("composants")) {
                composant.add(lireComposant(composantNode));
            }
            return new CircuitSerie(composant);
        }

        throw new IllegalArgumentException("Type de circuit inconnu : " + type);
    }

}
