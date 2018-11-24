package Controllers;


import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddAnimalController {
    public void add(String name, String exhibit, String type, String species, int age)
    {
        Connection conn = DatabaseConnector.establishConnection();
        try{
            Statement statement = conn.createStatement();
            String sql = "Insert INTO Animal (Name, Species, Type, Age, LivesIn) VALUES(\""
                    +name +"\",\"" + species + "\",\"" + type + "\"," + age + ",\"" + exhibit + "\")";
            statement.execute(sql);
        }
        catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
    }
}
