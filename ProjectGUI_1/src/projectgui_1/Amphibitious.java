package projectgui_1;


public class Amphibitious extends Storable implements Item {
    private double weight;

    public Amphibitious(double weight, String name, double volume) {
        super(name, volume);
        this.weight = weight;
    }

    @Override
    public String specInfo() {
        return "Weight is : " + weight;
    }

    @Override
    public String toString() {
        return "Amphibitious: " + "weight=" + weight + '}' + "name is " + super.getName();
    }
    
    
}
