package Main;

import Controllers.SearchForExhibitsController;
import Controllers.SessionData;
import DataModel.Exhibit;
import DataModel.User;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static javafx.application.Application.launch;


public class SearchForExhibits  {

    private TableView table;
    public final BorderPane rootPane;

    public static void main(String[] args) {
        launch(args);
    }


    public SearchForExhibits() {

        rootPane = new BorderPane();
        ObservableList<Exhibit> data = FXCollections.observableArrayList();
        SearchForExhibitsController controller = new SearchForExhibitsController();
        ResultSet set = controller.getExhibitInfo();
        try {
            while (set.next()) {
                HashMap<String, Integer> numAnimals = controller.getNumAnimals();
                String name = set.getString(1);
                int size = set.getInt(2);
                boolean waterFeature = set.getBoolean(3);
                data.addAll(new Exhibit(name, size, numAnimals.get(name), waterFeature));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

        table.setRowFactory(tv -> {
            TableRow<Exhibit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Exhibit rowData = row.getItem();
                    ExhibitDetail exhibitDetail = new ExhibitDetail(rowData.name, rowData.size, rowData.numAnimals, rowData.waterFeature);
                    primaryStage.getScene().setRoot(exhibitDetail.getRootPane());
                    primaryStage.hide();
                }
            });
            return row ;
        });

        Label orderByLabel = new Label("Order By:");
        grid.add(orderByLabel, 4,6);
        final ComboBox orderBy = new ComboBox();
        orderBy.getItems().addAll("Name", "Size", "numAnimals", "WaterFeature");
        grid.add(orderBy, 5,6);

        final ComboBox orderType = new ComboBox();
        orderType.getItems().addAll("ASC", "DESC");
        grid.add(orderType, 6,6);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Exhibit, String>("name"));
        TableColumn sizeCol = new TableColumn("Size");
        sizeCol.setCellValueFactory(new PropertyValueFactory<Exhibit, String>("size"));
        TableColumn animalNumCol = new TableColumn("Number of Animals");
        animalNumCol.setCellValueFactory(new PropertyValueFactory<Exhibit, String>("numAnimals"));
        TableColumn waterCol = new TableColumn("Water");
        waterCol.setCellValueFactory(new PropertyValueFactory<Exhibit, String>("waterFeature"));


        table.getColumns().setAll(nameCol, sizeCol, animalNumCol, waterCol);
        table.setItems(data);
        table.setPrefWidth(400);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        grid.add(table, 0, 7, 7, 7);

        Group root = new Group();
        root.getChildren().addAll(grid);

        Hyperlink previousLink = new Hyperlink();
        previousLink.setText("Home");
        grid.add(previousLink, 0, 15);
        previousLink.setOnAction(e -> {
            if(SessionData.user != null && SessionData.user.type == User.Type.ADMIN) {
                AdminFunctionality adminSignIn = new AdminFunctionality();
                primaryStage.getScene().setRoot(adminSignIn.getRootPane());
                primaryStage.hide();
            }
            else if(SessionData.user != null && SessionData.user.type == User.Type.STAFF) {
                StaffFunctionality staffSignIn = new StaffFunctionality();
                primaryStage.getScene().setRoot(staffSignIn.getRootPane());
                primaryStage.hide();
            }
            else{
                VisitorFunctionality visitorSignIn = new VisitorFunctionality();
                primaryStage.getScene().setRoot(visitorSignIn.getRootPane());
                primaryStage.hide();
            }
        });

        Scene scene = new Scene(root,600, 500);
        primaryStage.setScene(scene);

        primaryStage.show();

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = null != nameTextField.getText() ? nameTextField.getText() : "";
                int minNum = null != minNumber.getValue() ? (int) minNumber.getValue(): 0;
                int maxNum = null != maxNumber.getValue() ? (int) maxNumber.getValue(): 99;
                int minSize = null != minSizeNumber.getValue() ? (int) minSizeNumber.getValue(): 0;
                int maxSize = null != maxSizeNumber.getValue() ? (int) maxSizeNumber.getValue(): 0;
                String water = null != waterFeatureBox.getValue() ? (String) waterFeatureBox.getValue(): "2";

                String orderingColumn = null != orderBy.getValue() ? (String) orderBy.getValue() : "Name";
                String orderingType = null != orderType.getValue() ? (String) orderType.getValue() : "ASC";

                SearchForExhibitsController controller = new SearchForExhibitsController();
                ResultSet set = controller.searchButtonPressed(name, minNum, maxNum, water, minSize, maxSize, orderingColumn, orderingType);
                ObservableList<Exhibit> data = FXCollections.observableArrayList();
                try {
                    while (set.next()) {
                        data.addAll(new Exhibit(set.getString(1), set.getInt(2), set.getInt(5), set.getBoolean(3)));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                table.getItems().clear();
                table.getItems().addAll(data);

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
