package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StaffHostedShows {
    private TableView table;
    public final BorderPane rootPane;

    public StaffHostedShows(){

        rootPane = new BorderPane();

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
        TableColumn timeCol = new TableColumn("Time");
        TableColumn exhibitCol = new TableColumn("Exhibit");


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
