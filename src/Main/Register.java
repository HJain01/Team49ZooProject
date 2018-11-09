package Main;

import Controllers.RegistrationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Register  {
    private final BorderPane rootPane;


    public Register() {
        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button btn = new Button("Register Visitor");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 6);

        Button btn2 = new Button("Register Staff");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 1, 6);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        Text scenetitle = new Text("Atlanta Zoo");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label email = new Label("Email:");
        grid.add(email, 0, 1);

        TextField emailTextField = new TextField();
        grid.add(emailTextField, 1, 1);

        Label username = new Label("Username:");
        grid.add(username, 0, 2);

        TextField userNameBox = new TextField();
        grid.add(userNameBox, 1, 2);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 3);

        PasswordField pwTextBox = new PasswordField();
        grid.add(pwTextBox, 1, 3);

        Label pwConfirm = new Label("Confirm Password:");
        grid.add(pwConfirm, 0, 4);

        PasswordField pwConfirmBox = new PasswordField();
        grid.add(pwConfirmBox, 1, 4);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RegistrationController controller = new RegistrationController();
                controller.registerVisitor(userNameBox.getText(),pwTextBox.getText(), emailTextField.getText());
            }
        });
    }

    public Pane getRootPane() {
        return rootPane;
    }
}

