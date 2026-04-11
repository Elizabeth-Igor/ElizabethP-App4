package electronique;

import java.util.ArrayList;
import java.util.List;

public class CircuitParallele extends Circuit{

    private ArrayList<Composant> composants;

    public CircuitParallele (ArrayList<Composant> composantList){
        super(composantList);
        this.composants = composantList;
    }


    public double calculerResistance(){
        double total = 0;
        for (int i = 0; i < composants.size(); i++){
            total += 1 / composants.get(i).calculerResistance();
        }
        if (total == 0){
            return 0;
        }
        double rTotal = 1 / total;
        return rTotal;
    }

}
