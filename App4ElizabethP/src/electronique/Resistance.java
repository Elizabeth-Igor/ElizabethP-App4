package electronique;

public class Resistance {

    public double resistance;

    public Resistance (double resistance){
        setResistance(resistance);
    }

    public double getResistance() {
        return resistance;
    }

    public void setResistance(double resistance) {
        if (!isValideResistance(resistance)){
            throw new IllegalArgumentException("Valeur de resistance invalide");
        }
        this.resistance = resistance;
    }

    public boolean isValideResistance (double resistance){
        return resistance >= 0;
    }

    public double calculerResistance(){
        return getResistance();
    }
}
