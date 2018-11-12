package DataModel;

public class Exhibit {
    public String name;
    public int size;
    public int numAnimals;
    public boolean waterFeature;

    public Exhibit(String name, int size, int numAnimals, boolean waterFeature) {
        this.name = name;
        this.size = size;
        this.numAnimals = numAnimals;
        this.waterFeature = waterFeature;
    }
}
