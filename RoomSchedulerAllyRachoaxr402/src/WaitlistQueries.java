
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Calendar;
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
public class WaitlistQueries {

    private static Connection connection;
    private static PreparedStatement addWaitlistEntry;
    private static PreparedStatement deleteWaitlistEntry;
    private static PreparedStatement getWaitlist;
    private static PreparedStatement getWaitlistByFaculty;
    private static ResultSet resultSet;

    public static void addWaitlistEntry(String faculty, Date date, int seats, Timestamp timestamp) {
        connection = DBConnection.getConnection();

        try {
            addWaitlistEntry = connection.prepareStatement("INSERT into WAITLIST (faculty, date, seats, timestamp)" + "VALUES (?,?,?,?)");

            addWaitlistEntry.setString(1, faculty);
            addWaitlistEntry.setDate(2, date);
            addWaitlistEntry.setInt(3, seats);
            addWaitlistEntry.setTimestamp(4, timestamp);
            addWaitlistEntry.executeUpdate();
        
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static ArrayList<WaitlistEntry> getWaitlist() {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();

        try {
            getWaitlist = connection.prepareStatement("SELECT * FROM waitlist order by timestamp");
            resultSet = getWaitlist.executeQuery();

            while (resultSet.next()) {
                WaitlistEntry entry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlist.add(entry);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return waitlist;

    }

    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty) {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlistByFaculty = new ArrayList<WaitlistEntry>();

        try {
            getWaitlistByFaculty = connection.prepareStatement("SELECT * FROM waitlist WHERE faculty = ?");
            getWaitlistByFaculty.setString(1, faculty);
            resultSet = getWaitlistByFaculty.executeQuery();

            while (resultSet.next()) {
                WaitlistEntry entry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlistByFaculty.add(entry);
            }
        
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return waitlistByFaculty;

    }

    public static void deleteWaitlistEntry(String faculty, Date date) {

        connection = DBConnection.getConnection();
        try {
            deleteWaitlistEntry = connection.prepareStatement("DELETE FROM waitlist WHERE faculty = ? and date = ?");
            deleteWaitlistEntry.setString(1, faculty);
            deleteWaitlistEntry.setDate(2, date);
            deleteWaitlistEntry.executeUpdate();
        
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

}
