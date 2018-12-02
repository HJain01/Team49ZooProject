package Main;

import Controllers.SessionData;
import Controllers.ViewStaffController;
import Controllers.ViewVisitorsController;
import DataModel.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewStaff {
    private TableView table;
    public final BorderPane rootPane;

    public ViewStaff() {
        rootPane = new BorderPane();
        ObservableList<User> data = FXCollections.observableArrayList();
        ViewStaffController controller = new ViewStaffController();
        ResultSet set = controller.getAllStaff("Username", "ASC");
        try{
            while(set.next()){
                String username = set.getString(1);
                String email = set.getString(2);
                String password = set.getString(3);
                String type = set.getString(4);
                User.Type realType = User.stringToType(type);
                data.addAll(new User(username, email, password, realType));
            }
        }
        catch(SQLException e){
            System.out.println(e.getErrorCode());
        }

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("View Staff");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0);

        table = new TableView<>();
        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        table.setItems(data);
        table.getColumns().setAll(usernameCol, emailCol);
        table.setPrefWidth(400);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(table, 0, 2, 7, 3);

        Label orderByLabel = new Label("Order By:");
        grid.add(orderByLabel, 2,5);
        final ComboBox orderBy = new ComboBox();
        orderBy.getItems().addAll("Username", "Email");
        grid.add(orderBy, 3,5);

        Button reorderButton = new Button("Re-Order");
        HBox reorderBox = new HBox(10);
        //reorderBox.setAlignment(Pos.CENTER);
        reorderBox.getChildren().add(reorderButton);
        grid.add(reorderBox, 0, 5);

        final ComboBox orderType = new ComboBox();
        orderType.getItems().addAll("ASC", "DESC");
        grid.add(orderType, 4,5);

        Button deleteStaff = new Button("Delete Staff Member");
        HBox deleteBox = new HBox(10);
        deleteBox.getChildren().add(deleteStaff);
        grid.add(deleteBox, 0, 6);

        Hyperlink previousLink = new Hyperlink();
        previousLink.setText("Home");
        grid.add(previousLink, 0, 12);
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

        Scene scene = new Scene(grid, 580, 420);
        primaryStage.setScene(scene);

        primaryStage.show();

        deleteStaff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<User> list = table.getSelectionModel().getSelectedItems();
                if(list.size() == 1) {
                    ViewStaffController controller1 = new ViewStaffController();
                    controller1.removeStaff(list.get(0).username, list.get(0).email);
                    table.getItems().remove(list.get(0));
                }
            }
        });
        reorderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String orderingColumn = null != orderBy.getValue() ? (String) orderBy.getValue() : "Username";
                String orderingType = null != orderType.getValue() ? (String) orderType.getValue() : "ASC";

                ViewStaffController controller1 = new ViewStaffController();

                ObservableList<User> staff = FXCollections.observableArrayList();
                ResultSet newResults = controller1.getAllStaff(orderingColumn, orderingType);

                try{
                    while(newResults.next()){
                        String username = newResults.getString(1);
                        String email = newResults.getString(2);
                        String password = newResults.getString(3);
                        String type = newResults.getString(4);
                        User.Type realType = User.stringToType(type);
                        staff.addAll(new User(username, email, password, realType));
                    }
                }
                catch(SQLException e){
                    System.out.println(e.getErrorCode());
                }
                table.setItems(staff);
            }
        });
    }

    public Pane getRootPane() { return rootPane; }
}
