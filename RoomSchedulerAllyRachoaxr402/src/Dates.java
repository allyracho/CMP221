
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allyracho
 */
public class Dates {

    private static Connection connection;
    private static ArrayList<String> dates = new ArrayList<String>();
    private static PreparedStatement addDates;
    private static PreparedStatement getDatesList;
    private static ResultSet resultSet;
    
    public static void addDates(Date date)
    {
        connection = DBConnection.getConnection();
        try
        {
            addDates = connection.prepareStatement("insert into dates (date) values (?)");
            addDates.setDate(1, date);
            addDates.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllDates()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> datesList = new ArrayList<String>();
        try
        {
            getDatesList = connection.prepareStatement("SELECT date FROM dates ORDER BY date");
            resultSet = getDatesList.executeQuery();
            
            while(resultSet.next())
            {
                datesList.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return datesList;
        
    }
    
    
}
