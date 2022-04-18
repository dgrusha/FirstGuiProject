package projectgui_1;

import java.util.Comparator;
import java.util.Date;

public abstract class Space {
    private String id;
    private double volume;
    private Person owner; //responsible for fees
    
    public static Comparator<Space> sortingByVolume = (Space a1, Space a2)-> (int)(a1.volume - a2.volume);
    
    public abstract String getInfo();
    
    public abstract void setVolume(double a, double b, double c);
    
    public abstract void setUser(Person user, Date currentDate) throws TooMuchProperty, ProblematicTenantException;
     
    public double getVolume() {
        return volume;
    }

    public String getId() {
        return id;
    }

    public Person getOwner(){
        
        return owner;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setOwner(Person owner) {
        this.owner = owner;
    }
    
    public  void setVolume(double volume){
        this.volume = volume;
    };

    

  
}
