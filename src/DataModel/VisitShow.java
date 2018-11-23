package DataModel;

public class VisitShow {
    public String visitorUsername;
    public String showName;
    public String showTime;
    public String exhibitName;

    public VisitShow(String visitorUsername, String showName, String showTime, String exhibitName) {
        this.visitorUsername = visitorUsername;
        this.showName = showName;
        this.showTime = showTime;
        this.exhibitName = exhibitName;
    }

    public String getVisitorUsername() {
        return visitorUsername;
    }

    public void setVisitorUsername(String visitorUsername) {
        this.visitorUsername = visitorUsername;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getExhibitName() {
        return exhibitName;
    }

    public void setExhibitName(String exhibitName) {
        this.exhibitName = exhibitName;
    }
}
