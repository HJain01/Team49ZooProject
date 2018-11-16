package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AddShows {

    private TableView table;
    public final BorderPane rootPane;


    public AddShows() {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add Shows");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);


        Label name = new Label("Name:");
        grid.add(name, 0, 2);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 2);

        Label exhibit = new Label("Exhibit:");
        grid.add(exhibit, 0, 3);
        final ComboBox exhibitType = new ComboBox();
        exhibitType.getItems().addAll("Pacific", "Jungle", "Sahara", "Mountainous", "Birds");
        grid.add(exhibitType, 1, 3);

        Label staff = new Label("Staff:");
        grid.add(staff, 0, 4);
        final ComboBox staffList = new ComboBox();
        staffList.getItems().addAll("Martha", "Benjamin", "Ethan");
        grid.add(staffList, 1, 4);

        Label date = new Label("Date:");
        grid.add(date, 0, 5);
        DatePicker datePicker = new DatePicker();
        grid.add(datePicker, 1, 5);

        Label timeLabel = new Label("Time:");
        grid.add(timeLabel, 0, 6);
        TextField timeTextField = new TextField();
        grid.add(timeTextField, 1, 6);

        Button addButton = new Button("Add Show");
        HBox addBox = new HBox(10);
        addBox.getChildren().add(addButton);
        grid.add(addBox, 4, 4);

        Group root = new Group();
        root.getChildren().addAll(grid);

        Scene scene = new Scene(root,400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public Pane getRootPane() {
        return rootPane;
    }

}
