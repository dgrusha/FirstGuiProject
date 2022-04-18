package projectgui_1;


public class City_car extends Storable implements Item{
    private String brand;

    public City_car(String brand, String name, double volume) {
        super(name, volume);
        this.brand = brand;
    }

    @Override
    public String specInfo() {
        return "A car is of brand " + brand;
    }

    @Override
    public String toString() {
        return "City_car{" + "brand=" + brand + '}' + "| name is " + super.getName();
    }
    
    
}
