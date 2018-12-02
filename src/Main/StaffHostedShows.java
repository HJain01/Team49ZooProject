package Main;

import Controllers.ExhibitDetailController;
import Controllers.SessionData;
import Controllers.StaffHostedShowsController;
import DataModel.Animal;
import DataModel.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import DataModel.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class StaffHostedShows {
    private TableView table;
    public final BorderPane rootPane;

    public StaffHostedShows(){

        rootPane = new BorderPane();
        StaffHostedShowsController controller = new StaffHostedShowsController();

        ObservableList<Show> data = FXCollections.observableArrayList();

        User user = SessionData.user;
        ResultSet result = controller.getShowsForStaffMember(user.username, "Name", "ASC");

        try
        {
            while(result.next()){
                String showName = result.getString(1);
                String showDateTime = result.getString(2);
                String showLocation = result.getString(4);
                data.addAll(new Show(showName, showDateTime, user.username, showLocation));
            }
        } catch(SQLException e){
            System.out.println(e.getErrorCode());
        }


        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Staff - Show History");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0);

        table = new TableView<>();

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Show, String>("name"));

        TableColumn timeCol = new TableColumn("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<Show, String>("date"));

        TableColumn exhibitCol = new TableColumn("Exhibit");
        exhibitCol.setCellValueFactory(new PropertyValueFactory<Show, String>("locatedIn"));

        Label orderByLabel = new Label("Order By:");
        grid.add(orderByLabel, 3,5);
        final ComboBox orderBy = new ComboBox();
        orderBy.getItems().addAll("Name", "DateAndTime");
        grid.add(orderBy, 4,5);

        final ComboBox orderType = new ComboBox();
        orderType.getItems().addAll("ASC", "DESC");
        grid.add(orderType, 5,5);

        Button reorderButton = new Button("Re-Order");
        HBox reorderBox = new HBox(10);
        //reorderBox.setAlignment(Pos.CENTER);
        reorderBox.getChildren().add(reorderButton);
        grid.add(reorderBox, 0, 5);


        table.setItems(data);
        table.getColumns().setAll(nameCol, timeCol, exhibitCol);
        table.setPrefWidth(400);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(table, 0, 2, 7, 3);

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
        reorderButton.setOnAction(e -> {
            ObservableList<Show> newData = FXCollections.observableArrayList();

            String orderingColumn = null != orderBy.getValue() ? (String) orderBy.getValue() : "Name";
            String orderingType = null != orderType.getValue() ? (String) orderType.getValue() : "ASC";

            StaffHostedShowsController controller1 = new StaffHostedShowsController();
            ResultSet result1 = controller.getShowsForStaffMember(user.username, orderingColumn, orderingType);

            try
            {
                while(result1.next()){
                    String showName = result1.getString(1);
                    String showDateTime = result1.getString(2);
                    String showLocation = result1.getString(4);
                    newData.addAll(new Show(showName, showDateTime, user.username, showLocation));
                }
            } catch(SQLException f){
                System.out.println(f.getErrorCode());
            }
            table.setItems(newData);
        });

        Scene scene = new Scene(grid, 560, 400);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public Pane getRootPane() {
        return rootPane;
    }
}
