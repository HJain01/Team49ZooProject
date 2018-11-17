package Main;

import Controllers.ExhibitDetailController;
import DataModel.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


public class ExhibitDetail {

    private TableView table;
    public final BorderPane rootPane;

    public ExhibitDetail(String name, int size, int numAnimals, boolean waterFeature) {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Atlanta Zoo Exhibit Detail");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 8, 1);

        Label exhibitName = new Label("Name: " + name);
        Group rootName = new Group();
        rootName.getChildren().addAll(exhibitName);
        grid.add(rootName, 0, 2);

        Label exhibitSize = new Label("Size: " + size);
        Group rootSize = new Group();
        rootSize.getChildren().addAll(exhibitSize);
        grid.add(rootSize, 2, 2);

        Label AnimalNum = new Label("Num of Animals: " + numAnimals);
        Group rootNum = new Group();
        rootNum.getChildren().addAll(AnimalNum);
        grid.add(rootNum, 4, 2);

        Label water = new Label("Water Feature: " + waterFeature);
        Group rootWater = new Group();
        rootWater.getChildren().addAll(water);
        grid.add(rootWater, 6, 2);

        Button LogVisit = new Button("Log Visit");
        HBox LogVisitBox = new HBox(10);
        LogVisitBox.setAlignment(Pos.CENTER);
        LogVisitBox.getChildren().add(LogVisit);
        grid.add(LogVisitBox, 3, 4);

        ObservableList<Animal> data = FXCollections.observableArrayList();
        ExhibitDetailController controller = new ExhibitDetailController();
        List<Animal> animalList = controller.getAnimals(name);
        while (!animalList.isEmpty()) {
            data.addAll(animalList.get(0));
            animalList.remove(0);
        }
        table = new TableView<>();
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));
        TableColumn speciesCol = new TableColumn("Species");
        speciesCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("species"));
        TableColumn ageColumn = new TableColumn("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("age"));
        TableColumn livesInColumn = new TableColumn("Lives In");
        livesInColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("livesIn"));
        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("type"));


        table.getColumns().setAll(nameCol, speciesCol, ageColumn, livesInColumn, typeColumn);
        table.setItems(data);
        table.setPrefWidth(450);
        table.setPrefHeight(250);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(table, 0, 6, 8, 8);

        Scene scene = new Scene(grid,580, 500);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public Pane getRootPane() {
        return rootPane;
    }

}
