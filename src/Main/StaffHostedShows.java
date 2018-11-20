package Main;

import Controllers.SessionData;
import Controllers.StaffHostedShowsController;
import DataModel.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
        ResultSet result = controller.getShowsForStaffMember(user.username);

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


        table.setItems(data);
        table.getColumns().setAll(nameCol, timeCol, exhibitCol);
        table.setPrefWidth(400);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(table, 0, 2);

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public Pane getRootPane() {
        return rootPane;
    }
}
