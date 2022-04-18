
package projectgui_1;


public class OffRoadCar extends Storable implements Item{
    private double carcaseVolume;
    private boolean additionalWheel;

    public OffRoadCar(double carcaseVolume, boolean additionalWheel, String name, double volume) {
        super(name, volume);
        this.carcaseVolume = carcaseVolume;
        this.additionalWheel = additionalWheel;
    }

    @Override
    public String specInfo() {
        return "Availability of additional wheel: " + additionalWheel + " Carcase volume is :" + carcaseVolume; 
    }

    @Override
    public String toString() {
        return "OffRoadCar{" + "carcaseVolume=" + carcaseVolume + ", additionalWheel=" + additionalWheel + '}' + "| name is " + super.getName();
    }
    
    
}
