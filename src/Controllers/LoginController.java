package Controllers;

import DataModel.User;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.HashMap;

public class LoginController {
    public DatabaseConnector conn;

    public User loginUser(String email, String password) {
        User user=  new User();
        HashMap<String, String> map = new HashMap<>();
        Connection connection = DatabaseConnector.establishConnection();
        try {
            password = Base64.getEncoder().encodeToString(password.getBytes());
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM User ");
            ResultSet set = statement.getResultSet();
            while(set.next()) {
                if (set.getString(2).equals(email) && set.getString(3).equals(password)) {
                    user.username = set.getString(1);
                    user.email = set.getString(2);
                    user.password = set.getString(3);
                    user.type = User.Type.valueOf(set.getString(4).toUpperCase());
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return user;
    }
}
