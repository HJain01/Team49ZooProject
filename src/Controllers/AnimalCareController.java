package Controllers;

import DataModel.Note;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnimalCareController {
    public ResultSet getAnimalCareNotes(String animalName, String species, String orderColumn, String orderType)
    {
        ResultSet returnSet = null;
        Connection conn = DatabaseConnector.establishConnection();
        try
        {
            Statement statement = conn.createStatement();
            String sql = "Select * from Note WHERE AnimalName = \"" + animalName + "\" AND NoteSpecies = \"" + species +"\""
                    + " ORDER BY " + orderColumn + " " + orderType;
            statement.execute(sql);
            returnSet = statement.getResultSet();
        }catch(SQLException e)
        {
            System.out.println(e.getErrorCode());
        }
        return returnSet;
    }
    public void AddNote(Note note)
    {
        Connection conn = DatabaseConnector.establishConnection();
        try
        {
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO Note (StaffUsername, AnimalName, NoteSpecies, LogTime, Text) " +
                    "VALUES (\""+note.staffUsername +"\",\""+note.animalName+"\",\"" + note.species + "\",'"
                    + note.logTime + "',\"" + note.text + "\")";
            statement.execute(sql);
        } catch(SQLException e)
        {
            System.out.println(e.getErrorCode());
        }
    }
}
