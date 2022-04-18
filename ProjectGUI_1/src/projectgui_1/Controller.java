package projectgui_1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    public static void firstFrame() {
        System.out.println("Choose what you want to do: "
                +"\n 0. Stop prog."
                + "\n 1. Start rent of an appartment"
                + "\n 2. Start rent of a parking place"
                + "\n 3. Renew rent of an appartment"
                + "\n 4. Renew rent of a parking place"
                + "\n 5. Cancel rent of an appartment"
                + "\n 6. Cancel rent of a parking place"
                + "\n 7. Kick out from an appartment"
                + "\n 8. Insert items into parking place"
                + "\n 9. Get items from parking place"
                + "\n10. Save all info into a file"
                + "\n11. Add new person"
                + "\n12. Add new estate"
                + "\n13. Add new parking place"
                + "\n14. Show current time");
    }

    public static void startRent(ArrayList<Person> persons, ArrayList<Estate> est, MyCalendar mc) throws ProblematicTenantException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose a user to set: ");
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(i + ". " + persons.get(i));
        }
        int person_choice = scan.nextInt();
    
        if (person_choice < 0 || person_choice > persons.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        System.out.println("Choose an estate to see an info about appartments: ");
        for (int i = 0; i < est.size(); i++) {
            System.out.println(i + ". " + est.get(i));
        }
        int estate_choice = scan.nextInt();
        if (estate_choice < 0 || estate_choice > est.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        System.out.println("Choose an appartment to set a user: ");
        for (int i = 0; i < est.get(estate_choice).getNumberOfApps(); i++) {
            System.out.println(i + ". " + est.get(estate_choice).getApps(i));
        }
        int app_choice = scan.nextInt();
        if (app_choice < 0 || app_choice > est.get(estate_choice).getNumberOfApps()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        if(est.get(estate_choice).getApps(app_choice).getOwner() == null){
        persons.get(person_choice).setEstate_num(persons.get(person_choice).getEstate_num() + 1);
        persons.get(person_choice).addSp(est.get(estate_choice).getApps(app_choice));
        }
        try {
            est.get(estate_choice).getApps(app_choice).setUser(persons.get(person_choice), mc.getCurentDate());
        } catch (ProblematicTenantException e) {
            e.printStackTrace();
            return;
        }catch (TooMuchProperty m) {
            m.printStackTrace();
            return;
        }
        
        System.out.println("RENT FOR AN APPARTMENTS WAS SET PROPERLY!");
        
        //apps.get(app_choice).setUser(persons.get(person_choice), mc.getCurentDate());
    }

    public static void startRentParking(ArrayList<Person> persons, ArrayList<ParkingPlace> park, MyCalendar mc) throws ProblematicTenantException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose a user to set: ");
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(i + ". " + persons.get(i));
        }

        int person_choice = scan.nextInt();
      
        if (person_choice < 0 || person_choice > persons.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }

        System.out.println("Choose a parking place: ");
        for (int i = 0; i < park.size(); i++) {
            System.out.println(i + ". " + park.get(i));
        }

        int park_choice = scan.nextInt();
        if (park_choice < 0 || park_choice > park.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        if (park.get(park_choice).getOwner() != null) {
            System.out.println("PARKING PLACE IS OCCUPIED!");
            return;
        }
        System.out.println(park.get(park_choice));
        if(park.get(park_choice).getOwner() == null){
        persons.get(person_choice).setEstate_num(persons.get(person_choice).getEstate_num() + 1);
        persons.get(person_choice).addSp(park.get(park_choice));
        }
        try {
            park.get(park_choice).setUser(persons.get(person_choice), mc.getCurentDate());
        } catch (ProblematicTenantException e) {
            e.printStackTrace();
            return;
        }catch (TooMuchProperty m) {
            m.printStackTrace();
            return;
        }
         //Setting rent for a parking place 
        System.out.println("RENT FOR A PARKING PLACE WAS SET PROPERLY!");
        
    }

    public static void renewRent(ArrayList<Estate> est, MyCalendar mc) {
        Scanner scan = new Scanner(System.in);
        //estate operation
        System.out.println("Choose an estate to see an info about appartments: ");
        for (int i = 0; i < est.size(); i++) {
            System.out.println(i + ". " + est.get(i));
        }
        int estate_choice = scan.nextInt();
        if (estate_choice < 0 || estate_choice > est.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        //apps operation
        System.out.println("Choose an appartment renew a rent: ");
        for (int i = 0; i < est.get(estate_choice).getNumberOfApps(); i++) {
            System.out.println(i + ". " + est.get(estate_choice).getApps(i));
        }
        int app_choice = scan.nextInt();
        if(est.get(estate_choice).getApps(app_choice).getOwner() == null){
            System.out.println("NO ONE IS LIVING IN THIS APPARTMENTS!");
            return;
        }
        if (app_choice < 0 || app_choice > est.get(estate_choice).getNumberOfApps()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        if(est.get(estate_choice).getApps(app_choice).getDateEnd().getTime() > mc.getCurentDate().getTime()){
            System.out.println("THE TIME FOR RENEWING RENT HAVEN`T COME YET!");
            return;
        }
        //checking if we are allowed to renew our rent
        if(est.get(estate_choice).getApps(app_choice).getDateEnd().getTime() <= mc.getCurentDate().getTime() 
                && est.get(estate_choice).getApps(app_choice).getDateEnd().getTime() +  86400000 * 30  > mc.getCurentDate().getTime() )
        {
            //Setting new end date
            est.get(estate_choice)
                    .getApps(app_choice).setDateEnd(new Date(est.get(estate_choice)
                    .getApps(app_choice)
                    .getDateEnd()
                    .getTime() +  86400000 * 30) );
            //Deleting proper letter
            for (int i = 0; i < est.get(estate_choice).getApps(app_choice)
                    .getOwner().getNumLetters(); i++) {
                if(est.get(estate_choice).getApps(app_choice)
                        .getOwner().getLet().get(i).getId()//getting id of app
                        .equals(est.get(estate_choice).getApps(app_choice)
                        .getId()) )// and app id from array
                est.get(estate_choice).getApps(app_choice)
                .getOwner().getLet().remove(i); //deleting letter
            }
            System.out.println("NEW DATE SET SUCCESSFULLY!");
        }
        
    }
    
    public static void renewRentParking(ArrayList<ParkingPlace> park, MyCalendar mc){
        Scanner scan = new Scanner(System.in);
         System.out.println("Choose a parking place: ");
        for (int i = 0; i < park.size(); i++) {
            System.out.println(i + ". " + park.get(i));
        }

        int park_choice = scan.nextInt();
        if (park_choice < 0 || park_choice > park.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        if(park.get(park_choice).getOwner() == null){
            System.out.println("NO ONE USES THIS PARKING PLACE");
            return;
        }
        if(park.get(park_choice).getDateEnd().getTime() < mc.getCurentDate().getTime() 
                && park.get(park_choice).getDateEnd().getTime() + 86400000 * 30 
                > mc.getCurentDate().getTime())
        {
            park.get(park_choice).setDateEnd(new Date(park.get(park_choice)
            .getDateEnd().getTime() + 86400000 * 30));
            System.out.println("NEW DATE SET SUCCESSFULLY!");
        }
        
    }
    
    public static void cancelRent(ArrayList<Estate> est, MyCalendar mc){
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose an estate to see an info about appartments: ");
        for (int i = 0; i < est.size(); i++) {
            System.out.println(i + ". " + est.get(i));
        }
        int estate_choice = scan.nextInt();
        if (estate_choice < 0 || estate_choice > est.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        System.out.println("Choose an appartment to cancel a rent: ");
        for (int i = 0; i < est.get(estate_choice).getNumberOfApps(); i++) {
            System.out.println(i + ". " + est.get(estate_choice).getApps(i));
        }
        int app_choice = scan.nextInt();
        if(est.get(estate_choice).getApps(app_choice).getOwner() == null){
            System.out.println("NO ONE IS LIVING IN THIS APPARTMENTS!");
            return;
        }
        if (app_choice < 0 || app_choice > est.get(estate_choice).getNumberOfApps()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        if ( mc.getCurentDate().getTime() < est.get(estate_choice).getApps(app_choice).getDateEnd().getTime()) {
            est.get(estate_choice).getApps(app_choice).unsetOwner();
            return;
        }
        if(est.get(estate_choice).getApps(app_choice).getDateEnd().getTime() < mc.getCurentDate().getTime() 
                && est.get(estate_choice).getApps(app_choice).getDateEnd().getTime() +  86400000 * 30  > mc.getCurentDate().getTime() )
            
        {
            //Setting new amount of real estate
            est.get(estate_choice).getApps(app_choice).getOwner()
            .setEstate_num(est.get(estate_choice).getApps(app_choice).getOwner()
            .getEstate_num() - 1);
            //deleting apps from person
            est.get(estate_choice).getApps(app_choice).getOwner()
             .subSp(est.get(estate_choice).getApps(app_choice).getId());
            
            est.get(estate_choice).getApps(app_choice).unsetOwner();
            //Deleting proper letter
            for (int i = 0; i < est.get(estate_choice).getApps(app_choice).getOwner().getNumLetters(); i++) {
                if(est.get(estate_choice).getApps(app_choice).getOwner().getLet().get(i).getId()//getting id of app
                        .equals(est.get(estate_choice).getApps(app_choice).getId()) )// and app id from array
                est.get(estate_choice).getApps(app_choice).getOwner().getLet().remove(i); //deleting letter
            }
            System.out.println("RENT IS CANCELED SUCCESSFULLY!");
        }
        
    }
    
    public static void cancelRentParking(ArrayList<ParkingPlace> park, MyCalendar mc){
        Scanner scan = new Scanner(System.in);
         System.out.println("Choose a parking place: ");
        for (int i = 0; i < park.size(); i++) {
            System.out.println(i + ". " + park.get(i));
        }

        int park_choice = scan.nextInt();
        if (park_choice < 0 || park_choice > park.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        if(park.get(park_choice).getOwner() == null){
            System.out.println("NO ONE USES THIS PARKING PLACE");
            return;
        }
        if ( mc.getCurentDate().getTime() < park.get(park_choice).getDateEnd().getTime()) {
            //Setting new amount of real estate
            //Deleting parking from array of property
            park.get(park_choice).unsetUser();
            return;
        }
        if(park.get(park_choice).getDateEnd().getTime() < mc.getCurentDate().getTime() 
                && park.get(park_choice).getDateEnd().getTime() +  86400000 * 30  > mc.getCurentDate().getTime() )
        {
            //Setting new end date
            //Setting new amount of real estate
            park.get(park_choice).getOwner()
            .setEstate_num(park.get(park_choice).getOwner()
            .getEstate_num() - 1);
            //Deleting parking from array of property
            park.get(park_choice).getOwner()
             .subSp(park.get(park_choice).getId());
            park.get(park_choice).unsetUser();
            //Deleting proper letter
            for (int i = 0; i < park.get(park_choice).getOwner().getNumLetters(); i++) {
                if(park.get(park_choice).getOwner().getLet().get(i).getId()//getting id of app
                        .equals(park.get(park_choice).getId()) )// and app id from array
                park.get(park_choice).getOwner().getLet().remove(i); //deleting letter
            }
            System.out.println("RENT IS CANCELED SUCCESSFULLY!");
        }
        
    }
    
   public static void kickHabitant(ArrayList<Estate> est, MyCalendar mc){
       Scanner scan = new Scanner(System.in);
        //estate operation
        System.out.println("Choose an estate to see an info about appartments: ");
        for (int i = 0; i < est.size(); i++) {
            System.out.println(i + ". " + est.get(i));
        }
        int estate_choice = scan.nextInt();
        if (estate_choice < 0 || estate_choice > est.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        //apps operation
        System.out.println("Choose an appartment renew a rent: ");
        for (int i = 0; i < est.get(estate_choice).getNumberOfApps(); i++) {
            System.out.println(i + ". " + est.get(estate_choice).getApps(i));
        }
        int app_choice = scan.nextInt();
        if(est.get(estate_choice).getApps(app_choice).getOwner() == null){
            System.out.println("NO ONE IS LIVING IN THIS APPARTMENTS!");
            return;
        }
        if (app_choice < 0 || app_choice > est.get(estate_choice).getNumberOfApps()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        System.out.println("Choose a person to kick out: ");
        for (int i = 1; i < est.get(estate_choice).getApps(app_choice).getHabitants().size(); i++) {
            System.out.println(i + ". " + est.get(estate_choice)
                    .getApps(app_choice).getHabitants().get(i));
        }
        int person_choice = scan.nextInt();
        if (person_choice < 1 || person_choice > est.get(estate_choice)
                .getApps(app_choice).getHabitants().size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        est.get(estate_choice).getApps(app_choice).getHabitants().remove(person_choice);
        System.out.println("HABITANT IS REMOVED!");
        
   }
   
   public static void insertItem (ArrayList<ParkingPlace> park, MyCalendar mc)throws TooManyThingsException{
       
       Scanner scan = new Scanner(System.in);
       //Choosing parking
       System.out.println("Choose a parking place: ");
        for (int i = 0; i < park.size(); i++) {
            System.out.println(i + ". " + park.get(i));
        }

        int park_choice = scan.nextInt();
        if (park_choice < 0 || park_choice > park.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        if(park.get(park_choice).getOwner() == null){
            System.out.println("NO ONE USES THIS PARKING PLACE");
            return;}
       //choosing what type do you want to insert
       System.out.println("Choose what you want to insert: (1- for item, 2- for vechicle) ");
       int type_choice = scan.nextInt();
       if(type_choice == 1){
            System.out.println("Write a name:");
            String s = scan.next();
            System.out.println("Write a volume:");
            double vol = scan.nextDouble();
            park.get(park_choice).insertItem(new Storable(s, vol));
           
       }
       if(type_choice == 2){
            System.out.println("Write a type:\n"
                    + "1.Motocycle\n"
                    + "2.Off-road car\n"
                    + "3.City car\n"
                    + "4.Boat\n"
                    + "5.Amphibious\n");
            int type = scan.nextInt();
            
            switch(type){
                case 1:
                    System.out.println("Write a type:\n"
                    + "1.Standard\n"
                    + "2.Cruiser\n"
                    + "3.Sport bike\n"
                    + "4.Touring\n");
                    int type_m = scan.nextInt();
                    System.out.println("Write a name:\n");
                    String name_m = scan.next();
                    System.out.println("Write a volume:\n");
                    double volume_m = scan.nextDouble();
                    try {
                        park.get(park_choice).insertItem(new Motorcycle(name_m, volume_m, type_m));
                    } catch (TooManyThingsException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                    System.out.println("Write a volume of a carcase:");
                    double vol_off = scan.nextDouble();
                    System.out.println("Write a volume of a car:");
                    double vol_carc_off = scan.nextDouble();
                    System.out.println("Write if it has an additional wheel (y,n):");
                    String wheel = scan.next();
                    boolean wh;
                    if(wheel.equals("y")){
                     wh = true;
                    }else{wh = false;}
                    System.out.println("Write a name of a car:");
                    String name_off = scan.next();
                    try {
                        park.get(park_choice).insertItem(new OffRoadCar(vol_carc_off,wh, name_off, vol_off));
                    } catch (TooManyThingsException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 3:
                    System.out.println("Write a volume of a car:");
                    double city_car_vol = scan.nextDouble();
                    System.out.println("Write a name of a car:");
                    String city_car_name = scan.next();
                    System.out.println("Write a brand of a car:");
                    String city_car_brand = scan.next();
                    City_car car = new City_car(city_car_brand, city_car_name, city_car_vol);
                    try {
                        park.get(park_choice).insertItem(car);
                    } catch (TooManyThingsException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 4:
                    System.out.println("Write a volume of a boat:");
                    double vol_boat = scan.nextDouble();
                    System.out.println("Write a name of a boat:");
                    String boat_name = scan.next();
                    System.out.println("Write an engine power in horsepowers of a car:");
                    double enginePower = scan.nextDouble();
                    try {
                        park.get(park_choice).insertItem(new Boat(enginePower, boat_name, vol_boat));
                    } catch (TooManyThingsException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 5:
                    
                     System.out.println("Write a volume of a boat:");
                    double vol_am = scan.nextDouble();
                    System.out.println("Write a name of a boat:");
                    String am_name = scan.next();
                    System.out.println("Write an engine power in horsepowers of a car:");
                    double wight = scan.nextDouble();
                    try {
                        park.get(park_choice).insertItem(new Amphibitious(wight, am_name, vol_am));
                    } catch (TooManyThingsException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
       }
       
   }
   
   public static void getItem(ArrayList<ParkingPlace> park, MyCalendar mc){
       Scanner scan = new Scanner(System.in);
       //Choosing parking
       System.out.println("Choose a parking place: ");
        for (int i = 0; i < park.size(); i++) {
            System.out.println(i + ". " + park.get(i));
        }
        int park_choice = scan.nextInt();
        if (park_choice < 0 || park_choice > park.size()) {
            System.out.println("WRONG INDEX!");
            return;
        }
        if(park.get(park_choice).getOwner() == null){
            System.out.println("NO ONE USES THIS PARKING PLACE");
            return;}
        System.out.println("Write a name of what do you want to get:");
        for (int i = 0; i < park.get(park_choice).items.size(); i++) {
           System.out.println(park.get(park_choice).items.get(i)+"\n"); 
       }
        String name = scan.next();
        park.get(park_choice).getItem(name);
   }
   
   public static void writeData(ArrayList<Person> person) throws FileNotFoundException{
       
       String out = "";
        for (int i = 0; i < person.size(); i++) {
           out = out + i + ". " +person.get(i).getInfo() + "\n";
       }
        try (PrintStream outFile = new PrintStream(new FileOutputStream("output.txt"))) {
            outFile.print(out);
           }
   }
   
   public static void addPerson(ArrayList<Person> person){
        Scanner scan = new Scanner(System.in);
        System.out.println("Write a name");
        String name = scan.next();
        System.out.println("Write a surname");
        String surname = scan.next();
        System.out.println("Write pesel number");
        int pesel = scan.nextInt();
        System.out.println("Write first place of living");
        String adress = scan.next();
        person.add(new Person(name, surname, pesel, adress));
   }
   
   public static void addApp(ArrayList<Estate> est){
        Scanner scan = new Scanner(System.in);
        System.out.println("Write adress");
        String adress = scan.next();
        System.out.println("Write a number of appartments");
        int number = scan.nextInt();
        est.add(new Estate(adress, number));
        System.out.println("Write in what type do you want to type a volume "
                + "(1- cubic metres; 2 - length, height and weight)");
        int choice = scan.nextInt();
        for (int i = 0; i < number; i++) {
            if(choice == 1){
                System.out.println("Write a volume");
                int vol = scan.nextInt();
                est.get(est.size()-1).addApp(new Appartments(vol));
            }
            if(choice == 2){
                System.out.println("Write a length");
                int len = scan.nextInt();
                System.out.println("Write a height");
                int wei = scan.nextInt();
                System.out.println("Write a weight");
                int hei = scan.nextInt();
                est.get(est.size()-1).addApp(new Appartments(len, wei, hei));
            }
       }
   }
   
   public static void addParking(ArrayList<ParkingPlace> park){
            System.out.println("Write in what type do you want to type a volume "
                    + "(1- cubic metres; 2 - length, height and weight): ");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            if(choice == 1){
                System.out.println("Write a volume");
                int vol = scan.nextInt();
                park.add(new ParkingPlace(vol));
            }
            if(choice == 2){
                System.out.println("Write a length");
                int len = scan.nextInt();
                System.out.println("Write a height");
                int wei = scan.nextInt();
                System.out.println("Write a weight");
                int hei = scan.nextInt();
                park.add(new ParkingPlace(len, wei, hei));
            }
       
   }
   
}
