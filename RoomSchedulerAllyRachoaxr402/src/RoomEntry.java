/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allyracho
 */
public class RoomEntry {

    private String roomName;
    private int seats;

    public RoomEntry(String roomName, int seats) {
        this.roomName = roomName;
        this.seats = seats;
    }

    public String getName() {
        return roomName;
    }

    public int getSeats() {
        return seats;
    }
}
