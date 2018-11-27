package Main;

import Controllers.SearchForAnimalsController;
import Controllers.ViewAnimalsController;
import DataModel.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ViewAnimals {

    private TableView table;
    public final BorderPane rootPane;


    public ViewAnimals() {

        rootPane = new BorderPane();
        ObservableList<Animal> data = FXCollections.observableArrayList();
        SearchForAnimalsController controller = new SearchForAnimalsController();
        ResultSet set = controller.getAnimalInfo();
        try {
            while(set.next()) {
                String name = set.getString(1);
                String species = set.getString(2);
                String type = set.getString(3);
                int age = set.getInt(4);
                String livesIn = set.getString(5);
                data.addAll(new Animal(name, species, type, age, livesIn));
            }
        }  catch(SQLException e) {
            e.printStackTrace();
        }

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
        grid.add(exhibitSearchBox, 5, 0, 6, 1);

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
        animalType.getItems().addAll("Reptile", "Invertebrate", "Fish", "Mammal", "Amphibian", "Bird");
        grid.add(animalType, 5, 5);

        Label waterFeature = new Label("Species: ");
        grid.add(waterFeature, 0, 5);
        TextField speciesTextField = new TextField();
        grid.add(speciesTextField, 1, 5);

        table = new TableView<>();
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));
        TableColumn speciesCol = new TableColumn("Species");
        speciesCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("species"));
        TableColumn exhibitCol = new TableColumn("Exhibit");
        exhibitCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("livesIn"));
        TableColumn ageCol = new TableColumn("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("age"));
        TableColumn typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("type"));

        table.setItems(data);
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

        Button removeAnimalButton = new Button("Remove Animal");
        HBox removeBox = new HBox(10);
        removeBox.setAlignment(Pos.CENTER);
        removeBox.getChildren().add(removeAnimalButton);
        grid.add(removeBox, 3, 15);

        Group root = new Group();
        root.getChildren().addAll(grid);

        Hyperlink previousLink = new Hyperlink();
        previousLink.setText("Previous Page");
        grid.add(previousLink, 0, 15);
        previousLink.setOnAction(e -> {
            AdminFunctionality adminSignIn = new AdminFunctionality();
            primaryStage.getScene().setRoot(adminSignIn.getRootPane());
            primaryStage.hide();
        });

        Scene scene = new Scene(root,650, 500);
        primaryStage.setScene(scene);

        primaryStage.show();

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = null != nameTextField.getText() ? nameTextField.getText() : "";
                int minNum = null != minNumber.getValue() ? (int) minNumber.getValue() : 0;
                int maxNum = null != maxNumber.getValue() ? (int) maxNumber.getValue() : 0;
                String type = null != animalType.getValue() ? (String) animalType.getValue() : "";
                String species = null != speciesTextField.getText() ? speciesTextField.getText() : "";
                String livesIn = null != exhibitSearchBox.getValue() ? (String) exhibitSearchBox.getValue() : "";
                SearchForAnimalsController controller = new SearchForAnimalsController();
                ResultSet set = controller.searchButtonPressed(name, species, type, minNum, maxNum, livesIn);
                ObservableList<Animal> data = FXCollections.observableArrayList();
                try {
                    while (set.next()) {
                        data.addAll(new Animal(set.getString(1), set.getString(2),
                                set.getString(3), set.getInt(4), set.getString(5)));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                table.getItems().clear();
                table.getItems().addAll(data);

            }
        });

        removeAnimalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Animal> list = table.getSelectionModel().getSelectedItems();
                if (list.size() == 1) {
                    ViewAnimalsController controller = new ViewAnimalsController();
                    controller.removeAnimal(list.get(0).name, list.get(0).species);
                    table.getItems().remove(list.get(0));
                }
            }
        });
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
