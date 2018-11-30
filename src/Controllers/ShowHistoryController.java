package Controllers;

import DataModel.VisitShow;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowHistoryController {
    public List<VisitShow> getShowHistory(String username) {
        ResultSet set;
        List<VisitShow> list = new ArrayList<>();
        VisitShow visitShow;
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM VisitShow WHERE VisitorUsername=\"" + username + "\"";
            statement.execute(sql);
            set = statement.getResultSet();
            while(set.next()) {
                String visitorUsername = set.getString(1);
                String showName = set.getString(2);
                String showTime = set.getString(3);
                HashMap<String, String> exhibitName = getExhibitName();
                visitShow = new VisitShow(visitorUsername, showName, showTime, exhibitName.get(showName));
                list.add(visitShow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public HashMap<String, String> getExhibitName() {
        ResultSet set;
        String name;
        String locatedIn;
        HashMap<String, String> list = new HashMap<>();
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT Name, LocatedIn FROM Shows GROUP BY Name, LocatedIn";
            statement.execute(sql);
            set = statement.getResultSet();
            while(set.next()) {
                name = set.getString(1);
                locatedIn = set.getString(2);
                list.put(name, locatedIn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertVisit(String username, String showName, String dateTime) {
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO VisitShow " +
                    "VALUES(\"" + username + "\", \"" + showName + "\", \'" + dateTime + "\')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<VisitShow> searchButtonPressed(String username, String showName, String showTime, String exhibitName, String orderColumn, String orderType) {
        ResultSet set;
        List<VisitShow> list = new ArrayList<>();
        VisitShow visitShow;
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            HashMap<String, String> exhibitNames = getExhibitName();
            String sql = "SELECT * FROM VisitShow WHERE (VisitorUsername=\"" + username + "\" OR \"" + username + "\"=\"\")"
                         + " AND (ShowName=\"" + showName + "\" OR \"" + showName + "\"=\"\")"
                         + " AND (ShowTime=\"" + showTime + "\" OR \"" + showTime + "\"=\"\")"
                         + " ORDER BY " + orderColumn + " " + orderType;
//                         + " AND (" + exhibit + ") = \"" + exhibitName + "\"";
            statement.execute(sql);
            set = statement.getResultSet();
            while(set.next()) {
                String name = set.getString(1);
                String show = set.getString(2);
                String time = set.getString(3);
                String exhibit = exhibitNames.get(show);
                if (exhibit.equals(exhibitName)) {
                    visitShow = new VisitShow(name, show, time, exhibitNames.get(show));
                    list.add(visitShow);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
