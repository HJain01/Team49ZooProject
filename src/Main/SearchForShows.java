package Main;

import Controllers.*;
import DataModel.Exhibit;
import DataModel.Show;
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

import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SearchForShows {

    private TableView table;
    public final BorderPane rootPane;


    public SearchForShows() {

        rootPane = new BorderPane();
        User user = SessionData.user;

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
        grid.add(datePicker, 5, 2, 6, 2);

        Label exhibit = new Label("Exhibit:");
        grid.add(exhibit, 0, 5);
        final ComboBox exhibitType = new ComboBox();
        exhibitType.getItems().addAll("Pacific", "Jungle", "Sahara", "Mountainous", "Birds");
        grid.add(exhibitType, 1, 5);

        Label orderByLabel = new Label("Order By:");
        grid.add(orderByLabel, 4,5);
        final ComboBox orderBy = new ComboBox();
        orderBy.getItems().addAll("Name", "LocatedIn");
        grid.add(orderBy, 5,5);

        final ComboBox orderType = new ComboBox();
        orderType.getItems().addAll("ASC", "DESC");
        grid.add(orderType, 6,5);


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


        Button logButton = new Button("Log Visit");
        HBox logBox = new HBox(10);
        logBox.setAlignment(Pos.CENTER);
        logBox.getChildren().add(logButton);
        grid.add(logBox, 3, 15);

        Group root = new Group();
        root.getChildren().addAll(grid);

        Scene scene = new Scene(root,620, 500);
        primaryStage.setScene(scene);

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

        primaryStage.show();

        table.setRowFactory(tv -> {
            TableRow<Show> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Show rowData = row.getItem();
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SearchForAnimalsController controller1 = new SearchForAnimalsController();
                    Exhibit exhibitInfo = controller1.getExhibitInfo(rowData.locatedIn);
                    ExhibitDetail exhibitDetail = new ExhibitDetail(exhibitInfo.name, exhibitInfo.size,exhibitInfo.numAnimals, exhibitInfo.waterFeature);
                    primaryStage.getScene().setRoot(exhibitDetail.getRootPane());
                    primaryStage.hide();
                }
            });
            return row ;
        });
        logButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Show> list = table.getSelectionModel().getSelectedItems();
                if (list.size() == 1) {
                    ShowHistoryController showHistoryController = new ShowHistoryController();
                    ExhibitHistoryController exhibitHistoryController = new ExhibitHistoryController();
                    String username = user.username;
                    String showName = list.get(0).name;
                    String exhibitName = list.get(0).locatedIn;
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                    LocalDateTime now = LocalDateTime.now();
                    String nowStr = now.format(dateFormat);
                    String showTimeStr = list.get(0).date;
                    //CHECK WITH TA'S IF THEY WANT TO INSERT CURRENT TIME OR SHOW TIME
//                    LocalDateTime now =  LocalDateTime.parse(nowStr, dateFormat);
//                    LocalDateTime showTime = LocalDateTime.parse(showTimeStr, dateFormat);
                    if (showTimeStr.compareTo(nowStr) == -1) {
                        showHistoryController.insertVisit(username, showName, showTimeStr);
                        exhibitHistoryController.insertVisit(username, exhibitName, nowStr);
                    }
                }
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String showName = null != nameTextField.getText() ? nameTextField.getText() : "";
                String date = null != datePicker.getValue() ? Date.valueOf(datePicker.getValue()).toString() : "";
                String exhibit = null != exhibitType.getValue() ? (String) exhibitType.getValue() : "";
                SearchForShowsController controller = new SearchForShowsController();
                String orderingColumn = null != orderBy.getValue() ? (String) orderBy.getValue() : "Name";
                String orderingType = null != orderType.getValue() ? (String) orderType.getValue() : "ASC";

                ResultSet set = controller.searchButtonPressed(showName, date, exhibit, orderingColumn, orderingType);
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
    }


    public Pane getRootPane() {
        return rootPane;
    }
}
