package Controllers;

import database.DatabaseConnector;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewStaffController {
    public ResultSet getAllStaff(String orderColumn, String orderType){
        Connection conn = DatabaseConnector.establishConnection();
        ResultSet returnSet = null;
        try{
            Statement statement = conn.createStatement();
            String sql = "Select * from User where Type=\"Staff\""
                    +" ORDER BY " + orderColumn + " " + orderType;
            statement.execute(sql);
            returnSet = statement.getResultSet();
        }
        catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
        return returnSet;
    }
    public void removeStaff(String username, String email){
        Connection conn = DatabaseConnector.establishConnection();
        try{
            Statement statement = conn.createStatement();
            String sql = "DELETE From User Where Username= \"" + username+ "\" AND Email=\"" +
                    email + "\" AND Type=\"Staff\"";
            statement.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
    }
}
