package Controllers;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class SearchForExhibitsController {

    public HashMap<String, Integer> getNumAnimals() {
        Connection conn = DatabaseConnector.establishConnection();
        HashMap<String, Integer> list = new HashMap<>();
        int numAnimals;
        String exhibitName;
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT LivesIn, COUNT(LivesIn) FROM Animal GROUP BY LivesIn";
            statement.execute(sql);
            ResultSet set = statement.getResultSet();
            while(set.next()) {
                numAnimals = set.getInt(2);
                exhibitName = set.getString(1);
                list.put(exhibitName, numAnimals);
            }
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return list;
    }
    public ResultSet searchButtonPressed(String name, int minAnimals, int maxAnimals, String waterFeature,
                                       int minSize, int maxSize) {
        Connection conn = DatabaseConnector.establishConnection();
        ResultSet set = null;
        try {
            Statement statement = conn.createStatement();
            int water;
            if (waterFeature=="Yes") {
                water=1;
            } else {
                water=0;
            }
            String countSql = "SELECT LivesIn, COUNT(LivesIn) FROM Animal GROUP BY LivesIn HAVING (COUNT(LivesIn)>=" + minAnimals
                    + " AND COUNT(LivesIn)<=" + maxAnimals + ")";
            String filterSql = "SELECT * FROM Exhibit WHERE (Name=\"" + name + "\" OR \"" + name + "\" = \"\")"
                    + " AND (Size>=" + minSize + " OR " + minSize + " = 0)"
                    + " AND (Size<=" + maxSize + " OR " + maxSize + " = 0)"
                    + " AND (WaterFeature=" + water + " OR 1=0)";
//                    + " AND ((" + countSql + ") >= " + minAnimals + " OR " + minAnimals + " = 0)"
//                    + " AND ((" + countSql + ") <= " + maxAnimals + " OR " + maxAnimals + " = 0)";
            String sql = "SELECT * FROM" +
                    " (" + filterSql + ") t1" +
                    " INNER JOIN (" + countSql + ") t2 ON t1.Name=t2.LivesIn";
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
