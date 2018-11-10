package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//this class is currently not being used and is not connected
//to any of the other pages

public class ExhibitDetail {

    private TableView table;
    public final BorderPane rootPane;

    public ExhibitDetail() {

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

        Label name = new Label("Name: ");
        //add a getter for the animal name
        Group rootName = new Group();
        rootName.getChildren().addAll(name);
        grid.add(rootName, 0, 2);

        Label size = new Label("Size: ");
        //add getter method for size
        Group rootSize = new Group();
        rootSize.getChildren().addAll(size);
        grid.add(rootSize, 2, 2);

        Label AnimalNum = new Label("Num of Animals: ");
        //add getter method for number of animals
        Group rootNum = new Group();
        rootNum.getChildren().addAll(AnimalNum);
        grid.add(rootNum, 4, 2);

        Label water = new Label("Water Feature: ");
        //add getter method for water feature
        Group rootWater = new Group();
        rootWater.getChildren().addAll(water);
        grid.add(rootWater, 6, 2);

        Button LogVisit = new Button("Log Visit");
        HBox LogVisitBox = new HBox(10);
        LogVisitBox.setAlignment(Pos.CENTER);
        LogVisitBox.getChildren().add(LogVisit);
        grid.add(LogVisitBox, 3, 4);

        table = new TableView<>();
        TableColumn nameCol = new TableColumn("Name");
        TableColumn speciesCol = new TableColumn("Species");

        table.getColumns().setAll(nameCol, speciesCol);
        table.setPrefWidth(450);
        table.setPrefHeight(250);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(table, 0, 6, 8, 8);


        ExhibitDetail detailPage = new ExhibitDetail();
        primaryStage.getScene().setRoot(detailPage.getRootPane());

        Scene scene = new Scene(grid,580, 500);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public Pane getRootPane() {
        return rootPane;
    }

}
