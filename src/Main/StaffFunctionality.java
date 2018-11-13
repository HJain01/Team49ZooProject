package Main;

import Login.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StaffFunctionality  {

    private final BorderPane rootPane;


    public StaffFunctionality() {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button searchAnimals = new Button("Search Animals");
        HBox searchAnimalsBox = new HBox(10);
        searchAnimalsBox.getChildren().add(searchAnimals);
        grid.add(searchAnimalsBox, 0, 2);

        Button viewShows = new Button("View Shows");
        HBox viewShowsBox = new HBox(10);
        viewShowsBox.getChildren().add(viewShows);
        grid.add(viewShowsBox, 2, 2);

        Button logOut = new Button("Log Out");
        HBox logOutBox = new HBox(10);
        logOutBox.getChildren().add(logOut);
        grid.add(logOutBox, 4, 2);

        Text scenetitle = new Text("Atlanta Zoo");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();


    }

    public Pane getRootPane() {
        return rootPane;
    }
}
