
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acv
 */
public class Faculty
{
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addFaculty;
    private static PreparedStatement getAllFaculty;
    private static ResultSet resultSet;
    
    public static void addFaculty(String name)
    {
        connection = DBConnection.getConnection();
        try
        {
            addFaculty = connection.prepareStatement("insert into faculty (name) values (?)");
            addFaculty.setString(1, name);
            addFaculty.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllFaculty()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> facultyList = new ArrayList<String>();
        try
        {
            getAllFaculty = connection.prepareStatement("select name from faculty order by name");
            resultSet = getAllFaculty.executeQuery();
            
            while(resultSet.next())
            {
                facultyList.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return facultyList;
        
    }
    
    
}
