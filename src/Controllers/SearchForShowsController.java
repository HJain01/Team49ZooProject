package Controllers;

import database.DatabaseConnector;

import java.sql.*;

public class SearchForShowsController {

    public ResultSet searchButtonPressed(String name, String date, String exhibit, String orderby, String orderType) {
        ResultSet set = null;
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM Shows WHERE (Name=\"" + name + "\" OR \"" + name + "\" = \"\")"
                         + " AND (DateAndTime LIKE \"" + date + "%\" OR \"" + date + "\" = \"\")"
                         + " AND (LocatedIn=\"" + exhibit + "\" OR \"" + exhibit + "\" = \"\")"
                        + "ORDER BY " + orderby + " " + orderType;
            statement.execute(sql);
            set = statement.getResultSet();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    public ResultSet getShowInfo() {
        ResultSet set = null;
        Connection conn = DatabaseConnector.establishConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM Shows";
            statement.execute(sql);
            set = statement.getResultSet();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return set;
    }
}
