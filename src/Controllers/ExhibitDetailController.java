package Controllers;

import DataModel.Animal;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExhibitDetailController {

    public List<Animal> getAnimals(String exhibitName) {
        Animal animal = new Animal("", "", "", 0, "");
        List<Animal> animalList = new ArrayList<>();
        Connection conn = DatabaseConnector.establishConnection();

        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM Animal WHERE LivesIn=\"" + exhibitName + "\"";
            statement.execute(sql);
            ResultSet set = statement.getResultSet();
            while(set.next()) {
                animal.name = set.getString(1);
                animal.species = set.getString(2);
                animal.type = set.getString(3);
                animal.age = set.getInt(4);
                animal.livesIn = set.getString(5);
                animalList.add(animal);
                animal = new Animal("", "", "", 0, "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalList;
    }
}
