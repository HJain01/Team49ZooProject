package DataModel;

public class Note {
    public String staffUsername;
    public String animalName;
    public String species;
    public String logTime;
    public String text;

    public Note(String staffUsername, String animalName, String species, String logTime, String text)
    {
        this.staffUsername = staffUsername;
        this.animalName = animalName;
        this.species = species;
        this.logTime = logTime;
        this.text = text;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getLogTime() {
        return logTime;
    }

    public String getSpecies() {
        return species;
    }

    public String getStaffUsername() {
        return staffUsername;
    }

    public String getText() {
        return text;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }

    public void setText(String text) {
        this.text = text;
    }
}
