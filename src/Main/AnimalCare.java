package Main;

import DataModel.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;


public class AnimalCare {

    private TableView table;
    public final BorderPane rootPane;

    public AnimalCare() {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Atlanta Zoo Animal Detail");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 8, 1);

        Label animalName = new Label("Name: " );
        Group rootName = new Group();
        rootName.getChildren().addAll(animalName);
        grid.add(rootName, 0, 2);

        Label animalSpecies = new Label("Species: ");
        Group rootSpecies = new Group();
        rootSpecies.getChildren().addAll(animalSpecies);
        grid.add(rootSpecies, 2, 2);

        Label animalAge = new Label("Age: " );
        Group rootNum = new Group();
        rootNum.getChildren().addAll(animalAge);
        grid.add(rootNum, 4, 2);

        Label exhibitName = new Label("Exhibit: " );
        Group rootExhibit = new Group();
        rootExhibit.getChildren().addAll(exhibitName);
        grid.add(rootExhibit, 0, 3);

        Label animalType = new Label("Type: " );
        Group rootType = new Group();
        rootType.getChildren().addAll(animalType);
        grid.add(rootType, 2, 3);
        
        TextArea careLog = new TextArea();
        VBox vbox = new VBox(careLog);
        careLog.setWrapText(true);
        careLog.setPrefHeight(150);
        careLog.setPrefWidth(200);

        grid.add(careLog, 0, 4, 4, 5);

        Button logButton = new Button("Log Notes");
        HBox logBox = new HBox(10);
        logBox.getChildren().add(logButton);
        grid.add(logBox, 5, 4);

        table = new TableView<>();
        TableColumn staffCol = new TableColumn("Staff Member");
        TableColumn noteCol = new TableColumn("Note");
        TableColumn timeCol = new TableColumn("Time");


        table.getColumns().setAll(staffCol, noteCol, timeCol);
        table.setPrefWidth(400);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(table, 0, 10, 7, 10);

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);


        primaryStage.show();

    }

    public Pane getRootPane() {
        return rootPane;
    }

}
