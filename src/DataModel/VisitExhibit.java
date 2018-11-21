package DataModel;

public class VisitExhibit {
    public String visitorUsername;
    public String exhibitName;
    public String dateAndTime;
    public int numOfVisits;

    public VisitExhibit(String visitorUsername, String exhibitName, String dateAndTime, int numOfVisits) {
        this.visitorUsername = visitorUsername;
        this.exhibitName = exhibitName;
        this.dateAndTime = dateAndTime;
        this.numOfVisits = numOfVisits;
    }

    public String getVisitorUsername() {
        return visitorUsername;
    }

    public void setVisitorUsername(String visitorUsername) {
        this.visitorUsername = visitorUsername;
    }

    public String getExhibitName() {
        return exhibitName;
    }

    public void setExhibitName(String exhibitName) {
        this.exhibitName = exhibitName;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getNumOfVisits() {
        return numOfVisits;
    }

    public void setNumOfVisits(int numOfVisits) {
        this.numOfVisits = numOfVisits;
    }
}
