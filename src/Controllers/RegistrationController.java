package Controllers;

import DataModel.User;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class RegistrationController {
    public DatabaseConnector conn;

    public User sqlCall(String username, String email, String password, User.Type type) {
        Connection conn = DatabaseConnector.establishConnection();
        User user = new User();
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO User VALUES ('"+username+"', '"+email +
                    "', '"+password+"', '"+type+"')");
            System.out.println("Success!");
            user.username = username;
            user.email = email;
            user.password = password;
            user.type = type;
        } catch (SQLException e){
            e.getMessage();
        }
        return user;
    }

    public User registerVisitor(String username, String password, String email)
    {
        User newVisitor;
        newVisitor = sqlCall(username, email, password, User.Type.VISITOR);
        return newVisitor;
    }

    public User registerStaff(String username, String password, String email)
    {
        User newStaff;
        newStaff = sqlCall(username, email, password, User.Type.STAFF);
        return newStaff;
    }
}
