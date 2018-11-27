package Main;

import Controllers.AddAnimalController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


public class AddAnimal {

    private TableView table;
    public final BorderPane rootPane;


    public AddAnimal() {

        rootPane = new BorderPane();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Atlanta Zoo");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add Animals");
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

        Label type = new Label("Type:");
        grid.add(type, 0, 4);
        final ComboBox animalType = new ComboBox();
        animalType.getItems().addAll("Reptile", "Invertebrate", "Fish", "Amphibian", "Mammal", "Bird");
        grid.add(animalType, 1, 4);

        Label species = new Label("Species:");
        grid.add(species, 0, 5);
        TextField speciesField = new TextField();
        grid.add(speciesField, 1, 5);

        Label age = new Label("Age:");
        grid.add(age, 0, 6);
        final ComboBox ageNum = new ComboBox();
        ageNum.getItems().addAll(ageGenerator());
        grid.add(ageNum, 1, 6);

        Button addButton = new Button("Add Animal");
        HBox addBox = new HBox(10);
        addBox.getChildren().add(addButton);
        grid.add(addBox, 4, 4);

        Group root = new Group();
        root.getChildren().addAll(grid);

        Hyperlink previousLink = new Hyperlink();
        previousLink.setText("Previous Page");
        grid.add(previousLink, 0, 10);
        previousLink.setOnAction(e -> {
            AdminFunctionality adminSignIn = new AdminFunctionality();
            primaryStage.getScene().setRoot(adminSignIn.getRootPane());
            primaryStage.hide();
        });

        Scene scene = new Scene(root,400, 320);
        primaryStage.setScene(scene);

        primaryStage.show();

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameTextField.getText();
                String exhibit = exhibitType.getValue().toString();
                String type = animalType.getValue().toString();
                String species = speciesField.getText();
                int age = Integer.valueOf(ageNum.getValue().toString()).intValue();

                AddAnimalController controller = new AddAnimalController();
                controller.add(name, exhibit, type, species, age);

                nameTextField.clear();
                speciesField.clear();
            }
        });
    }

    private Integer[] ageGenerator() {
        int size = 51;
        Integer[] result = new Integer[size];

        for (int i = 0; i < result.length; i++) {
            result[i] = i;

        }
        return result;
    }

    public Pane getRootPane() {
        return rootPane;
    }

}
