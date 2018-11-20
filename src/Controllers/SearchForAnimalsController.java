package Controllers;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchForAnimalsController {
    public ResultSet searchButtonPressed(String name, String species, String type,
                                         int minNum, int maxNum, String livesIn) {
        Connection conn = DatabaseConnector.establishConnection();
        ResultSet set = null;
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM Animal WHERE (Name=\"" + name + "\" OR \"" + name + "\" = \"\")"
                         + " AND (Species=\"" + species + "\" OR \"" + species + "\" = \"\")"
                         + " AND (Type=\"" + type + "\" OR \"" + species + "\" = \"\")"
                         + " AND (Age>=" + minNum + " OR " + minNum + " = 0)"
                         + " AND (Age<=" + maxNum + " OR " + maxNum + " = 0)"
                         + " AND (livesIn=\"" + livesIn + "\" OR \"" + livesIn + "\" = \"\")";
            statement.execute(sql);
            set = statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    public ResultSet getAnimalInfo() {
        ResultSet set = null;
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM Animal";
            statement.execute(sql);
            set = statement.getResultSet();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return set;
    }
}