package Controllers;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchForExhibitsController {

    public int getNumAnimals(String exhibitName) {
        Connection conn = DatabaseConnector.establishConnection();
        int numAnimals = 0;
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT COUNT(LivesIn) FROM Animal WHERE LivesIn=\'" + exhibitName + "\'";
            statement.execute(sql);
            ResultSet set = statement.getResultSet();
            while(set.next()) {
                numAnimals = set.getInt(1);
            }
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return numAnimals;
    }
    public ResultSet searchButtonPressed(String name, int minAnimals, int maxAnimals, String waterFeature,
                                       int minSize, int maxSize) {
        Connection conn = DatabaseConnector.establishConnection();
        ResultSet set = null;
        try {
            Statement statement = conn.createStatement();
            int numAnimals = getNumAnimals(name);
            int water;
            if (waterFeature=="Yes") {
                water=1;
            } else {
                water=0;
            }
            String countSql = "SELECT COUNT(LivesIn) FROM Animal WHERE LivesIn=\"" + name + "\"";
            String sql = "SELECT * FROM Exhibit WHERE Name=\"" + name + "\" AND Size>=" + minSize + " AND Size<=" + maxSize
                    + " AND WaterFeature=" + water + " AND (" + countSql + ")>=" + minAnimals
                    + " AND (" + countSql + ")<=" + maxAnimals;
            statement.execute(sql);
            set = statement.getResultSet();
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return set;
    }

    public ResultSet getExhibitInfo() {
        ResultSet returnSet = null;
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM Exhibit";
            statement.execute(sql);
            returnSet = statement.getResultSet();
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return returnSet;
    }
}
