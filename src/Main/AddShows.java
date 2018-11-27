package Main;

import Controllers.AddShowsController;
import Controllers.ViewStaffController;
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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


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

        ViewStaffController otherContoller = new ViewStaffController();
        ObservableList<User> data = FXCollections.observableArrayList();
        ResultSet set = otherContoller.getAllStaff();
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
        ObservableList<String> list= FXCollections.observableArrayList();

        Label staff = new Label("Staff:");
        grid.add(staff, 0, 4);
        final ComboBox staffList = new ComboBox();
        //staffList.getItems().addAll("Martha", "Benjamin", "Ethan");

        data.forEach((user) -> {staffList.getItems().add(user.username);});

        //staffList.getItems().add();
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
                String date = datePicker.getValue().toString();
                String time = timeTextField.getText();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String test = "invalid";
                String dateTime = "";
                try{
                    test = simpleDateFormat.parse(date + " " + time).toString();
                    dateTime = date + " " + time;

                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
                AddShowsController controller = new AddShowsController();
                String othertest = staffList.getValue().toString();
                ResultSet testing = controller.checkForShowsAtTheSameTime(staffList.getValue().toString(), dateTime);
                int count = -1;
                try{
                    while(testing.next()){
                        count = Integer.parseInt(testing.getString(1));
                    }
                } catch(SQLException e){
                    System.out.println(e.getErrorCode());
                }
                if(count <= 0)
                {
                    //add the damn show
                    controller.addAShow(nameTextField.getText(), exhibitType.getValue().toString(), othertest, dateTime);
                    nameTextField.clear();
                }
            }
        });

    }

    public Pane getRootPane() {
        return rootPane;
    }

}
