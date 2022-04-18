package projectgui_1;

public class Boat extends Storable implements Item{

    private double enginePower;

    public Boat(double enginePower, String name, double volume) {
        super(name, volume);
        this.enginePower = enginePower;
    }

    @Override
    public String specInfo() {
        return "Engine has " + enginePower + " horsepowers";
    }

    @Override
    public String toString() {
        return "Boat:" + "enginePower=" + enginePower + "| name is " + super.getName();
    }

    
}
