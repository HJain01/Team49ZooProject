package Main;

import Controllers.ExhibitHistoryController;
import Controllers.SessionData;
import DataModel.User;
import DataModel.VisitExhibit;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class ExhibitHistory {

    private TableView table;
    public final BorderPane rootPane;


    public ExhibitHistory() {

        rootPane = new BorderPane();
        User user = SessionData.user;

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Atlanta Zoo Exhibit History");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 7, 1);

        Label name = new Label("Name:");
        grid.add(name, 0, 2);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 2);

        Label date = new Label("Time:");
        grid.add(date, 0, 5);
        DatePicker datePicker = new DatePicker();
        grid.add(datePicker, 1, 5);

        Label visitNum = new Label("Num of Visits:");
        grid.add(visitNum, 4, 2);

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

        table = new TableView<>();

        ObservableList<VisitExhibit> data = FXCollections.observableArrayList();
        ExhibitHistoryController controller = new ExhibitHistoryController();
        List<VisitExhibit> list = controller.getExhibitHistory(user.username);
        while(!list.isEmpty()) {
            data.addAll(list.get(0));
            list.remove(0);
        }
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<VisitExhibit, String>("exhibitName"));
        TableColumn exhibitCol = new TableColumn("Time");
        exhibitCol.setCellValueFactory(new PropertyValueFactory<VisitExhibit, String>("dateAndTime"));
        TableColumn dateCol = new TableColumn("Number of Visits");
        dateCol.setCellValueFactory(new PropertyValueFactory<VisitExhibit, String>("numOfVisits"));

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

        Group root = new Group();
        root.getChildren().addAll(grid);

        Scene scene = new Scene(root,580, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<VisitExhibit> list = new ArrayList<>();
                ObservableList<VisitExhibit> data = FXCollections.observableArrayList();
                String name = null != nameTextField.getText() ? nameTextField.getText() : "";
                String time = null != datePicker.getValue() ? Date.valueOf(datePicker.getValue()).toString() : "";
                int minNum = null != minNumber.getValue() ? (int) minNumber.getValue() : 0;
                int maxNum = null != maxNumber.getValue() ? (int) maxNumber.getValue() : 0;
                ExhibitHistoryController controller = new ExhibitHistoryController();
                list = controller.searchButtonPressed(user.username, name, time, minNum, maxNum);
                while(!list.isEmpty()) {
                    data.addAll(list.get(0));
                    list.remove(0);
                }
                table.getItems().clear();
                table.setItems(data);
            }
        });

    }

    private Integer[] generator() {
        int size = 50;
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
