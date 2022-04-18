package projectgui_1;


public class Estate {
    private static int counter = 0;
    private String adress;
    private Appartments[] apps;
    private static int id_num = 0;
    private int numberOfApps;
    private String id;

    public Estate(String adress, int num) {
        this.adress = adress;
        this.apps =  new Appartments[num];
        this.numberOfApps = num;
        this.id = "E" + id_num;
        id_num = id_num + 1;
    }
    
    public void putApps(int index, Appartments app){
        this.apps[index] = app;
    }

    public int getNumberOfApps() {
        return numberOfApps;
    }
    
    public void addApp(Appartments app){
        apps[counter] = app;
        counter = counter + 1;
    }

    public Appartments getApps(int ind) {
        return apps[ind];
    }
    
    

    @Override
    public String toString() {
        return "Estate{" + "adress=" + adress + '}';
    }
    
}
