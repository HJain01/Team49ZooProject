package Controllers;

import DataModel.User;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class RegistrationController {
    public DatabaseConnector conn;

    public User registerVisitor(String username, String password, String email)
    {
        User newVisitor = new User();
        newVisitor.email = email;
        newVisitor.username = username;
        //TODO: add password hashing
        newVisitor.password = password;
        newVisitor.type = User.Type.VISITOR;

        Connection connection = DatabaseConnector.establishConnection();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO User VALUES ('"+newVisitor.username+"', '"+newVisitor.email +
                    "', '"+newVisitor.password+"', '"+newVisitor.type+"')");
            System.out.println("Success!");
        } catch (SQLException e){
            e.getMessage();
        }
        return newVisitor;
    }

}
