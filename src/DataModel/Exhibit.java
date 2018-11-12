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

    public String getName() {
        return name;
    }
    public void setName(String nameIn) {
        name = nameIn;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int sizeIn) {
        size = sizeIn;
    }
    public int getNumAnimals() {
        return numAnimals;
    }
    public void setNumAnimals(int numAnimalsIn) {
        numAnimals = numAnimalsIn;
    }
    public boolean getWaterFeature() {
        return waterFeature;
    }
    public void setWaterFeature(boolean waterFeatureIn) {
        waterFeature = waterFeatureIn;
    }
}
