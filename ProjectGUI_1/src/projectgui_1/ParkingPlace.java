package projectgui_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ParkingPlace extends Space {

    private static int id_num = 1;
    private Date dateStart;
    private Date dateEnd;
    private Date dateKick;
    private final long day = 86400000;
    public ArrayList<Storable> items = new ArrayList<Storable>();
    double volumeStart;

    public ParkingPlace(double volume) {
        super.setVolume(volume);
        volumeStart = volume;
        super.setId("P" + id_num);
        id_num = id_num + 1;
    }
    
    public ParkingPlace(double l, double w, double h) {
        super.setVolume(l*w*h);
        super.setId("P" + id_num);
        id_num = id_num + 1;
    }

    public void insertItem(Storable item) throws TooManyThingsException {
        
        if (item.getVolume() > super.getVolume()) {
            try {
                throw new TooManyThingsException();
            } catch (TooManyThingsException e) {
                e.printStackTrace();
            }
        } else {
            super.setVolume(super.getVolume() - item.getVolume());
            items.add(item);
        }
    }

    public void getItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                super.setVolume(super.getVolume() + items.get(i).getVolume());
                items.remove(i);
            }
        }
    }
    
//    public void setUser(Person user, Date currentDate) {
//        if (super.getOwner() == null) {
//            super.setOwner(user);
//            
//        } 
//    }
    
    public void setUser(Person user, Date currentDate) throws TooMuchProperty, ProblematicTenantException{
        if (user.getEstate_num() >= 5) {
            throw new TooMuchProperty();
        }
        if (user.getNumLetters() >= 3){
            throw new ProblematicTenantException(user);
        }
       super.setOwner(user);
       dateStart = new Date(currentDate.getTime());
       dateEnd = new Date(currentDate.getTime() + day*30);
       dateKick = new Date(dateEnd.getTime() + day*30);
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    
    public void unsetUser() {
       super.getOwner().subSp(super.getId());
       super.getOwner().setEstate_num(super.getOwner().getEstate_num() - 1);
       super.setOwner(null);
       dateStart = null;
       dateEnd = null;
       items.clear();
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public Date getDateKick() {
        return dateKick;
    }
    
    public void clearItems() {
        items.clear();
    }

    @Override
    public String toString() {
        return super.getId() + "/ParkingPlace owner :" + super.getOwner() + "Start/End rent:" + this.dateStart + " / "+ this.dateEnd + " / " + items;
    }

    @Override
    public String getInfo() {
        String out= "";
        ArrayList<Storable> itemsCopy = new ArrayList<>();
        itemsCopy.addAll(items);
        //Collections.copy(itemsCopy, items);
        Collections.sort(itemsCopy);
        for (int i = 0; i < itemsCopy.size(); i++) {
            out = out + itemsCopy.get(i).specInfo() + "|";
        }
        if(items.isEmpty()){
         out = "No items contained";
        }
        return "Parking place("+this.getId()+") With volume" + this.getVolume() + "| Owner is: " + this.getOwner()+"| Items contained: " + out;
    }

    @Override
    public void setVolume(double a , double b, double c) {
        super.setVolume(a*b*c);
    }

    public ArrayList<Storable> getItems() {
        return items;
    }

}
