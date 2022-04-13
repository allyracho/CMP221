
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
 * @author allyracho
 */
public class RoomQueries {

    private static Connection connection;
    private static PreparedStatement addRoom;
    private static PreparedStatement dropRoom;
    private static PreparedStatement getAllPossibleRooms;
    private static PreparedStatement getAllRooms;
    private static ResultSet resultSet;

    public static void addRoom(String name, int seats) {
        connection = DBConnection.getConnection();
        try {
            addRoom = connection.prepareStatement("insert into rooms (name, seats) values (?,?)");
            addRoom.setString(1, name);
            addRoom.setInt(2, seats);
            addRoom.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static ArrayList<String> getAllPossibleRooms(int seats) {
        connection = DBConnection.getConnection();
        ArrayList<String> roomsList = new ArrayList<String>();

        try {
            getAllPossibleRooms = connection.prepareStatement("select name,seats from rooms where seats >= ? order by seats");
            getAllPossibleRooms.setInt(1, seats);
            resultSet = getAllPossibleRooms.executeQuery();

            while (resultSet.next()) {
                roomsList.add(resultSet.getString(1));

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return roomsList;

    }
    
    public static ArrayList<String> getAllRooms() {
        connection = DBConnection.getConnection();
        ArrayList<String> roomsList = new ArrayList<String>();

        try {
            getAllRooms = connection.prepareStatement("select * from rooms");
            resultSet = getAllRooms.executeQuery();

            while (resultSet.next()) {
                roomsList.add(resultSet.getString(1));

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return roomsList;

    }

    public static void dropRoom(String name) {

        connection = DBConnection.getConnection();
        try {
            dropRoom = connection.prepareStatement("DELETE FROM Rooms WHERE name = ?");
            dropRoom.setString(1, name);
            dropRoom.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

}
