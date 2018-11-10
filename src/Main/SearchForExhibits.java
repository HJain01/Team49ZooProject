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


public class SearchForExhibits {

    private TableView table;
    public final BorderPane rootPane;


    public SearchForExhibits() {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Atlanta Zoo Exhibits");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Button searchButton = new Button("Search");
        HBox searchBox = new HBox(10);
        searchBox.getChildren().add(searchButton);
        grid.add(searchBox, 5, 0);

        Label name = new Label("Name:");
        grid.add(name, 0, 2);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 2);

        Label animalNum = new Label("Num Animals:");
        grid.add(animalNum, 4, 2);

        Label min = new Label("Min");
        grid.add(min, 5, 1);
        final ComboBox minNumber = new ComboBox();
        minNumber.getItems().addAll(generator());
        grid.add(minNumber, 5, 2);

        Label max = new Label("Max");
        grid.add(max, 6, 1);
        final ComboBox maxNumber = new ComboBox();
        maxNumber.getItems().addAll(generator());
        grid.add(maxNumber, 6, 2);

        Label size = new Label("Size of Exhibit:");
        grid.add(size, 4, 5);

        Label minSize = new Label("Min");
        grid.add(minSize, 5, 4);
        final ComboBox minSizeNumber = new ComboBox();
        minSizeNumber.getItems().addAll(exhibitGenerator());
        grid.add(minSizeNumber, 5, 5);

        Label maxSize = new Label("Max");
        grid.add(maxSize, 6, 4);
        final ComboBox maxSizeNumber = new ComboBox();
        maxSizeNumber.getItems().addAll(exhibitGenerator());
        grid.add(maxSizeNumber, 6, 5);

        Label waterFeature = new Label("Water Feature: ");
        grid.add(waterFeature, 0, 5);
        final ComboBox waterFeatureBox = new ComboBox();
        waterFeatureBox.getItems().addAll(
                "Yes", "No"
        );
        grid.add(waterFeatureBox, 1, 5);

        table = new TableView<>();
        TableColumn nameCol = new TableColumn("Name");
        TableColumn sizeCol = new TableColumn("Size");
        TableColumn animalNumCol = new TableColumn("Number of Animals");
        TableColumn waterCol = new TableColumn("Water");

        table.getColumns().setAll(nameCol, sizeCol, animalNumCol, waterCol);
        table.setPrefWidth(400);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        grid.add(table, 0, 7, 7, 7);

        Group root = new Group();
        root.getChildren().addAll(grid);

        Scene scene = new Scene(root,580, 500);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    private Integer[] generator() {
        int size = 100;
        Integer[] result = new Integer[size];

        for (int i = 0; i < result.length; i++) {
            result[i] = i;

        }
        return result;
    }

    private Integer[] exhibitGenerator() {
        int size = 50;
        Integer[] result = new Integer[size];
        result[0] = 0;

        for (int i = 1; i < result.length; i++) {
            result[i] = result[i-1] + 50;

        }
        return result;
    }

    public Pane getRootPane() {
        return rootPane;
    }
}
