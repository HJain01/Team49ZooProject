package Main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class VisitorFunctionality {

    private final BorderPane rootPane;

    public VisitorFunctionality() {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button searchExhibits = new Button("Search Exhibits");
        HBox searchExhibitsBox = new HBox(10);
        searchExhibitsBox.getChildren().add(searchExhibits);
        grid.add(searchExhibitsBox, 0, 1);

        Button viewExhibitHistory = new Button("View Exhibit History");
        HBox viewExhibitBox = new HBox(10);
        viewExhibitBox.getChildren().add(viewExhibitHistory);
        grid.add(viewExhibitBox, 3, 1);

        Button searchShows = new Button("Search Shows");
        HBox searchShowsBox = new HBox(10);
        searchShowsBox.getChildren().add(searchShows);
        grid.add(searchShowsBox, 0, 2);

        Button viewShowHistory = new Button("View Show History");
        HBox showHistoryBox = new HBox(10);
        showHistoryBox.getChildren().add(viewShowHistory);
        grid.add(showHistoryBox, 3, 2);

        Button searchForAnimals = new Button("Search for Animals");
        HBox searchAnimalsBox = new HBox(10);
        searchAnimalsBox.getChildren().add(searchForAnimals);
        grid.add(searchAnimalsBox, 0, 3);

        Button logOut = new Button("Log Out");
        HBox logOutBox = new HBox(10);
        logOutBox.getChildren().add(logOut);
        grid.add(logOutBox, 3, 3);

        Text scenetitle = new Text("Atlanta Zoo");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        searchExhibits.setOnAction(e -> {
            SearchForExhibits exhibits = new SearchForExhibits();
            primaryStage.getScene().setRoot(exhibits.getRootPane());
            primaryStage.hide();
        });

        searchForAnimals.setOnAction(e -> {
            SearchForAnimals animals = new SearchForAnimals();
            primaryStage.getScene().setRoot(animals.getRootPane());
            primaryStage.hide();
        });

        searchShows.setOnAction(e -> {
            SearchForShows shows = new SearchForShows();
            primaryStage.getScene().setRoot(shows.getRootPane());
            primaryStage.hide();
        });

        viewExhibitHistory.setOnAction(e -> {
            ExhibitHistory exhibitHistory = new ExhibitHistory();
            primaryStage.getScene().setRoot(exhibitHistory.getRootPane());
            primaryStage.hide();
        });

        primaryStage.show();
    }

    public Pane getRootPane() {
        return rootPane;
    }

}
