package electronique;

import java.util.ArrayList;

public class CircuitSerie extends Circuit{

    public CircuitSerie(ArrayList<Composant> composantlist) {
        super(composantlist);
    }

    @Override
    public double calculerResistance(){
        double rTotal = 0;
        for (int i = 0; i < composants.size(); i++){
            rTotal += composants.get(i).calculerResistance();
        }
        return rTotal;
    }
}
