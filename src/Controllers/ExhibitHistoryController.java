package Controllers;

import DataModel.VisitExhibit;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExhibitHistoryController {
    public List<VisitExhibit> getExhibitHistory(String username) {
        ResultSet set = null;
        List<VisitExhibit> list = new ArrayList<>();
        VisitExhibit visitExhibit = new VisitExhibit("", "", null, 0);
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM VisitExhibit WHERE VisitorUsername=\"" + username + "\"";
            statement.execute(sql);
            set = statement.getResultSet();
            while(set.next()) {
                String visitorUsername = set.getString(1);
                String exhibitName = set.getString(2);
                String dateAndTime = set.getString(3);
                int numOfVisits = getNumOfVisits(visitorUsername, exhibitName);
                visitExhibit = new VisitExhibit(visitorUsername, exhibitName, dateAndTime, numOfVisits);
                list.add(visitExhibit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getNumOfVisits(String username, String exhibitName) {
        ResultSet set;
        int numOfVisits = 0;
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT COUNT(VisitorUsername) FROM VisitExhibit WHERE VisitorUsername=\"" + username + "\""
                         + " AND ExhibitName=\"" + exhibitName + "\"";
            statement.execute(sql);
            set = statement.getResultSet();
            while(set.next()) {
                numOfVisits = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numOfVisits;
    }

    public void insertVisit(String username, String exhibitName, String dateTime) {
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO VisitExhibit " +
                         "VALUES(\"" + username + "\", \"" + exhibitName + "\", \"" + dateTime + "\")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<VisitExhibit> searchButtonPressed(String username, String name, String time, int minNum, int maxNum) {
        List<VisitExhibit> list = new ArrayList<>();
        VisitExhibit exhibitHistory;
        int numOfVisits = getNumOfVisits(username, name);
        ResultSet set;
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM VisitExhibit WHERE (VisitorUsername=\"" + username + "\" OR \"" + username + "\"=\"\")"
                         + " AND (exhibitName=\"" + name + "\" OR \"" + name + "\"=\"\")"
                         + " AND (DateAndTime LIKE \"" + time + "%\" OR \"" + time + "\"=\"\")"
                         + " AND (" + numOfVisits + ">=" + minNum + " OR " + minNum + "=0)"
                         + " AND (" + numOfVisits + "<=" + maxNum + " OR " + maxNum + "=0)";
            statement.execute(sql);
            set = statement.getResultSet();
            while(set.next()) {
                String exhibitName = set.getString(2);
                String dateTime = set.getString(3);
                int numVisits = getNumOfVisits(set.getString(1), exhibitName);
                exhibitHistory = new VisitExhibit(username, exhibitName, dateTime, numVisits);
                list.add(exhibitHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
