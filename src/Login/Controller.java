package Login;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Controller {
    public Label helloWorld;

    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("Hello World!");
    }

    public boolean Login(String username, String password)
    {
        System.out.println(username);
        System.out.println(password);
        return true;
    }
}
