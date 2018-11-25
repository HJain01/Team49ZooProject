package Controllers;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddShowsController {
    public void addAShow(String name, String exhibit, String staff, String datetime)
    {
        Connection conn = DatabaseConnector.establishConnection();
        try{
            Statement statement = conn.createStatement();
            String sql = "INSERT into Shows (Name, DateAndTime, HostUsername, LocatedIn) VALUES (\"" + name +"\",\'"+
            datetime + "\',\"" + staff + "\",\"" + exhibit + "\")";
            statement.execute(sql);
        }
        catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
    }
    public ResultSet checkForShowsAtTheSameTime(String staffUsername, String datetime)
    {
        Connection conn = DatabaseConnector.establishConnection();
        ResultSet returnSet = null;


        try
        {
            Statement statement = conn.createStatement();
            String sql = "Select COUNT(Name) from Shows where DateAndTime = \'" + datetime + "\' AND HostUsername = \""
                    + staffUsername + "\"";
            statement.execute(sql);
            returnSet = statement.getResultSet();
        }
        catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
        return returnSet;
    }
}
