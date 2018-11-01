package Login;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class Controller {
    public Label helloWorld;
    public DatabaseConnector conn = new DatabaseConnector();

    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("Hello World!");
    }

    public boolean Login(String username, String password)
    {
        System.out.println(username);
        System.out.println(password);
        Connection connection = DatabaseConnector.establishConnection();
        try {
            Statement statement = connection.createStatement();
        } catch(SQLException e)
        {
            return false;
        }
        return true;
    }

    public void Register(String username, String password) {
        Connection connection = DatabaseConnector.establishConnection();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO User VALUES ('test', 'test@test.com', 'test', 'Visitor')");
            System.out.println("Success!");
        } catch (SQLException e){
            e.getMessage();
        }
    }
}
