package Controllers;

import database.DatabaseConnector;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewVisitorsController {
    public ResultSet getAllVisitors()
    {
        Connection conn = DatabaseConnector.establishConnection();
        ResultSet returnSet = null;
        try {
            Statement statement = conn.createStatement();
            String sql = "Select * from User WHERE Type = \"Visitor\"";
            statement.execute(sql);
            returnSet = statement.getResultSet();
        }catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        return returnSet;
    }
    public void removeVisitor(String username, String email)
    {
        Connection conn = DatabaseConnector.establishConnection();
        try{
            Statement statement = conn.createStatement();
            String sql = "DELETE From User Where Username= \"" + username+ "\" AND Email=\"" +
                    email + "\" AND Type=\"Visitor\"";
            statement.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
    }
}
