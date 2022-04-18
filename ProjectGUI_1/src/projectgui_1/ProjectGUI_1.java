package projectgui_1;

import java.io.FileNotFoundException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectGUI_1 {

    public static void main(String[] args) throws TooManyThingsException, ProblematicTenantException, FileNotFoundException, TooMuchProperty {
        
         Scanner scan = new Scanner(System.in);
        
        
        //Creating people
        ArrayList<Person> persons = new ArrayList<>();
        Person user = new Person("Dima", "Grushevskyy", 1235689, "Kyiv");
        Person user2 = new Person("John", "Right", 123123567, "Warsaw");
        Person user3 = new Person("Abraham", "Lincoln", 45678994, "D.C");
        Person user4 = new Person("Johny", "Depp", 1452634789, "L.A");
        Person user5 = new Person("Mister", "X", 1452634789, "Oslo");
        persons.add(user);
        persons.add(user2);
        persons.add(user3);
        persons.add(user4);
        persons.add(user5);
        
        
        //Creating Parking places
        ArrayList<ParkingPlace>  parking = new ArrayList<>();
        ParkingPlace park1 = new ParkingPlace(200);
        ParkingPlace park2 = new ParkingPlace(5*5*4);
        ParkingPlace park3 = new ParkingPlace(500);
        ParkingPlace park4 = new ParkingPlace(12);
        parking.add(park1);
        parking.add(park2);
        parking.add(park3);
        parking.add(park4);
        
        
        //Estates and appartments
        Estate est1 = new Estate("Kanarskiego", 10);
        ArrayList<Estate> est = new ArrayList<>();
        est.add(est1);
        Appartments app1 = new Appartments(5*6*7);
        Appartments app2 = new Appartments(30);
        Appartments app3 = new Appartments(15);
        Appartments app4 = new Appartments(8*9*10);
        Appartments app5 = new Appartments(28);
        Appartments app6 = new Appartments(5*6*7);
        Appartments app7 = new Appartments(30);
        Appartments app8 = new Appartments(15);
        Appartments app9 = new Appartments(8*9*10);
        Appartments app0 = new Appartments(28);
        est.get(0).addApp(app1);
        est.get(0).addApp(app2);
        est.get(0).addApp(app3);
        est.get(0).addApp(app4);
        est.get(0).addApp(app5);
        est.get(0).addApp(app6);
        est.get(0).addApp(app7);
        est.get(0).addApp(app8);
        est.get(0).addApp(app9);
        est.get(0).addApp(app0);

        //local date 
        //Time thread start
        Date currentDate = new Date();
        MyCalendar mc = new MyCalendar(currentDate, est, parking);
        Thread t = new Thread(mc);
        t.start();
         //new Thread(mc).start();
        
        app1.setUser(user2, mc.getCurentDate()); 
        user2.addSp(app1);
        user2.setEstate_num(user2.getEstate_num() + 1);
        app4.setUser(user4, mc.getCurentDate());
        user4.addSp(app4);
        user4.setEstate_num(user4.getEstate_num() + 1);
        
        boolean proc = true;
        
        while (proc == true) {
            Controller.firstFrame();
            System.out.println(mc.getCurentDate() );
            int choice = scan.nextInt();
            switch (choice) {
                case 0:
                    t.interrupt();
                    proc = false;
                    break;
                case 1:
                    Controller.startRent( persons,  est,  mc);
                    break;
                case 2:
                    Controller.startRentParking(persons, parking, mc);
                    break;
                case 3:
                    Controller.renewRent(est, mc);
                    break;
                case 4:
                    Controller.renewRentParking(parking, mc);
                    break;
                case 5:
                    Controller.cancelRent(est, mc);
                    break;
                case 6:
                    Controller.cancelRentParking(parking, mc);
                    break;
                case 7:
                    Controller.kickHabitant(est, mc);
                    break;
                case 8:
                    Controller.insertItem(parking, mc);
                    break;
                case 9:
                    Controller.getItem(parking, mc);
                    break;
                case 10:
                    Controller.writeData(persons);
                    break;
                case 11:
                    Controller.addPerson(persons);
                    break;
                case 12:
                    Controller.addApp(est);
                    break;
                case 13:
                    Controller.addParking(parking);
                    break;
                case 14:
                    System.out.println(mc.getCurentDate() );
                    break;
                default:
                // code block
            }
        }
    }
    }
    
    

   
