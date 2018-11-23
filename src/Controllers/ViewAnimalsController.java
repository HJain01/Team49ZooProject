package Controllers;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewAnimalsController {
    public void removeAnimal(String name, String species) {
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM Animal WHERE Name=\"" + name + "\""
                         + " AND Species=\"" + species + "\"";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
