package Main;

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

public class AdminFunctionality {

    private final BorderPane rootPane;


    public AdminFunctionality() {
        rootPane = new BorderPane();

        Stage primaryStage = new Stage();

        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button viewVisitors = new Button("View Visitors");
        HBox viewVisitorsBox = new HBox(10);
        viewVisitorsBox.getChildren().add(viewVisitors);
        grid.add(viewVisitorsBox, 0, 1);

        Button viewStaff = new Button("View Staff");
        HBox viewStaffBox = new HBox(10);
        viewStaffBox.getChildren().add(viewStaff);
        grid.add(viewStaffBox, 3, 1);

        Button viewShows = new Button("View Shows");
        HBox viewShowsBox = new HBox(10);
        viewShowsBox.getChildren().add(viewShows);
        grid.add(viewShowsBox, 0, 2);

        Button viewAnimals = new Button("View Animals");
        HBox viewAnimalsBox = new HBox(10);
        viewAnimalsBox.getChildren().add(viewAnimals);
        grid.add(viewAnimalsBox, 3, 2);

        Button addShow = new Button("Add Show");
        HBox addShowBox = new HBox(10);
        addShowBox.getChildren().add(addShow);
        grid.add(addShowBox, 0, 3);

        Button logOut = new Button("Log Out");
        HBox logOutBox = new HBox(10);
        logOutBox.getChildren().add(logOut);
        grid.add(logOutBox, 3, 3);

        Text scenetitle = new Text("Atlanta Zoo");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();

        addShow.setOnAction(e -> {
            AddShows addShowPage = new AddShows();
            primaryStage.getScene().setRoot(addShowPage.getRootPane());
            primaryStage.hide();
        });

    }

    public Pane getRootPane() {
        return rootPane;
    }
}
