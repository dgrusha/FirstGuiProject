package projectgui_1;
import java.util.ArrayList;
import java.util.Date;

public class Appartments extends Space {

    private static int id_num = 1;
    public ArrayList<Person> habitants = new ArrayList<Person>();
    private Date dateStart;
    private Date dateEnd;
    private Date dateKick;
    private long day = 86400000;
    

    public Appartments(double volume) {
        super.setVolume(volume);
        super.setId("A" + id_num);
        id_num = id_num + 1;
       
    }
    
    public Appartments(double l, double w, double h) {
        this.setVolume(l, w, h);
        super.setId("A" + id_num);
        id_num = id_num + 1;
    }



    public void setUser(Person user, Date currentDate) throws ProblematicTenantException, TooMuchProperty {
        if (user.getEstate_num() >= 5) {
            throw new TooMuchProperty();
        }
        if (user.getNumLetters() >= 3){
            throw new ProblematicTenantException(user);
        }
        if (super.getOwner() == null) {
            this.setOwner(user);
            habitants.add(user);
            dateStart = new Date(currentDate.getTime());
            dateEnd = new Date(currentDate.getTime() + day*30);
            dateKick = new Date(dateEnd.getTime() + day*30);
        } else {
            habitants.add(user);
        }
    }

    public Date getDateKick() {
        return dateKick;
    }
    
    
    public void unsetUser(Person user){
        if (super.getOwner() == user) {
            super.setOwner(user);
            habitants.clear();
            dateStart = null;
            dateEnd = null;
        } else {
            for (int i = 0; i < habitants.size(); i++) {
                if(habitants.get(i)==user){
                habitants.remove(i);
                break;
                }               
            }
        }

    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    
    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }
    

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String toString() {
            String res = "";
        for (int i = 0; i < habitants.size(); i++) {
            res = res + habitants.get(i) + "/";
        }
        return super.getId() + "/Habitants are : " + res + "/ Owner is : " + super.getOwner()+ " | Date start/end " + dateStart+" / "+dateEnd ;
    }
    
    public void unsetOwner(){
       super.getOwner().subSp(super.getId());
       super.getOwner().setEstate_num(super.getOwner().getEstate_num() - 1);
       dateStart = null;
       dateEnd = null;
       dateKick = null;
       super.setOwner(null);
       habitants.clear();
    }

    @Override
    public String getInfo() {
        String out= "";
        for (int i = 1; i < habitants.size(); i++) {
            out = out + habitants.get(i).toString() + "|";
        }
        if(habitants.size() == 1){
         out = "Only owner lives there";
        }
        return "Appartments("+this.getId()+") With volume" + this.getVolume() + "| Owner is: " + this.getOwner()+"| People lives: " + out + "| Date start/end" + dateStart+"/"+dateEnd;
    }
    
    @Override
    public void setVolume(double a , double b, double c) {
        super.setVolume(a*b*c);
    }

    public ArrayList<Person> getHabitants() {
        return habitants;
    }

    public void setHabitants(ArrayList<Person> habitants) {
        this.habitants = habitants;
    }

}
