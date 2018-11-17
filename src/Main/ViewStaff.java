package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ViewStaff {
    private TableView table;
    public final BorderPane rootPane;

    public ViewStaff() {
        rootPane = new BorderPane();

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
        TableColumn emailCol = new TableColumn("Email");

        table.getColumns().setAll(usernameCol, emailCol);
        table.setPrefWidth(400);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(table, 0, 2);

        Button deleteStaff = new Button("Delete Staff Member");
        HBox deleteBox = new HBox(10);
        deleteBox.getChildren().add(deleteStaff);
        grid.add(deleteBox, 0, 4);

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public Pane getRootPane() { return rootPane; }
}
