package projectgui_1;

enum Type {
        Standard,
        Cruiser,
        SportBike,
        Touring
    }

public class Motorcycle extends Storable implements Item {
    private double volumeTank; // in cm^3
    Type type;
    

    public Motorcycle(String name, double volume, int choice) {
        super(name, volume);
        switch(choice){
                 case 1:
                    this.type = Type.Standard;
                    break;
                case 2:
                    this.type = Type.Cruiser;
                    break;
                case 3:
                    this.type = Type.SportBike;
                    break;
                case 4:
                    this.type = Type.Touring;
                    break;
        }
        
        switch (type) {
            case Standard:
                volumeTank = 600;
                break;
            case Cruiser:
                volumeTank = 700;
                break;
            case SportBike:
                volumeTank = 800;
                break;
            case Touring:
                volumeTank = 900;
                break; 
        }
    }

    

    @Override
    public String specInfo() {
        return "Morocycle is of type " + type + " and it has tank of size (L) " + volumeTank;
    }

    @Override
    public String toString() {
        return "Motorcycle{" + "volumeTank=" + volumeTank + ", type=" + type + '}' + "| name is " + super.getName();
    }
    
    

}
