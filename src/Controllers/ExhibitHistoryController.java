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
                         "VALUES(\"" + username + "\", \"" + exhibitName + "\", \'" + dateTime + "\')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet searchButtonPressed(String username, String name, String time, int minNum, int maxNum, String orderColumn, String orderType) {
        int numOfVisits = getNumOfVisits(username, name);
        ResultSet set = null;
        Connection conn = DatabaseConnector.establishConnection();
        if(orderColumn.equals("ExhibitName")){
            orderColumn = "t2.ExhibitName";
        }
        try {
            Statement statement = conn.createStatement();
            String countSql = "SELECT ExhibitName, VisitorUsername, COUNT(VisitorUsername) FROM VisitExhibit WHERE (VisitorUsername=\"" + username + "\" OR \"" + username + "\"=\"\")"
                    + " AND (ExhibitName=\"" + name + "\" OR \"" + name + "\"=\"\") GROUP BY ExhibitName HAVING (COUNT(VisitorUsername)>=" + minNum + " AND COUNT(VisitorUsername)<=" + maxNum + ")";
            String filterSql = "SELECT * FROM VisitExhibit WHERE (VisitorUsername=\"" + username + "\" OR \"" + username + "\"=\"\")"
                         + " AND (exhibitName=\"" + name + "\" OR \"" + name + "\"=\"\")"
                         + " AND (DateAndTime LIKE \"" + time + "%\" OR \"" + time + "\"=\"\")";
            String sql = "SELECT * FROM (" + countSql + ") t1 INNER JOIN (" + filterSql + ") t2 ON t1.ExhibitName=t2.ExhibitName"
            + " ORDER BY " + orderColumn + " " + orderType;
            statement.execute(sql);
            set = statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }
}
