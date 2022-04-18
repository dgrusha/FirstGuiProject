package projectgui_1;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyCalendar implements Runnable {

    private Date curentDate = new Date();
    private static int dayCounter = 0;
    
    public ArrayList<Estate> estates;
    public ArrayList<ParkingPlace> parks;

    public MyCalendar(Date curentDate, ArrayList<Estate> estate, ArrayList<ParkingPlace> parks ) {
        this.curentDate = curentDate;
        this.estates = estate;
        this.parks = parks;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(5000);
                long l = curentDate.getTime();
                curentDate.setTime(l + 86400000);
                dayCounter = dayCounter + 1;
                
                if (dayCounter % 2 == 0) {
                    //checking renting of the parking place
                    for (int i = 0; i < parks.size(); i++) {
                        if(parks.get(i).getDateEnd() == null){continue;}
                        if (parks.get(i).getDateEnd().getTime() == curentDate.getTime()) {
                            parks.get(i).getOwner().addLetter(parks.get(i));
                        }
                        if(parks.get(i).getDateKick().getTime() == curentDate.getTime() ){
                            parks.get(i).unsetUser();
                            }
                    }
                    //Checking renting of the appartments
                    for (int i = 0; i < estates.size(); i++) {
                        for (int j = 0; j < estates.get(i).getNumberOfApps(); j++) {
                            if(estates.get(i).getApps(j).getDateEnd() == null){
                            continue;
                            }
                            if(estates.get(i).getApps(j).getDateEnd().getTime() == curentDate.getTime()){
                            estates.get(i).getApps(j).getOwner().addLetter(estates.get(i).getApps(i));
                            }
                            if(estates.get(i).getApps(j).getDateKick().getTime() == curentDate.getTime() ){
                            estates.get(i).getApps(j).unsetOwner();
                            }
                        }
                    }
                    
                }
            } catch (InterruptedException ex) {

            }

        }
    }

    public Date getCurentDate() {
        return curentDate;
    }

}
