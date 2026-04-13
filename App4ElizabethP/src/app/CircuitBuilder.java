package app;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import electronique.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CircuitBuilder {

    private ArrayList<Composant> composants;


    public Composant construireCircuit(String cheminFichier){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode donnees = mapper.readTree(new File(cheminFichier));
            JsonNode type = donnees.get("circuit");
            return lireComposant(type);
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }
        return null;
    }


    private Composant lireComposant(JsonNode node){
        ArrayList<Composant> composant = new ArrayList<>();
        String type = node.get("type").asText();

        if (Objects.equals(type,"resistance")) {
            return new Resistance(node.get("valeur").asDouble());

        } else if (Objects.equals(type, "parallele")) {
            for (JsonNode composantNode : node.get("composants")) {
                composant.add(lireComposant(composantNode));
            }
            return new CircuitParallele(composant);
        } else if (Objects.equals(type, "serie")) {
            for (JsonNode composantNode : node.get("composants")) {
                composant.add(lireComposant(composantNode));
            }
            return new CircuitSerie(composant);
        }else {
            throw new IllegalArgumentException("Type de circuit inconnu : " + type);
        }
    }

}
