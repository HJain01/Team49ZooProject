package Main;

import Controllers.ExhibitDetailController;
import DataModel.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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


public class AnimalDetail {

    private TableView table;
    public final BorderPane rootPane;

    public AnimalDetail() {

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

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);


        primaryStage.show();

    }

    public Pane getRootPane() {
        return rootPane;
    }

}
