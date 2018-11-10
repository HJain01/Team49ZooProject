package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SearchForShows {

    private TableView table;
    public final BorderPane rootPane;


    public SearchForShows() {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Atlanta Zoo Shows");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);


        Label name = new Label("Name:");
        grid.add(name, 0, 2);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 2);

        Label date = new Label("Date:");
        grid.add(date, 4, 2);
        DatePicker datePicker = new DatePicker();
        grid.add(datePicker, 5, 2);

        Label exhibit = new Label("Exhibit:");
        grid.add(exhibit, 0, 5);
        final ComboBox exhibitType = new ComboBox();
        exhibitType.getItems().addAll("Pacific", "Jungle", "Sahara", "Mountainous", "Birds");
        grid.add(exhibitType, 1, 5);
        
        table = new TableView<>();
        TableColumn nameCol = new TableColumn("Name");
        TableColumn exhibitCol = new TableColumn("Exhibit");
        TableColumn dateCol = new TableColumn("Date");


        table.getColumns().setAll(nameCol, exhibitCol, dateCol);
        table.setPrefWidth(400);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(table, 0, 7, 7, 7);

        Button searchButton = new Button("Search");
        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.getChildren().add(searchButton);
        grid.add(searchBox, 3, 5);


        Button logButton = new Button("Log Visit");
        HBox logBox = new HBox(10);
        logBox.setAlignment(Pos.CENTER);
        logBox.getChildren().add(logButton);
        grid.add(logBox, 3, 15);

        Group root = new Group();
        root.getChildren().addAll(grid);

        Scene scene = new Scene(root,580, 500);
        primaryStage.setScene(scene);

        primaryStage.show();

    }


    public Pane getRootPane() {
        return rootPane;
    }
}
