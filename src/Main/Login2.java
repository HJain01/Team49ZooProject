package Main;

import Controllers.LoginController;
import Controllers.SessionData;
import DataModel.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login2 {

    private final BorderPane rootPane;

    public Login2() {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button SignIn = new Button("Sign in");
        HBox SignInBox = new HBox(10);
        SignInBox.setAlignment(Pos.BOTTOM_RIGHT);
        SignInBox.getChildren().add(SignIn);
        grid.add(SignInBox, 1, 4);

        Button register = new Button("Register");
        HBox registerBox = new HBox(10);
        registerBox.setAlignment(Pos.BOTTOM_RIGHT);
        registerBox.getChildren().add(register);
        grid.add(registerBox, 1, 5);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        Text scenetitle = new Text("Atlanta Zoo");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label email = new Label("Email:");
        grid.add(email, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        SignIn.setOnAction(e -> {
            LoginController con = new LoginController();
            User user = con.loginUser(userTextField.getText(), pwBox.getText());

            SessionData session = new SessionData();
            session.user = user;

            if (user.email != null) {
                if (user.type.toString().equals("VISITOR")) {
                    VisitorFunctionality visitorSignIn = new VisitorFunctionality();
                    primaryStage.getScene().setRoot(visitorSignIn.getRootPane());
                    primaryStage.hide();
                }
                if (user.type.toString().equals("STAFF")) {
                    StaffFunctionality staffSignIn = new StaffFunctionality();
                    primaryStage.getScene().setRoot(staffSignIn.getRootPane());
                    primaryStage.hide();
                }
                if (user.type.toString().equals("ADMIN")) {
                    AdminFunctionality adminSignIn = new AdminFunctionality();
                    primaryStage.getScene().setRoot(adminSignIn.getRootPane());
                    primaryStage.hide();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Information");
                alert.setHeaderText(null);
                alert.setContentText("You have entered the incorrect username/password.");
                alert.showAndWait();
            }
        });


        register.setOnAction(e -> {
            Register registerPage = new Register();
            primaryStage.getScene().setRoot(registerPage.getRootPane());
            primaryStage.hide();
        });

        primaryStage.show();
    }

    public Pane getRootPane() {
        return rootPane;
    }

}
