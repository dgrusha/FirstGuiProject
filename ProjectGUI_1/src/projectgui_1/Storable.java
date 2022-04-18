package projectgui_1;

public class Storable implements Item,  Comparable <Storable>{

    private String name;
    private double volume;

    public Storable(String name, double volume) {
        this.name = name;
        this.volume = volume;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "name=" + name + ", volume=" + volume + "/";
    }

    @Override
    public String specInfo() {
        return "Name: " + name + "of volume " + this.getVolume();
    }

    @Override
    public int compareTo(Storable o) {
        if(this.name.equals(o.getName())){
            if(this.volume > o.getVolume()){
            return -1;
            } else if(this.volume < o.getVolume()){
            return 1;
            }
        }
        return this.name.compareTo(o.getName());
    }
}
