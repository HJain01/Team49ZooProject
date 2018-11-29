package Main;

import Controllers.SessionData;
import DataModel.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AnimalDetail {

    private TableView table;
    public final BorderPane rootPane;

    public AnimalDetail(String name, String species, String type, int age, String livesIn) {

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

        Label animalAge = new Label("Age: " + age);
        Group rootNum = new Group();
        rootNum.getChildren().addAll(animalAge);
        grid.add(rootNum, 4, 2);

        Label exhibitName = new Label("Exhibit: " + livesIn);
        Group rootExhibit = new Group();
        rootExhibit.getChildren().addAll(exhibitName);
        grid.add(rootExhibit, 0, 3);

        Label animalType = new Label("Type: " + type);
        Group rootType = new Group();
        rootType.getChildren().addAll(animalType);
        grid.add(rootType, 2, 3);

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

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);


        primaryStage.show();

    }

    public Pane getRootPane() {
        return rootPane;
    }

}
