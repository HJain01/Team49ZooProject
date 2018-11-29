package Main;

import Controllers.AnimalCareController;
import Controllers.SessionData;
import DataModel.*;
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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class AnimalCare {

    private TableView table;
    public final BorderPane rootPane;

    public AnimalCare(String name, String species, String type, int age, String exhibit) {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Atlanta Zoo Animal Detail");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 8, 1);

        Label animalName = new Label("Name: " + name);
        Group rootName = new Group();
        rootName.getChildren().addAll(animalName);
        grid.add(rootName, 0, 2);

        Label animalSpecies = new Label("Species: " + species);
        Group rootSpecies = new Group();
        rootSpecies.getChildren().addAll(animalSpecies);
        grid.add(rootSpecies, 2, 2);

        Label animalAge = new Label("Age: " + age );
        Group rootNum = new Group();
        rootNum.getChildren().addAll(animalAge);
        grid.add(rootNum, 4, 2);

        Label exhibitName = new Label("Exhibit: " + exhibit);
        Group rootExhibit = new Group();
        rootExhibit.getChildren().addAll(exhibitName);
        grid.add(rootExhibit, 0, 3);

        Label animalType = new Label("Type: " + type);
        Group rootType = new Group();
        rootType.getChildren().addAll(animalType);
        grid.add(rootType, 2, 3);

        TextArea careLog = new TextArea();
        VBox vbox = new VBox(careLog);
        careLog.setWrapText(true);
        careLog.setPrefHeight(150);
        careLog.setPrefWidth(200);

        grid.add(careLog, 0, 4, 4, 5);

        Button logButton = new Button("Log Notes");

        HBox logBox = new HBox(10);
        logBox.getChildren().add(logButton);
        grid.add(logBox, 5, 4);

        //run query
        ObservableList<Note> notes = FXCollections.observableArrayList();
        AnimalCareController controller = new AnimalCareController();
        ResultSet set = controller.getAnimalCareNotes(name, species);

        //resultset -> observable list
        try
        {
            while(set.next())
            {
                String staffUsername = set.getString(1);
                String Name = set.getString(2);
                String Species = set.getString(3);
                String logTime = set.getString(4);
                String content = set.getString(5);
                notes.addAll(new Note(staffUsername, Name, Species, logTime, content));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        table = new TableView<>();
        TableColumn staffCol = new TableColumn("Staff Member");
        staffCol.setCellValueFactory(new PropertyValueFactory<Note, String>("staffUsername"));
        TableColumn noteCol = new TableColumn("Note");
        noteCol.setCellValueFactory(new PropertyValueFactory<Note, String>("text"));
        TableColumn timeCol = new TableColumn("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<Note, String>("logTime"));

        table.setItems(notes);

        table.getColumns().setAll(staffCol, noteCol, timeCol);
        table.setPrefWidth(400);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(table, 0, 10, 7, 10);

        Scene scene = new Scene(grid, 500, 400);
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

        logButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String noteContent = careLog.getText();
                AnimalCareController animalCareController = new AnimalCareController();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime now = LocalDateTime.now();
                String nowStr = now.format(dateFormat);
                Note note = new Note(SessionData.user.username, name, species, nowStr, noteContent);
                controller.AddNote(note);
                notes.add(note);
            }
        });
    }

    public Pane getRootPane() {
        return rootPane;
    }

}
