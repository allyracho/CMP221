/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allyracho
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;

public class ReservationQueries {

    private static Connection connection;
    private static PreparedStatement addReservationEntry;
    private static PreparedStatement deleteReservationEntry;
    private static PreparedStatement getReservationsByDate;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement getRoomsReservedByDate;
    private static PreparedStatement getReservationsByRoom;
    private static ResultSet resultSet;

    public static ArrayList<ReservationEntry> getReservationsByDate(Date date) {

        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservationsByDate = new ArrayList<ReservationEntry>();

        try {
            getReservationsByDate = connection.prepareStatement("SELECT * FROM reservations WHERE date = (?)");
            getReservationsByDate.setDate(1, date);
            resultSet = getReservationsByDate.executeQuery();

            while (resultSet.next()) {
                ReservationEntry entry = new ReservationEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reservationsByDate.add(entry);

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return reservationsByDate;
    }
    
    public static ArrayList<ReservationEntry> getReservationsByRoom(String room) {

        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservationsByRoom = new ArrayList<ReservationEntry>();

        try {
            getReservationsByRoom = connection.prepareStatement("SELECT * FROM reservations WHERE room = (?)");
            getReservationsByRoom.setString(1, room);
            resultSet = getReservationsByRoom.executeQuery();

            while (resultSet.next()) {
                ReservationEntry entry = new ReservationEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reservationsByRoom.add(entry);

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return reservationsByRoom;
    }

    public static ArrayList<String> getRoomsReservedByDate(Date date) {
        connection = DBConnection.getConnection();
        ArrayList<String> roomsReservedByDate = new ArrayList<String>();

        try {
            getRoomsReservedByDate = connection.prepareStatement("SELECT room FROM reservations WHERE date = (?) ORDER BY room");
            getRoomsReservedByDate.setDate(1, date);
            resultSet = getRoomsReservedByDate.executeQuery();

            while (resultSet.next()) {
                roomsReservedByDate.add(resultSet.getString(1));
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return roomsReservedByDate;
    }

    public static void addReservationEntry(String faculty, String room, Date date, int seats, Timestamp timestamp) {

        connection = DBConnection.getConnection();

        try {
            addReservationEntry = connection.prepareStatement("INSERT into RESERVATIONS (faculty, room, date, seats, timestamp)" + "VALUES(?,?,?,?,?)");

            addReservationEntry.setString(1, faculty);
            addReservationEntry.setString(2, room);
            addReservationEntry.setDate(3, date);
            addReservationEntry.setInt(4, seats);
            addReservationEntry.setTimestamp(5, timestamp);

            addReservationEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<ReservationEntry> getReservationsByFaculty(String Faculty) {

        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservationsByFaculty = new ArrayList<ReservationEntry>();

        try {
            getReservationsByFaculty = connection.prepareStatement("SELECT * FROM reservations WHERE Faculty = (?) ORDER BY Date");
            getReservationsByFaculty.setString(1, Faculty);
            resultSet = getReservationsByFaculty.executeQuery();

            while (resultSet.next()) {
                ReservationEntry entry = new ReservationEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reservationsByFaculty.add(entry);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return reservationsByFaculty;

    }

    public static void deleteReservation(String faculty, Date date) {

        connection = DBConnection.getConnection();

        try {
            deleteReservationEntry = connection.prepareStatement("DELETE FROM reservations WHERE faculty = ? and date = ?");
            deleteReservationEntry.setString(1, faculty);
            deleteReservationEntry.setDate(2, date);
            deleteReservationEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
    
    public static void deleteReservationByRoom(String room) {

        connection = DBConnection.getConnection();

        try {
            deleteReservationEntry = connection.prepareStatement("DELETE FROM reservations WHERE room = ?");
            deleteReservationEntry.setString(1, room);
            deleteReservationEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
