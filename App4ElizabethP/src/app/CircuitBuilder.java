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


    public CircuitBuilder (){
    }

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

//    private double lireComposant(JsonNode node){
//        ArrayList<Composant> composant = new ArrayList<>();
//        double t = 0;
//        String type = node.get("type").asText();
//        if (Objects.equals(type,"resistance")) {
//            Resistance r = new Resistance(node.get("valeur").asDouble());
//            t = r.calculerResistance();
//        } else if (Objects.equals(type, "parallele")) {
//            for (JsonNode composantNode : node.get("composants")) {
//                composant.add(lireComposant(composantNode));
//            }
//            CircuitParallele c = new CircuitParallele(composant);
//            t = c.calculerResistance();
//        } else if (Objects.equals(type, "serie")) {
//            for (JsonNode composantNode : node.get("composants")) {
//                composant.add(lireComposant(composantNode));
//            }
//            CircuitSerie s = new CircuitSerie(composant);
//            t = s.calculerResistance();
//        }
//        return t;
//
//        throw new IllegalArgumentException("Type de circuit inconnu : " + type);
//    }

}
