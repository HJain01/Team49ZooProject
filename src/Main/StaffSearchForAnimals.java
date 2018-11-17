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


public class StaffSearchForAnimals {

    private TableView table;
    public final BorderPane rootPane;


    public StaffSearchForAnimals() {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Atlanta Zoo Animals");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label exhibit = new Label("Exhibit: ");
        grid.add(exhibit, 4, 0);
        final ComboBox exhibitSearchBox = new ComboBox();
        exhibitSearchBox.getItems().addAll(
                "Pacific", "Jungle", "Sahara", "Mountainous", "Birds"
        );
        grid.add(exhibitSearchBox, 5, 0);

        Label name = new Label("Name:");
        grid.add(name, 0, 2);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 2);

        Label age = new Label("Age:");
        grid.add(age, 4, 2);

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

        Label type = new Label("Type:");
        grid.add(type, 4, 5);
        final ComboBox animalType = new ComboBox();
        animalType.getItems().addAll("Fish", "Mammal", "Amphibian", "Bird");
        grid.add(animalType, 5, 5);

        Label waterFeature = new Label("Species: ");
        grid.add(waterFeature, 0, 5);
        TextField speciesTextField = new TextField();
        grid.add(speciesTextField, 1, 5);

        table = new TableView<>();
        TableColumn nameCol = new TableColumn("Name");
        TableColumn speciesCol = new TableColumn("Species");
        TableColumn exhibitCol = new TableColumn("Exhibit");
        TableColumn ageCol = new TableColumn("Age");
        TableColumn typeCol = new TableColumn("Type");

        table.getColumns().setAll(nameCol, speciesCol, exhibitCol, ageCol, typeCol);
        table.setPrefWidth(400);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button searchButton = new Button("Search");
        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.getChildren().add(searchButton);
        grid.add(searchBox, 3, 6);


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

    public Pane getRootPane() {
        return rootPane;
    }
}
