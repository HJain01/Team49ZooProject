package Main;

import Controllers.SessionData;
import Controllers.ShowHistoryController;
import DataModel.User;
import DataModel.VisitShow;
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
import java.util.List;


public class ShowHistory {

    private TableView table;
    public final BorderPane rootPane;


    public ShowHistory() {

        rootPane = new BorderPane();
        User user = SessionData.user;
        ObservableList<VisitShow> data = FXCollections.observableArrayList();
        ShowHistoryController controller = new ShowHistoryController();
        List<VisitShow> list = controller.getShowHistory(user.username);
        while (!list.isEmpty()) {
            data.addAll(list.get(0));
            list.remove(0);
        }

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Atlanta Zoo Show History");
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

        Label exhibit = new Label("Exhibit:");
        grid.add(exhibit, 4, 2);
        final ComboBox exhibitType = new ComboBox();
        exhibitType.getItems().addAll("Pacific", "Jungle", "Sahara", "Mountainous", "Birds");
        grid.add(exhibitType, 5, 2);

        table = new TableView<>();
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<VisitShow, String>("showName"));
        TableColumn dateCol = new TableColumn("Time");
        dateCol.setCellValueFactory(new PropertyValueFactory<VisitShow, String>("showTime"));
        TableColumn exhibitCol = new TableColumn("Exhibit");
        exhibitCol.setCellValueFactory(new PropertyValueFactory<VisitShow, String>("exhibitName"));

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
                ShowHistoryController controller = new ShowHistoryController();
                ObservableList<VisitShow> data = FXCollections.observableArrayList();
                String showName = null != nameTextField.getText() ? nameTextField.getText() : "";
                String exhibitName = null != exhibitType.getValue() ? (String) exhibitType.getValue() : "";
                String time = null != datePicker.getValue() ? Date.valueOf(datePicker.getValue()).toString() : "";
                List<VisitShow> list = controller.searchButtonPressed(user.username, showName, time, exhibitName);
                while(!list.isEmpty()) {
                    data.addAll(list.get(0));
                    list.remove(0);
                }
                table.getItems().clear();
                table.setItems(data);
            }
        });


    }

    public Pane getRootPane() {
        return rootPane;
    }
}
