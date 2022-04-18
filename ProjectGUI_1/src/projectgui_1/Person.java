package projectgui_1;

import java.util.ArrayList;
import java.util.Collections;

public class Person {

    private String name;
    private String surname;
    private int estate_num;
    private ArrayList<Space> sp = new ArrayList<Space>();
    private int pesel;
    private String adress;
    private ArrayList<TenantLetter> let = new ArrayList<TenantLetter>();

    public Person(String name, String surname, int pesel, String adress) {
        this.name = name;
        this.surname = surname;
        this.estate_num = 0;
        this.pesel = pesel;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }    

    public void addSp(Space sp) {
        this.sp.add(sp);
    }
    
    public void subSp(String id) {
        for (int i = 0; i < sp.size(); i++) {
            if(sp.get(i).getId().equals(id)){
                sp.remove(i);
            }
        }
    }

    public void addLetter(Space space) {
        TenantLetter letter = new TenantLetter(space);
        let.add(letter);
    }

    public void setEstate_num(int estate_num) {
        this.estate_num = estate_num;
    }

    public int getEstate_num() {
        return estate_num;
    }

    public int getNumLetters() {
        return let.size();
    }

    public ArrayList<TenantLetter> getLet() {
        return let;
    }
    
    public String getInfo(){
        String output = this.toString()+"\n";
        ArrayList<Space> spCopy = new ArrayList<Space>();
        spCopy.addAll(sp);
        //Collections.copy(spCopy, sp);
        spCopy.sort(Space.sortingByVolume);
        for (int i = 0; i < spCopy.size(); i++) {
            output = output + spCopy.get(i).getInfo() + "\n";
        }
        return output;
    }
    
    
    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", surname=" + surname +", pesel ="+ pesel + "}";
    }

    
}
