package app;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import electronique.*;

import java.io.File;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Objects;

public class CircuitBuilder {

    private ArrayList<Composant> composants;


    public CircuitBuilder (){
    }

    public void construireCircuit(String cheminFichier){
        try {
            //composants = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode donnees = mapper.readTree(new File(cheminFichier));
            JsonNode type = donnees.get("circuit");
            lireComposant(type);
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }
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
        }

        throw new IllegalArgumentException("Type de circuit inconnu : " + type);
    }

    //    private Composant lireComposant(JsonNode node){
//        String type = node.get("type").asText();
//
//        if ("resistance".equals(type)) {
//            return new Resistance(node.get("valeur").asDouble());
//
//        } else if (Objects.equals(type,"resitance")) {
//            return new Resistance(node.get("valeur").asDouble());
//
//        } else if ("parallele".equals(type)) {
//            ArrayList<Composant> composant = new ArrayList<>();
//            for (JsonNode composantNode : node.get("composants")) {
//                composant.add(lireComposant(composantNode));
//            }
//            return new CircuitParallele(composant);
//        } else if ("serie".equals(type)) {
//            ArrayList<Composant> composant = new ArrayList<>();
//            for (JsonNode composantNode : node.get("composants")) {
//                composant.add(lireComposant(composantNode));
//            }
//            return new CircuitSerie(composant);
//        }
//
//        throw new IllegalArgumentException("Type de circuit inconnu : " + type);
//    }
}
