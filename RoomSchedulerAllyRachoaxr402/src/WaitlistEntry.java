/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Timestamp;
import java.sql.Date;

/**
 *
 * @author allyracho
 */
public class WaitlistEntry {

    private String faculty;
    private Date date;
    private int seats;
    private Timestamp timestamp;

    public WaitlistEntry(String faculty, Date date, int seats, Timestamp timestamp) {
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getSeats() {
        return seats;
    }

    public Date getDate() {
        return date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

}
