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

    public User loginUser(String username, String password) {
        User user = new User();
        HashMap<String, String> map = new HashMap<>();
        password = Base64.getEncoder().encodeToString(password.getBytes());
        Connection connection = DatabaseConnector.establishConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM User " +
                    "WHERE username=" + username + "AND password=" + password);
            ResultSet set = statement.getResultSet();
            if (set != null) {
                user.email = set.getString(1);
                user.username = set.getString(2);
                user.password = set.getString(3);
                user.type = User.Type.valueOf(set.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return user;
    }
}
