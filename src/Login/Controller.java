package Login;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import database.DatabaseConnector;

public class Controller {
    public Label helloWorld;
    public DatabaseConnector conn;

    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("Hello World!");
    }

    public boolean Login(String username, String password)
    {
        System.out.println(username);
        System.out.println(password);
        conn = new DatabaseConnector();
        conn.test();
        return true;
    }
}
