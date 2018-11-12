package Controllers;

import DataModel.User;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;


public class RegistrationController {
    public DatabaseConnector conn;

    public void sqlCall(User user) {
        Connection conn = DatabaseConnector.establishConnection();
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO User VALUES ('"+user.username+"', '"+user.email +
                    "', '"+user.password+"', '"+user.type+"')");
            System.out.println("Success!");
        } catch (SQLException e){
            e.getMessage();
        }
    }

    public User registerVisitor(String username, String password, String email)
    {
        User newVisitor = new User();
        newVisitor.email = email;
        newVisitor.username = username;
        password = Base64.getEncoder().encodeToString(password.getBytes());
        //TODO: add password hashing
        newVisitor.password = password;
        newVisitor.type = User.Type.VISITOR;
        sqlCall(newVisitor);
        return newVisitor;
    }

    public User registerStaff(String username, String password, String email)
    {
        User newStaff = new User();
        newStaff.email = email;
        newStaff.username = username;
        password = Base64.getEncoder().encodeToString(password.getBytes());
        //TODO: add password hashing
        newStaff.password = password;
        newStaff.type = User.Type.STAFF;
        sqlCall(newStaff);
        return newStaff;
    }
}
