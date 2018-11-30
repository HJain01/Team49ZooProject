package Controllers;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffHostedShowsController {
    public ResultSet getShowsForStaffMember(String hostUsername, String orderColumn, String orderType)
    {
        Connection conn = DatabaseConnector.establishConnection();
        ResultSet set = null;

        try
        {
            Statement statement = conn.createStatement();

            String sql = "Select * FROM Shows WHERE HostUsername =\"" + hostUsername + "\""
                    + " ORDER BY " + orderColumn + " " + orderType;
            statement.execute(sql);
            set = statement.getResultSet();
        } catch(SQLException e)
        {
            System.out.println(e.getErrorCode());
        }
        return set;
    }
}
