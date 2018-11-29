package Main;

import Controllers.SearchForShowsController;
import Controllers.ViewShowsController;
import DataModel.Show;
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

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ViewShows {

    private TableView table;
    public final BorderPane rootPane;


    public ViewShows() {

        rootPane = new BorderPane();
        ObservableList<Show> data = FXCollections.observableArrayList();
        SearchForShowsController controller = new SearchForShowsController();
        ResultSet set = controller.getShowInfo();
        try {
            while(set.next()) {
                String showName = set.getString(1);
                String showDate = set.getString(2);
                String hostUsername = set.getString(3);
                String showExhibit = set.getString(4);
                data.addAll(new Show(showName, showDate, hostUsername, showExhibit));
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
        nameCol.setCellValueFactory(new PropertyValueFactory<Show, String>("name"));
        TableColumn exhibitCol = new TableColumn("Exhibit");
        exhibitCol.setCellValueFactory(new PropertyValueFactory<Show, String>("locatedIn"));
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<Show, String>("date"));

        table.setItems(data);
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


        Button removeShowButton = new Button("Remove Show");
        HBox removeBox = new HBox(10);
        removeBox.setAlignment(Pos.CENTER);
        removeBox.getChildren().add(removeShowButton);
        grid.add(removeBox, 3, 15);


        Group root = new Group();
        root.getChildren().addAll(grid);

        Scene scene = new Scene(root,650, 500);
        primaryStage.setScene(scene);

        primaryStage.show();

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String showName = null != nameTextField.getText() ? nameTextField.getText() : "";
                String date = null != datePicker.getValue() ? Date.valueOf(datePicker.getValue()).toString() : "";
                String exhibit = null != exhibitType.getValue() ? (String) exhibitType.getValue() : "";
                SearchForShowsController controller = new SearchForShowsController();
                ResultSet set = controller.searchButtonPressed(showName, date, exhibit, "Name", "ASC");
                ObservableList<Show> data = FXCollections.observableArrayList();
                try {
                    while (set.next()) {
                        data.addAll(new Show(set.getString(1), set.getString(2),
                                set.getString(3), set.getString(4)));                   }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                table.getItems().clear();
                table.getItems().addAll(data);
            }
        });

        removeShowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Show> list = table.getSelectionModel().getSelectedItems();
                if(list.size() == 1) {
                    ViewShowsController controller = new ViewShowsController();
                    controller.removeShows(list.get(0).name, list.get(0).date, list.get(0).locatedIn);
                    table.getItems().remove(list.get(0));
                }
            }
        });

    }


    public Pane getRootPane() {
        return rootPane;
    }
}
