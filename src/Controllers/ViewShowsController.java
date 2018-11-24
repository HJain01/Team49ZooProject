package Controllers;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewShowsController {
    public void removeShows(String name, String dateTime, String locatedIn) {
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM Shows WHERE Name=\"" + name + "\""
                         + " AND DateAndTime=\"" + dateTime + "\""
                         + " AND LocatedIn=\"" + locatedIn + "\"";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
