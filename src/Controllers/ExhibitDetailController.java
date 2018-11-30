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

    public List<Animal> getAnimals(String exhibitName, String orderColumn, String orderType) {
        Animal animal = new Animal("", "", "", 0, "");
        List<Animal> animalList = new ArrayList<>();
        Connection conn = DatabaseConnector.establishConnection();

        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM Animal WHERE LivesIn=\"" + exhibitName + "\"" + " ORDER BY " + orderColumn + " "
                    + orderType;
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

    public Animal getAnimal(String name) {
        Connection conn = DatabaseConnector.establishConnection();
        ResultSet set;
        Animal animal = new Animal("","","",0,"");
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM Animal WHERE Name=\"" + name + "\"";
            statement.execute(sql);
            set = statement.getResultSet();
            while(set.next()) {
                String animalName = set.getString(1);
                String species = set.getString(2);
                String type = set.getString(3);
                int age = set.getInt(4);
                String livesIn = set.getString(5);
                animal = new Animal(animalName, species, type, age, livesIn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }
}
