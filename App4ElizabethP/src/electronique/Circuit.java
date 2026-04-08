package electronique;
import java.util.*;

public abstract class Circuit extends Composant {


    protected ArrayList<Composant> composants;


    public Circuit(ArrayList<Composant> c){
        this.composants = c;
    }




}
